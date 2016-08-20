package irille.wpt.actions.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.AbstractControllAction;

@Controller
@Scope("prototype")
public class ShowCashAction extends AbstractControllAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -135720894376631856L;

	@Override
	public String execute() throws Exception {
		setResult("me/cashList.jsp");
		return TRENDS;
	}
}
