package irille.wx.wa;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.FldDec;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.wx.Wx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.Wx.OQRCodeType;

import java.math.BigDecimal;
import java.util.Date;

public class WaQRCode extends BeanInt<WaQRCode> {
	private static final Log LOG = new Log(WaQRCode.class);
	public static final Tb TB = new Tb(WaQRCode.class, "二维码管理").setAutoIncrement().addActIUDL()
			.addActOpt("obtain", "获取二维码").addActOpt("download", "下载二维码");

	public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    TYPE(TB.crt(Wx.OQRCodeType.DEFAULT)),
    SCENE_KEY(SYS.STR__100,"场景值"),
    VALID_TERM(new FldDec("validTerm", "有效天数", 4,2)),
    IMG_URL(SYS.STR__1000_NULL,"二维码图片"),
    SUMMARY(SYS.STR__100_NULL,"二维码详情"),
    URL(SYS.STR__100_NULL,"解析地址"),
    EXPIRE_TIME(SYS.TIME__NULL, "到期时间"),
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
  private Integer _pkey;	// 编号  INT
  private Byte _type;	// 二维码类型 <OQRCodeType>  BYTE
	// TEMPORARY:1,临时二维码
	// PERMANENT:2,永久二维码
  private String _sceneKey;	// 场景值  STR(100)
  private BigDecimal _validTerm;	// 有效天数  DEC(4,2)
  private String _imgUrl;	// 二维码图片  STR(1000)<null>
  private String _summary;	// 二维码详情  STR(100)<null>
  private String _url;	// 解析地址  STR(100)<null>
  private Date _expireTime;	// 到期时间  TIME<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Date _createdTime;	// 创建时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaQRCode init(){
		super.init();
    _type=OQRCodeType.DEFAULT.getLine().getKey();	// 二维码类型 <OQRCodeType>  BYTE
    _sceneKey=null;	// 场景值  STR(100)
    _validTerm=ZERO;	// 有效天数  DEC(3,2)
    _imgUrl=null;	// 二维码图片  STR(1000)
    _summary=null;	// 二维码详情  STR(100)
    _url=null;	// 解析地址  STR(100)
    _expireTime=null;	// 到期时间  TIME
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _createdTime=Env.getTranBeginTime();	// 创建时间  TIME
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
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public OQRCodeType gtType(){
    return (OQRCodeType)(OQRCodeType.TEMPORARY.getLine().get(_type));
  }
  public void stType(OQRCodeType type){
    _type=type.getLine().getKey();
  }
  public String getSceneKey(){
    return _sceneKey;
  }
  public void setSceneKey(String sceneKey){
    _sceneKey=sceneKey;
  }
  public BigDecimal getValidTerm(){
    return _validTerm;
  }
  public void setValidTerm(BigDecimal validTerm){
    _validTerm=validTerm;
  }
  public String getImgUrl(){
    return _imgUrl;
  }
  public void setImgUrl(String imgUrl){
    _imgUrl=imgUrl;
  }
  public String getSummary(){
    return _summary;
  }
  public void setSummary(String summary){
    _summary=summary;
  }
  public String getUrl(){
    return _url;
  }
  public void setUrl(String url){
    _url=url;
  }
  public Date getExpireTime(){
    return _expireTime;
  }
  public void setExpireTime(Date expireTime){
    _expireTime=expireTime;
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
