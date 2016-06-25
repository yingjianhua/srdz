package irille.wx.wp;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.wx.js.Js;
import irille.wx.js.JsMenuShare;
import irille.wx.wa.WaActVotePrize;
import irille.wx.wp.WpArtThemeDAO.Msgs;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WpArtShowDAO {
	public static final Log LOG = new Log(WpArtShow.class);

	public static class Ins extends IduIns<Ins, WpArtShow> {
		String url;
		JsMenuShare jsMenuShare;
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		@Override
		public void before() {
			super.before();
			jsMenuShare=new JsMenuShare().init();
			jsMenuShare.stEnabled(false);
			jsMenuShare.stAccount(WxAccountDAO.getByUser(getUser()));
			jsMenuShare.setType(Js.OJsMenuType.LINK.getLine().getKey());
			jsMenuShare.ins();
			getB().stJsMenushare(jsMenuShare);
			getB().setAccount(jsMenuShare.getAccount());
			getB().setCreateBy(getUser().getPkey());
			getB().setCreateTime(Env.getSystemTime());
		}

		@Override
		public void after() {
			super.after();
			jsMenuShare.setLink(getUrl() + "?pkey=" + getB().getPkey());
			jsMenuShare.upd();
		}
	}

	public static class Upd extends IduUpd<Upd, WpArtShow> {
		@Override
		public void before() {
			super.before();
			WpArtShow bean = WpArtShow.chkUniqueAccountTitle(false, getB().getAccount(), getB().getTitle());
			WpArtShow model = null;
			if (bean != null)
				if (bean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getTitle());
				else
					model = bean;
			else
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WpArtShow.T.JS_MENUSHARE, WpArtShow.T.ACCOUNT,WpArtShow.T.CREATE_BY,WpArtShow.T.CREATE_TIME);
			model.setUpdateBy(getUser().getPkey());
			model.setUpdateTime(Env.getSystemTime());
			setB(model);
		}
	}
	
	public static class Del extends IduDel<Del, WpArtShow> {
		@Override
		public void valid() {
			super.valid();
			haveBeUsed(WpArt.class, WpArt.T.ART_SHOW, getB().getPkey());
			haveBeUsed(WpArtTheme.class, WpArtTheme.T.ART_SHOW, getB().getPkey());
		}
	}
}
