package irille.wx.wa;

import java.math.BigDecimal;
import java.util.Date;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.wa.Wa.OActDrawType;
import irille.wx.wa.Wa.OActStatus;
import irille.wx.wx.WxAccount;

public class WaAct extends BeanInt<WaAct> implements IExtName{
  private static final Log LOG = new Log(WaAct.class);
  public static final Tb TB = new Tb(WaAct.class, "抽奖活动").setAutoIncrement().addActIUDL().addActOpt("doOpen", "发布")
      .addActOpt("doClose", "关闭").addActOpt("doWinning", "查看中奖纪录").addActOpt("doDraw", "查看抽奖记录");

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    NAME(SYS.NAME__100,"活动名称"),//活动名称
    TITLE(SYS.STR__200_NULL,"活动描述"),//活动描述
    DO_COUNT(SYS.INT,"抽奖次数"),
    START_TIME(SYS.DATE_TIME,"开始时间"),//开始时间
    END_TIME(SYS.DATE_TIME,"结束时间"),//结束时间
    RATE(SYS.RATE, "中奖概率(%)"),
    TYPE(TB.crt(Wa.OActDrawType.DEFAULT)),//活动类型
    STATUS(TB.crt(Wa.OActStatus.DEFAULT)),//活动状态
    ACCOUNT(WxAccount.fldOutKey()),//公众账号
    ROW_VERSION(SYS.ROW_VERSION),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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
      _fld = TB.add(fld,this);
    }
    public Fld getFld() {
      return _fld;
    }
  }
  
  static { // 在此可以加一些对FLD进行特殊设定的代码
    T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
  }
  //@formatter:on
  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }
  @Override
  public String getExtName() {
    return getName();
  }
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 活动名称  STR(100)
  private String _title;	// 活动描述  STR(200)<null>
  private Integer _doCount;	// 抽奖次数  INT
  private Date _startTime;	// 开始时间  TIME
  private Date _endTime;	// 结束时间  TIME
  private BigDecimal _rate;	// 中奖概率(%)  DEC(8,4)
  private Byte _type;	// 抽奖类型 <OActDrawType>  BYTE
	// TURNPLATE:1,大转盘
	// SCRATCH:2,刮刮乐
  private Byte _status;	// 活动状态 <OActStatus>  BYTE
	// NOTPUBLISH:1,未发布
	// PUBLISH:2,发布
	// CLOSE:3,关闭
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaAct init(){
		super.init();
    _name=null;	// 活动名称  STR(100)
    _title=null;	// 活动描述  STR(200)
    _doCount=0;	// 抽奖次数  INT
    _startTime=Env.getTranBeginTime();	// 开始时间  TIME
    _endTime=Env.getTranBeginTime();	// 结束时间  TIME
    _rate=ZERO;	// 中奖概率(%)  DEC(8,4)
    _type=OActDrawType.DEFAULT.getLine().getKey();	// 抽奖类型 <OActDrawType>  BYTE
    _status=OActStatus.DEFAULT.getLine().getKey();	// 活动状态 <OActStatus>  BYTE
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
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public Integer getDoCount(){
    return _doCount;
  }
  public void setDoCount(Integer doCount){
    _doCount=doCount;
  }
  public Date getStartTime(){
    return _startTime;
  }
  public void setStartTime(Date startTime){
    _startTime=startTime;
  }
  public Date getEndTime(){
    return _endTime;
  }
  public void setEndTime(Date endTime){
    _endTime=endTime;
  }
  public BigDecimal getRate(){
    return _rate;
  }
  public void setRate(BigDecimal rate){
    _rate=rate;
  }
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public OActDrawType gtType(){
    return (OActDrawType)(OActDrawType.TURNPLATE.getLine().get(_type));
  }
  public void stType(OActDrawType type){
    _type=type.getLine().getKey();
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OActStatus gtStatus(){
    return (OActStatus)(OActStatus.NOTPUBLISH.getLine().get(_status));
  }
  public void stStatus(OActStatus status){
    _status=status.getLine().getKey();
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

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
