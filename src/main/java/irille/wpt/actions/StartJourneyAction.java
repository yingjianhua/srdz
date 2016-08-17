package irille.wpt.actions;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.interceptor.CityInterceptor;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptCityDAO;;
@Controller
@Scope("prototype")
public class StartJourneyAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4887405020330095178L;
	private WptCity currCity;
	private List<WptCity> citys;
	@Override
	public String execute() throws Exception {
		currCity = (WptCity)getSession().get(CityInterceptor.CITY);
		citys = WptCityDAO.getCitys(getAccount().getPkey());
		setResult("pt/index.jsp");
		return TRENDS;
	}
	
	public WptCity getCurrCity() {
		return currCity;
	}
	public void setCurrCity(WptCity currCity) {
		this.currCity = currCity;
	}
	public List<WptCity> getCitys() {
		return citys;
	}
	public void setCitys(List<WptCity> citys) {
		this.citys = citys;
	}
}
