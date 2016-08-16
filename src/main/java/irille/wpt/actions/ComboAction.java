package irille.wpt.actions;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.dialect.identity.SybaseAnywhereIdentityColumnSupport;
import org.springframework.stereotype.Controller;

import irille.wpt.bean.Combo;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.ComboService;
import irille.wx.wpt.WptCity;

@Controller
public class ComboAction extends AbstractWptAction  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String banquetId;
	private String pnum;
	private String budget;
	private String areaId;
	private String longitude;
	private String latitude;
	private List<Combo> combos;
	@Resource
	private ComboService comboService;

	public void list() {
		
	}
	public ComboAction() {
		combos = comboService.findByCondition(banquetId, pnum, budget, ((WptCity)getSession().get(CityInterceptor.CITY)).getPkey().toString(), areaId, longitude, latitude);
		for(Combo combo:combos) {
			System.out.println(combo);
			System.out.println(combo.getRestaurant());
		}
	}
}
