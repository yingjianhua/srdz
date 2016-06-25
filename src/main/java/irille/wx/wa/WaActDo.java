package irille.wx.wa;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldDec;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.wa.WaActGet.T;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

import java.util.Date;

public class WaActDo extends BeanInt<WaActDo> {
  private static final Log LOG = new Log(WaActDo.class);
  public static final Tb TB = new Tb(WaActDo.class, "抽奖记录").setAutoIncrement().addActList();

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()), // 主键
    ACT(WaAct.fldOutKey()), // 活动对象
    WX_USER(WxUser.fldOutKey()),
    TOTAL(SYS.INT,"总数"),//总数
    DO_TIME(SYS.DATE_TIME,"抽奖时间"),//抽奖时间
    ACCOUNT(WxAccount.fldOutKey()),//公众账号
    ROW_VERSION(SYS.ROW_VERSION),
    // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
    private Fld _fld;
    public static final Index IDX_ACT_WX_USER = TB.addIndex("actWxUser", true,T.ACT,T.WX_USER);
    private T(Class clazz, String name, boolean... isnull) {
      _fld = TB.addOutKey(clazz, this, name, isnull);
    }

    private T(IEnumFld fld, boolean... isnull) {
      this(fld, null, isnull);
    }

    private T(IEnumFld fld, String name, boolean... null1) {
      _fld = TB.add(fld, this, name, null1);
    }

    private T(IEnumFld fld, String name, int strLen) {
      _fld = TB.add(fld, this, name, strLen);
    }

    private T(Fld fld) {
      _fld = TB.add(fld, this);
    }

    public Fld getFld() {
      return _fld;
    }
  }

  static { // 在此可以加一些对FLD进行特殊设定的代码
    T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
  }

  // @formatter:on
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _act;	// 抽奖活动 <表主键:WaAct>  INT
  private Integer _wxUser;	// 关注用户 <表主键:WxUser>  INT
  private Integer _total;	// 总数  INT
  private Date _doTime;	// 抽奖时间  TIME
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActDo init(){
		super.init();
    _act=null;	// 抽奖活动 <表主键:WaAct>  INT
    _wxUser=null;	// 关注用户 <表主键:WxUser>  INT
    _total=0;	// 总数  INT
    _doTime=Env.getTranBeginTime();	// 抽奖时间  TIME
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WaActDo loadUniqueActWxUser(boolean lockFlag,Integer act,Integer wxUser) {
    return (WaActDo)loadUnique(T.IDX_ACT_WX_USER,lockFlag,act,wxUser);
  }
  public static WaActDo chkUniqueActWxUser(boolean lockFlag,Integer act,Integer wxUser) {
    return (WaActDo)chkUnique(T.IDX_ACT_WX_USER,lockFlag,act,wxUser);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getAct(){
    return _act;
  }
  public void setAct(Integer act){
    _act=act;
  }
  public WaAct gtAct(){
    if(getAct()==null)
      return null;
    return (WaAct)get(WaAct.class,getAct());
  }
  public void stAct(WaAct act){
    if(act==null)
      setAct(null);
    else
      setAct(act.getPkey());
  }
  public Integer getWxUser(){
    return _wxUser;
  }
  public void setWxUser(Integer wxUser){
    _wxUser=wxUser;
  }
  public WxUser gtWxUser(){
    if(getWxUser()==null)
      return null;
    return (WxUser)get(WxUser.class,getWxUser());
  }
  public void stWxUser(WxUser wxUser){
    if(wxUser==null)
      setWxUser(null);
    else
      setWxUser(wxUser.getPkey());
  }
  public Integer getTotal(){
    return _total;
  }
  public void setTotal(Integer total){
    _total=total;
  }
  public Date getDoTime(){
    return _doTime;
  }
  public void setDoTime(Date doTime){
    _doTime=doTime;
  }
  public Integer getAccount(){
    return _account;
  }
  public void setAccount(Integer account){
    _account=account;
  }
  public WxAccount gtAccount(){
    if(getAccount()==null)
      return null;
    return (WxAccount)get(WxAccount.class,getAccount());
  }
  public void stAccount(WxAccount account){
    if(account==null)
      setAccount(null);
    else
      setAccount(account.getPkey());
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  // <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
