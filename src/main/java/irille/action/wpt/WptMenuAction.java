package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptMenu;

public class WptMenuAction extends ActionWx<WptMenu,WptMenuAction> {
	public WptMenu getBean() {
		return _bean;
	}

	public void setBean(WptMenu bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptMenu.class;
	}
}
