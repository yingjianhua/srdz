package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptCustomService;

public class WptServiceAction extends ActionWx<WptCustomService,WptServiceAction> {
	public WptCustomService getBean() {
		return _bean;
	}

	public void setBean(WptCustomService bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptCustomService.class;
	}
}
