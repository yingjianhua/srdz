package irille.wpt.actions.controller.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.bean.BeanBase;
import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.interceptor.CityInterceptor;
import irille.wx.wpt.WptBanquet;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptCityLine;
@Controller
@Scope("prototype")
public class ApplyServiceAction extends AbstractControllAction {
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
