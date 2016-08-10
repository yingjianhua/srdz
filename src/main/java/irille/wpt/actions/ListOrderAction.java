package irille.wpt.actions;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import irille.wpt.service.OrderService;
import irille.wx.wpt.WptOrder;
@Controller
public class ListOrderAction extends AbstractWptAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8118977310825408135L;
	private List<WptOrder> orders;
	@Resource
	private OrderService orderService;
	/**
	 * 我的私人订单页面
	 */
	@Override
	public String execute() throws Exception {
		orders = orderService.list(chkWxUser().getPkey());
		setResult("me/orderList.jsp");
		return TRENDS;
	}
	
	public List<WptOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<WptOrder> orders) {
		this.orders = orders;
	}
}
