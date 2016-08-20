package irille.wpt.actions.impl;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.bean.Bean;
import irille.wpt.actions.AbstractControllAction;
import irille.wx.wpt.WptCashJournal;
@Controller
@Scope("prototype")
public class ListCashHistoryAction extends AbstractControllAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6167838262656665489L;
	private List<WptCashJournal> history;
	
	@Override
	public String execute() throws Exception {
		history = Bean.list(WptCashJournal.class, WptCashJournal.T.WXUSER+"=?", false, chkWxUser().getPkey());
		setResult("me/cashHistoryList.jsp");
		return TRENDS;
	}

	public List<WptCashJournal> getHistory() {
		return history;
	}
	public void setHistory(List<WptCashJournal> history) {
		this.history = history;
	}
}
