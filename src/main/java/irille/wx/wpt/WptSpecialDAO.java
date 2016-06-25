package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduUpdLines;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptSpecialDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		imgEmptyErr("餐厅不可为空!"),
		uniqueErr("记录【{0}】已存在，不可操作！"),
		repeat("餐厅不可重复")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WptSpecialDAO.class);

	public static class Ins extends IduInsLines<Ins, WptSpecial, WptSpecialLine> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			if(WptSpecial.chkUniqueTitle(false, getB().getTitle()) != null)
				throw LOG.err(Msgs.uniqueErr, getB().getTitle());
		}

		@Override
		public void after() {
			super.after();
			if (getLines()==null||getLines().size() ==0)
				throw LOG.err(Msgs.imgEmptyErr);
			for(int i=0;i<getLines().size();i++){
				int res1 = getLines().get(i).getRestaurant();
				for(int j=0;j<getLines().size();j++){
					int res2 = getLines().get(j).getRestaurant();
					if(i != j && res1 == res2)
						throw LOG.err(Msgs.repeat);
				}
				getLines().get(i).setAccount(account.getPkey());
			}
			insLine(getB(), getLines(), WptSpecialLine.T.SPECIAL.getFld());
		}
	}

	public static class Upd extends IduUpdLines<Upd, WptSpecial, WptSpecialLine> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			WptSpecial dbBean = WptSpecial.chkUniqueTitle(false, getB().getTitle());
			WptSpecial model = null;
			if (dbBean != null)
				if (dbBean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getTitle());
				else
					model = dbBean;
			else
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptSpecial.T.ACCOUNT);
			account = WxAccountDAO.getByUser(getUser());
			setB(model);
			if (getLines()==null||getLines().size() ==0)
				throw LOG.err(Msgs.imgEmptyErr);
			for (WptSpecialLine line : getLines()){
				line.setAccount(account.getPkey());
				WptSpecialLine tline = WptSpecialLine.chkUniqueSpecialRestaurant(false, getB().getPkey(), line.getRestaurant());
				if(tline != null)
					PropertyUtils.copyPropertiesWithout(line, tline, WptSpecialLine.T.SORT);
			}
			updLine(getB(), getLines(), WptSpecialLine.T.SPECIAL.getFld());
		}

	}

	public static class Del extends IduDel<Del, WptSpecial> {
		@Override
		public void after() {
			super.after();
			delLine(getLines(WptSpecialLine.T.SPECIAL.getFld(), getB().getPkey()));
		}
	}
}