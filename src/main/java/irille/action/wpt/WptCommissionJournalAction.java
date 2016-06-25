package irille.action.wpt;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.wx.wpt.WptCommissionJournal;

public class WptCommissionJournalAction extends ActionWx<WptCommissionJournal,WptCommissionJournalAction> {
	public static final Log LOG = new Log(WptCommissionJournalAction.class);

	public WptCommissionJournal getBean() {
		return _bean;
	}

	public void setBean(WptCommissionJournal bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptCommissionJournal.class;
	}
}
