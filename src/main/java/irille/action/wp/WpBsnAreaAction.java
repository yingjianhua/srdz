package irille.action.wp;

import irille.action.ActionBase;
import irille.wx.wp.WpBsnArea;

public class WpBsnAreaAction extends ActionBase<WpBsnArea> {
	public WpBsnArea getBean() {
		return _bean;
	}

	public void setBean(WpBsnArea bean) {
		this._bean = bean;
	}
	
	@Override
	public Class beanClazz() {
		return WpBsnArea.class;
	}
}
