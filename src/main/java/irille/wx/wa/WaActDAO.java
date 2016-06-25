package irille.wx.wa;

import java.util.List;

import irille.pub.Cn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpdLines;
import irille.wx.wa.Wa.OActDrawType;
import irille.wx.wa.Wa.OActStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaActDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		largeRotaryTableErr("大转盘活动奖项设置必须为三项!"),
		scratchErr("刮刮乐奖项设置不能超出三项!"),
		emptyErr("请填写完整!"),
		publishErr("【{0}】状态，不能操作!"),
		closeErr("【{0}】状态，不能操作!"),
		prizeErr("奖项设置必须为三项!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
  public static final Log LOG = new Log(WaActDAO.class);

  public static class Ins extends IduInsLines<Ins, WaAct, WaActSet> {
    @Override
    public void before() {
      super.before();
      WxAccount account = WxAccountDAO.getByUser(getUser());
      getB().setAccount(account.getPkey());
      getB().stStatus(OActStatus.NOTPUBLISH);
    } 

    @Override
    public void after() {
      super.after();
      if(getLines()==null)
      	return;
      if(getLines().size()!=3||getB().gtType() == OActDrawType.TURNPLATE)
      	throw LOG.err(Msgs.largeRotaryTableErr);
      if(getLines().size()>3||getB().gtType() == OActDrawType.SCRATCH)
        throw LOG.err(Msgs.scratchErr);
      for (WaActSet actSet : getLines()) {
        if(actSet==null)
          throw LOG.err(Msgs.emptyErr);          
        actSet.setAccount(getB().getAccount());
        actSet.setAct(actSet.getPkey());
      }
      insLine(getB(), getLines(), WaActSet.T.ACT.getFld());
    }
  }

  public static class Upd extends IduUpdLines<Upd, WaAct, WaActSet> {
    @Override
    public void before() {
      super.before();
      String where = Idu.sqlString("{0}=?", WaActDo.T.ACT);
      System.err.println(getB().getPkey());
      List<WaActDo> list = BeanBase.list(WaActDo.class, where, true, getB().getPkey());
      if(list.size()!=0)
          if(list.get(0).gtAct().gtStatus() == OActStatus.PUBLISH)
        throw LOG.err(Msgs.publishErr,OActStatus.PUBLISH.getLine().getName());
      WaAct dbBean = load(getB().getPkey());
      if(dbBean.gtStatus() == OActStatus.CLOSE)
        throw LOG.err(Msgs.closeErr,OActStatus.CLOSE.getLine().getName());
      PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaAct.T.ACCOUNT, WaAct.T.STATUS);
      setB(dbBean);
    }

    @Override
    public void after() {
      super.after();
      if(getLines().size()!=3)
        throw LOG.err(Msgs.prizeErr);
      for (WaActSet actSet : getLines()) {
        if(actSet==null)
          throw LOG.err(Msgs.emptyErr);          
        actSet.setAccount(getB().getAccount());
        actSet.setAct(actSet.getPkey());
      }
      updLine(getB(), getLines(), WaActSet.T.ACT.getFld());
    }
  }

  public static class Del extends IduDel<Del, WaAct> {
    @Override
    public void valid() {
      super.valid();
      delLine(getLines(WaActSet.T.ACT.getFld(), getB().getPkey()));
    }
  }
  /**
   * @return 获取全部大转盘活动
   */
  public static List<WaAct> getList(int pkey,int accountPkey) {
    String sql = WaAct.T.TYPE.getFld().getCodeSqlField() + " =? and "+
        WaAct.T.ACCOUNT.getFld().getCodeSqlField() +" =? and "+WaAct.T.STATUS.getFld().getCodeSqlField()+" =2 order by pkey desc";
    List<WaAct> acts = BeanBase.list(WaAct.class, sql, false,pkey,accountPkey);
    return acts;
  }
  public static class Publish extends IduOther<Publish, WaAct> {
    public static Cn CN = new Cn("doOpen", "发布");

    @Override
    public void run() {
      super.run();
      WaAct act = load(getB().getPkey());
      act.stStatus(OActStatus.PUBLISH);
      act.upd();
      setB(act);
    }
  }

  public static class Close extends IduOther<Close, WaAct> {
    public static Cn CN = new Cn("doClose", "关闭");

    @Override
    public void run() {
      super.run();
      WaAct act = load(getB().getPkey());
      act.stStatus(OActStatus.CLOSE);
      act.upd();
      setB(act);
    }
  }

}
