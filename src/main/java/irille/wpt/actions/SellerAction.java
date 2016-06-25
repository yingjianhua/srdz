package irille.wpt.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;

import irille.pub.Log;
import irille.pub.idu.Idu;
import irille.wpt.service.SellerService;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptRestaurant;

public class SellerAction extends AbstractWptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6300913511553794515L;
	public static final Log LOG = new Log(SellerAction.class);
	
	private String manager;
	private String identify;
	private String orderId;
	private Integer restaurantId;
	
	private SellerService service;
	
	/**
	 * 短信校验登录
	 */
	public void sendCheckCode(){
		String where = Idu.sqlString("{0}=?", WptRestaurant.T.MANAGER);
		List<WptRestaurant> list = WptRestaurant.list(WptRestaurant.class, where, false, manager);
		if(list.isEmpty()){
			try {
				ServletActionContext.getResponse().getWriter().print("null");//成功返回一个字符串
			} catch (IOException e) {
				e.printStackTrace();
			}
			return ;
		}
		service.sendCheckCode(manager, getSession());
	}
	public void listOrder() {
		JSONArray result = service.listOrder4Json(restaurantId, orderId, OStatus.PAYMENT);
		PrintWriter writer;
		try {
			writer = ServletActionContext.getResponse().getWriter();
			writer.println(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 核对校验码
	 */
	public void checkCode() {
		if(service.checkCode(identify, getSession())) {
			try {
				ServletActionContext.getResponse().getWriter().print("ok");//成功返回一个字符串
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}
	public SellerService getService() {
		return service;
	}
	public void setService(SellerService service) {
		this.service = service;
	}
}
