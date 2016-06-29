package irille.wpt.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.wx.wpt.Wpt;
import irille.wx.wpt.WptCommissionJournal;
import irille.wx.wpt.WptOrder;
import irille.wx.wx.WxUser;

public class UserService {

	public int getFans1Num(Integer userid){
		String sql = Idu.sqlString("select count(*) fansNum from {0} where {1}=?", WxUser.class, WxUser.T.INVITED3);
		Map<String, Object> resultMap = Bean.executeQueryMap(sql, userid)[0];
		long fansNum = (long)resultMap.get("fansNum");
		return (int)fansNum;
	}
	public int getFans2Num(Integer userid){
		String sql = Idu.sqlString("select count(*) fansNum from {0} where {1}=?", WxUser.class, WxUser.T.INVITED2);
		Map<String, Object> resultMap = Bean.executeQueryMap(sql, userid)[0];
		long fansNum = (long)resultMap.get("fansNum");
		return (int)fansNum;
	}
	public int getFans3Num(Integer userid){
		String sql = Idu.sqlString("select count(*) fansNum from {0} where {1}=?", WxUser.class, WxUser.T.INVITED1);
		Map<String, Object> resultMap = Bean.executeQueryMap(sql, userid)[0];
		long fansNum = (long)resultMap.get("fansNum");
		return (int)fansNum;
	}
	public int getFansNum(Integer userid) {
		String sql = Idu.sqlString("select count(*) fansNum from {0} where {1}=? or {2}=? or {3}=?", WxUser.class, WxUser.T.INVITED1, WxUser.T.INVITED2, WxUser.T.INVITED3);
		Map<String, Object> resultMap = Bean.executeQueryMap(sql, userid, userid, userid)[0];
		long fansNum = (long)resultMap.get("fansNum");
		return (int)fansNum;
	}
	public List<WxUser> getFans(Integer userid) {
		List<WxUser> fanses = Bean.list(WxUser.class, Idu.sqlString("{0}=? or {1}=? or {2}=?", WxUser.T.INVITED1, WxUser.T.INVITED2, WxUser.T.INVITED3), false, userid, userid, userid);
		return fanses;
	}
	public List<WxUser> getFansByCondition(String openid, Integer accountPkey, int level, int fanid) {
		if(fanid != 0) {
			List<WxUser> list = new ArrayList<WxUser>();
			list.add(Bean.load(WxUser.class, fanid));
			return list;
		}
		WxUser user = WxUser.loadUniqueOpenIdAccount(false, openid, accountPkey);
		if(level == 1) {
			return Bean.list(WxUser.class, WxUser.T.INVITED3+"=?", false, user.getPkey());
		} else if(level == 2){
			return Bean.list(WxUser.class, WxUser.T.INVITED2+"=?", false, user.getPkey());
		} else if(level == 3){
			return Bean.list(WxUser.class, WxUser.T.INVITED1+"=?", false, user.getPkey());
		} else {
			return null;
		}
	}
	/**
	 * 获取粉丝销量统计
	 */
	public BigDecimal getFansSaleAmount(Integer userid) {
		WptOrder.T ot = WptOrder.T.PKEY;
		WxUser.T ut = WxUser.T.PKEY;
		String sql = Idu.sqlString("select sum({0}) amount from {1} where {2} in (select {3} from {4} where {5}=? or {6}=? or {7}=?) and {8}=?",
				ot.PRICE, WptOrder.class, ot.WXUSER, ut.PKEY, WxUser.class, ut.INVITED1, ut.INVITED2, ut.INVITED3, ot.STATUS);
		Map<String, Object> amountMap = Bean.executeQueryMap(sql, userid, userid, userid, Wpt.OStatus.FINISH.getLine().getKey())[0];
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
}
