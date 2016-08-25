package irille.wpt.actions.resource.impl;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wx.wpt.WptServiceCen;
@Controller
@Scope("prototype")
public class ServiceCenterAction extends AbstractCRUDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private WptServiceCen serviceCenter;
	
	/**
	 * 客服中心
	 */
	public void info() throws Exception {
		serviceCenter = WptServiceCen.load(WptServiceCen.class, getAccount().getPkey());
		try {
			PrintWriter writer = ServletActionContext.getResponse().getWriter();
			writer.print(new JSONObject().put("qrcode", serviceCenter.getQrcode()).put("mobile", serviceCenter.getMobile()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public WptServiceCen getServiceCenter() {
		return serviceCenter;
	}
	public void setServiceCenter(WptServiceCen serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
}
