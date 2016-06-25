package irille.action.wax;

import irille.action.ActionWx;
import irille.wx.wax.WaxLadyBsn;

public class WaxLadyBsnAction extends ActionWx<WaxLadyBsn,WaxLadyBsnAction> {
	public WaxLadyBsn getBean() {
		return _bean;
	}

	public void setBean(WaxLadyBsn bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WaxLadyBsn.class;
	}
}
