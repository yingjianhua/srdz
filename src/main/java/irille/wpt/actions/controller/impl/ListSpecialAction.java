package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.City;
import irille.wpt.bean.Special;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.impl.SpecialService;
@Controller
@Scope("prototype")
public class ListSpecialAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	private List<Special> specials;

	@Resource
	private SpecialService specialService;
	
	/**
	 * 发现和热销
	 */
	@Override
	public String execute() throws Exception {
		City city = (City)getSession().get(CityInterceptor.CITY);
		specials = specialService.listByCity(city.getPkey());
		setResult("find/specialList.jsp");
		return TRENDS;
	}
	
	public List<Special> getSpecials() {
		return specials;
	}
	public void setSpecials(List<Special> specials) {
		this.specials = specials;
	}
	
}
