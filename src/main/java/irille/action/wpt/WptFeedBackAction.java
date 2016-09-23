package irille.action.wpt;

import irille.action.ActionWx;
import irille.wx.wpt.WptFeedBack;

public class WptFeedBackAction extends ActionWx<WptFeedBack,WptFeedBackAction> {
	private static final long serialVersionUID = 1L;
	private WptFeedBack _feedBack;

	public WptFeedBack getFeedBack() {
		return _feedBack;
	}

	public void setFeedBack(WptFeedBack feedBack) {
		_feedBack = feedBack;
	}

	public WptFeedBack getBean() {
		return _bean;
	}

	public void setBean(WptFeedBack bean) {
		this._bean = bean;
	}

	@Override
	public Class<WptFeedBack> beanClazz() {
		return WptFeedBack.class;
	}

}
