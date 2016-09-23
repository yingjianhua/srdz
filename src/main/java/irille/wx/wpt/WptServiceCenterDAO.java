package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptServiceCenterDAO {
	public static final Log LOG = new Log(WptServiceCenter.class);
	public static class Ins extends IduIns<Ins, WptServiceCenter> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WptServiceCenter> {
		@Override
		public void before() {
			super.before();
			WptServiceCenter model = WptServiceCenter.load(WptServiceCenter.class, getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptServiceCenter.T.ACCOUNT);
			setB(model);
		}
	}
}
