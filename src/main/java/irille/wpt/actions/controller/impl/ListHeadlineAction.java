package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.Banquet;
import irille.wpt.bean.City;
import irille.wpt.bean.CityLine;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.impl.BanquetService;
import irille.wpt.service.impl.CityLineService;
@Controller
@Scope("prototype")
public class ListHeadlineAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private CityLineService cityLineService;
	@Resource
	private BanquetService banquetService;
	
	private List<CityLine> areas;
	private List<Banquet> banquets;
	
	/**
	 * 头条列表
	 */

	@Override
	public String execute() throws Exception {
		City city = (City) getSession().get(CityInterceptor.CITY);
		areas = cityLineService.listByCity(city.getPkey());
		banquets = banquetService.listByAccount(getAccount().getPkey());
		setResult("headline/headlineList.jsp");
		return TRENDS;
	}

	public List<CityLine> getAreas() {
		return areas;
	}

	public void setAreas(List<CityLine> areas) {
		this.areas = areas;
	}

	public List<Banquet> getBanquets() {
		return banquets;
	}

	public void setBanquets(List<Banquet> banquets) {
		this.banquets = banquets;
	}
	
}
