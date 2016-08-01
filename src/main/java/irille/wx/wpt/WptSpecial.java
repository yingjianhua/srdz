package irille.wx.wpt;



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

public class WptSpecial extends BeanInt<WptSpecial> implements IExtName {
	private static final Log LOG = new Log(WptSpecial.class);
	public static final Tb TB = new Tb(WptSpecial.class, "专题").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		TITLE(SYS.STR__20,"标题"),
		CITY(WptCity.fldOutKey()),
		IGNORE_CITY(SYS.NY, "忽略城市"),
		TOP_IMG_URL(SYS.PHOTO__NULL,"顶图"),
		BASE_IMG_URL(SYS.PHOTO__NULL,"底图"),
		INTRO(SYS.STR__200_NULL,"介绍"),
		SORT(SYS.INT,"排序"),
		CMB_WX(CmbWx.fldFlds()),


		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		public static final Index IDX_TITLE = TB.addIndex("title", true, T.TITLE);

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
	    return getTitle();
	  }

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _title;	// 标题  STR(20)
  private Integer _city;	// 城市 <表主键:WptCity>  INT
  private Byte _ignoreCity;	// 忽略城市 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _topImgUrl;	// 顶图  STR(200)<null>
  private String _baseImgUrl;	// 底图  STR(200)<null>
  private String _intro;	// 介绍  STR(200)<null>
  private Integer _sort;	// 排序  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptSpecial init(){
		super.init();
    _title=null;	// 标题  STR(20)
    _city=null;	// 城市 <表主键:WptCity>  INT
    _ignoreCity=OYn.DEFAULT.getLine().getKey();	// 忽略城市 <OYn>  BYTE
    _topImgUrl=null;	// 顶图  STR(200)
    _baseImgUrl=null;	// 底图  STR(200)
    _intro=null;	// 介绍  STR(200)
    _sort=0;	// 排序  INT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptSpecial loadUniqueTitle(boolean lockFlag,String title) {
    return (WptSpecial)loadUnique(T.IDX_TITLE,lockFlag,title);
  }
  public static WptSpecial chkUniqueTitle(boolean lockFlag,String title) {
    return (WptSpecial)chkUnique(T.IDX_TITLE,lockFlag,title);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
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
  public Byte getIgnoreCity(){
    return _ignoreCity;
  }
  public void setIgnoreCity(Byte ignoreCity){
    _ignoreCity=ignoreCity;
  }
  public Boolean gtIgnoreCity(){
    return byteToBoolean(_ignoreCity);
  }
  public void stIgnoreCity(Boolean ignoreCity){
    _ignoreCity=booleanToByte(ignoreCity);
  }
  public String getTopImgUrl(){
    return _topImgUrl;
  }
  public void setTopImgUrl(String topImgUrl){
    _topImgUrl=topImgUrl;
  }
  public String getBaseImgUrl(){
    return _baseImgUrl;
  }
  public void setBaseImgUrl(String baseImgUrl){
    _baseImgUrl=baseImgUrl;
  }
  public String getIntro(){
    return _intro;
  }
  public void setIntro(String intro){
    _intro=intro;
  }
  public Integer getSort(){
    return _sort;
  }
  public void setSort(Integer sort){
    _sort=sort;
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
