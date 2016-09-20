package irille.wpt.actions.resource.impl;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.ServiceCen;
import irille.wpt.service.impl.ServiceCenService;
@Controller
@Scope("prototype")
public class ServiceCenterAction extends AbstractCRUDAction<ServiceCen> {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ServiceCenService serviceCenService;
	
	
	/**
	 * 客服中心
	 */
	@PermitAll
	@IncludeProperties({
		"qrcode",
		"mobile"
	})
	public String info() throws Exception {
		bean = service.load(ServiceCen.class, getAccount().getPkey());
		return BEAN;
	}

}
