package irille.wpt.actions;

import java.util.List;

import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.TopService;
import irille.wx.wpt.WptBanquet;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptCityLine;
import irille.wx.wpt.WptTop;

public class ListTopAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4251137891559443632L;
	
	private List<WptCityLine> areas;
	private List<WptBanquet> banquets;
	private List<WptTop> tops;
	
	private TopService service;
	/**
	 * 头条列表
	 */

	@Override
	public String execute() throws Exception {
		WptCity city = (WptCity) getSession().get(CityInterceptor.CITY);
		areas = WptCityLine.list(WptCityLine.class, WptCityLine.T.CITY + " = ?", false, city.getPkey());
		banquets = WptBanquet.list(WptBanquet.class,  WptBanquet.T.ACCOUNT + " = ?", false, getAccount().getPkey());
		tops = service.search(city.getPkey(), null, null, getAccount().getPkey());
		setResult("front/topList.jsp");
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
	public TopService getService() {
		return service;
	}
	public void setService(TopService service) {
		this.service = service;
	}
}
