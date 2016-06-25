package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptFeedBackDAO {
	public static final Log LOG = new Log(WptFeedBack.class);
	public static class Ins extends IduIns<Ins, WptFeedBack> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WptFeedBack> {
		@Override
		public void before() {
			super.before();
			WptFeedBack model = WptService.load(WptFeedBack.class, getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptFeedBack.T.ACCOUNT);
			setB(model);
		}
	}
}
