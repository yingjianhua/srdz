package irille.wpt.actions.controller.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.RestaurantBsn;
@Controller
@Scope("prototype")
public class LoginSellerAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	public static final String RESTAURANT = "restaurant";

	/**
	 * 登录界面
	 */
	@Override
	public String execute() throws Exception {
		RestaurantBsn restaurantBsn = restaurantBsnService.findByMember(chkMember().getPkey());
		if(restaurantBsn != null){
			getSession().put(RESTAURANT, restaurantBsn.getRestaurant().getPkey());
			setResult("listSellerOrder?account.pkey="+getAccount().getPkey());
			return RTRENDS;
		}
		setResult("seller/sellerLogin.jsp");
		return TRENDS;
	}
}
