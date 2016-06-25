package irille.action.wp;

import irille.action.ActionBase;
import irille.action.ActionWx;
import irille.wx.js.JsMenuShare;
import irille.wx.wp.WpBsn;
import irille.wx.wx.WxAccount;

public class WpBsnAction extends ActionBase<WpBsn> {
	public WpBsn getBean() {
		return _bean;
	}

	public void setBean(WpBsn bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WpBsn.class;
	}
}
