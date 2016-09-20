package irille.wpt.actions.controller.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;

@Controller
@Scope("prototype")
public class ShowCashAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception {
		setResult("me/cashList.jsp");
		return TRENDS;
	}
}
