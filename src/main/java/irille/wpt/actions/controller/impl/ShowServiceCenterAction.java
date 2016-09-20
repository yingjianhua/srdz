package irille.wpt.actions.controller.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.ServiceCen;
import irille.wpt.service.impl.ServiceCenService;
@Controller
@Scope("prototype")
public class ShowServiceCenterAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;

	private ServiceCen serviceCenter;
	
	@Resource
	private ServiceCenService serviceCenService;
	
	/**
	 * 客服中心
	 */
	@Override
	public String execute() throws Exception {
		serviceCenter = serviceCenService.find(getAccount().getPkey());
		setResult("me/serviceCenter.jsp");
		return TRENDS;
	}

	public ServiceCen getServiceCenter() {
		return serviceCenter;
	}

	public void setServiceCenter(ServiceCen serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
	
}
