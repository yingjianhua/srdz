package irille.wx.wpt;

import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptRestaurantTemplateDAO {
	public static class Ins extends IduIns<Ins, WptRestaurantTemplate> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WptRestaurantTemplate> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}
	public static class Del extends IduDel<Del, WptRestaurantTemplate> {
		@Override
		public void run() {
		}
	}
}
