package irille.wx.wax;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduUpdLines;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaxLadyDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		imgEmptyErr("图片不可为空!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WaxLadyDAO.class);

	public static class Ins extends IduInsLines<Ins, WaxLady, WaxLadyPic> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}

		@Override
		public void after() {
			super.after();
			if (getLines()==null||getLines().size() ==0)
				throw LOG.err(Msgs.imgEmptyErr);
			for (WaxLadyPic line : getLines())
				line.setAccount(account.getPkey());
			insLine(getB(), getLines(), WaxLadyPic.T.LADY.getFld());
		}
	}

	public static class Upd extends IduUpdLines<Upd, WaxLady, WaxLadyPic> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			WaxLady dbBean = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaxLady.T.ACCOUNT);
			account = WxAccountDAO.getByUser(getUser());
			setB(dbBean);
			for (WaxLadyPic line : getLines())
				line.setAccount(account.getPkey());
			updLine(getB(), getLines(), WaxLadyPic.T.LADY.getFld());
		}

		@Override
		public void after() {
			super.after();
			for (WaxLadyPic line : getLines())
				line.setAccount(account.getPkey());
			updLine(getB(), getLines(), WaxLadyPic.T.LADY.getFld());
		}
	}

	public static class Del extends IduDel<Del, WaxLady> {
		@Override
		public void after() {
			super.after();
			delLine(getLines(WaxLadyPic.T.LADY.getFld(), getB().getPkey()));
		}
	}
}