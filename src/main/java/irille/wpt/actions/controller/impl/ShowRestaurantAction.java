package irille.wpt.actions.controller.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.actions.controller.IMenuShareAppMessage;
import irille.wpt.actions.controller.IMenuShareTimeline;
import irille.wpt.bean.Restaurant;
import irille.wpt.service.impl.RestaurantService;
@Controller
@Scope("prototype")
public class ShowRestaurantAction extends AbstractControllAction implements IMenuShareAppMessage, IMenuShareTimeline{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String scombos;
	private Restaurant restaurant;
	private static final String TITLE_PRE = "【享食光】特别推出 • ";
	
	@Resource
	private RestaurantService restaurantService;
	
	/**
	 * 显示餐厅信息
	 */
	@Override
	public String execute() throws Exception {
		restaurant = restaurantService.load(id);
		if(getRestaurant().getTemplate() != null) {
			setResult(getRestaurant().getTemplate().getPath());
		} else {
			setResult("pt/restaurantDetail.jsp");
		}
		return TRENDS;
	}
	@Override
	public String getShareTimelineTitle() {
		return TITLE_PRE + getRestaurant().getName();
	}
	@Override
	public String getShareTimelineLink() {
		return getRequestUrl();
	}
	@Override
	public String getShareTimelineImgUrl() {
		return getDomain()+"/"+getRestaurant().getImgUrl();
	}
	@Override
	public String getShareAppMessageTitle() {
		return TITLE_PRE + getRestaurant().getName();
	}
	@Override
	public String getShareAppMessageDesc() {
		return getRestaurant().getDes();
	}
	@Override
	public String getShareAppMessageLink() {
		return getRequestUrl();
	}
	@Override
	public String getShareAppMessageImgUrl() {
		return getDomain()+"/"+getRestaurant().getImgUrl();
	}
	@Override
	public String getShareAppMessageType() {
		return "link";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Restaurant getRestaurant() {
		if(restaurant == null) 
			restaurant = restaurantService.load(id);
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	public String getScombos() {
		return scombos;
	}
	public void setScombos(String scombos) {
		this.scombos = scombos;
	}
}
