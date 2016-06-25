package irille.wx.wax;

import irille.pub.Cn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.wx.wa.Wa;
import irille.wx.wa.Wa.OActVoteStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaxBnftDAO {
	public static final Log LOG = new Log(WaxBnftDAO.class);

	public static class Ins extends IduIns<Ins, WaxBnft> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().stStatus(Wa.OActVoteStatus.INIT);
			getB().setAccount(account.getPkey());
		}

	}

	public static class Upd extends IduUpd<Upd, WaxBnft> {
		@Override
		public void before() {
			super.before();
			WaxBnft dbBean = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaxBnft.T.ACCOUNT,WaxBnft.T.STATUS);
			setB(dbBean);
		}
	}

	public static class Del extends IduDel<Del, WaxBnft> {

	}
	public static class close extends IduOther<close, WaxBnft> {
		public static Cn CN = new Cn("doClose", "关闭");

		@Override
		public void run() {
			super.run();
			WaxBnft bnft = load(getB().getPkey());
			bnft.stStatus(OActVoteStatus.CLOSE);
			bnft.upd();
			setB(bnft);
		}
	}
}
