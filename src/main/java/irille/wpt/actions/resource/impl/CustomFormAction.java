package irille.wpt.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.CustomForm;
@Controller
@Scope("prototype")
public class CustomFormAction extends AbstractCRUDAction<CustomForm> {
	private static final long serialVersionUID = 1L;

}
