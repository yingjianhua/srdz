package irille.wpt.actions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wx.wpt.WptServiceCen;
@Controller
@Scope("prototype")
public class ShowServiceCenterAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4671621940447787133L;
	private WptServiceCen serviceCenter;
	
	/**
	 * 客服中心
	 */
	@Override
	public String execute() throws Exception {
		serviceCenter = WptServiceCen.load(WptServiceCen.class, getAccount().getPkey());
		setResult("me/serviceCenter.jsp");
		return TRENDS;
	}

	public WptServiceCen getServiceCenter() {
		return serviceCenter;
	}
	public void setServiceCenter(WptServiceCen serviceCenter) {
		this.serviceCenter = serviceCenter;
	}
}
