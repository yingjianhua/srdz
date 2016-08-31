package irille.wpt.actions.resource.impl;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Log;
import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.City;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.impl.CityService;
@Controller
@Scope("prototype")
public class CityAction extends AbstractCRUDAction<City> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5574652896687120596L;
	
	private static final Log LOG = new Log(CityAction.class);
	private int id;
	private String petitionCity;
	@Resource
	private CityService cityService;
	
	@PermitAll
	public String select() {
		City city = cityService.load(id);
		LOG.info("select:{0}", city.getName());
		if(city != null) {
			getSession().put(CityInterceptor.CITY, city);
		}
		object = "success";
		return OBJECT;
	}
	@IncludeProperties({
		"\\[\\d+\\]\\.pkey",
		"\\[\\d+\\]\\.name"
	})
	@PermitAll
	public String currCity() {
		City city = (City)getSession().get(CityInterceptor.CITY);
		if(city != null) {
			object = city;
		}
		return OBJECT;
	}
	
	@IncludeProperties({
		"\\[\\d+\\]\\.pkey",
		"\\[\\d+\\]\\.name"
	})
	@PermitAll
	public String search() {
		beans = cityService.search(getAccount().getPkey());
		return BEANS;
	}
	
	/**
	 * 请愿城市
	 */
	@PermitAll
	public String petition(){
		cityService.insOrUpd(getPetitionCity(), getAccount().getPkey());
		object = "享食光马上就来";
		return OBJECT;
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
