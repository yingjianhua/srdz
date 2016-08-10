package irille.wpt.actions;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import irille.wpt.service.UserService;
import irille.wx.wpt.Wpt;
import irille.wx.wpt.WptCollect;
import irille.wx.wpt.WptOrder;
import irille.wx.wx.WxUser;
@Controller
public class ShowMeAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8309114554907509460L;
	private WxUser user;
	private int orderNum;
	private int collectNum;
	private int fansNum;
	private BigDecimal fansSaleAmount;
	@Resource
	private UserService userService;
	/**
	 * 显示我的页面
	 */
	@Override
	public String execute() throws Exception {
		user = chkWxUser();
		orderNum = WptOrder.list(WptOrder.class, WptOrder.T.WXUSER + " = ?" + " AND " + WptOrder.T.STATUS + "<>"+ Wpt.OStatus.FINISH.getLine().getKey() + " AND " + WptOrder.T.STATUS + " <>" + Wpt.OStatus.CLOSE.getLine().getKey(), false, user.getPkey()).size();
		collectNum = WptCollect.list(WptCollect.class, WptCollect.T.WXUSER + " = ?", false, user.getPkey()).size();
		fansNum = userService.getFansNum(user.getPkey());
		fansSaleAmount = userService.getFansSaleAmount(user.getPkey());
		setResult("me/index.jsp");
		return TRENDS;
	}
	
	public WxUser getUser() {
		return user;
	}
	public void setUser(WxUser user) {
		this.user = user;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public int getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(int collectNum) {
		this.collectNum = collectNum;
	}
	public int getFansNum() {
		return fansNum;
	}
	public void setFansNum(int fansNum) {
		this.fansNum = fansNum;
	}
	public BigDecimal getFansSaleAmount() {
		return fansSaleAmount;
	}
	public void setFansSaleAmount(BigDecimal fansSaleAmount) {
		this.fansSaleAmount = fansSaleAmount;
	}
}
