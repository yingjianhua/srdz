package irille.wpt.actions;

import java.util.List;

import irille.wpt.service.UserService;
import irille.wx.wx.WxUser;

public class ListFansAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8752277328802692183L;
	private List<WxUser> fans;
	private UserService service;
	@Override
	public String execute() throws Exception {
		fans = service.getFans(chkWxUser().getPkey());
		setResult("me/fansList.jsp");
		return TRENDS;
	}
	
	public List<WxUser> getFans() {
		return fans;
	}
	public void setFans(List<WxUser> fans) {
		this.fans = fans;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
}
