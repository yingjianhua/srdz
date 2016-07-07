package irille.wpt.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.wpt.service.UserService;
import irille.wx.wpt.WptCommissionJournal;
import irille.wx.wx.WxUser;

public class UserAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3883419323449309464L;
	private int level;
	private String userid;
	private int fanid;
	private String orderOrFan;
	private BigDecimal amt;
	
	private UserService service;

	public void cashDetail() {
		WxUser user = chkWxUser();
		BigDecimal commission = user.getCashableCommission();
		boolean isMember = user.gtIsMember();
		JSONObject json = new JSONObject();
		try {
			json.put("commission", commission);
			json.put("isMember", isMember);
			PrintWriter writer;
			writer = getResponse().getWriter();
			writer.print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 佣金提现
	 */
	public void cash() {
		System.out.println("------------user_cash------------");
		try {
			service.cash(amt, chkWxUser(), getRequest().getRemoteHost(), "");
			PrintWriter writer;
			writer = getResponse().getWriter();
			writer.print(new JSONObject().put("success", true).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("------------user_cash------------");
	}
	public void fans() {
		List<WxUser> fans = service.getFansByCondition(userid, getAccount().getPkey(), level, fanid);
		JSONArray result = new JSONArray();
		try {
			for(WxUser fan:fans) {
				JSONObject o = new JSONObject();
				o.put("head", fan.getImageUrl());
				o.put("nick", fan.getNickname());
				o.put("id", fan.getPkey());
				o.put("subtime", DateFormat.getDateTimeInstance().format(fan.getSubscribeTime()));
				result.put(o);
			}
			PrintWriter writer;
			writer = getResponse().getWriter();
			writer.print(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 获取佣金流水记录
	 */
	public void fanOrders() {
		List<WptCommissionJournal> journals = service.getCommissionJournal(userid, getAccount().getPkey(), orderOrFan);
		JSONArray result = new JSONArray();
		try {
			for(WptCommissionJournal journal:journals) {
				JSONObject o = new JSONObject();
				o.put("orderid", journal.getOrderid());
				o.put("createTime", DateFormat.getDateTimeInstance().format(journal.getCreateTime()));
				o.put("price", journal.getPrice());
				o.put("commission", journal.getCommission());
				o.put("fanid", journal.getFans());
				o.put("head", journal.getImageUrl());
				o.put("nick", journal.getNickname());
				o.put("status", journal.gtStatus().getLine().getName());
				result.put(o);
			}
			PrintWriter writer;
			writer = getResponse().getWriter();
			writer.print(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
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
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
}
