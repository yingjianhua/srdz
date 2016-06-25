package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptRestaurantBsn;

public class WptRestaurantBsnAction extends ActionWx<WptRestaurantBsn,WptRestaurantBsnAction> {
	public WptRestaurantBsn getBean() {
		return _bean;
	}

	public void setBean(WptRestaurantBsn bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptRestaurantBsn.class;
	}
}
