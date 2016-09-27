package irille.wpt.actions.resource.impl;

import java.io.IOException;

import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.ServiceCenter;
@Controller
@Scope("prototype")
public class ServiceCenterAction extends AbstractCRUDAction<ServiceCenter> {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 客服中心
	 */
	@PermitAll
	@IncludeProperties({
		"qrcode",
		"mobile"
	})
	public String info() throws Exception {
		bean = serviceCenterService.load(getAccount().getPkey());
		return BEAN;
	}

	/**
	 * 客服中心设置
	 */
	public String insOrUpd() throws IOException, JSONException {
		serviceCenterService.insOrUpd(bean);
		return BEAN;
	}
}
