package irille.wpt.actions.resource.impl;

import javax.annotation.security.PermitAll;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.PetitionCity;
@Controller
@Scope("prototype")
public class PetitionCityAction extends AbstractCRUDAction<PetitionCity> {
	private static final long serialVersionUID = 1L;
	
	//private static final Log LOG = new Log(PetitionCityAction.class);
	
	private String name;
	
	/**
	 * 请愿城市
	 */
	@PermitAll
	public String petition(){
		petitionCityService.petition(getName(), getAccount().getPkey());
		object = "享食光马上就来";
		return OBJECT;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
