package irille.wx.wx;

import irille.core.sys.Sys;
import irille.core.sys.Sys.OEnabled;
import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.wx.Wx.OMsgType;

import java.util.Date;

public class WxAuto extends BeanInt<WxAuto>{
  private static final Log LOG = new Log(WxAuto.class);
  public static final Tb TB = new Tb(WxAuto.class, "自动回复").setAutoIncrement().addActIUDL().addActEnabled();

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    KEYWORD(SYS.STR__100,"关键字"),//关键字
    MSG_TYPE(TB.crt(Wx.OMsgType.DEFAULT)),//消息类型
    TEMPLATE(Tb.crtLongTbObj("template", "消息模板")),//消息模板
    ENABLED(TB.crt(Sys.OEnabled.DEFAULT)),//启停标志
    ACCOUNT(WxAccount.fldOutKey()),//公众账号
    UPDATED_TIME(SYS.TIME,"更新时间"),//更新时间
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
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _keyword;	// 关键字  STR(100)
  private Byte _msgType;	// 消息类型 <OMsgType>  BYTE
	// TEXT:1,文本消息
	// IMAGE:2,图片消息
	// MUSIC:3,音乐消息
	// VIDEO:4,视频消息
	// VOICE:5,语音消息
	// NEWS:6,图文消息
  private Long _template;	// 消息模板  LONG
  private Byte _enabled;	// 启用标志 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Date _updatedTime;	// 更新时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxAuto init(){
		super.init();
    _keyword=null;	// 关键字  STR(100)
    _msgType=OMsgType.DEFAULT.getLine().getKey();	// 消息类型 <OMsgType>  BYTE
    _template=null;	// 消息模板  LONG
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 启用标志 <OEnabled>  BYTE
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _updatedTime=null;	// 更新时间  TIME
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
  public String getKeyword(){
    return _keyword;
  }
  public void setKeyword(String keyword){
    _keyword=keyword;
  }
  public Byte getMsgType(){
    return _msgType;
  }
  public void setMsgType(Byte msgType){
    _msgType=msgType;
  }
  public OMsgType gtMsgType(){
    return (OMsgType)(OMsgType.TEXT.getLine().get(_msgType));
  }
  public void stMsgType(OMsgType msgType){
    _msgType=msgType.getLine().getKey();
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
  public Byte getEnabled(){
    return _enabled;
  }
  public void setEnabled(Byte enabled){
    _enabled=enabled;
  }
  public Boolean gtEnabled(){
    return byteToBoolean(_enabled);
  }
  public void stEnabled(Boolean enabled){
    _enabled=booleanToByte(enabled);
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
  public Date getUpdatedTime(){
    return _updatedTime;
  }
  public void setUpdatedTime(Date updatedTime){
    _updatedTime=updatedTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
