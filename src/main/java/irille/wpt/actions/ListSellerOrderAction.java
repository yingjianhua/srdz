package irille.wpt.actions;

import java.util.List;

import irille.wpt.service.SellerService;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.WptRestaurant;

public class ListSellerOrderAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6006935221209494793L;
	private String manager;
	private String identify;
	private WptRestaurant restaurant;
	private List<WptOrder> orders;
	
	private SellerService service;
	/**
	 * 商家订单列表
	 */
	@Override
	public String execute() throws Exception {
		restaurant = service.sellerLogin(getSession(), identify, getAccount().getPkey(), chkWxUser());
		orders = service.listOrder(restaurant.getPkey(), null, OStatus.PAYMENT);
		setResult("seller/sellerOrderList.jsp");
		return TRENDS;
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
	public WptRestaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(WptRestaurant restaurant) {
		this.restaurant = restaurant;
	}
	public List<WptOrder> getOrders() {
		return orders;
	}
	public void setOrders(List<WptOrder> orders) {
		this.orders = orders;
	}
	public SellerService getService() {
		return service;
	}
	public void setService(SellerService service) {
		this.service = service;
	}
}
