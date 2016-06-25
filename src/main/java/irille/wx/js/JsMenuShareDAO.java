package irille.wx.js;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduUpd;

public class JsMenuShareDAO {
	public static final Log LOG = new Log(JsMenuShareDAO.class);

	public static class Upd extends IduUpd<Upd, JsMenuShare> {
		@Override
		public void before() {
			super.before();
			JsMenuShare dbBean = load(getB().getPkey());
			if (getB().getAppMessage() == null)
				getB().stAppMessage(false);
			if (getB().getTimeLine() == null)
				getB().stTimeLine(false);
			if (getB().getQq() == null)
				getB().stQq(false);
			if (getB().getWeibo() == null)
				getB().stWeibo(false);
			if (getB().getQzone() == null)
				getB().stQzone(false);
			PropertyUtils.copyPropertiesWithout(dbBean,getB(),JsMenuShare.T.ACCOUNT, JsMenuShare.T.LINK, JsMenuShare.T.TYPE);
			setB(dbBean);
		}
	}
}
