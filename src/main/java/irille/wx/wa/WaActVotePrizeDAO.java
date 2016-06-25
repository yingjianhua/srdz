package irille.wx.wa;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;

import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaActVotePrizeDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		negativeErr("奖品数量不能为负!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WaActVotePrizeDAO.class);

	  public static class Ins extends IduIns<Ins, WaActVotePrize> {
	    @Override
	    public void before() {
	      super.before();
	      WxAccount account = WxAccountDAO.getByUser(getUser());
	      getB().setAccount(account.getPkey());
	      if(getB().getAmount()<0) {
				throw LOG.err(Msgs.negativeErr);
			}
	    }

	  }

	  public static class Upd extends IduUpd<Upd, WaActVotePrize> {
	    @Override
	    public void before() {
	      super.before();
	      WaActVotePrize dbBean = load(getB().getPkey());
	      PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaActVotePrize.T.ACCOUNT);
	      setB(dbBean);
	    }

	  }

	  public static class Del extends IduDel<Del, WaActVotePrize> {
		  @Override
		public void before() {
			// TODO Auto-generated method stub
			super.before();
			haveBeUsed(WaActVotePrize.class, WaActVotePrize.T.PRIZEITEM.getFld().getCodeSqlField(), getB().getPkey());
		}
	  }
}
