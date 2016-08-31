package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.City;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.impl.HeadlineService;
import irille.wx.wpt.WptBanquet;
import irille.wx.wpt.WptCityLine;
import irille.wx.wpt.WptTop;
@Controller
@Scope("prototype")
public class ListHeadlineAction extends AbstractControllAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4251137891559443632L;
	
	private List<WptCityLine> areas;
	private List<WptBanquet> banquets;
	private List<WptTop> tops;
	@Resource
	private HeadlineService topService;
	/**
	 * 头条列表
	 */

	@Override
	public String execute() throws Exception {
		City city = (City) getSession().get(CityInterceptor.CITY);
		areas = WptCityLine.list(WptCityLine.class, WptCityLine.T.CITY + " = ?", false, city.getPkey());
		banquets = WptBanquet.list(WptBanquet.class,  WptBanquet.T.ACCOUNT + " = ?", false, getAccount().getPkey());
		setResult("headline/headlineList.jsp");
		return TRENDS;
	}
	
	public List<WptCityLine> getAreas() {
		return areas;
	}
	public void setAreas(List<WptCityLine> areas) {
		this.areas = areas;
	}
	public List<WptBanquet> getBanquets() {
		return banquets;
	}
	public void setBanquets(List<WptBanquet> banquets) {
		this.banquets = banquets;
	}
	public List<WptTop> getTops() {
		return tops;
	}
	public void setTops(List<WptTop> tops) {
		this.tops = tops;
	}
}
