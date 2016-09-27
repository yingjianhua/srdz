package irille.wpt.actions.resource.impl;

import java.util.List;

import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Log;
import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.City;
import irille.wpt.bean.CityLine;
import irille.wpt.interceptor.CityInterceptor;
@Controller
@Scope("prototype")
public class CityAction extends AbstractCRUDAction<City> {
	private static final long serialVersionUID = 1L;
	
	private static final Log LOG = new Log(CityAction.class);
	
	private int id;
	private String petitionCity;
	private List<CityLine> listLine;
	
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
		"pkey",
		"name"
	})
	@PermitAll
	public String currCity() {
		City city = (City)getSession().get(CityInterceptor.CITY);
		if(city != null) {
			bean = city;
		}
		return BEAN;
	}
	
	@IncludeProperties({"\\[\\d+\\]\\.pkey","\\[\\d+\\]\\.name"})
	@PermitAll
	public String search() {
		beans = cityService.search(getAccount().getPkey());
		return BEANS;
	}
	
	@Override
	public String ins() {
		cityService.save(bean, listLine, account.getPkey());
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
