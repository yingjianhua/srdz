package irille.wx.wa;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaActVoteBannerDAO {
	  public static final Log LOG = new Log(WaActVoteBannerDAO.class);

	  public static class Ins extends IduIns<Ins, WaActVoteBanner> {
	    @Override
	    public void before() {
	      super.before();
	      WxAccount account = WxAccountDAO.getByUser(getUser());
	      getB().setAccount(account.getPkey());
	    }

	  }

	  public static class Upd extends IduUpd<Upd, WaActVoteBanner> {
	    @Override
	    public void before() {
	      super.before();
	      WaActVoteBanner dbBean = load(getB().getPkey());
	  	  PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaActVoteBanner.T.ACCOUNT);   
	      setB(dbBean);
	    }

	  }

	  public static class Del extends IduDel<Del, WaActVoteBanner> {
	   
	  }
}
