package irille.wpt.actions.resource.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Log;
import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.service.impl.UserService;
import irille.wx.wpt.WptCommissionJournal;
import irille.wx.wx.WxUser;
@Controller
@Scope("prototype")
public class UserAction extends AbstractCRUDAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3883419323449309464L;
	private static final Log LOG = new Log(UserAction.class);
	private int level;
	private String userid;
	private int fanid;
	private String orderOrFan;
	private BigDecimal amt;
	@Resource
	private UserService userService;

	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getFanid() {
		return fanid;
	}
	public void setFanid(int fanid) {
		this.fanid = fanid;
	}
	public String getOrderOrFan() {
		return orderOrFan;
	}
	public void setOrderOrFan(String orderOrFan) {
		this.orderOrFan = orderOrFan;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
}
