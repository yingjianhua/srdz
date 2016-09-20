package irille.wpt.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Log;
import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.CityLine;
@Controller
@Scope("prototype")
public class CityLineAction extends AbstractCRUDAction<CityLine> {
	private static final long serialVersionUID = 1L;
	
	private static final Log LOG = new Log(CityLineAction.class);
}
