package irille.wx.wa;


import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaActConfigDAO {
	public static final Log LOG = new Log(WaActConfigDAO.class);

	public static class Ins extends IduIns<Ins, WaActConfig> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WaActConfig> {
		@Override
		public void before() {
			super.before();
			WaActConfig model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WaActConfig.T.ACCOUNT);
			setB(model);
		}
	}

	public static class Del extends IduDel<Del, WaActConfig> {
		@Override
		public void before() {
			super.before();
			haveBeUsed(WaActVote.class, WaActVote.T.VOTE_Config.getFld().getCodeSqlField(), getB().getPkey());
		}
	}
}
