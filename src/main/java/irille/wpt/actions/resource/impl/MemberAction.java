package irille.wpt.actions.resource.impl;

import javax.annotation.Resource;

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

	public String becomeMember() {
		//TODO memberService.becomeMember(bean);
		return BEAN;
	}
}
