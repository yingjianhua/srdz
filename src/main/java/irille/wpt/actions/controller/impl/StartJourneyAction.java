package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.City;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.impl.CityService;
@Controller
@Scope("prototype")
public class StartJourneyAction extends AbstractControllAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4887405020330095178L;
	private City currCity;
	private List<City> citys;
	
	@Resource
	private CityService cityService;
	
	@Override
	public String execute() throws Exception {
		currCity = (City)getSession().get(CityInterceptor.CITY);
		citys = cityService.search(getAccount().getPkey());
		setResult("pt/index.jsp");
		return TRENDS;
	}
	
	public City getCurrCity() {
		return currCity;
	}
	public void setCurrCity(City currCity) {
		this.currCity = currCity;
	}
	public List<City> getCitys() {
		return citys;
	}
	public void setCitys(List<City> citys) {
		this.citys = citys;
	}
}
