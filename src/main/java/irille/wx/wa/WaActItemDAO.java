package irille.wx.wa;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaActItemDAO {
  public static final Log LOG = new Log(WaActItemDAO.class);

  public static class Ins extends IduIns<Ins, WaActItem> {
    @Override
    public void before() {
      super.before();
      WxAccount account = WxAccountDAO.getByUser(getUser());
      getB().setAccount(account.getPkey());
    }

  }

  public static class Upd extends IduUpd<Upd, WaActItem> {
    @Override
    public void before() {
      super.before();
      WaActItem dbBean = load(getB().getPkey());
      PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaActItem.T.ACCOUNT);
      setB(dbBean);
    }

  }

  public static class Del extends IduDel<Del, WaAct> {
    @Override
    public void valid() {
      super.valid();
    }
  }

}
