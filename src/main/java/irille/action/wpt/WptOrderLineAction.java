package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptOrderLine;

public class WptOrderLineAction extends ActionWx<WptOrderLine,WptOrderLineAction> {
	public WptOrderLine getBean() {
		return _bean;
	}

	public void setBean(WptOrderLine bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptOrderLine.class;
	}
}
