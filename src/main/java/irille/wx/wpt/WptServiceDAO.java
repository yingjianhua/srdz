package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptServiceDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("记录【{0}】已存在，不可操作！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	}
	public static final Log LOG = new Log(WptCustomService.class);
	public static class Ins extends IduIns<Ins, WptCustomService> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			if(WptCustomService.chkUniqueName(false, getB().getName()) != null)
				throw LOG.err(Msgs.uniqueErr, getB().getName());
		}
	}

	public static class Upd extends IduUpd<Upd, WptCustomService> {
		@Override
		public void before() {
			super.before();
			WptCustomService bean = WptCustomService.chkUniqueName(false, getB().getName());
			WptCustomService model = null;
			if (bean != null)
				if (bean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getName());
				else
					model = bean;
			else
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptCustomService.T.ACCOUNT);
			setB(model);
		}
	}
}
