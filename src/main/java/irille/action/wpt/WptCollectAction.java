package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptCollect;

public class WptCollectAction extends ActionWx<WptCollect,WptCollectAction> {
	public WptCollect getBean() {
		return _bean;
	}

	public void setBean(WptCollect bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptCollect.class;
	}
}
