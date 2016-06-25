package irille.wx.wax;

import irille.pub.Cn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaxBnftEntryDAO {
	public static final Log LOG = new Log(WaxBnftEntryDAO.class);

	public static class Ins extends IduIns<Ins, WaxBnftEntry> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}

	}

	public static class Upd extends IduUpd<Upd, WaxBnftEntry> {
		@Override
		public void before() {
			super.before();
			WaxBnftEntry dbBean = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaxBnftEntry.T.ACCOUNT);
			setB(dbBean);
		}
	}

	public static class Del extends IduDel<Del, WaxBnftEntry> {

	}
}
