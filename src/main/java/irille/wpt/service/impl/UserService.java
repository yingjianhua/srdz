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
import irille.pub.idu.Idu;
import irille.wx.wpt.WptCashJournal;
import irille.wx.wpt.WptCommissionJournal;
import irille.wx.wpt.WptQrcodeRule;
import irille.wx.wpt.WptRedPackRule;
import irille.wx.wx.WxUser;
import irille.wxpub.util.mch.SendRedPack;
@Service
public class UserService {
	public static final Log LOG = new Log(UserService.class);
	@Resource
	private CashJournalService cashJournalService;
	@Resource
	private QrcodeRuleService qrcodeRuleService;
	
	public int getFans1Num(Integer userid){
		String sql = Idu.sqlString("select count(*) fansNum from {0} where {1}=?", WxUser.class, WxUser.T.INVITED2);
		Map<String, Object> resultMap = Bean.executeQueryMap(sql, userid)[0];
		long fansNum = (long)resultMap.get("fansNum");
		return (int)fansNum;
	}
	public int getFans2Num(Integer userid){
		String sql = Idu.sqlString("select count(*) fansNum from {0} where {1}=?", WxUser.class, WxUser.T.INVITED1);
		Map<String, Object> resultMap = Bean.executeQueryMap(sql, userid)[0];
		long fansNum = (long)resultMap.get("fansNum");
		return (int)fansNum;
	}
	public int getFansNum(Integer userid) {
		String sql = Idu.sqlString("select count(*) fansNum from {0} where {1}=? or {2}=?", WxUser.class, WxUser.T.INVITED1, WxUser.T.INVITED2);
		Map<String, Object> resultMap = Bean.executeQueryMap(sql, userid, userid)[0];
		long fansNum = (long)resultMap.get("fansNum");
		return (int)fansNum;
	}
	public List<WxUser> getFans(Integer userid) {
		List<WxUser> fanses = Bean.list(WxUser.class, Idu.sqlString("{0}=? or {1}=?", WxUser.T.INVITED1, WxUser.T.INVITED2), false, userid, userid);
		return fanses;
	}
	public List<WxUser> getFansByCondition(String openid, Integer accountPkey, int level, int fanid) {
		WxUser user = WxUser.loadUniqueOpenIdAccount(false, openid, accountPkey);
		WxUser.T t = WxUser.T.PKEY;
		if(fanid != 0) {
			return Bean.list(WxUser.class, t+"=? and ("+t.INVITED1+"=? or "+t.INVITED2+"=?)", false, fanid, user.getPkey(), user.getPkey(), user.getPkey());
		}
		if(level == 1) {
			return Bean.list(WxUser.class, WxUser.T.INVITED2+"=?", false, user.getPkey());
		} else if(level == 2){
			return Bean.list(WxUser.class, WxUser.T.INVITED1+"=?", false, user.getPkey());
		} else {
			return null;
		}
	}
	/**
	 * 获取粉丝销量统计 包括已付款和已完成的
	 */
	public BigDecimal getFansSaleAmount(Integer userid) {
		String sql = Idu.sqlString("select sum({0}) amount from {1} where {2}=?", 
				WptCommissionJournal.T.PRICE, WptCommissionJournal.class, WptCommissionJournal.T.WXUSER);
		Map<String, Object> amountMap = Bean.executeQueryMap(sql, userid)[0];
		BigDecimal amount = (BigDecimal)amountMap.get("amount");
		return amount==null?BigDecimal.ZERO:amount;
	}
	/**
	 * 根据订单ID或者粉丝ID 搜索佣金流水
	 * @param orderOrFan
	 * @return
	 */
	public List<WptCommissionJournal> getCommissionJournal(String openid, Integer accountPkey, String orderOrFan) {
		WxUser user = WxUser.loadUniqueOpenIdAccount(false, openid, accountPkey);
		String where = "";
		if(orderOrFan == null || orderOrFan.equals("")) {
			where = Idu.sqlString("{0}=?", WptCommissionJournal.T.WXUSER);
			return Bean.list(WptCommissionJournal.class, where, false, user.getPkey());
		} else {
			where = Idu.sqlString("{0}=? and ({1}=? or {2}=?)", WptCommissionJournal.T.WXUSER, WptCommissionJournal.T.FANS, WptCommissionJournal.T.ORDERID);
			return Bean.list(WptCommissionJournal.class, where, false, user.getPkey(), orderOrFan, orderOrFan);
		}
	}
	/**
	 * 提现
	 * @throws Exception 
	 */
	public WptCashJournal cash(BigDecimal amt, WxUser user, String client_ip) throws Exception {
		WptRedPackRule rule = Bean.get(WptRedPackRule.class, user.getAccount());
		if(amt.compareTo(rule.getLeastAmt()) < 0) {
			throw LOG.err("less than least", "提现金额少于最少提现金额");
		} else if(amt.compareTo(user.getCashableCommission()) > 0) {
			throw LOG.err("more than cashable", "提现金额多于用户可提现佣金");
		}
		SendRedPack.sendRedPack(user.gtAccount(), user.getOpenId(), rule.getSendName(), amt.multiply(BigDecimal.valueOf(100)).intValue(), rule.getWishing(), client_ip, rule.getActName(), rule.getRemark());
		return cashJournalService.add(user, amt);
	}
	/**
	 * 检查推广二维码是否达到需要更新的时间，是则更新
	 * @param user
	 */
	public void checkQrcode(WxUser user) {
		WptQrcodeRule rule = Bean.get(WptQrcodeRule.class, user.getAccount());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, rule.getAheadUpdate());
		if(user.getQrcodeExpireTime() == null || calendar.getTime().after(user.getQrcodeExpireTime())) {
			qrcodeRuleService.createQrcode(user, rule);
		}
	}
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -15);
		System.out.println(calendar.getTime());
		System.out.println(calendar.getTime().before(new Date()));
	}
}