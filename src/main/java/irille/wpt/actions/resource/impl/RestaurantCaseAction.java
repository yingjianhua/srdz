package irille.wpt.actions.resource.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.RestaurantCase;
import irille.wpt.service.impl.RestaurantCaseService;
@Controller
@Scope("prototype")
public class RestaurantCaseAction extends AbstractCRUDAction<RestaurantCase> {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private RestaurantCaseService restaurantCaseService;
	
	@Override
	public String ins() {
		restaurantCaseService.save(bean, account.getPkey());
		return BEAN;
	}
	
	@Override
	public String upd() {
		restaurantCaseService.update(bean, account.getPkey());
		return BEAN;
	}

}
