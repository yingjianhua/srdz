package irille.wpt.actions;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class ShowCashAction extends AbstractWptAction {

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
