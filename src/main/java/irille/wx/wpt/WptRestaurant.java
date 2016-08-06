package irille.wx.wpt;



import java.math.BigDecimal;

import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WptRestaurant extends BeanInt<WptRestaurant> implements IExtName {
	private static final Log LOG = new Log(WptRestaurant.class);
	public static final Tb TB = new Tb(WptRestaurant.class, "餐厅").setAutoIncrement().addActIUDL().addActOpt("menuSet", "菜品设置", "edit-icon")
			.addActOpt("addHot", "添加热销", "edit-icon").addActOpt("delHot", "删除热销", "edit-icon")
			.addActOpt("addSpec", "同步到专题", "win-refresh-icon").addActOpt("bannerSet", "轮播图设置", "edit-icon")
			.addActOpt("enableDisable", "启停用");

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		NAME(SYS.NAME__40,"餐厅名称"),
		MOBILE(SYS.MOBILE__NULL,"电话",true),
		MANAGER(SYS.MOBILE__NULL, "店长电话"),
		CITYNAME(SYS.STR__40_NULL,"城市名称"),
		CITY(WptCity.fldOutKey()),
		CITYLINE(WptCityLine.fldOutKey().setName("区")),
		ADDR(SYS.ADDR__200_NULL),
		COORDINATE(SYS.STR__40_NULL,"经纬度"),
		LONGITUDE(SYS.STR__20_NULL),//冗余字段
		IMG_URL(SYS.PHOTO__NULL,"图片"),
		REM(SYS.REM__200_NULL,"备注"),
		DISPLAY(SYS.NY,"显示图片"),
		DES(SYS.STR__1000_NULL,"描述"),
		STARTDATE(SYS.STR__40_NULL,"开始营业时间"),
		STOPDATE(SYS.STR__40_NULL,"结束营业时间"),
		CONSUMPTION(SYS.AMT,"人均消费"),
		WIFIACCOUNT(SYS.STR__40_NULL,"wifi账号"),
		WIFIPASSWORD(SYS.STR__40_NULL,"wifi密码"),
		TEMPLATE(WptRestaurantTemplate.fldOutKey().setName("详情页模板").setNull()),
		ENABLED(SYS.ENABLED, "启用"),
		CMB_WX(CmbWx.fldFlds()),


		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		public static final Index IDX_NAME = TB.addIndex("name", true, T.NAME);

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
		T.CMB_WX.getFld().getTb().lockAllFlds();
	}

	// @formatter:on
	 public static Fld fldOutKey() {
		    Fld fld = fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
		    fld.setType(null);
		    return fld;
		  }
	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}
	@Override
	  public String getExtName() {
	    return getName();
	  }

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 餐厅名称  STR(40)
  private String _mobile;	// 电话  STR(20)<null>
  private String _manager;	// 店长电话  STR(20)<null>
  private String _cityname;	// 城市名称  STR(40)<null>
  private Integer _city;	// 城市 <表主键:WptCity>  INT
  private Integer _cityline;	// 区 <表主键:WptCityLine>  INT
  private String _addr;	// 地址  STR(200)<null>
  private String _coordinate;	// 经纬度  STR(40)<null>
  private String _longitude;	// 字符20  STR(20)<null>
  private String _imgUrl;	// 图片  STR(200)<null>
  private String _rem;	// 备注  STR(200)<null>
  private Byte _display;	// 显示图片 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _des;	// 描述  STR(1000)<null>
  private String _startdate;	// 开始营业时间  STR(40)<null>
  private String _stopdate;	// 结束营业时间  STR(40)<null>
  private BigDecimal _consumption;	// 人均消费  DEC(16,2)
  private String _wifiaccount;	// wifi账号  STR(40)<null>
  private String _wifipassword;	// wifi密码  STR(40)<null>
  private Integer _template;	// 详情页模板 <表主键:WptRestaurantTemplate>  INT<null>
  private Byte _enabled;	// 启用 <OEnabled>  BYTE
	// TRUE:1,启用
	// FALSE:0,停用
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptRestaurant init(){
		super.init();
    _name=null;	// 餐厅名称  STR(40)
    _mobile=null;	// 电话  STR(20)
    _manager=null;	// 店长电话  STR(20)
    _cityname=null;	// 城市名称  STR(40)
    _city=null;	// 城市 <表主键:WptCity>  INT
    _cityline=null;	// 区 <表主键:WptCityLine>  INT
    _addr=null;	// 地址  STR(200)
    _coordinate=null;	// 经纬度  STR(40)
    _longitude=null;	// 字符20  STR(20)
    _imgUrl=null;	// 图片  STR(200)
    _rem=null;	// 备注  STR(200)
    _display=OYn.DEFAULT.getLine().getKey();	// 显示图片 <OYn>  BYTE
    _des=null;	// 描述  STR(1000)
    _startdate=null;	// 开始营业时间  STR(40)
    _stopdate=null;	// 结束营业时间  STR(40)
    _consumption=ZERO;	// 人均消费  DEC(16,2)
    _wifiaccount=null;	// wifi账号  STR(40)
    _wifipassword=null;	// wifi密码  STR(40)
    _template=null;	// 详情页模板 <表主键:WptRestaurantTemplate>  INT
    _enabled=OEnabled.DEFAULT.getLine().getKey();	// 启用 <OEnabled>  BYTE
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptRestaurant loadUniqueName(boolean lockFlag,String name) {
    return (WptRestaurant)loadUnique(T.IDX_NAME,lockFlag,name);
  }
  public static WptRestaurant chkUniqueName(boolean lockFlag,String name) {
    return (WptRestaurant)chkUnique(T.IDX_NAME,lockFlag,name);
  }
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
  public String getMobile(){
    return _mobile;
  }
  public void setMobile(String mobile){
    _mobile=mobile;
  }
  public String getManager(){
    return _manager;
  }
  public void setManager(String manager){
    _manager=manager;
  }
  public String getCityname(){
    return _cityname;
  }
  public void setCityname(String cityname){
    _cityname=cityname;
  }
  public Integer getCity(){
    return _city;
  }
  public void setCity(Integer city){
    _city=city;
  }
  public WptCity gtCity(){
    if(getCity()==null)
      return null;
    return (WptCity)get(WptCity.class,getCity());
  }
  public void stCity(WptCity city){
    if(city==null)
      setCity(null);
    else
      setCity(city.getPkey());
  }
  public Integer getCityline(){
    return _cityline;
  }
  public void setCityline(Integer cityline){
    _cityline=cityline;
  }
  public WptCityLine gtCityline(){
    if(getCityline()==null)
      return null;
    return (WptCityLine)get(WptCityLine.class,getCityline());
  }
  public void stCityline(WptCityLine cityline){
    if(cityline==null)
      setCityline(null);
    else
      setCityline(cityline.getPkey());
  }
  public String getAddr(){
    return _addr;
  }
  public void setAddr(String addr){
    _addr=addr;
  }
  public String getCoordinate(){
    return _coordinate;
  }
  public void setCoordinate(String coordinate){
    _coordinate=coordinate;
  }
  public String getLongitude(){
    return _longitude;
  }
  public void setLongitude(String longitude){
    _longitude=longitude;
  }
  public String getImgUrl(){
    return _imgUrl;
  }
  public void setImgUrl(String imgUrl){
    _imgUrl=imgUrl;
  }
  public String getRem(){
    return _rem;
  }
  public void setRem(String rem){
    _rem=rem;
  }
  public Byte getDisplay(){
    return _display;
  }
  public void setDisplay(Byte display){
    _display=display;
  }
  public Boolean gtDisplay(){
    return byteToBoolean(_display);
  }
  public void stDisplay(Boolean display){
    _display=booleanToByte(display);
  }
  public String getDes(){
    return _des;
  }
  public void setDes(String des){
    _des=des;
  }
  public String getStartdate(){
    return _startdate;
  }
  public void setStartdate(String startdate){
    _startdate=startdate;
  }
  public String getStopdate(){
    return _stopdate;
  }
  public void setStopdate(String stopdate){
    _stopdate=stopdate;
  }
  public BigDecimal getConsumption(){
    return _consumption;
  }
  public void setConsumption(BigDecimal consumption){
    _consumption=consumption;
  }
  public String getWifiaccount(){
    return _wifiaccount;
  }
  public void setWifiaccount(String wifiaccount){
    _wifiaccount=wifiaccount;
  }
  public String getWifipassword(){
    return _wifipassword;
  }
  public void setWifipassword(String wifipassword){
    _wifipassword=wifipassword;
  }
  public Integer getTemplate(){
    return _template;
  }
  public void setTemplate(Integer template){
    _template=template;
  }
  public WptRestaurantTemplate gtTemplate(){
    if(getTemplate()==null)
      return null;
    return (WptRestaurantTemplate)get(WptRestaurantTemplate.class,getTemplate());
  }
  public void stTemplate(WptRestaurantTemplate template){
    if(template==null)
      setTemplate(null);
    else
      setTemplate(template.getPkey());
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
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
