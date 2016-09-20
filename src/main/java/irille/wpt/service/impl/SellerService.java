package irille.wpt.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.Log;
import irille.wpt.actions.controller.impl.LoginSellerAction;
import irille.wpt.bean.Member;
import irille.wpt.bean.Restaurant;
import irille.wpt.bean.RestaurantBsn;
import irille.wpt.dao.impl.OrderDao;
import irille.wpt.dao.impl.RestaurantBsnDao;
import irille.wpt.dao.impl.RestaurantDao;
import irille.wpt.tools.SmsTool;
import irille.wxpub.util.mch.MchUtil;
@Service
public class SellerService {
	
	private static final Log LOG = new Log(SellerService.class);
	
	@Resource
	private RestaurantDao restaurantDao;
	@Resource
	private RestaurantBsnDao restaurantBsnDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private SmsTool smsTool;
	private static final String SMG_CODE = "msgCode";
	
	private static final String CHECK_MSG = "【享食光】验证码: 1234 (享食光手机登录验证，请完成验证)，如非本人操作，请忽略本条短息。";
	
	public void sendCheckCode(String manager, Map<String, Object> session, Integer account){
		List<Restaurant> list = restaurantDao.listByManger(account, manager);
		if(list.isEmpty()) {
			throw LOG.err("null", "null");
		}
		String code = MchUtil.createRandomNum(4);
		LOG.info("code:" + code);
		session.put(SMG_CODE, code);
		session.put("manager", manager);
    	session.put("date", System.currentTimeMillis());
		smsTool.doSent(manager, CHECK_MSG.replace("1234", code));
	}
	public boolean checkCode(String identify, Map<String, Object> session) {
		long i = System.currentTimeMillis()- (Long)session.get("date");
		if(!identify.equals(session.get(SMG_CODE)) || i > 300000){
			return false;
		} else {
			return true;
		}
	}

	public Restaurant sellerLogin(Map<String, Object> session, String identify, Integer account, Member member) {
		Integer restaurantId = (Integer)session.get(LoginSellerAction.RESTAURANT);
		if(restaurantId != null) {
			return restaurantDao.get(restaurantId);
		} else {
			if(!identify.equals((String) session.get(SMG_CODE))){
				throw LOG.err("invalidCheckCode", "错误验证码："+identify);
			}
			String manager;
			if((manager=(String)session.get("manager")) == null){
				throw LOG.err("timeout", "会话超时，请重新登陆");
			}
			List<Restaurant> restaurants = restaurantDao.listByManger(account, manager);
	    	if(restaurants.size() == 0) {
	    		throw LOG.err("restaurantErr", "餐厅异常");
	    	}
			if(member != null && restaurantBsnDao.findByMember(member.getPkey()) == null){
				
				RestaurantBsn restaurantBsn = new RestaurantBsn();
				restaurantBsn.setAccount(member.getAccount());
				restaurantBsn.setCreateTime(new Date());
				restaurantBsn.setOpenid(member.getOpenId());
				restaurantBsn.setRestaurant(restaurants.get(0));
				restaurantBsn.setMember(member);
				restaurantBsnDao.save(restaurantBsn);
			}
	    	return restaurants.get(0);
		}
	}
}
