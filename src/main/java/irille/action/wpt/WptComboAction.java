package irille.action.wpt;

import irille.action.ActionForm;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptComboLine;

public class WptComboAction extends ActionForm<WptCombo,WptComboLine> {

	public WptCombo getBean() {
		return _bean;
	}

	public void setBean(WptCombo bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptCombo.class;
	}

}
