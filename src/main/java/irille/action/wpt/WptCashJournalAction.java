package irille.action.wpt;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.wx.wpt.WptCashJournal;

public class WptCashJournalAction extends ActionWx<WptCashJournal,WptCashJournalAction> {
	public static final Log LOG = new Log(WptCashJournalAction.class);

	public WptCashJournal getBean() {
		return _bean;
	}

	public void setBean(WptCashJournal bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptCashJournal.class;
	}
}
