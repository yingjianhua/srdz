package irille.wx.wa;

import irille.pub.Log;
import irille.pub.bean.BeanBase;
import irille.wx.wa.Wa.OActSendStatus;
import irille.wx.wx.WxUser;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WaActGetDAO {
  public static final Log LOG = new Log(WaActGetDAO.class);
  
  public static void add(WaActSet actSet, WxUser wxUser) {
    WaActGet actGet = new WaActGet();
    actGet.setAct(actSet.getAct());
    actGet.setAccount(actSet.getAccount());
    actGet.setActItem(actSet.getActItem());
    actGet.setActPrize(actSet.getActPrize());
    actGet.setActTime(new Date());
    actGet.setActKey(UUID.randomUUID().toString());
    actGet.setWxUser(wxUser.getPkey());
    actGet.stSendStatus(OActSendStatus.DEFAULT);
    actGet.ins();
  }
  public static void update(WaActGet actGet) {
    WaActGet waActGet = WaActGet.load(WaActGet.class, actGet.getPkey());
    waActGet.setRecMobile(actGet.getRecMobile());
    waActGet.setRecName(actGet.getRecName());
    waActGet.setRecAddr(actGet.getRecAddr());
    waActGet.upd();
  }
  /**
   * @param act
   * @param wxUser
   * @return 是否填写资料
   */
  public static boolean validData(WaAct act,WxUser wxUser) {
    WaActGet actGet = WaActGet.chkUniqueActWxUser(false, act.getPkey(), wxUser.getPkey());
    if(actGet.getRecMobile()!=null&&actGet.getRecName()!=null&&actGet.getRecAddr()!=null){
      return true;
    }else{      
      return false;
    }
  }
  public static boolean validWinning(WaAct act,WxUser wxUser) {
    WaActGet actGet = WaActGet.chkUniqueActWxUser(false, act.getPkey(), wxUser.getPkey());
    if(actGet!=null){
      return true;
    }else{      
      return false;
    }
  }
  public static boolean validChance(WaAct act,List<WaActSet> actSet,int num) {
    String sql = WaActGet.T.ACT.getFld().getCodeSqlField() + " =? and " + 
        WaActGet.T.ACT_ITEM.getFld().getCodeSqlField() + " =? ";
    int count = actSet.get(num-1).getActNum();
    List<WaActGet> actGets = BeanBase.list(WaActGet.class, sql, false, act.getPkey(),actSet.get(num-1).getActItem());
    if(actGets.size()>=count){
      return true;
    }else{  
      return false;
    }
  }
  
}
