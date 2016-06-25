package irille.action.wpt;

import irille.action.ActionBase;
import irille.wx.wpt.WptCase;

public class WptCaseAction extends ActionBase<WptCase> {

	public WptCase getBean() {
		return _bean;
	}

	public void setBean(WptCase bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptCase.class;
	}
}
