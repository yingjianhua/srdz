package irille.wpt.actions;

import java.util.List;

import org.springframework.stereotype.Controller;

import irille.pub.bean.BeanBase;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.WptOrderLine;
import irille.wx.wpt.WptOrderService;
import irille.wxpub.js.JMChooseWXPay;
import irille.wxpub.js.JMOpenLocation;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;
import irille.wxpub.js.JsFunDefine;
@Controller
public class ShowOrderAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8746257455067112640L;
	private Integer id;
	private WptOrder order;
	private List<WptOrderService> orderServices;
	private List<WptOrderLine> orderLines;
	
	/**
	 * 完整的订单详情页
	 */
	@Override
	public String execute() throws Exception {
		order = WptOrder.load(WptOrder.class, id);
		if(order.gtIsPt()) {
			setResult("me/orderDetailPT.jsp");
			orderServices = BeanBase.list(WptOrderService.class, WptOrderService.T.WPTORDER+"=?", false, id);
		} else {
			orderLines = BeanBase.list(WptOrderLine.class, WptOrderLine.T.WPTORDER+"=?", false, id);
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
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public List<WptOrderLine> getOrderLines() {
		return orderLines;
	}
	public void setOrderLines(List<WptOrderLine> orderLines) {
		this.orderLines = orderLines;
	}
}
