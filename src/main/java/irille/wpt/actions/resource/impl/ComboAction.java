package irille.wpt.actions.resource.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Combo;
import irille.wpt.bean.ComboLine;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.impl.ComboService;
import irille.wx.wpt.WptCity;

@Controller
@Scope("prototype")
public class ComboAction extends AbstractCRUDAction<Combo> {

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
	
	private List<ComboLine> listLine;
	
	@Resource
	protected ComboService service;
	
	@Override
	public String add() {
		
		return OBJECT;
	}
	
	@PermitAll
	@IncludeProperties({
		"\\[\\d+\\]\\.pkey",
		"\\[\\d+\\]\\.imgUrl",
		"\\[\\d+\\]\\.name",
		"\\[\\d+\\]\\.desc",
		"\\[\\d+\\]\\.restaurant\\.cityline\\.name",
		"\\[\\d+\\]\\.price",
		"\\[\\d+\\]\\.restaurant\\.longitude",
		"\\[\\d+\\]\\.restaurant\\.latitude"})
	public String search() throws JSONException, IOException {
		beans = service.findByCondition(banquetId, pnum, budget, ((WptCity)getSession().get(CityInterceptor.CITY)).getPkey().toString(), areaId, longitude, latitude);
		System.out.println(beans.size());
		return "json";
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
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public List<ComboLine> getListLine() {
		return listLine;
	}
	public void setListLine(List<ComboLine> listLine) {
		this.listLine = listLine;
	}
}
