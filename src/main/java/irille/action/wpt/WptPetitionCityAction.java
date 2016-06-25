package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptPetitionCity;

public class WptPetitionCityAction extends ActionWx<WptPetitionCity,WptPetitionCityAction> {
	public WptPetitionCity getBean() {
		return _bean;
	}

	public void setBean(WptPetitionCity bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptPetitionCity.class;
	}
}
