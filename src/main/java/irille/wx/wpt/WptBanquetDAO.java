package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptBanquetDAO {
	public static final Log LOG = new Log(WptBanquet.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("记录【{0}】已存在！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	}
	public static class Ins extends IduIns<Ins, WptBanquet> {
		@Override
		public void before() {
			super.before();
			if (WptBanquet.chkUniqueName(false, getB().getName()) != null)
				throw LOG.err(Msgs.uniqueErr, getB().getName());
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WptBanquet> {
		@Override
		public void before() {
			super.before();
			WptBanquet bean = WptBanquet.chkUniqueName(false, getB().getName());
			WptBanquet model = null;
			if (bean != null)
				if (bean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getName());
				else
					model = bean;
			else
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptBanquet.T.ACCOUNT);
			setB(model);
		}
	}
	public static class Del extends IduDel<Del, WptBanquet> {
		@Override
		public void valid() {
			super.valid();
			haveBeUsed(WptRestaurantLine.class, WptRestaurantLine.T.BANQUET, getB().getPkey());
		}
	}
}
