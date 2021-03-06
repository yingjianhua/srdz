package irille.wpt.actions.controller.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.service.impl.RestaurantService;
import irille.wx.wpt.WptRestaurant;
import irille.wxpub.js.JMOpenLocation;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;
@Controller
@Scope("prototype")
public class ListRestaurantAction extends AbstractControllAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5359961711384861232L;
	private Integer banquetId;
	private Double pnum;
	private Double perCapitaBudget;
	private Integer areaId;
	private Map<WptRestaurant, List<Object>> mapCombo;
	@Resource
	private RestaurantService restaurantService;
	
	/**
	 * 餐厅列表 跳转到餐厅列表页面 列出满足条件的餐厅列表
	 * @return
	 */
	@Override
	public String execute() throws Exception {
		//获取需要显示的餐厅和套餐数据
		mapCombo = restaurantService.listRestaurant(areaId, banquetId, pnum.intValue(), perCapitaBudget.intValue());
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
	
	public int getBanquetId() {
		return banquetId;
	}
	public void setBanquetId(int banquetId) {
		this.banquetId = banquetId;
	}
	public double getPnum() {
		return pnum;
	}
	public void setPnum(double pnum) {
		this.pnum = pnum;
	}
	public double getPerCapitaBudget() {
		return perCapitaBudget;
	}
	public void setPerCapitaBudget(double perCapitaBudget) {
		this.perCapitaBudget = perCapitaBudget;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public Map<WptRestaurant, List<Object>> getMapCombo() {
		return mapCombo;
	}
	public void setMapCombo(Map<WptRestaurant, List<Object>> mapCombo) {
		this.mapCombo = mapCombo;
	}
}
