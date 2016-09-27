package irille.wpt.actions.controller.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.ServiceCenter;
import irille.wpt.service.impl.ServiceCenterService;
@Controller
@Scope("prototype")
public class ShowServiceCenterAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;

	private ServiceCenter serviceCenter;
	
	@Resource
	private ServiceCenterService serviceCenService;
	
	/**
	 * 客服中心
	 */
	@Override
	public String execute() throws Exception {
		serviceCenter = serviceCenService.find(getAccount().getPkey());
		setResult("me/serviceCenter.jsp");
		return TRENDS;
	}

	public ServiceCenter getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(ServiceCenter serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
	
}
