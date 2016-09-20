package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.CashJournal;
import irille.wpt.service.impl.CashJournalService;
@Controller
@Scope("prototype")
public class ListCashHistoryAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private CashJournalService cashJournalService;
	
	private List<CashJournal> cashJournals;
	
	@Override
	public String execute() throws Exception {
		cashJournals = cashJournalService.listByMember(chkMember().getPkey());
		setResult("me/cashHistoryList.jsp");
		return TRENDS;
	}

	public List<CashJournal> getCashJournals() {
		return cashJournals;
	}

	public void setCashJournals(List<CashJournal> cashJournals) {
		this.cashJournals = cashJournals;
	}

}
