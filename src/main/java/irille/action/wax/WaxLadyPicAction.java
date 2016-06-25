package irille.action.wax;

import irille.action.ActionWx;
import irille.wx.wax.WaxBnft;
import irille.wx.wax.WaxLadyPic;

public class WaxLadyPicAction extends ActionWx<WaxLadyPic,WaxLadyPicAction> {
	public WaxLadyPic getBean() {
		return _bean;
	}

	public void setBean(WaxLadyPic bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WaxLadyPic.class;
	}
}
