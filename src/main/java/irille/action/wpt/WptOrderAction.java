package irille.action.wpt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.WptOrderDAO;
import irille.wx.wpt.WptOrderLine;
import irille.wx.wpt.WptOrderService;

public class WptOrderAction extends ActionWx<WptOrder,WptOrderAction> {
	public static final Log LOG = new Log(WptOrderAction.class);
	private List<WptOrderLine> _listLine;
	private List<WptOrderService> _listService;


	public List<WptOrderService> getListService() {
		return _listService;
	}

	public void setListService(List<WptOrderService> listService) {
		_listService = listService;
	}

	public List<WptOrderLine> getListLine() {
		return _listLine;
	}

	public void setListLine(List<WptOrderLine> listLine) {
		_listLine = listLine;
	}

	public WptOrder getBean() {
		return _bean;
	}

	public void setBean(WptOrder bean) {
		this._bean = bean;
	}
	

	@Override
	public Class beanClazz() {
		return WptOrder.class;
	}
	@Override
	public WptOrder insRun() throws Exception {
		insBefore();
		WptOrderDAO.Ins ins = new WptOrderDAO.Ins();
		ins.setB(_bean);
		ins.setLines(_listLine);
		ins.commit();
		insAfter();
		return ins.getB();
	}

	@Override
	public WptOrder updRun() throws Exception {
		updBefore();
		WptOrderDAO.Upd upd = new WptOrderDAO.Upd();
		upd.setB(_bean);
		upd.setLines(_listLine);
		upd.commit();
		updAfter();
		return upd.getB();
	}
	public void insOrUpd() throws Exception {
		WptOrder order=WptOrder.load(WptOrder.class, getBean().getPkey());
		List<WptOrderService> orderService = getListService();
		if(orderService == null)
			orderService = new ArrayList();
		for(WptOrderService line : orderService){
			line.setAccount(order.getAccount());
		}
		Idu.updLine(order, orderService, WptOrderService.T.WPTORDER.getFld());
	}

	/**
	 * 工作人员与顾客确定完订单内容，进入支付定金状态
	 * @throws Exception 
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public void accept() throws IOException, JSONException, Exception {
		WptOrder order = WptOrder.load(WptOrder.class, getPkey());
		WptOrderDAO.accept(order);
		ServletActionContext.getResponse().getWriter().print(crtJsonByBean(order, "bean.").put(SUCCESS, true));
	}
	/**
	 * 由工作人员确认已收取定金
	 * @throws Exception 
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public void deposit() throws IOException, JSONException, Exception {
		WptOrder order = WptOrder.load(WptOrder.class, getPkey());
		WptOrderDAO.deposit(order);
		ServletActionContext.getResponse().getWriter().print(crtJsonByBean(order, "bean.").put(SUCCESS, true));
	}
	/**
	 * 由工作人员确认已收取余款
	 * @throws Exception 
	 * @throws JSONException 
	 * @throws IOException 
	 */
	public void residue() throws IOException, JSONException, Exception {
		WptOrder order = WptOrder.load(WptOrder.class, getPkey());
		WptOrderDAO.residue(order);
		ServletActionContext.getResponse().getWriter().print(crtJsonByBean(order, "bean.").put(SUCCESS, true));
	}
	/**
	 * 工作人员确认同意进行退款
	 * @throws Exception 
	 */
	public void agreeRefund() throws Exception {
		WptOrder order = WptOrder.load(WptOrder.class, getPkey());
		WptOrderDAO.refundOrder(order);
		ServletActionContext.getResponse().getWriter().print(crtJsonByBean(order, "bean.").put(SUCCESS, true));
	}
}
