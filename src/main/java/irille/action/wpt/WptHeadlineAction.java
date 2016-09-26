package irille.action.wpt;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.wx.wpt.WptHeadline;

public class WptHeadlineAction extends ActionWx<WptHeadline,WptHeadlineAction> {
	private static final long serialVersionUID = 1L;
	public static final Log LOG = new Log(WptRestaurantAction.class);

	public WptHeadline getBean() {
		return _bean;
	}

	public void setBean(WptHeadline bean) {
		this._bean = bean;
	}

	@Override
	public Class<WptHeadline> beanClazz() {
		return WptHeadline.class;
	}
	
}
