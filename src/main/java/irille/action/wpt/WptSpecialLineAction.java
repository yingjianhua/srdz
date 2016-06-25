package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptSpecialLine;

public class WptSpecialLineAction extends ActionWx<WptSpecialLine,WptSpecialLineAction> {
	public WptSpecialLine getBean() {
		return _bean;
	}

	public void setBean(WptSpecialLine bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptSpecialLine.class;
	}
}
