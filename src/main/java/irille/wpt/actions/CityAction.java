package irille.wpt.actions;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import irille.pub.Exp;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.CityService;
import irille.wx.wpt.WptCity;

public class CityAction extends AbstractWptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5574652896687120596L;
	private int id;
	private String petitionCity;
	
	private CityService service;
	
	public void select() {
		System.out.println("----------------cityAction.select():id="+id+"----------------------");
		WptCity city = WptCity.get(WptCity.class, id);
		if(city != null) {
			getSession().put(CityInterceptor.CITY, city);
		}
		System.out.println("----------------cityAction.select():id="+id+"----------------------");
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
	/**
	 * 请愿城市
	 */
	public void petition(){
		PrintWriter writer = null;
		try {
			writer = ServletActionContext.getResponse().getWriter();
			if(getPetitionCity() != null)
				service.insOrUpd(getPetitionCity(), getAccount().getPkey());
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
	public CityService getService() {
		return service;
	}
	public void setService(CityService service) {
		this.service = service;
	}
}
