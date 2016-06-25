package irille.wx.wpt;

import irille.pub.Cn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.wx.wa.Wa;
import irille.wx.wa.WaActVote;
import irille.wx.wa.WaActVoteDAO.Edit;
import irille.wx.wa.WaActVoteDAO.Msgs;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptTopDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("记录【{0}】已存在，不可操作！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	}
	public static final Log LOG = new Log(WptTop.class);
	public static class Ins extends IduIns<Ins, WptTop> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			if(WptTop.chkUniqueTitle(false, getB().getTitle()) != null)
				throw LOG.err(Msgs.uniqueErr, getB().getTitle());
		}
	}

	public static class Upd extends IduUpd<Upd, WptTop> {
		@Override
		public void before() {
			super.before();
			WptTop bean = WptTop.chkUniqueTitle(false, getB().getTitle());
			WptTop model = null;
			if (bean != null)
				if (bean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getTitle());
				else
					model = bean;
			else
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptTop.T.ACCOUNT);
			setB(model);
		}
	}
	public static class Edit  extends IduOther<Edit, WptTop> {
		public static Cn CN = new Cn("edit", "编辑");
		
		@Override
		public void run() {
			super.run();
			
			WptTop model = load(getB().getPkey());
			model.setContent(getB().getContent());
			model.upd();
			setB(model);
		}
	}
}
