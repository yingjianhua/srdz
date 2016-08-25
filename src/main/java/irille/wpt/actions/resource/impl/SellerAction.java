package irille.wpt.actions.resource.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Log;
import irille.pub.idu.Idu;
import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.service.SellerService;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptRestaurant;
@Controller
@Scope("prototype")
public class SellerAction extends AbstractCRUDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6300913511553794515L;
	public static final Log LOG = new Log(SellerAction.class);
	
	private String manager;
	private String identify;
	private String orderId;
	private Integer restaurantId;
	private boolean isHistory;
	@Resource
	private SellerService sellerService;
	
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
		sellerService.sendCheckCode(manager, getSession());
	}
	public void listOrder() {
		JSONArray result;
		if(isHistory) {
			result = sellerService.listOrder4Json(restaurantId, orderId, OStatus.FINISH);	
		} else {
			result = sellerService.listOrder4Json(restaurantId, orderId, OStatus.PAYMENT);
		}
		PrintWriter writer;
		try {
			writer = ServletActionContext.getResponse().getWriter();
			writer.print(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 核对校验码
	 */
	public void checkCode() {
		if(sellerService.checkCode(identify, getSession())) {
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
	public boolean getIsHistory() {
		return isHistory;
	}
	public void setIsHistory(boolean isHistory) {
		this.isHistory = isHistory;
	}
}
