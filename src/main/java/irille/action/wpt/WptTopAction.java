package irille.action.wpt;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.wx.wpt.WptTop;
import irille.wx.wpt.WptTopDAO.Edit;

public class WptTopAction extends ActionWx<WptTop,WptTopAction> {
	public static final Log LOG = new Log(WptRestaurantAction.class);

	public WptTop getBean() {
		return _bean;
	}

	public void setBean(WptTop bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptTop.class;
	}
	/**
	 * 编辑
	 * @throws Exception
	 */
	public void edit() throws Exception {
		Edit act = new Edit();
		act.setB(getBean());
		act.commit();
		writeSuccess(act.getB());
	}
}
