package irille.wx.wx;

import java.util.Date;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class WxUserQrcode extends BeanInt<WxUserQrcode> {
  private static final Log LOG = new Log(WxUserQrcode.class);
  public static final Tb TB = new Tb(WxUserQrcode.class, "专属二维码").setAutoLocal();

  public enum T implements IEnumFld {// @formatter:off
	USER(WxUser.fldOneToOne()),
	PATH(SYS.PHOTO__NULL, "保存路径"),
	MEDIAID(SYS.STR__100, "mediaId"),
	CREATED_TIME(SYS.CREATED_DATE_TIME),
	VALIDITY_PERIOD(SYS.INT, "有效期"),//单位：天
    ROW_VERSION(SYS.ROW_VERSION),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		PKEY(TB.get("pkey")),	//编号
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
    T.USER.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
  }
  //@formatter:on
  public static Fld fldOutKey() {
	    Fld fld = fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	    fld.setType(null);
	    return fld;
	  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }
  
  public static Fld fldOneToOne() {
		return fldOneToOne(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOneToOne(String code, String name) {
		return Tb.crtOneToOne(TB, code, name);
	}
  
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _path;	// 图片  STR(200)<null>
  private String _mediaid;	// 字符100  STR(100)
  private Date _createdTime;	// 建档时间  TIME
  private Integer _validityPeriod;	// 有效期  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxUserQrcode init(){
		super.init();
    _path=null;	// 图片  STR(200)
    _mediaid=null;	// 字符100  STR(100)
    _createdTime=Env.getTranBeginTime();	// 建档时间  TIME
    _validityPeriod=0;	// 有效期  INT
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
  //取一对一表对象: WxUser
  public WxUser gtUser(){
    return get(WxUser.class,getPkey());
  }
  public void stUser(WxUser user){
      setPkey(user.getPkey());
  }
  public String getPath(){
    return _path;
  }
  public void setPath(String path){
    _path=path;
  }
  public String getMediaid(){
    return _mediaid;
  }
  public void setMediaid(String mediaid){
    _mediaid=mediaid;
  }
  public Date getCreatedTime(){
    return _createdTime;
  }
  public void setCreatedTime(Date createdTime){
    _createdTime=createdTime;
  }
  public Integer getValidityPeriod(){
    return _validityPeriod;
  }
  public void setValidityPeriod(Integer validityPeriod){
    _validityPeriod=validityPeriod;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
