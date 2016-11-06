package irille.vote.actions.controller.impl;

import javax.inject.Singleton;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Singleton
public class VoteAction extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		return ActionSupport.SUCCESS;
	}

}
