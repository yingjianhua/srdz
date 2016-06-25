package irille.wx.wp;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.wx.wp.WpArtShowDAO.Del;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WpArtDAO {
	public static final Log LOG = new Log(WpArt.class);

	public static class Ins extends IduIns<Ins, WpArt> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			getB().setCreateBy(getUser().getPkey());
			getB().setCreateTime(Env.getSystemTime());
		}
	}

	public static class Upd extends IduUpd<Upd, WpArt> {
		@Override
		public void before() {
			super.before();
			WpArt model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WpArt.T.ACCOUNT,WpArt.T.CREATE_BY,WpArt.T.CREATE_TIME);
			model.setUpdateBy(getUser().getPkey());
			model.setUpdateTime(Env.getSystemTime());
			setB(model);
		}
	}
	
	public static class Del extends IduDel<Del, WpArt> {
		@Override
		public void valid() {
			super.valid();
			haveBeUsed(WpArtBanner.class, WpArtBanner.T.ART, getB().getPkey());
		}
	}
}
