package irille.wx.wp;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wp.WpArtShowDAO.Del;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WpArtThemeDAO {
	public static final Log LOG = new Log(WpArtTheme.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("记录【{0}】已存在，不可操作！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on

	public static class Ins extends IduIns<Ins, WpArtTheme> {
		@Override
		public void before() {
			super.before();
			if (WpArtTheme.chkUniqueArtShowName(false, getB().getArtShow(), getB().getName()) != null)
				throw LOG.err(Msgs.uniqueErr, getB().getName());
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WpArtTheme> {
		@Override
		public void before() {
			super.before();
			WpArtTheme bean = WpArtTheme.chkUniqueArtShowName(false, getB().getArtShow(), getB().getName());
			WpArtTheme model = null;
			if (bean != null)
				if (bean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getName());
				else
					model = bean;
			else
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WpArtTheme.T.ACCOUNT);
			setB(model);
		}
	}
	public static class Del extends IduDel<Del, WpArtTheme> {
		@Override
		public void valid() {
			super.valid();
			haveBeUsed(WpArt.class, WpArt.T.THEME, getB().getPkey());
		}
	}
}
