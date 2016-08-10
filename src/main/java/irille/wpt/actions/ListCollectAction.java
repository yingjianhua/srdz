package irille.wpt.actions;

import java.util.List;

import org.springframework.stereotype.Controller;

import irille.wx.wpt.WptCollect;
import irille.wx.wpt.WptTop;
import irille.wx.wx.WxUser;
@Controller
public class ListCollectAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7264087029941100928L;
	private List<WptTop> tops;
	private List<WptCollect> collects;

	/**
	 * 我的收藏
	 */
	@Override
	public String execute() throws Exception {
		WxUser user = chkWxUser();
		tops = WptTop.list(WptTop.class, "1=1 order by " + WptTop.T.DATE + " DESC", false);
		collects = WptCollect.list(WptCollect.class, WptCollect.T.WXUSER + " = ?", false, user.getPkey());
		setResult("me/listCollect.jsp");
		return TRENDS;
	}

	public List<WptTop> getTops() {
		return tops;
	}
	public void setTops(List<WptTop> tops) {
		this.tops = tops;
	}
	public List<WptCollect> getCollects() {
		return collects;
	}
	public void setCollects(List<WptCollect> collects) {
		this.collects = collects;
	}
}
