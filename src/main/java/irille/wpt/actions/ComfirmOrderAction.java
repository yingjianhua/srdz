package irille.wpt.actions;

import java.util.List;

import irille.pub.bean.BeanBase;
import irille.wx.wpt.WptBanquet;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.WptOrderService;
import irille.wxpub.js.JMChooseWXPay;
import irille.wxpub.js.JsFunDefine;

public class ComfirmOrderAction extends AbstractWptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8451388149340391377L;

	private String orderId;
	private WptBanquet banquet;
	private WptOrder order;
	private List<WptOrderService> orderServices;
	
	/**
	 * 订单提交成功，跳转到简单的临时订单详情页，若为普通的套餐订单，则直接跳转到支付页面
	 * @return
	 * @throws Exception 
	 */
	public String execute() {
		order = WptOrder.loadUniqueOrderid(false, orderId);
		if(order.gtIsPt()) {
			banquet = order.gtBanquet();
			order.getComboName();
			orderServices = BeanBase.list(WptOrderService.class, WptOrderService.T.WPTORDER+"=?", false, order.getPkey());
			setResult("pt/orderDetail.jsp");
		} else {
			setResult("pt/comboOrder.jsp");
		}
		return TRENDS;
	}
	public void addExtraWxJsCode() {
		JsFunDefine fun = new JsFunDefine("eiiijojkjd");
		JMChooseWXPay cwxp = new JMChooseWXPay();//创建微信打开地理位置对象
		getJsCreater().add(fun.add(cwxp));
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public WptBanquet getBanquet() {
		return banquet;
	}
	public void setBanquet(WptBanquet banquet) {
		this.banquet = banquet;
	}
	public WptOrder getOrder() {
		return order;
	}
	public void setOrder(WptOrder order) {
		this.order = order;
	}
	public List<WptOrderService> getOrderServices() {
		return orderServices;
	}
	public void setOrderServices(List<WptOrderService> orderServices) {
		this.orderServices = orderServices;
	}
}
