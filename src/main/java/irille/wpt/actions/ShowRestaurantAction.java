package irille.wpt.actions;

import java.util.List;

import org.springframework.stereotype.Controller;

import irille.core.sys.Sys.OEnabled;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptCase;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptRestaurantBanner;
import irille.wx.wpt.WptRestaurantTemplate;
@Controller
public class ShowRestaurantAction extends AbstractWptAction implements IMenuShareAppMessage, IMenuShareTimeline{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8896673981225319609L;
	private Integer id;
	private String scombos;
	private WptRestaurant restaurant;
	private List<WptRestaurantBanner> banners;
	private List<WptCombo> combos;
	private List<WptCase> cases;
	private static final String TITLE_PRE = "【享食光】特别推出 • ";
	
	/**
	 * 显示餐厅信息
	 */
	@Override
	public String execute() throws Exception {
		String bannerSql = Idu.sqlString("{0}=? order by {1}", WptRestaurantBanner.T.RESTAURANT, WptRestaurantBanner.T.SORT);
		banners = WptRestaurantBanner.list(WptRestaurantBanner.class, bannerSql, false, id);
		cases = BeanBase.list(WptCase.class, WptCase.T.RESTAURANT+"=?", false, id);
		if(scombos == null || scombos.equals("")){
			combos = BeanBase.list(WptCombo.class, WptCombo.T.RESTAURANT+"=? and "+WptCombo.T.ENABLED+"=? order by "+WptCombo.T.SORT, false, id, OEnabled.TRUE.getLine().getKey());
		}else{
			combos = BeanBase.list(WptCombo.class, WptCombo.T.ENABLED+"=? and "+WptCombo.T.PKEY +" in ("+scombos+") order by " +WptCombo.T.SORT, false, OEnabled.TRUE.getLine().getKey());
		}
		if(getRestaurant().getTemplate() != null) {
			WptRestaurantTemplate template = getRestaurant().gtTemplate();
			setResult(template.getPath());
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
	public WptRestaurant getRestaurant() {
		if(restaurant == null) 
			restaurant = WptRestaurant.load(WptRestaurant.class, id);
		return restaurant;
	}
	public void setRestaurant(WptRestaurant restaurant) {
		this.restaurant = restaurant;
	}
	public String getScombos() {
		return scombos;
	}
	public void setScombos(String scombos) {
		this.scombos = scombos;
	}
	public List<WptRestaurantBanner> getBanners() {
		return banners;
	}
	public void setBanners(List<WptRestaurantBanner> banners) {
		this.banners = banners;
	}
	public List<WptCombo> getCombos() {
		return combos;
	}
	public void setCombos(List<WptCombo> combos) {
		this.combos = combos;
	}
	public List<WptCase> getCases() {
		return cases;
	}
	public void setCases(List<WptCase> cases) {
		this.cases = cases;
	}
}
