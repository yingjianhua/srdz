package irille.wpt.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.wx.wa.WaQRCodeDAO;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.WptQrcodeRule;
import irille.wx.wx.WxUser;
@Service
public class QrcodeRuleService {
	/**
	 * 判断微信用户是否满足产生推广二维码的要求，并进行相应的操作
	 * @param force 是否不管判断条件 强制将用户转换为会员， 并且生成推广二维码
	 * @param user
	 */
	public void create(WxUser user, boolean force){
		if(user.gtIsMember() == true) {
			//是会员，已拥有推广二维码，不做处理
			return;
		}
		boolean flag = false;
		WptQrcodeRule rule = Bean.get(WptQrcodeRule.class, user.getAccount());
		if(!force) {
			BigDecimal single = rule.getSingle();
			BigDecimal amount = rule.getAmount();
			String sql = Idu.sqlString("select max({0}) max, sum({0}) sum from {1} where {2}=? and {3}=?", WptOrder.T.PRICE, WptOrder.class, WptOrder.T.WXUSER, WptOrder.T.STATUS);
			Map<String, Object> resultMap = Bean.executeQueryMap(sql, user.getPkey(), OStatus.FINISH.getLine().getKey())[0];
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
			user.stIsMember(true);
			createQrcode(user, rule);
		}
	}
	public void createQrcode(WxUser user, WptQrcodeRule rule){
		Map<String, Object> map = WaQRCodeDAO.obtain(true, user.getPkey()+"", BigDecimal.valueOf(rule.getValidityPeriod()), user.gtAccount(), user.getPkey()+".jpg");
		user.setQrcode((String)map.get("imgUrl"));
		Calendar time = Calendar.getInstance();
		time.add(Calendar.SECOND, rule.getValidityPeriod()*24*60*60);
		user.setQrcodeExpireTime(time.getTime());
		user.upd();
	}
	public void createAllQrcode() {
		WptQrcodeRule rule = Bean.get(WptQrcodeRule.class, 10);
		List<WxUser> users = Bean.list(WxUser.class, WxUser.T.QRCODE+" is null", false);
		QrcodeRuleService q = new QrcodeRuleService();
		for(int i=1;i<users.size();i++) {
			System.out.println(i);
			q.createQrcode(users.get(i), rule);
		}
	}
}
