package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptHotDAO {
	public static final Log LOG = new Log(WptHot.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("记录【{0}】已存在，不可操作！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static class Ins extends IduIns<Ins, WptHot> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			if(WptHot.chkUniqueRestaurantAccount(false, getB().getRestaurant(), account.getPkey()) != null)
				throw LOG.err(Msgs.uniqueErr, getB().gtRestaurant().getName());
		}
	}

	public static class Upd extends IduUpd<Upd, WptHot> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			WptHot bean = WptHot.chkUniqueRestaurantAccount(false, getB().getRestaurant(), account.getPkey());
			WptHot model = null;
			if (bean != null)
				if (bean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().gtRestaurant().getName());
				else
					model = bean;
			else
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptHot.T.ACCOUNT);
			setB(model);
		}
	}
}
