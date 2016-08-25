package irille.wpt.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.RestaurantMenu;
@Controller
@Scope("prototype")
public class RestaurantMenuAction extends AbstractCRUDAction<RestaurantMenu> {

}
