package irille.wpt.actions.resource.impl;

import java.math.BigDecimal;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Log;
import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Member;
@Controller
@Scope("prototype")
public class UserAction extends AbstractCRUDAction<Member> {
	private static final long serialVersionUID = 1L;
	
	private static final Log LOG = new Log(UserAction.class);
	private int level;
	private String userid;
	private int fanid;
	private String orderOrFan;
	private BigDecimal amt;
	
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
