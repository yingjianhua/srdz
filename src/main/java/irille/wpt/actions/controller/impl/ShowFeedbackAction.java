package irille.wpt.actions.controller.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
@Controller
@Scope("prototype")
public class ShowFeedbackAction extends AbstractControllAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2042702346845357933L;

	/**
	 * 意见反馈
	 */
	@Override
	public String execute() throws Exception {
		setResult("me/feedBack.jsp");
		return TRENDS;
	}
}
