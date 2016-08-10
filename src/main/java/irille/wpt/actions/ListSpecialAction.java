package irille.wpt.actions;

import java.util.List;

import org.springframework.stereotype.Controller;

import irille.core.sys.Sys.OYn;
import irille.pub.idu.Idu;
import irille.wpt.interceptor.CityInterceptor;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptSpecial;
@Controller
public class ListSpecialAction extends AbstractWptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6110915177662480823L;
	private List<WptSpecial> specials;

	/**
	 * 发现和热销
	 */
	@Override
	public String execute() throws Exception {
		WptCity city = (WptCity)getSession().get(CityInterceptor.CITY);
		String where = Idu.sqlString("{0}=? or {1}=? order by {2}", WptSpecial.T.CITY, WptSpecial.T.IGNORE_CITY, WptSpecial.T.SORT);
		specials = WptSpecial.list(WptSpecial.class, where, false, city.getPkey(), OYn.YES.getLine().getKey());
		setResult("find/specialList.jsp");
		return TRENDS;
	}
	public List<WptSpecial> getSpecials() {
		return specials;
	}
	public void setSpecials(List<WptSpecial> specials) {
		this.specials = specials;
	}
}
