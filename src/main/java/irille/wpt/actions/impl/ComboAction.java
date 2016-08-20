package irille.wpt.actions.impl;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.AbstractCRUDAction;
import irille.wpt.bean.Combo;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.ComboService;
import irille.wx.wpt.WptCity;

@Controller
@Scope("prototype")
public class ComboAction extends AbstractCRUDAction {

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
	
	@DenyAll
	public void deny() {
		
	}
	@PermitAll
	public void permit() {
		
	}
	@RolesAllowed({"admin","ddd"})
	public void roles() {
		
	}

	@RolesAllowed("user")
	public void list() throws JSONException, IOException {
		combos = comboService.findByCondition(banquetId, pnum, budget, ((WptCity)getSession().get(CityInterceptor.CITY)).getPkey().toString(), areaId, longitude, latitude);
		JSONArray array = new JSONArray();
		for(Combo combo:combos) {
			JSONObject json = new JSONObject();
			json.put("id", combo.getPkey());
			json.put("imgUrl", combo.getImgUrl());
			json.put("name", combo.getName());
			json.put("desc", combo.getDes());
			json.put("area", combo.getRestaurant().getCityline().getName());
			json.put("price", combo.getPrice());
			json.put("longitude", combo.getRestaurant().getLongitude());
			json.put("latitude", combo.getRestaurant().getLatitude());
			array.put(json);
		}
		getResponse().getWriter().print(array.toString());
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
	public List<Combo> getCombos() {
		return combos;
	}
	public void setCombos(List<Combo> combos) {
		this.combos = combos;
	}
}
