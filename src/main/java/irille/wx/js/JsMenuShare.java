package irille.wx.js;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.js.Js.OJsMenuType;
import irille.wx.wx.WxAccount;

public class JsMenuShare extends BeanInt<JsMenuShare> implements IExtName{
  private static final Log LOG = new Log(JsMenuShare.class);
  public static final Tb TB = new Tb(JsMenuShare.class, "公共类接口").setAutoIncrement().addActUpd();

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    ENABLED(SYS.ENABLED),
    TITLE(SYS.STR__100,"分享标题",true),
    DES(SYS.STR__100,"分享描述",true),
    LINK(SYS.URL__NULL,"分享链接"),
    IMG_URL(SYS.PHOTO__NULL,"分享图标"),
    TYPE(TB.crt(Js.OJsMenuType.DEFAULT).setName("分享类型")),
    DATA_URL(SYS.URL__NULL,"数据链接"),
    APP_MESSAGE(SYS.YN,"微信朋友"),
    TIME_LINE(SYS.YN,"微信朋友圈"),
    QQ(SYS.YN,"QQ好友"),
    WEIBO(SYS.YN,"微博"),
    QZONE(SYS.YN,"QQ空间"),
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
  @Override
  public String getExtName() {
    return getTitle();
  }
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Byte _enabled;	// 启用标志 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private String _title;	// 分享标题  STR(100)<null>
  private String _des;	// 分享描述  STR(100)<null>
  private String _link;	// 分享链接  STR(200)<null>
  private String _imgUrl;	// 分享图标  STR(200)<null>
  private Byte _type;	// 分享类型 <OJsMenuType>  BYTE
	// MUSIC:0,音乐
	// VIDEO:1,视频
	// LINK:2,链接
  private String _dataUrl;	// 数据链接  STR(200)<null>
  private Byte _appMessage;	// 微信朋友 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _timeLine;	// 微信朋友圈 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _qq;	// QQ好友 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _weibo;	// 微博 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _qzone;	// QQ空间 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public JsMenuShare init(){
		super.init();
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 启用标志 <OEnabled>  BYTE
    _title=null;	// 分享标题  STR(100)
    _des=null;	// 分享描述  STR(100)
    _link=null;	// 分享链接  STR(200)
    _imgUrl=null;	// 分享图标  STR(200)
    _type=OJsMenuType.DEFAULT.getLine().getKey();	// 分享类型 <OJsMenuType>  BYTE
    _dataUrl=null;	// 数据链接  STR(200)
    _appMessage=OYn.DEFAULT.getLine().getKey();	// 微信朋友 <OYn>  BYTE
    _timeLine=OYn.DEFAULT.getLine().getKey();	// 微信朋友圈 <OYn>  BYTE
    _qq=OYn.DEFAULT.getLine().getKey();	// QQ好友 <OYn>  BYTE
    _weibo=OYn.DEFAULT.getLine().getKey();	// 微博 <OYn>  BYTE
    _qzone=OYn.DEFAULT.getLine().getKey();	// QQ空间 <OYn>  BYTE
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
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public String getDes(){
    return _des;
  }
  public void setDes(String des){
    _des=des;
  }
  public String getLink(){
    return _link;
  }
  public void setLink(String link){
    _link=link;
  }
  public String getImgUrl(){
    return _imgUrl;
  }
  public void setImgUrl(String imgUrl){
    _imgUrl=imgUrl;
  }
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public OJsMenuType gtType(){
    return (OJsMenuType)(OJsMenuType.LINK.getLine().get(_type));
  }
  public void stType(OJsMenuType type){
    _type=type.getLine().getKey();
  }
  public String getDataUrl(){
    return _dataUrl;
  }
  public void setDataUrl(String dataUrl){
    _dataUrl=dataUrl;
  }
  public Byte getAppMessage(){
    return _appMessage;
  }
  public void setAppMessage(Byte appMessage){
    _appMessage=appMessage;
  }
  public Boolean gtAppMessage(){
    return byteToBoolean(_appMessage);
  }
  public void stAppMessage(Boolean appMessage){
    _appMessage=booleanToByte(appMessage);
  }
  public Byte getTimeLine(){
    return _timeLine;
  }
  public void setTimeLine(Byte timeLine){
    _timeLine=timeLine;
  }
  public Boolean gtTimeLine(){
    return byteToBoolean(_timeLine);
  }
  public void stTimeLine(Boolean timeLine){
    _timeLine=booleanToByte(timeLine);
  }
  public Byte getQq(){
    return _qq;
  }
  public void setQq(Byte qq){
    _qq=qq;
  }
  public Boolean gtQq(){
    return byteToBoolean(_qq);
  }
  public void stQq(Boolean qq){
    _qq=booleanToByte(qq);
  }
  public Byte getWeibo(){
    return _weibo;
  }
  public void setWeibo(Byte weibo){
    _weibo=weibo;
  }
  public Boolean gtWeibo(){
    return byteToBoolean(_weibo);
  }
  public void stWeibo(Boolean weibo){
    _weibo=booleanToByte(weibo);
  }
  public Byte getQzone(){
    return _qzone;
  }
  public void setQzone(Byte qzone){
    _qzone=qzone;
  }
  public Boolean gtQzone(){
    return byteToBoolean(_qzone);
  }
  public void stQzone(Boolean qzone){
    _qzone=booleanToByte(qzone);
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
