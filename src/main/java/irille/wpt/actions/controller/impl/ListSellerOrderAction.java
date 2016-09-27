package irille.wpt.actions.controller.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.Order;
import irille.wpt.bean.Restaurant;
@Controller
@Scope("prototype")
public class ListSellerOrderAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	private String manager;
	private String identify;
	private Restaurant restaurant;
	private List<Order> orders;
	
	/**
	 * 商家订单列表
	 */
	@Override
	public String execute() throws Exception {
		restaurant = restaurantBsnService.sellerLogin(getSession(), identify, getAccount().getPkey(), chkMember());
		orders = orderService.listByRestaurant(restaurant.getPkey());
		setResult("seller/sellerOrderList.jsp");
		return TRENDS;
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

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
