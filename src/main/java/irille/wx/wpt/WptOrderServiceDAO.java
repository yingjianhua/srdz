package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptOrderServiceDAO {
	public static final Log LOG = new Log(WptOrderService.class);
	public static class Ins extends IduIns<Ins, WptOrderService> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WptOrderService> {
		@Override
		public void before() {
			super.before();
			WptOrderService model = WptOrderService.load(WptOrderService.class, getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptOrderService.T.ACCOUNT);
			setB(model);
		}
	}
}
