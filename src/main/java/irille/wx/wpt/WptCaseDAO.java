package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptCaseDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		imgEmptyErr("区不可为空!"),
		uniqueErr("记录【{0}】已存在，不可操作！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WptCaseDAO.class);

	public static class Ins extends IduIns<Ins, WptCase> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}
	public static class Upd extends IduUpd<Upd, WptCase> {
		@Override
		public void before() {
			WptCase dbBean = WptCase.load(WptCase.class, getB().getPkey());
			getB().setAccount(dbBean.getAccount());
			super.before();
		}
	}

}