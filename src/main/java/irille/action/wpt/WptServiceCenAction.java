package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptServiceCenter;

public class WptServiceCenAction extends ActionWx<WptServiceCenter, WptServiceCenAction> {
	private WptServiceCenter _serviceCen;

	public WptServiceCenter getServiceCen() {
		return _serviceCen;
	}

	public void setServiceCen(WptServiceCenter serviceCen) {
		_serviceCen = serviceCen;
	}

	public WptServiceCenter getBean() {
		return _bean;
	}

	public void setBean(WptServiceCenter bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptServiceCenter.class;
	}

}
