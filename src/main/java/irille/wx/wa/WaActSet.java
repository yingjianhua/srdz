package irille.wx.wa;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.wx.WxAccount;

public class WaActSet extends BeanInt<WaActSet> {
  private static final Log LOG = new Log(WaActSet.class);
  public static final Tb TB = new Tb(WaActSet.class, "活动配置信息").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()), // 主键
    ACT(WaAct.fldOutKey()), // 活动对象
    ACT_ITEM(WaActItem.fldOutKey()), // 奖项对象
    ACT_PRIZE(WaActPrize.fldOutKey()), // 奖品对象
    ACT_NUM(SYS.INT,"奖品数量"), // 奖品数量
    ACCOUNT(WxAccount.fldOutKey()),//公众账号
    ROW_VERSION(SYS.ROW_VERSION),
    // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
    private Fld _fld;

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
  public WaActItem getActItems(){
    if(getActItem()==null)
      return null;
    return (WaActItem)get(WaActItem.class,getActItem());
  }
  public void setActItems(WaActItem actItem){
    if(actItem==null)
      setActItem(null);
    else
      setActItem(actItem.getPkey());
  }
  public WaActPrize getActPrizes(){
    if(getActPrize()==null)
      return null;
    return (WaActPrize)get(WaActPrize.class,getActPrize());
  }
  public void setActPrizes(WaActPrize actPrize){
    if(actPrize==null)
      setActPrize(null);
    else
      setActPrize(actPrize.getPkey());
  }
  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _act;	// 抽奖活动 <表主键:WaAct>  INT
  private Integer _actItem;	// 奖项设置 <表主键:WaActItem>  INT
  private Integer _actPrize;	// 奖品设置 <表主键:WaActPrize>  INT
  private Integer _actNum;	// 奖品数量  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActSet init(){
		super.init();
    _act=null;	// 抽奖活动 <表主键:WaAct>  INT
    _actItem=null;	// 奖项设置 <表主键:WaActItem>  INT
    _actPrize=null;	// 奖品设置 <表主键:WaActPrize>  INT
    _actNum=0;	// 奖品数量  INT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
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
  public Integer getActItem(){
    return _actItem;
  }
  public void setActItem(Integer actItem){
    _actItem=actItem;
  }
  public WaActItem gtActItem(){
    if(getActItem()==null)
      return null;
    return (WaActItem)get(WaActItem.class,getActItem());
  }
  public void stActItem(WaActItem actItem){
    if(actItem==null)
      setActItem(null);
    else
      setActItem(actItem.getPkey());
  }
  public Integer getActPrize(){
    return _actPrize;
  }
  public void setActPrize(Integer actPrize){
    _actPrize=actPrize;
  }
  public WaActPrize gtActPrize(){
    if(getActPrize()==null)
      return null;
    return (WaActPrize)get(WaActPrize.class,getActPrize());
  }
  public void stActPrize(WaActPrize actPrize){
    if(actPrize==null)
      setActPrize(null);
    else
      setActPrize(actPrize.getPkey());
  }
  public Integer getActNum(){
    return _actNum;
  }
  public void setActNum(Integer actNum){
    _actNum=actNum;
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
