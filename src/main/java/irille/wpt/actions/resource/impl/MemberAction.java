package irille.wpt.actions.resource.impl;

import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Member;
import irille.wpt.service.impl.MemberService;

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
	private BigDecimal amt;

	public String becomeMember() {
		//TODO memberService.becomeMember(bean);
		return BEAN;
	}
	@PermitAll
	@IncludeProperties({
		"imageUrl",
		"nickname",
		"pkey",
		"subscribeTime",
		})
	public String fan() {
		bean = memberService.findFanByCondition(chkMember(), level, fanId);
		return BEAN;
	}
	@PermitAll
	@IncludeProperties({
		"\\[\\d+\\]\\.imageUrl",
		"\\[\\d+\\]\\.nickname",
		"\\[\\d+\\]\\.pkey",
		"\\[\\d+\\]\\.subscribeTime",
		})
	public String pageFans() {
		pages = memberService.pageFansByCondition(chkMember(), level, start, limit);
		return PAGES;
	}
	@PermitAll
	@IncludeProperties({
		"\\[\\d+\\]\\.imageUrl",
		"\\[\\d+\\]\\.nickname",
		"\\[\\d+\\]\\.pkey",
		"\\[\\d+\\]\\.subscribeTime",
		})
	public String listFans() {
		beans = memberService.listFansByCondition(chkMember(), level);
		return BEANS;
	}
	
	@PermitAll
	@IncludeProperties({
		"cashableCommission",
		"isMember",
		})
	public String cashDetail() {
		bean = chkMember();
		return BEAN;
	}
	
	/**
	 * 佣金提现
	 */
	@PermitAll
	public String cash() {
		memberService.cash(amt, chkMember(), getRequest().getRemoteHost());
		return BEAN;
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
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	
}
