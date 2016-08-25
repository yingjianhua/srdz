package irille.wpt.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.ComboLine;
@Controller
@Scope("prototype")
public class ComboLineAction extends AbstractCRUDAction<ComboLine> {

}
