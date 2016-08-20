package irille.wpt.actions.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.idu.Idu;
import irille.wpt.actions.AbstractControllAction;
import irille.wpt.interceptor.CityInterceptor;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptHot;
import irille.wxpub.js.JMGetLocation;
import irille.wxpub.js.JMOpenLocation;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;
import irille.wxpub.js.JsFunDefine;
@Controller
@Scope("prototype")
public class ListHotAction extends AbstractControllAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6110915177662480823L;
	private List<WptHot> hots;

	/**
	 * 发现和热销
	 */
	@Override
	public String execute() throws Exception {
		WptCity city = (WptCity)getSession().get(CityInterceptor.CITY);
		String where = Idu.sqlString("{0}=? order by {1}", WptHot.T.CITY, WptHot.T.SORT);
		hots = WptHot.list(WptHot.class, where, false, city.getPkey());
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
	
	public List<WptHot> getHots() {
		return hots;
	}
	public void setHots(List<WptHot> hots) {
		this.hots = hots;
	}
}
