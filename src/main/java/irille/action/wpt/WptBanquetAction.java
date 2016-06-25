package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptBanquet;

public class WptBanquetAction extends ActionWx<WptBanquet,WptBanquetAction> {
	public WptBanquet getBean() {
		return _bean;
	}

	public void setBean(WptBanquet bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptBanquet.class;
	}
}
