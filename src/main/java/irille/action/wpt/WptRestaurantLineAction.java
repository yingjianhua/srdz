package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptRestaurantLine;

public class WptRestaurantLineAction extends ActionWx<WptRestaurantLine,WptRestaurantLineAction> {
	public WptRestaurantLine getBean() {
		return _bean;
	}

	public void setBean(WptRestaurantLine bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptRestaurantLine.class;
	}
}
