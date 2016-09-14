package irille.wpt.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.wpt.bean.Member;
import irille.wpt.bean.QrcodeRule;
import irille.wpt.dao.impl.CommissionJournalDao;
import irille.wpt.dao.impl.MemberDao;
import irille.wpt.dao.impl.QrcodeRuleDao;
import irille.wpt.tools.Page;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptOrder;

@Service
public class MemberService {

	@Resource
	private MemberDao memberDao;
	@Resource
	private QrcodeRuleDao qrcodeRuleDao;
	@Resource
	private CommissionJournalDao commissionJournalDao;
	
	public Member findByOpenidInAccount(Integer accountId, String openid) {
		return memberDao.findByOpenidInAccount(accountId, openid);
	}
	
	public void becomeMember(Member member, Boolean force) {
		if(member.getIsMember() == true) {
			//已经是会员，不做处理
			return;
		}
		boolean flag = false;
		QrcodeRule rule = qrcodeRuleDao.get(member.getAccount());
		if(!force) {
			BigDecimal single = rule.getSingle();
			BigDecimal amount = rule.getAmount();
			String sql = Idu.sqlString("select max({0}) max, sum({0}) sum from {1} where {2}=? and {3}=?", WptOrder.T.PRICE, WptOrder.class, WptOrder.T.WXUSER, WptOrder.T.STATUS);
			Map<String, Object> resultMap = Bean.executeQueryMap(sql, member.getPkey(), OStatus.FINISH.getLine().getKey())[0];
			//判断是否满足条件
			if(resultMap.get("max") != null && resultMap.get("sum") != null) {
				if(single.compareTo((BigDecimal)resultMap.get("max")) <= 0 || amount.compareTo((BigDecimal)resultMap.get("sum")) <= 0) {
					flag = true;
				}
			}
		} else {
			flag = true;
		}
		if(flag) {
			member.setIsMember(true);
			//createQrcode(user, rule);
		}
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
}
