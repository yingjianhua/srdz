package irille.wpt.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.RestaurantTemplate;
@Controller
@Scope("prototype")
public class RestaurantTemplateAction extends AbstractCRUDAction<RestaurantTemplate> {
	private static final long serialVersionUID = 1L;

}
