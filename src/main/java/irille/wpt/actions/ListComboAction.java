package irille.wpt.actions;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.ComboService;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptRestaurant;
import irille.wxpub.js.JMOpenLocation;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;
@Controller
public class ListComboAction extends AbstractWptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5359961711384861232L;
	private String banquetId;
	private String pnum;
	private String budget;
	private String areaId;
	private Map<WptRestaurant, List<Object>> mapCombo;
	@Resource
	private ComboService comboService;
	
	/**
	 * 填选了私人订制的基本需求后 显示符合条件的套餐页面
	 * @return
	 */
	@Override
	public String execute() throws Exception {
		comboService.findByCondition(banquetId, pnum, budget,((WptCity)getSession().get(CityInterceptor.CITY)).getPkey().toString(), areaId);
		System.out.println(banquetId);
		System.out.println(banquetId==null);
		System.out.println(pnum);
		//获取需要显示的餐厅和套餐数据
		//mapCombo = comboService.listCombo(areaId, banquetId, pnum.intValue(), perCapitaBudget.intValue());
		setResult("pt/restaurantList.jsp");
		return TRENDS;
	}
	/**
	 * 添加微信获取地理位置JsCode代码
	 */
	@Override
	public void addExtraWxJsCode() {
		JQFunDefine fun = new JQFunDefine(".addr","click");//创建JQuery方法
		JMOpenLocation ol = new JMOpenLocation();//创建微信打开地理位置对象
		ol.setLatitude("parseFloat($(this).attr('latitude'))");
		ol.setLongitude("parseFloat($(this).attr('longitude'))");
		ol.setName(new JsExp("$(this).attr('name')"));
		ol.setAddress(new JsExp("$(this).attr('address')"));
		ol.setScale(14);
		getJsCreater().add(fun.add(ol));
	}
	
	public String getBanquetId() {
		return banquetId;
	}
	public void setBanquetId(String banquetId) {
		this.banquetId = banquetId;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getPerCapitaBudget() {
		return perCapitaBudget;
	}
	public void setPerCapitaBudget(String perCapitaBudget) {
		this.perCapitaBudget = perCapitaBudget;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public Map<WptRestaurant, List<Object>> getMapCombo() {
		return mapCombo;
	}
	public void setMapCombo(Map<WptRestaurant, List<Object>> mapCombo) {
		this.mapCombo = mapCombo;
	}
}
