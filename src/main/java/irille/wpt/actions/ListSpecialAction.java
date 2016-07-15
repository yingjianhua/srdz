package irille.wpt.actions;

import java.util.List;

import irille.pub.idu.Idu;
import irille.wpt.interceptor.CityInterceptor;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptSpecial;

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
		String where = Idu.sqlString("{0}=? order by {1}", WptSpecial.T.CITY, WptSpecial.T.SORT);
		specials = WptSpecial.list(WptSpecial.class, where, false, city.getPkey());
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
