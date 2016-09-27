package irille.wpt.actions.resource.impl;

import java.math.BigDecimal;

import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Member;

@Controller
@Scope("prototype")
public class MemberAction extends AbstractCRUDAction<Member>  {
	private static final long serialVersionUID = 1L;
	
	private int level;
	private int fanId;
	private String orderIdOrFanId;
	private BigDecimal amt;

	public String becomeMember() {
		memberService.becomeMember(chkMember(), true);
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
	
	/**
	 * 获取佣金流水记录
	 */
	@PermitAll
	@IncludeProperties({
		"\\[\\d+\\]\\.orderid",
		"\\[\\d+\\]\\.createTime",
		"\\[\\d+\\]\\.price",
		"\\[\\d+\\]\\.commission",
		"\\[\\d+\\]\\.fan\\.pkey",
		"\\[\\d+\\]\\.imageUrl",
		"\\[\\d+\\]\\.nickname",
		"\\[\\d+\\]\\.status"
		})
	public String fanOrders() {
		object = commissionJournalService.list(chkMember().getPkey(), orderIdOrFanId);
		return OBJECT;
	}
	
	/**
	 * 为操作用户所在公众号的所有会员创建二维码
	 */
	public void createAllQrcode() {
		memberService.createAllQrcode(account.getPkey());
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
	public String getOrderIdOrFanId() {
		return orderIdOrFanId;
	}
	public void setOrderIdOrFanId(String orderIdOrFanId) {
		this.orderIdOrFanId = orderIdOrFanId;
	}
	
}
