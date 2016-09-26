package irille.wpt.actions.resource.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.CustomService;
import irille.wpt.service.impl.CustomServiceService;
@Controller
@Scope("prototype")
public class CustomServiceAction extends AbstractCRUDAction<CustomService> {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private CustomServiceService customServiceService;

	@Override
	public String ins() {
		customServiceService.save(bean, account.getPkey());
		return BEAN;
	}
	
	@Override
	public String upd() {
		customServiceService.update(bean, account.getPkey());
		return BEAN;
	}
}
