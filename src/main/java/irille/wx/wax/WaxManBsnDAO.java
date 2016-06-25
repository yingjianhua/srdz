package irille.wx.wax;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaxManBsnDAO {
	public static final Log LOG = new Log(WaxManBsnDAO.class);

	public static class Ins extends IduIns<Ins, WaxManBsn> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}

	}

	public static class Upd extends IduUpd<Upd, WaxManBsn> {
		@Override
		public void before() {
			super.before();
			WaxManBsn dbBean = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaxManBsn.T.ACCOUNT);
			setB(dbBean);
		}
	}

	public static class Del extends IduDel<Del, WaxManBsn> {

	}
}
