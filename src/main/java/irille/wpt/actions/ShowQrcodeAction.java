package irille.wpt.actions;

import org.springframework.stereotype.Controller;

import irille.wx.wx.WxUser;
@Controller
public class ShowQrcodeAction extends AbstractWptAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5169235604893267915L;
	private WxUser user;

	@Override
	public String execute() throws Exception {
		user = chkWxUser();
		setResult("me/qrcode.jsp");
		return TRENDS;
	}

	public WxUser getUser() {
		return user;
	}
	public void setUser(WxUser user) {
		this.user = user;
	}
}
