package irille.wpt.actions.resource.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Member;
import irille.wpt.service.impl.MemberService;
import irille.wx.wx.WxUser;

@Controller
@Scope("prototype")
public class MemberAction extends AbstractCRUDAction<Member>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private MemberService memberService;
	
	private int level;
	private int fanId;

	public String becomeMember() {
		//TODO memberService.becomeMember(bean);
		return BEAN;
	}
	@PermitAll
	@IncludeProperties({
		"\\[\\d+\\]\\.imageUrl",
		"\\[\\d+\\]\\.nickname",
		"\\[\\d+\\]\\.pkey",
		"\\[\\d+\\]\\.subscribeTime",
		})
	public String fans() {
		pages = memberService.pageFansByCondition(chkMember(), level, fanId, start, limit);
		return PAGES;
	}
	@PermitAll
	public String fan() {
		
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getFanId() {
		return fanId;
	}
	public void setFanId(int fanId) {
		this.fanId = fanId;
	}
	
}
