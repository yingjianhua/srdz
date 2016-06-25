package irille.wx.wa;

import java.util.Date;

import irille.pub.Log;
import irille.wx.wx.WxUser;

public class WaActDoDAO {
  public static final Log LOG = new Log(WaActDoDAO.class);

  public static void add(WaAct act, WxUser wxUser) {
    WaAct waAct = WaAct.load(WaAct.class, act.getPkey());
    WaActDo waActDo = new WaActDo();
    waActDo.setAct(waAct.getPkey());
    waActDo.setAccount(waAct.getAccount());
    waActDo.setDoTime(new Date());
    waActDo.setTotal(1);
    waActDo.setWxUser(wxUser.getPkey());
    waActDo.ins();
  }
  public static void upDate(WaActDo actDo) {
    actDo.setDoTime(new Date());
    actDo.setTotal(actDo.getTotal()+1);
    actDo.upd();
  }
  /**
   * @param act
   * @param wxUser
   * @return 是否超出抽奖次数
   */
  public static boolean validCount(int pkey, WaActDo actDo) {
    WaAct waAct = WaAct.load(WaAct.class, pkey);
    if (actDo.getTotal() >= waAct.getDoCount()) {
      return true;
    } else {
      return false;
    }
  }
}
