package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.Order;
import irille.wpt.service.impl.OrderService;
@Controller
@Scope("prototype")
public class ListOrderAction extends AbstractControllAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8118977310825408135L;
	private List<Order> orders;
	@Resource
	private OrderService orderService;
	/**
	 * 我的私人订单页面
	 */
	@Override
	public String execute() throws Exception {
		orders = orderService.list(chkMember().getPkey());
		setResult("me/orderList.jsp");
		return TRENDS;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
