package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptServiceCenDAO {
	public static final Log LOG = new Log(WptServiceCen.class);
	public static class Ins extends IduIns<Ins, WptServiceCen> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WptServiceCen> {
		@Override
		public void before() {
			super.before();
			WptServiceCen model = WptServiceCen.load(WptServiceCen.class, getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptServiceCen.T.ACCOUNT);
			setB(model);
		}
	}
}
