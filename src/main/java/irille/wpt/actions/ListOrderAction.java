package irille.wpt.actions;

import java.util.List;

import irille.wpt.service.OrderService;
import irille.wx.wpt.WptOrder;

public class ListOrderAction extends AbstractWptAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8118977310825408135L;
	private List<WptOrder> orders;
	
	private OrderService service;
	/**
	 * 我的私人订单页面
	 */
	@Override
	public String execute() throws Exception {
		orders = service.list(chkWxUser().getPkey());
		setResult("me/orderList.jsp");
		return TRENDS;
	}
	
	public List<WptOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<WptOrder> orders) {
		this.orders = orders;
	}
	public OrderService getService() {
		return service;
	}
	public void setService(OrderService service) {
		this.service = service;
	}
}
