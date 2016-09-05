package irille.wpt.actions.resource.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Log;
import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.City;
import irille.wpt.bean.CityLine;
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
	private List<CityLine> listLine;
	@Resource
	private CityService cityService;
	
	@PermitAll
	public String select() {
		City city = service.load(City.class, id);
		LOG.info("select:{0}", city.getName());
		if(city != null) {
			getSession().put(CityInterceptor.CITY, city);
		}
		object = "success";
		return OBJECT;
	}
	
	@IncludeProperties({"\\[\\d+\\]\\.pkey","\\[\\d+\\]\\.name"})
	@PermitAll
	public String currCity() {
		City city = (City)getSession().get(CityInterceptor.CITY);
		if(city != null) {
			object = city;
		}
		return OBJECT;
	}
	
	@IncludeProperties({"\\[\\d+\\]\\.pkey","\\[\\d+\\]\\.name"})
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
	
	@Override
	public String ins() {
		cityService.save(bean, listLine, account.getPkey());
		System.out.println("cityAction.add.bean:"+bean);
		return BEAN;
	}
	@Override
	public String upd() {
		cityService.update(bean, listLine, account.getPkey());
		return BEAN;
	}
	@Override
	public String del() {
		cityService.delete(bean);
		return BEAN;
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
	public List<CityLine> getListLine() {
		return listLine;
	}
	public void setListLine(List<CityLine> listLine) {
		this.listLine = listLine;
	}
}
