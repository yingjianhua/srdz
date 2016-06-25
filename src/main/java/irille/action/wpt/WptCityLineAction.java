package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptCityLine;

public class WptCityLineAction extends ActionWx<WptCityLine,WptCityLineAction> {
	public WptCityLine getBean() {
		return _bean;
	}

	public void setBean(WptCityLine bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptCityLine.class;
	}

}
