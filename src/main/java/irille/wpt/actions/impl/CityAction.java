package irille.wpt.actions.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Exp;
import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.wpt.actions.AbstractCRUDAction;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.CityService;
import irille.wx.wpt.WptCity;
@Controller
@Scope("prototype")
public class CityAction extends AbstractCRUDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5574652896687120596L;
	private static final Log LOG = new Log(CityAction.class);
	private int id;
	private String petitionCity;
	@Resource
	private CityService cityService;
	
	public void select() {
		LOG.info("--------------select():start--------------");
		LOG.info("id:{0}", id);
		WptCity city = WptCity.get(WptCity.class, id);
		if(city != null) {
			getSession().put(CityInterceptor.CITY, city);
		}
		PrintWriter writer;
		try {
			writer = ServletActionContext.getResponse().getWriter();
			writer.print("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOG.info("--------------select():end--------------");
	}
	public void currCity() {
		WptCity city = (WptCity)getSession().get(CityInterceptor.CITY);
		if(city != null) {
			try {
				PrintWriter writer = ServletActionContext.getResponse().getWriter();
				writer.print(new JSONObject().put("id", city.getPkey()).put("name", city.getName()));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	public void listCity() {
		List<WptCity> citys = Bean.list(WptCity.class, WptCity.T.ACCOUNT+"=?", false, getAccount().getPkey());
		JSONArray result = new JSONArray();
		for(WptCity city:citys) {
			try {
				result.put(new JSONObject().put("id", city.getPkey()).put("name", city.getName()));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		PrintWriter writer;
		try {
			writer = ServletActionContext.getResponse().getWriter();
			writer.print(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 请愿城市
	 */
	public void petition(){
		PrintWriter writer = null;
		try {
			writer = ServletActionContext.getResponse().getWriter();
			if(getPetitionCity() != null)
				cityService.insOrUpd(getPetitionCity(), getAccount().getPkey());
			writer.print("享食光马上就来");
		} catch (Exp e) {
			writer.print(e.getLastMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public String getPetitionCity() {
		return petitionCity;
	}
	public void setPetitionCity(String petitionCity) {
		this.petitionCity = petitionCity;
	}
}
