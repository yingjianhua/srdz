package irille.wx.wa;

import irille.pub.Log;
import irille.pub.bean.BeanBase;

import java.util.List;

public class WaActSetDAO {
  public static final Log LOG = new Log(WaActSetDAO.class);
  /**
   * @param act
   * @return 根据活动pkey得到全部活动相关信息
   */
  public static List<WaActSet> getList(WaAct act) {
    act = WaAct.load(WaAct.class, act.getPkey());
    String where = WaActSet.T.ACT.getFld().getCodeSqlField() + " =? and "
        + WaActSet.T.ACCOUNT.getFld().getCodeSqlField() + " =? ORDER BY "
        + WaActSet.T.ACT_ITEM.getFld().getCodeSqlField();
    List<WaActSet> actSets = BeanBase.list(WaActSet.class, where, false, act.getPkey(), act.getAccount());
    return actSets;
  }
  public static int getPrizeSum(List<WaActSet> actSets) {
    int sum = 0;
    for (WaActSet waActSet : actSets) {
      sum += waActSet.getActNum();
    }
    return sum;
  }
}
