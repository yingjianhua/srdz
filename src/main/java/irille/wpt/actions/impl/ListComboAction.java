package irille.wpt.actions.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.AbstractControllAction;
import irille.wpt.service.ComboService;
import irille.wx.wpt.WptCombo;
import irille.wxpub.js.JMOpenLocation;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;
@Controller
@Scope("prototype")
public class ListComboAction extends AbstractControllAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5359961711384861232L;
	private String banquetId;
	private String pnum;
	private String budget;
	private String areaId;
	private List<WptCombo> combos;
	@Resource
	private ComboService comboService;
	
	/**
	 * 填选了私人订制的基本需求后 显示符合条件的套餐页面
	 * @return
	 */
	@Override
	public String execute() throws Exception {
		setResult("pt/comboList.jsp");
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
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public List<WptCombo> getCombos() {
		return combos;
	}
	public void setCombos(List<WptCombo> combos) {
		this.combos = combos;
	}
}
