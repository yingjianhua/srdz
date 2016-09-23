package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptWxTips;

public class WptWxTipsAction extends ActionWx<WptWxTips, WptWxTipsAction> {
	public WptWxTips getBean() {
		return _bean;
	}

	public void setBean(WptWxTips bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptWxTips.class;
	}
	
}
