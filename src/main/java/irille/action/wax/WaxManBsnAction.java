package irille.action.wax;

import irille.action.ActionWx;
import irille.wx.wax.WaxManBsn;

public class WaxManBsnAction extends ActionWx<WaxManBsn,WaxManBsnAction> {
	public WaxManBsn getBean() {
		return _bean;
	}

	public void setBean(WaxManBsn bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WaxManBsn.class;
	}
}
