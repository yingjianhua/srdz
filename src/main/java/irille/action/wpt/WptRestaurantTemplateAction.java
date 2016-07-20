package irille.action.wpt;

import irille.action.ActionBase;
import irille.wx.wpt.WptRestaurantTemplate;

public class WptRestaurantTemplateAction extends ActionBase<WptRestaurantTemplate> {

	public WptRestaurantTemplate getBean() {
		return _bean;
	}

	public void setBean(WptRestaurantTemplate bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptRestaurantTemplate.class;
	}

}
