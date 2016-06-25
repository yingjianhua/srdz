package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptComboLine;

public class WptComboLineAction extends ActionWx<WptComboLine,WptComboLineAction> {
	public WptComboLine getBean() {
		return _bean;
	}

	public void setBean(WptComboLine bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptComboLine.class;
	}
}
