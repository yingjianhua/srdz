package irille.wx.wx;

import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanLong;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.Wx.OMassMsgType;

import java.util.Date;

public class WxMassMessage extends BeanLong<WxMassMessage> {
  private static final Log LOG = new Log(WxMassMessage.class);
  public static final Tb TB = new Tb(WxMassMessage.class, "群发功能").setAutoIncrement()
  		.addActList().addActDel().addActOpt("preUser", "预览用户管理");

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtLongPkey()),//主键
    MASSMSG_TYPE(TB.crt(Wx.OMassMsgType.DEFAULT)),
    TEMPLATE(Tb.crtLongTbObj("template", "消息模板")),//消息模板
    IS_TO_ALL(SYS.NY,"向全部用户"),
    USER_GROUP(SYS.NAME__100,"群发分组"),
    CREATED_TIME(SYS.CREATED_DATE_TIME,"发送时间"),
    COMPLETE_TIME(SYS.TIME__NULL,"完成时间"),
    REM(SYS.REM__200_NULL),
    MSG_ID(SYS.LONG,"消息ID"),
    MSG_DATA_ID(SYS.LONG, "数据ID", true),
    CMB_WX(CmbWx.fldFlds()),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
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
      _fld = TB.add(fld, this);
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
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Long _pkey;	// 编号  LONG
  private Byte _massmsgType;	// 群发消息类型 <OMassMsgType>  BYTE
	// ARTICLE:1,图文消息
	// TEXT:2,文本消息
	// VOICE:3,语音消息
	// IMAGE:4,图片消息
	// VIDEO:5,视频消息
	// CARD:6,卡券消息
  private Long _template;	// 消息模板  LONG
  private Byte _isToAll;	// 向全部用户 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _userGroup;	// 群发分组  STR(100)
  private Date _createdTime;	// 发送时间  TIME
  private Date _completeTime;	// 完成时间  TIME<null>
  private String _rem;	// 备注  STR(200)<null>
  private Long _msgId;	// 消息ID  LONG
  private Long _msgDataId;	// 数据ID  LONG<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxMassMessage init(){
		super.init();
    _massmsgType=OMassMsgType.DEFAULT.getLine().getKey();	// 群发消息类型 <OMassMsgType>  BYTE
    _template=null;	// 消息模板  LONG
    _isToAll=OYn.DEFAULT.getLine().getKey();	// 向全部用户 <OYn>  BYTE
    _userGroup=null;	// 群发分组  STR(100)
    _createdTime=Env.getTranBeginTime();	// 发送时间  TIME
    _completeTime=null;	// 完成时间  TIME
    _rem=null;	// 备注  STR(200)
    _msgId=(long)0;	// 消息ID  LONG
    _msgDataId=(long)0;	// 数据ID  LONG
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Long getPkey(){
    return _pkey;
  }
  public void setPkey(Long pkey){
    _pkey=pkey;
  }
  public Byte getMassmsgType(){
    return _massmsgType;
  }
  public void setMassmsgType(Byte massmsgType){
    _massmsgType=massmsgType;
  }
  public OMassMsgType gtMassmsgType(){
    return (OMassMsgType)(OMassMsgType.ARTICLE.getLine().get(_massmsgType));
  }
  public void stMassmsgType(OMassMsgType massmsgType){
    _massmsgType=massmsgType.getLine().getKey();
  }
  public Long getTemplate(){
    return _template;
  }
  public void setTemplate(Long template){
    _template=template;
  }
  //外部主键对象: BeanLong
  public Bean gtTemplate(){
    return (Bean)gtLongTbObj(getTemplate());
  }
  public void stTemplate(Bean template){
      setTemplate(template.gtLongPkey());
  }
  public Byte getIsToAll(){
    return _isToAll;
  }
  public void setIsToAll(Byte isToAll){
    _isToAll=isToAll;
  }
  public Boolean gtIsToAll(){
    return byteToBoolean(_isToAll);
  }
  public void stIsToAll(Boolean isToAll){
    _isToAll=booleanToByte(isToAll);
  }
  public String getUserGroup(){
    return _userGroup;
  }
  public void setUserGroup(String userGroup){
    _userGroup=userGroup;
  }
  public Date getCreatedTime(){
    return _createdTime;
  }
  public void setCreatedTime(Date createdTime){
    _createdTime=createdTime;
  }
  public Date getCompleteTime(){
    return _completeTime;
  }
  public void setCompleteTime(Date completeTime){
    _completeTime=completeTime;
  }
  public String getRem(){
    return _rem;
  }
  public void setRem(String rem){
    _rem=rem;
  }
  public Long getMsgId(){
    return _msgId;
  }
  public void setMsgId(Long msgId){
    _msgId=msgId;
  }
  public Long getMsgDataId(){
    return _msgDataId;
  }
  public void setMsgDataId(Long msgDataId){
    _msgDataId=msgDataId;
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
