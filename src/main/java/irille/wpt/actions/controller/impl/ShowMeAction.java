package irille.wpt.actions.controller.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.bean.Bean;
import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.Member;
import irille.wpt.service.impl.CollectService;
import irille.wpt.service.impl.CustomFormService;
import irille.wpt.service.impl.MemberService;
import irille.wpt.service.impl.OrderService;
import irille.wpt.service.impl.UserService;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;
@Controller
@Scope("prototype")
public class ShowMeAction extends AbstractControllAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8309114554907509460L;
	private Member member;
	private long orderNum;
	private long customFormNum;
	private long collectNum;
	private long fansNum;
	private BigDecimal fansSaleAmount;
	@Resource
	private UserService userService;
	@Resource
	private OrderService orderService;
	@Resource
	private CollectService collectService;
	@Resource
	private CustomFormService customFormService;
	@Resource
	private MemberService memberService;
	
	/**
	 * 显示我的页面
	 */
	@Override
	public String execute() throws Exception {
		member = chkMember();
		account = Bean.get(WxAccount.class, account.getPkey());
		orderNum = orderService.countPending(member.getPkey());
		customFormNum = customFormService.countByMember(member.getPkey());
		collectNum = collectService.countByMember(member.getPkey());
		fansNum = memberService.countFans1(member.getPkey());
		fansSaleAmount = memberService.countFans1Sale(member.getPkey());
		setResult("me/index.jsp");
		return TRENDS;
	}
	
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public long getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(long orderNum) {
		this.orderNum = orderNum;
	}
	public long getCustomFormNum() {
		return customFormNum;
	}
	public void setCustomFormNum(long customFormNum) {
		this.customFormNum = customFormNum;
	}
	public long getCollectNum() {
		return collectNum;
	}
	public void setCollectNum(long collectNum) {
		this.collectNum = collectNum;
	}
	public long getFansNum() {
		return fansNum;
	}
	public void setFansNum(long fansNum) {
		this.fansNum = fansNum;
	}
	public BigDecimal getFansSaleAmount() {
		return fansSaleAmount;
	}
	public void setFansSaleAmount(BigDecimal fansSaleAmount) {
		this.fansSaleAmount = fansSaleAmount;
	}
}
