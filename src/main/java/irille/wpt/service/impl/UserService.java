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
