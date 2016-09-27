package irille.wpt.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Member;
import irille.wpt.bean.WxTips;
@Controller
@Scope("prototype")
public class WxTipsAction extends AbstractCRUDAction<WxTips> {
	private static final long serialVersionUID = 1L;

	@Override
	public String ins() {
		Member member = memberService.load(bean.getMember().getPkey());
		bean.setMember(member);
		service.save(bean);
		return BEAN;
	}
}
