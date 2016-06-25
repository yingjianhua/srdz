package irille.wx.wx;

import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanLong;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.wx.Wx.OWxMsgDir;
import irille.wx.wx.Wx.OWxMsgType;

import java.util.Date;

public class WxMessage extends BeanLong<WxMessage> {
  private static final Log LOG = new Log(WxMessage.class);
  public static final Tb TB = new Tb(WxMessage.class, "消息管理").setAutoIncrement()
      .addActOpt("reply", "回复").addActOpt("check", "查看");

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtLongPkey()),//主键
    WXMSG_TYPE(TB.crt(Wx.OWxMsgType.DEFAULT)),
    WXMSG_DIR(TB.crt(Wx.OWxMsgDir.DEFAULT)),
    MSG_ID(SYS.LONG,"消息ID",true),
    CONTENT(SYS.STR__200_NULL,"消息內容"),
    IS_REPLY(SYS.NY,"回复标志"),
    IS_COLLECT(SYS.NY,"收藏标志"),
    WX_USER(WxUser.fldOutKey()),
    ACCOUNT(WxAccount.fldOutKey()),//公众账号
    CREATED_TIME(SYS.CREATED_DATE_TIME,"创建时间"),
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
  private Byte _wxmsgType;	// 消息类型 <OWxMsgType>  BYTE
	// TEXT:1,文本消息
	// IMG:2,图文消息
	// VOICE:3,语音消息
	// VIDEO:4,视频消息
	// MINVIDEO:5,小视频消息
	// GP:6,地理位置消息
	// LINK:7,链接消息
  private Byte _wxmsgDir;	// 消息方向 <OWxMsgDir>  BYTE
	// RECEIVE:1,接收消息
	// REPLY:2,回复消息
  private Long _msgId;	// 消息ID  LONG<null>
  private String _content;	// 消息內容  STR(200)<null>
  private Byte _isReply;	// 回复标志 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _isCollect;	// 收藏标志 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _wxUser;	// 关注用户 <表主键:WxUser>  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Date _createdTime;	// 创建时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxMessage init(){
		super.init();
    _wxmsgType=OWxMsgType.DEFAULT.getLine().getKey();	// 消息类型 <OWxMsgType>  BYTE
    _wxmsgDir=OWxMsgDir.DEFAULT.getLine().getKey();	// 消息方向 <OWxMsgDir>  BYTE
    _msgId=(long)0;	// 消息ID  LONG
    _content=null;	// 消息內容  STR(200)
    _isReply=OYn.DEFAULT.getLine().getKey();	// 回复标志 <OYn>  BYTE
    _isCollect=OYn.DEFAULT.getLine().getKey();	// 收藏标志 <OYn>  BYTE
    _wxUser=null;	// 关注用户 <表主键:WxUser>  INT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _createdTime=Env.getTranBeginTime();	// 创建时间  TIME
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
  public Byte getWxmsgType(){
    return _wxmsgType;
  }
  public void setWxmsgType(Byte wxmsgType){
    _wxmsgType=wxmsgType;
  }
  public OWxMsgType gtWxmsgType(){
    return (OWxMsgType)(OWxMsgType.TEXT.getLine().get(_wxmsgType));
  }
  public void stWxmsgType(OWxMsgType wxmsgType){
    _wxmsgType=wxmsgType.getLine().getKey();
  }
  public Byte getWxmsgDir(){
    return _wxmsgDir;
  }
  public void setWxmsgDir(Byte wxmsgDir){
    _wxmsgDir=wxmsgDir;
  }
  public OWxMsgDir gtWxmsgDir(){
    return (OWxMsgDir)(OWxMsgDir.RECEIVE.getLine().get(_wxmsgDir));
  }
  public void stWxmsgDir(OWxMsgDir wxmsgDir){
    _wxmsgDir=wxmsgDir.getLine().getKey();
  }
  public Long getMsgId(){
    return _msgId;
  }
  public void setMsgId(Long msgId){
    _msgId=msgId;
  }
  public String getContent(){
    return _content;
  }
  public void setContent(String content){
    _content=content;
  }
  public Byte getIsReply(){
    return _isReply;
  }
  public void setIsReply(Byte isReply){
    _isReply=isReply;
  }
  public Boolean gtIsReply(){
    return byteToBoolean(_isReply);
  }
  public void stIsReply(Boolean isReply){
    _isReply=booleanToByte(isReply);
  }
  public Byte getIsCollect(){
    return _isCollect;
  }
  public void setIsCollect(Byte isCollect){
    _isCollect=isCollect;
  }
  public Boolean gtIsCollect(){
    return byteToBoolean(_isCollect);
  }
  public void stIsCollect(Boolean isCollect){
    _isCollect=booleanToByte(isCollect);
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
  public Date getCreatedTime(){
    return _createdTime;
  }
  public void setCreatedTime(Date createdTime){
    _createdTime=createdTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
