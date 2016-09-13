package irille.wpt.actions.controller.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.CustomForm;
import irille.wpt.bean.Order;
import irille.wpt.service.impl.CustomFormService;
import irille.wpt.service.impl.OrderService;
import irille.wx.wpt.Wpt.OStatus;
import irille.wxpub.js.JMChooseWXPay;
import irille.wxpub.js.JsFunDefine;
@Controller
@Scope("prototype")
public class ConfirmOrderAction extends AbstractControllAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8451388149340391377L;

	private String orderid;
	private String formid;
	private Order order;
	private CustomForm customForm;
	
	@Resource
	private OrderService orderService;
	@Resource
	private CustomFormService customFormService;
	
	/**w
	 * 订单提交成功，跳转到简单的临时订单详情页，若为普通的套餐订单，则直接跳转到支付页面
	 * @return
	 * @throws Exception 
	 */
	public String execute() {
		if(orderid != null) {
			order = orderService.findByOrderid(orderid);
			if(order.getStatus().equals(OStatus.NOTACCEPTED.getLine().getKey())) {//未受理
				setResult("pt/orderDetail.jsp");
			} else if(order.getStatus().equals(OStatus.UNPAYMENT.getLine().getKey())){//未付款
				setResult("pt/comboOrder.jsp");
			}
		} else if(formid != null) {
			customForm = customFormService.findByFormid(formid);
			setResult("pt/formDetail.jsp");//私人订制申请表单详情
		}
		return TRENDS;
	}
	public void addExtraWxJsCode() {
		JsFunDefine fun = new JsFunDefine("eiiijojkjd");
		JMChooseWXPay cwxp = new JMChooseWXPay();//创建微信打开地理位置对象
		getJsCreater().add(fun.add(cwxp));
	}
	
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getFormid() {
		return formid;
	}
	public void setFormid(String formid) {
		this.formid = formid;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public CustomForm getCustomForm() {
		return customForm;
	}
	public void setCustomForm(CustomForm customForm) {
		this.customForm = customForm;
	}
	
}
