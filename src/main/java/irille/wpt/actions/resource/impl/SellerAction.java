package irille.wpt.actions.resource.impl;

import java.io.IOException;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Log;
import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.service.impl.OrderService;
import irille.wpt.service.impl.SellerService;
@Controller
@Scope("prototype")
public class SellerAction extends AbstractCRUDAction {
	private static final long serialVersionUID = 1L;

	public static final Log LOG = new Log(SellerAction.class);
	
	private String manager;
	private String identify;
	private String orderid;
	@Resource
	private SellerService sellerService;
	@Resource
	private OrderService orderService;
	
	/**
	 * 短信校验登录
	 */
	@PermitAll
	public void sendCheckCode(){
		sellerService.sendCheckCode(manager, getSession(), getAccount().getPkey());
	}
	
	/**
	 * 核对校验码
	 */
	@PermitAll
	public void checkCode() {
		if(sellerService.checkCode(identify, getSession())) {
			try {
				ServletActionContext.getResponse().getWriter().print("ok");//成功返回一个字符串
			} catch (IOException e) {
				e.printStackTrace();
			}
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
