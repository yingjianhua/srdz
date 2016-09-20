package irille.wpt.actions.controller.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.Member;
import irille.wpt.service.impl.MemberService;
@Controller
@Scope("prototype")
public class ShowQrcodeAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;

	private Member member;
	
	@Resource
	private MemberService memberService;

	@Override
	public String execute() throws Exception {
		member = chkMember();
		memberService.checkQrcode(member);
		setResult("me/qrcode.jsp");
		return TRENDS;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
}
