package irille.wpt.actions.controller.impl;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.bean.BeanBase;
import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.Order;
import irille.wpt.bean.OrderCustomService;
import irille.wpt.bean.OrderDetail;
import irille.wpt.service.impl.OrderService;
import irille.wx.wpt.WptOrderLine;
import irille.wx.wpt.WptOrderService;
import irille.wxpub.js.JMChooseWXPay;
import irille.wxpub.js.JMOpenLocation;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;
import irille.wxpub.js.JsFunDefine;
@Controller
@Scope("prototype")
public class ShowOrderAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	private String orderid;
	private Order order;
	
	@Resource
	private OrderService orderService;
	
	/**
	 * 完整的订单详情页
	 */
	@Override
	public String execute() throws Exception {
		order = orderService.findByOrderid(orderid);
		if(order.getStatus().equals(0)) {
			setResult("me/orderDetailPT.jsp");
		} else {
			setResult("me/orderDetail.jsp");
		}
		return TRENDS;
	}
	public void addExtraWxJsCode() {
		JsFunDefine fun = new JsFunDefine("eiiijojkjd");
		JMChooseWXPay cwxp = new JMChooseWXPay();//创建微信打开地理位置对象
		getJsCreater().add(fun.add(cwxp));
		JQFunDefine fun2 = new JQFunDefine(".addr","click");//创建JQuery方法
		JMOpenLocation ol = new JMOpenLocation();//创建微信打开地理位置对象
		ol.setLatitude("parseFloat($(this).attr('latitude'))");
		ol.setLongitude("parseFloat($(this).attr('longitude'))");
		ol.setName(new JsExp("$(this).attr('name')"));
		ol.setAddress(new JsExp("$(this).attr('address')"));
		ol.setScale(14);
		getJsCreater().add(fun2.add(ol));
	}
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
