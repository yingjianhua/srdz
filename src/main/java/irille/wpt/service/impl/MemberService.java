package irille.wpt.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.wpt.bean.CashJournal;
import irille.wpt.bean.Member;
import irille.wpt.bean.QrcodeRule;
import irille.wpt.bean.RedPackRule;
import irille.wpt.dao.impl.CashJournalDao;
import irille.wpt.dao.impl.CommissionJournalDao;
import irille.wpt.dao.impl.MemberDao;
import irille.wpt.dao.impl.OrderDao;
import irille.wpt.dao.impl.QrcodeRuleDao;
import irille.wpt.dao.impl.RedPackRuleDao;
import irille.wpt.tools.Page;
import irille.wx.wa.WaQRCodeDAO;
import irille.wx.wx.WxAccount;
import irille.wxpub.util.mch.SendRedPack;

@Service
public class MemberService {
	private static final Log LOG = new Log(MemberService.class);

	@Resource
	private MemberDao memberDao;
	@Resource
	private QrcodeRuleDao qrcodeRuleDao;
	@Resource
	private CommissionJournalDao commissionJournalDao;
	@Resource
	private RedPackRuleDao redPackRuleDao;
	@Resource
	private CashJournalDao cashJournalDao;
	@Resource
	private OrderDao orderDao;
	
	
	public Member findByOpenidInAccount(Integer accountId, String openid) {
		return memberDao.findByOpenidInAccount(accountId, openid);
	}
	
	/**
	 * 成为会员
	 * @param member
	 * @param force
	 */
	public void becomeMember(Member member, Boolean force) {
		if(member.getIsMember() == true) {
			//已经是会员，不做处理
			return;
		}
		boolean flag = false;
		Integer memberId = member.getPkey();
		QrcodeRule rule = qrcodeRuleDao.get(member.getAccount());
		
		if(!force) {
			BigDecimal single = rule.getSingle();
			BigDecimal amount = rule.getAmount();
			if(orderDao.totalConsumptionByMember(memberId).compareTo(amount) >= 0 || orderDao.MaxSingleConsumptionByMember(memberId).compareTo(single) >= 0) {
				flag = true;
			}
		} else {
			flag = true;
		}
		if(flag) {
			member.setIsMember(true);
			createQrcode(member, rule);
		}
	}
	/**
	 * 为一指定会员创建二维码
	 * @param member
	 * @param rule
	 */
	public void createQrcode(Member member, QrcodeRule rule){
		Map<String, Object> map = WaQRCodeDAO.obtain(true, member.getPkey()+"", BigDecimal.valueOf(rule.getValidityPeriod()), Bean.get(WxAccount.class, member.getAccount()), member.getPkey()+".jpg");
		member.setQrcode((String)map.get("imgUrl"));
		Calendar time = Calendar.getInstance();
		time.add(Calendar.SECOND, rule.getValidityPeriod()*24*60*60);
		member.setQrcodeExpireTime(time.getTime());
		memberDao.update(member);
	}
	/**
	 * 为所有没有二维码的会员创建二维码
	 */
	public void createAllQrcode(Integer account) {
		QrcodeRule rule = qrcodeRuleDao.get(account);
		List<Member> members = memberDao.listQrcodeIsNull(account);
		for(int i=1;i<members.size();i++) {
			System.out.println(i);
			createQrcode(members.get(i), rule);
		}
	}
	
	/**
	 * 检查推广二维码是否达到需要更新的时间，是则更新
	 * @param member
	 */
	public void checkQrcode(Member member) {
		QrcodeRule rule = qrcodeRuleDao.get(member.getAccount());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, rule.getAheadUpdate());
		if(member.getQrcodeExpireTime() == null || calendar.getTime().after(member.getQrcodeExpireTime())) {
			createQrcode(member, rule);
		}
	}
	
	/**
	 * 提现
	 */
	public CashJournal cash(BigDecimal amt, Member member, String client_ip) {
		final RedPackRule rule = redPackRuleDao.load(member.getAccount());
		if(amt.compareTo(rule.getLeastAmt()) < 0) {
			throw LOG.err("less than least", "提现金额少于最少提现金额");
		} else if(amt.compareTo(member.getCashableCommission()) > 0) {
			throw LOG.err("more than cashable", "提现金额多于用户可提现佣金");
		}
		WxAccount account = Bean.get(WxAccount.class, member.getAccount());
		SendRedPack.sendRedPack(account, member.getOpenId(), rule.getSendName(), amt.multiply(BigDecimal.valueOf(100)).intValue(), rule.getWishing(), client_ip, rule.getActName(), rule.getRemark());
		
		member.setCashableCommission(member.getCashableCommission().subtract(amt));
		if(member.getCashableCommission().compareTo(BigDecimal.ZERO) < 0) {
			throw LOG.err("not enough", "可提现金额不足");
		}
		memberDao.update(member);
		CashJournal journal = new CashJournal();
		journal.setMember(member);
		journal.setPrice(amt);
		journal.setCreateTime(new Date());
		journal.setAccount(member.getAccount());
		cashJournalDao.save(journal);
		
		return journal;
	}
	/**
	 * 统计下一级粉丝的数量
	 * @param memberId
	 * @return
	 */
	public Long countFans1(Integer memberId){
		return memberDao.countByInvited2(memberId);
	}
	
	/**
	 * 获取粉丝销量统计 包括已付款和已完成的
	 */
	public BigDecimal countFans1Sale(Integer memberId) {
		BigDecimal amount = commissionJournalDao.countFans1Sale(memberId);
		return amount==null?BigDecimal.ZERO:amount; 
	}
	/**
	 * 根据层级关系和粉丝id获取粉丝对象
	 * @param member
	 * @param level 1级表示层级最接近
	 * @param fanId
	 * @return
	 */
	public Member findFanByCondition(Member member, int level, int fanId) {
		if(level == 1) {
			return memberDao.findByInvited2(member.getPkey(), fanId);
		} else if(level == 2){
			return memberDao.findByInvited1(member.getPkey(), fanId);
		}
		return null;
	}
	/**
	 * 根据层级关系和粉丝id获取粉丝对象
	 * @param member
	 * @param level 1级表示层级最接近
	 * @param fanId
	 * @return
	 */
	public Page<Member> pageFansByCondition(Member member, int level, Integer start, Integer limit) {
		if(level == 1) {
			return memberDao.pageByInvited2(start, limit, member.getPkey());
		} else if(level == 2){
			return memberDao.pageByInvited1(start, limit, member.getPkey());
		}
		return null;
	}
	/**
	 * 根据层级关系和粉丝id获取粉丝对象
	 * @param member
	 * @param level 1级表示层级最接近
	 * @param fanId
	 * @return
	 */
	public List<Member> listFansByCondition(Member member, int level) {
		if(level == 1) {
			return memberDao.listByInvited2(member.getPkey());
		} else if(level == 2){
			return memberDao.listByInvited1(member.getPkey());
		}
		return null;
	}

}
