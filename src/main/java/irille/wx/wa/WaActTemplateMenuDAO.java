package irille.wx.wa;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaActTemplateMenuDAO {
	  public static final Log LOG = new Log(WaActTemplateMenuDAO.class);
	  public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
			unionErr("模板为【{0}】且页面类型为【{1}】的记录已存在，不能操作!")
			;
			private String _msg;
			private Msgs(String msg) { _msg=msg; }
			public String getMsg() {return _msg; }
		} //@formatter:on

	  public static class Ins extends IduIns<Ins, WaActTemplateMenu> {
	    @Override
	    public void before() {
	      super.before();
	      if (WaActTemplateMenu.chkUniqueTempPageType(false, getB().getTemp(), getB().getPageType()) != null)
	    	  throw LOG.err(Msgs.unionErr, getB().gtTemp().getName(), getB().gtPageType().getLine().getName());
	      WxAccount account = WxAccountDAO.getByUser(getUser());
	      getB().setAccount(account.getPkey());
	    }

	  }

	  public static class Upd extends IduUpd<Upd, WaActTemplateMenu> {
	    @Override
	    public void before() {
	      super.before();
	      if (WaActTemplateMenu.chkUniqueTempPageType(false, getB().getTemp(), getB().getPageType()) != null)
	    	  throw LOG.err(Msgs.unionErr, getB().gtTemp().getName(), getB().gtPageType().getLine().getName());
	      WaActTemplateMenu dbBean = load(getB().getPkey());
	      PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaActTemplateMenu.T.ACCOUNT);
	      setB(dbBean);
	    }

	  }

	  public static class Del extends IduDel<Del, WaActTemplateMenu> {
	   
	  }
}
