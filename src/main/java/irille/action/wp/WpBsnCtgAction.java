package irille.action.wp;

import irille.action.ActionBase;
import irille.wx.wp.WpBsnCtg;

public class WpBsnCtgAction extends ActionBase<WpBsnCtg> {
	public WpBsnCtg getBean() {
		return _bean;
	}

	public void setBean(WpBsnCtg bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WpBsnCtg.class;
	}
}
