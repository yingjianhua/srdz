package irille.wpt.actions;

import java.util.List;

import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptCase;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptRestaurantBanner;

public class ShowRestaurantAction extends AbstractWptAction {
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
	
	/**
	 * 显示餐厅信息
	 */
	@Override
	public String execute() throws Exception {
		restaurant = WptRestaurant.load(WptRestaurant.class, id);
		String bannerSql = Idu.sqlString("{0}=? order by {1}", WptRestaurantBanner.T.RESTAURANT, WptRestaurantBanner.T.SORT);
		banners = WptRestaurantBanner.list(WptRestaurantBanner.class, bannerSql, false, id);
		cases = BeanBase.list(WptCase.class, WptCase.T.RESTAURANT+"=?", false, id);
		if(scombos == null || scombos.equals("")){
			combos = BeanBase.list(WptCombo.class, WptCombo.T.RESTAURANT+"=? order by "+WptCombo.T.SORT, false, id);
		}else{
			combos = BeanBase.list(WptCombo.class, WptCombo.T.PKEY +" in ("+scombos+") order by " +WptCombo.T.SORT, false);
		}
		setResult("pt/restaurantDetail.jsp");
		return TRENDS;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public WptRestaurant getRestaurant() {
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
