package irille.wpt.actions.resource.impl;

import javax.annotation.security.PermitAll;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Log;
import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.RestaurantBsn;
@Controller
@Scope("prototype")
public class RestaurantBsnAction extends AbstractCRUDAction<RestaurantBsn> {
	private static final long serialVersionUID = 1L;
	public static final Log LOG = new Log(RestaurantBsnAction.class);
	
	private String manager;
	private String identify;
	private String orderid;
	
	@Override
	public String ins() {
		restaurantBsnService.save(bean, account.getPkey());
		return BEAN;
	}
	
	/**
	 * 短信校验登录
	 */
	@PermitAll
	public void sendCheckCode(){
		restaurantBsnService.sendCheckCode(manager, getSession(), getAccount().getPkey());
	}
	
	/**
	 * 核对校验码
	 */
	@PermitAll
	public String checkCode() {
		if(restaurantBsnService.checkCode(identify, getSession())) {
			object = "ok";//成功返回一个字符串
			return OBJECT;
		} else {
			return null;
		}
	}

	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
}
