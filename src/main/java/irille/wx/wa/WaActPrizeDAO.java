package irille.wx.wa;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaActPrizeDAO {
  public static final Log LOG = new Log(WaActPrizeDAO.class);

  public static class Ins extends IduIns<Ins, WaActPrize> {
    @Override
    public void before() {
      super.before();
      WxAccount account = WxAccountDAO.getByUser(getUser());
      getB().setAccount(account.getPkey());
    }

  }

  public static class Upd extends IduUpd<Upd, WaActPrize> {
    @Override
    public void before() {
      super.before();
      WaActPrize dbBean = load(getB().getPkey());
      PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaActPrize.T.ACCOUNT);
      setB(dbBean);
    }

  }

  public static class Del extends IduDel<Del, WaActPrize> {
    @Override
    public void valid() {
      super.valid();
    }
    
    @Override
    public void before() {
    	super.before();
    	haveBeUsed(WaActVotePrize.class, WaActVotePrize.T.PRIZE.getFld().getCodeSqlField(), getB().getPkey());
    }
  }

}
