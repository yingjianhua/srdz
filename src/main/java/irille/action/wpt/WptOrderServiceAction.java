package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptOrderService;

public class WptOrderServiceAction extends ActionWx<WptOrderService,WptOrderServiceAction> {

	public WptOrderService getBean() {
		return _bean;
	}

	public void setBean(WptOrderService bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptOrderService.class;
	}
}
