package irille.wpt.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.pub.Log;
import irille.wpt.tools.SmsTool;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptRestaurantBsn;
import irille.wx.wx.WxUser;
import irille.wxpub.util.MchUtil;

public class SellerService {
	public static final Log LOG = new Log(SellerService.class);
	private SmsTool smsTool;
	
	public void sendCheckCode(String manager, Map<String, Object> session){
		String code = MchUtil.createRandom(4);
		System.out.println("code:" + code);
		session.put("msgCode", code);
		session.put("manager", manager);
    	session.put("date", System.currentTimeMillis());
		String c = "【享食光】验证码: 1234 (享食光手机登录验证，请完成验证)，如非本人操作，请忽略本条短息。";
		c = c.replace("1234", code);
		smsTool.doSent(manager, c);
	}
	public boolean checkCode(String identify, Map<String, Object> session) {
		long i = System.currentTimeMillis()- (long)session.get("date");
		if(!identify.equals(session.get("msgCode")) || i > 300000){
			return false;
		} else {
			return true;
		}
	}
	public WptRestaurant sellerLogin(Map<String, Object> session, String identify, Integer accountId, WxUser user) {
		if((Integer)session.get("restaurant") != null) {
			return WptRestaurant.get(WptRestaurant.class, (Integer) session.get("restaurant"));
		} else {
			if(!identify.equals((String) session.get("identify"))){
				LOG.err("invalidCheckCode", "验证码错误");
			}
			String manager;
			if((manager=(String)session.get("manager")) == null){
				LOG.err("timeout", "会话超时，请重新登陆");
			}
			List<WptRestaurant> list = WptRestaurant.list(WptRestaurant.class, WptRestaurant.T.MANAGER + "=? AND " + WptRestaurant.T.ACCOUNT + "=?", false, manager, accountId);
	    	if(list.size() == 0) {
	    		LOG.err("restaurantErr", "餐厅异常");
	    	}
			if(user != null && WptRestaurantBsn.chkUniqueWxUserAccount(false, user.getPkey(), accountId) == null){
				WptRestaurantBsn restaurantBsn = new WptRestaurantBsn();
				restaurantBsn.setAccount(accountId);
				restaurantBsn.setCreateTime(new Date());
				restaurantBsn.setOpenid(user.getOpenId());
				restaurantBsn.setRestaurant(list.get(0).getPkey());
				restaurantBsn.setWxuser(user.getPkey());
				restaurantBsn.ins();
			}
	    	return list.get(0);
		}
	}
	public List<WptOrder> listOrder(Integer restaurantId, String orderId, OStatus status) {
		StringBuilder where = new StringBuilder();
		where.append(WptOrder.T.RESTAURANT).append("=?");
		if(orderId != null) {
			where.append(" and ").append(WptOrder.T.ORDERID).append(" like '%").append(orderId).append("%'");
		}
		if(status != null) {
			where.append(" and ").append(WptOrder.T.STATUS).append("="+status.getLine().getKey());
		}
		where.append(" and ").append(WptOrder.T.IS_PT).append("=?");
		List<WptOrder> list = WptOrder.list(WptOrder.class, where.toString(), false, restaurantId, WptOrder.booleanToByte(false));
		return list;
	}
	public JSONArray listOrder4Json(Integer restaurantId, String orderId, OStatus status) {
		List<WptOrder> list = listOrder(restaurantId, orderId, status);
		JSONArray result = new JSONArray();
		try {
			for(WptOrder order:list) {
				JSONObject o = new JSONObject();
				o.put("comboName", order.getComboName());
				o.put("orderId", order.getOrderid());
				o.put("price", order.getPrice().intValue());
				result.put(o);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	public SmsTool getSmsTool() {
		return smsTool;
	}
	public void setSmsTool(SmsTool smsTool) {
		this.smsTool = smsTool;
	}
}
