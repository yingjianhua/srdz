package irille.wx.wa;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.wa.Wa.OActSendStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

import java.util.Date;

public class WaActGet extends BeanInt<WaActGet>{
  private static final Log LOG = new Log(WaActGet.class);
  public static final Tb TB = new Tb(WaActGet.class, "中奖记录").setAutoIncrement().addActUpd();

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    ACT(WaAct.fldOutKey()),//活动对象
    REC_NAME(SYS.STR__100_NULL,"收货人名称"), //收货人名称 NULL
    REC_ADDR(SYS.STR__200_NULL,"收货人地址"), //收货人地址 NULL
    REC_MOBILE(SYS.STR__20_NULL,"手机号码"),   //手机号码 NULL
    ACT_ITEM(WaActItem.fldOutKey()), // 奖项对象
    ACT_PRIZE(WaActPrize.fldOutKey()), // 奖品对象
    ACT_TIME(SYS.DATE_TIME,"抽奖时间"), //抽奖时间
    WX_USER(WxUser.fldOutKey()),  //关注用户
    WX_CARD(SYS.STR__200_NULL,"身份证图片"),  //身份证图片 NULL
    ACT_KEY(SYS.STR__200_NULL,"兑奖KEY"),      //兑奖KEY NULL
    //TYPE,        //中奖类型(前台抽奖、后台产生)，作用未明
    SEND_STATUS(TB.crt(Wa.OActSendStatus.DEFAULT)),   //发货状态(未发货、已发货)
    SEND_NAME(SYS.NAME__100_NULL,"快递名称"),  //快递名称 NULL
    SEND_NUM(SYS.NAME__100_NULL,"快递编号"),   //快递编号 NULL
    SEND_TIME(SYS.TIME__NULL,"快递时间"),      //快递时间 NULL
    ACCOUNT(WxAccount.fldOutKey()), //公众号
    ROW_VERSION(SYS.ROW_VERSION),
    
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
    public static final Index IDX_ACT_WX_USER = TB.addIndex("actWxUser", true,T.ACT,T.WX_USER);
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
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _act;	// 抽奖活动 <表主键:WaAct>  INT
  private String _recName;	// 收货人名称  STR(100)<null>
  private String _recAddr;	// 收货人地址  STR(200)<null>
  private String _recMobile;	// 手机号码  STR(20)<null>
  private Integer _actItem;	// 奖项设置 <表主键:WaActItem>  INT
  private Integer _actPrize;	// 奖品设置 <表主键:WaActPrize>  INT
  private Date _actTime;	// 抽奖时间  TIME
  private Integer _wxUser;	// 关注用户 <表主键:WxUser>  INT
  private String _wxCard;	// 身份证图片  STR(200)<null>
  private String _actKey;	// 兑奖KEY  STR(200)<null>
  private Byte _sendStatus;	// 发货状态 <OActSendStatus>  BYTE
	// NOTDELIVERY:1,未发货
	// DELIVERY:2,已发货
  private String _sendName;	// 快递名称  STR(100)<null>
  private String _sendNum;	// 快递编号  STR(100)<null>
  private Date _sendTime;	// 快递时间  TIME<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActGet init(){
		super.init();
    _act=null;	// 抽奖活动 <表主键:WaAct>  INT
    _recName=null;	// 收货人名称  STR(100)
    _recAddr=null;	// 收货人地址  STR(200)
    _recMobile=null;	// 手机号码  STR(20)
    _actItem=null;	// 奖项设置 <表主键:WaActItem>  INT
    _actPrize=null;	// 奖品设置 <表主键:WaActPrize>  INT
    _actTime=Env.getTranBeginTime();	// 抽奖时间  TIME
    _wxUser=null;	// 关注用户 <表主键:WxUser>  INT
    _wxCard=null;	// 身份证图片  STR(200)
    _actKey=null;	// 兑奖KEY  STR(200)
    _sendStatus=OActSendStatus.DEFAULT.getLine().getKey();	// 发货状态 <OActSendStatus>  BYTE
    _sendName=null;	// 快递名称  STR(100)
    _sendNum=null;	// 快递编号  STR(100)
    _sendTime=null;	// 快递时间  TIME
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WaActGet loadUniqueActWxUser(boolean lockFlag,Integer act,Integer wxUser) {
    return (WaActGet)loadUnique(T.IDX_ACT_WX_USER,lockFlag,act,wxUser);
  }
  public static WaActGet chkUniqueActWxUser(boolean lockFlag,Integer act,Integer wxUser) {
    return (WaActGet)chkUnique(T.IDX_ACT_WX_USER,lockFlag,act,wxUser);
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
  public String getRecName(){
    return _recName;
  }
  public void setRecName(String recName){
    _recName=recName;
  }
  public String getRecAddr(){
    return _recAddr;
  }
  public void setRecAddr(String recAddr){
    _recAddr=recAddr;
  }
  public String getRecMobile(){
    return _recMobile;
  }
  public void setRecMobile(String recMobile){
    _recMobile=recMobile;
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
  public Date getActTime(){
    return _actTime;
  }
  public void setActTime(Date actTime){
    _actTime=actTime;
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
  public String getWxCard(){
    return _wxCard;
  }
  public void setWxCard(String wxCard){
    _wxCard=wxCard;
  }
  public String getActKey(){
    return _actKey;
  }
  public void setActKey(String actKey){
    _actKey=actKey;
  }
  public Byte getSendStatus(){
    return _sendStatus;
  }
  public void setSendStatus(Byte sendStatus){
    _sendStatus=sendStatus;
  }
  public OActSendStatus gtSendStatus(){
    return (OActSendStatus)(OActSendStatus.NOTDELIVERY.getLine().get(_sendStatus));
  }
  public void stSendStatus(OActSendStatus sendStatus){
    _sendStatus=sendStatus.getLine().getKey();
  }
  public String getSendName(){
    return _sendName;
  }
  public void setSendName(String sendName){
    _sendName=sendName;
  }
  public String getSendNum(){
    return _sendNum;
  }
  public void setSendNum(String sendNum){
    _sendNum=sendNum;
  }
  public Date getSendTime(){
    return _sendTime;
  }
  public void setSendTime(Date sendTime){
    _sendTime=sendTime;
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
