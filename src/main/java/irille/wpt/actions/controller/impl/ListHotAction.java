package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.City;
import irille.wpt.bean.Hot;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.impl.HotService;
import irille.wxpub.js.JMGetLocation;
import irille.wxpub.js.JMOpenLocation;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;
import irille.wxpub.js.JsFunDefine;
@Controller
@Scope("prototype")
public class ListHotAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private HotService hotService;
	
	private List<Hot> hots;

	/**
	 * 发现和热销
	 */
	@Override
	public String execute() throws Exception {
		City city = (City)getSession().get(CityInterceptor.CITY);
		hots = hotService.listByCity(city.getPkey());
		setResult("find/hotList.jsp");
		return TRENDS;
	}
	public void addExtraWxJsCode()  {
		JQFunDefine fun = new JQFunDefine(".addr","click");//创建JQuery方法
		JMOpenLocation ol = new JMOpenLocation();//创建微信打开地理位置对象
		ol.setLatitude("parseFloat($(this).attr('latitude'))");
		ol.setLongitude("parseFloat($(this).attr('longitude'))");
		ol.setName(new JsExp("$(this).attr('name')"));
		ol.setAddress(new JsExp("$(this).attr('address')"));
		ol.setScale(14);
		getJsCreater().add(fun.add(ol));
		JsFunDefine fun2 = new JsFunDefine("getLocation");
		JMGetLocation gl = new JMGetLocation();
		gl.setType("wgs84");
		gl.setSuccess("jsDistance");
		getJsCreater().add(fun2.add(gl));
	}
	
	public List<Hot> getHots() {
		return hots;
	}
	public void setHots(List<Hot> hots) {
		this.hots = hots;
	}
	
}
