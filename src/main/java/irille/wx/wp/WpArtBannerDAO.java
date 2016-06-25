package irille.wx.wp;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WpArtBannerDAO {
	public static final Log LOG = new Log(WpArtBanner.class);

	public static class Ins extends IduIns<Ins, WpArtBanner> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			getB().setCreateBy(getUser().getPkey());
			getB().setCreateTime(Env.getSystemTime());
		}
	}

	public static class Upd extends IduUpd<Upd, WpArtBanner> {
		@Override
		public void before() {
			super.before();
			WpArtBanner model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WpArtBanner.T.ACCOUNT,WpArtBanner.T.CREATE_BY,WpArtBanner.T.CREATE_TIME);
			model.setUpdateBy(getUser().getPkey());
			model.setUpdateTime(Env.getSystemTime());
			setB(model);
		}
	}
}
