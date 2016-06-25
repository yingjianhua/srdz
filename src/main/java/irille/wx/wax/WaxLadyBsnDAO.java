package irille.wx.wax;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaxLadyBsnDAO {
	public static final Log LOG = new Log(WaxLadyBsnDAO.class);

	public static class Ins extends IduIns<Ins, WaxLadyBsn> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}

	}

	public static class Upd extends IduUpd<Upd, WaxLadyBsn> {
		@Override
		public void before() {
			super.before();
			WaxLadyBsn dbBean = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaxLadyBsn.T.ACCOUNT);
			setB(dbBean);
		}
	}

	public static class Del extends IduDel<Del, WaxBnft> {

	}
}
