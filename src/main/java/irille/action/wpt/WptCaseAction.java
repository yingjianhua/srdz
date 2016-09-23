package irille.action.wpt;

import irille.action.ActionBase;
import irille.wx.wpt.WptRestaurantCase;

public class WptCaseAction extends ActionBase<WptRestaurantCase> {

	public WptRestaurantCase getBean() {
		return _bean;
	}

	public void setBean(WptRestaurantCase bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptRestaurantCase.class;
	}
}
