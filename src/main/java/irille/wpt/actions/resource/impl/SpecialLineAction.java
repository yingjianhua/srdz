package irille.wpt.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.SpecialLine;
@Controller
@Scope("prototype")
public class SpecialLineAction extends AbstractCRUDAction<SpecialLine> {
	private static final long serialVersionUID = 1L;

}
