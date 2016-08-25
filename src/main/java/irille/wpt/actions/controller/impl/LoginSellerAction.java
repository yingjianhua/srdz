package irille.wpt.actions.controller.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wx.wpt.WptRestaurantBsn;
import irille.wx.wx.WxUser;
@Controller
@Scope("prototype")
public class LoginSellerAction extends AbstractControllAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6551598661979904482L;
	public static final String RESTAURANT = "restaurant";

	/**
	 * 登录界面
	 */
	@Override
	public String execute() throws Exception {
		WxUser user = chkWxUser();
		WptRestaurantBsn bsn = WptRestaurantBsn.chkUniqueWxUserAccount(false, user.getPkey(), getAccount().getPkey());
		if(bsn != null){
			getSession().put(RESTAURANT, bsn.getRestaurant());
			setResult("listSellerOrder?account.pkey="+getAccount().getPkey());
			return RTRENDS;
		}
		setResult("seller/sellerLogin.jsp");
		return TRENDS;
	}
}
