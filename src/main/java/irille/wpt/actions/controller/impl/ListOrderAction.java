package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.CustomForm;
import irille.wpt.bean.Member;
import irille.wpt.bean.Order;
import irille.wpt.service.impl.CustomFormService;
import irille.wpt.service.impl.OrderService;
@Controller
@Scope("prototype")
public class ListOrderAction extends AbstractControllAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8118977310825408135L;
	private List<Order> orders;
	private List<CustomForm> customForms;
	private long orderNum;
	private long customFormNum;
	@Resource
	private OrderService orderService;
	@Resource
	private CustomFormService customFormService;
	/**
	 * 我的私人订单页面
	 */
	@Override
	public String execute() throws Exception {
		Member member = chkMember();
		orders = orderService.list(member.getPkey());
		customForms = customFormService.list(member.getPkey());
		orderNum = orders.size();
		customFormNum = customForms.size();
		setResult("me/orderList.jsp");
		return TRENDS;
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<CustomForm> getCustomForms() {
		return customForms;
	}

	public void setCustomForms(List<CustomForm> customForms) {
		this.customForms = customForms;
	}

	public long getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}

	public long getCustomFormNum() {
		return customFormNum;
	}

	public void setCustomFormNum(long customFormNum) {
		this.customFormNum = customFormNum;
	}
	
}
