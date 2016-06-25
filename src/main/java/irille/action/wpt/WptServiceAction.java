package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptService;

public class WptServiceAction extends ActionWx<WptService,WptServiceAction> {
	public WptService getBean() {
		return _bean;
	}

	public void setBean(WptService bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptService.class;
	}
}
