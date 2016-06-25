package irille.action.wax;

import irille.action.ActionWx;
import irille.wx.wax.WaxManPic;

public class WaxManPicAction extends ActionWx<WaxManPic,WaxManPicAction> {
	public WaxManPic getBean() {
		return _bean;
	}

	public void setBean(WaxManPic bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WaxManPic.class;
	}
}
