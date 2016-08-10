package irille.wpt.actions;

import java.util.List;

import org.springframework.stereotype.Controller;

import irille.pub.bean.BeanBase;
import irille.wpt.interceptor.CityInterceptor;
import irille.wx.wpt.WptBanquet;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptCityLine;
@Controller
public class ApplyServiceAction extends AbstractWptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4071624555401326302L;
	private List<WptCityLine> areas;
	private List<WptBanquet> banquets;
	@Override
	public String execute() throws Exception {
		areas = BeanBase.list(WptCityLine.class, WptCityLine.T.CITY + "=?", false, ((WptCity)getSession().get(CityInterceptor.CITY)).getPkey());
		banquets = BeanBase.list(WptBanquet.class, WptBanquet.T.ACCOUNT + "=?", false, getAccount().getPkey());
		setResult("pt/applyService.jsp");
		return TRENDS;
	}
	public List<WptCityLine> getAreas() {
		return areas;
	}
	public List<WptBanquet> getBanquets() {
		return banquets;
	}
}
