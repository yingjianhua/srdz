package irille.wpt.actions.resource.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.CashJournal;
@Controller
@Scope("prototype")
public class CashJournalAction extends AbstractCRUDAction<CashJournal> {
	private static final long serialVersionUID = 1L;

}
