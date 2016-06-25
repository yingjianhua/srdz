package irille.wx.wpt;



import java.util.Date;

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

public class WptTop extends BeanInt<WptTop> implements IExtName {
	private static final Log LOG = new Log(WptTop.class);
	public static final Tb TB = new Tb(WptTop.class, "头条").setAutoIncrement().addActIUDL().addActOpt("edit", "编辑", "edit-icon");

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		CITY(WptCity.fldOutKey().setName("城市")),
		CITYLINE(WptCityLine.fldOutKey().setName("区域")),
		BANQUET(WptBanquet.fldOutKey().setName("主题")),
		TITLE(SYS.STR__40,"标题"),
		CONTENT(TB.crtText("content", "正文", 65535, true)),
		IMG_URL(SYS.PHOTO__NULL,"图片"),
		DATE(SYS.DATE,"时间"),
		URL(SYS.URL__NULL, "链接"),
		TOP(SYS.NY,"置顶"),
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
  private Integer _city;	// 城市 <表主键:WptCity>  INT
  private Integer _cityline;	// 区域 <表主键:WptCityLine>  INT
  private Integer _banquet;	// 主题 <表主键:WptBanquet>  INT
  private String _title;	// 标题  STR(40)
  private String _content;	// 正文  TEXT(65535)<null>
  private String _imgUrl;	// 图片  STR(200)<null>
  private Date _date;	// 时间  DATE
  private String _url;	// 链接  STR(200)<null>
  private Byte _top;	// 置顶 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _sort;	// 排序  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptTop init(){
		super.init();
    _city=null;	// 城市 <表主键:WptCity>  INT
    _cityline=null;	// 区域 <表主键:WptCityLine>  INT
    _banquet=null;	// 主题 <表主键:WptBanquet>  INT
    _title=null;	// 标题  STR(40)
    _content=null;	// 正文  TEXT(65535)
    _imgUrl=null;	// 图片  STR(200)
    _date=null;	// 时间  DATE
    _url=null;	// 链接  STR(200)
    _top=OYn.DEFAULT.getLine().getKey();	// 置顶 <OYn>  BYTE
    _sort=0;	// 排序  INT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptTop loadUniqueTitle(boolean lockFlag,String title) {
    return (WptTop)loadUnique(T.IDX_TITLE,lockFlag,title);
  }
  public static WptTop chkUniqueTitle(boolean lockFlag,String title) {
    return (WptTop)chkUnique(T.IDX_TITLE,lockFlag,title);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
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
  public Integer getBanquet(){
    return _banquet;
  }
  public void setBanquet(Integer banquet){
    _banquet=banquet;
  }
  public WptBanquet gtBanquet(){
    if(getBanquet()==null)
      return null;
    return (WptBanquet)get(WptBanquet.class,getBanquet());
  }
  public void stBanquet(WptBanquet banquet){
    if(banquet==null)
      setBanquet(null);
    else
      setBanquet(banquet.getPkey());
  }
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public String getContent(){
    return _content;
  }
  public void setContent(String content){
    _content=content;
  }
  public String getImgUrl(){
    return _imgUrl;
  }
  public void setImgUrl(String imgUrl){
    _imgUrl=imgUrl;
  }
  public Date getDate(){
    return _date;
  }
  public void setDate(Date date){
    _date=date;
  }
  public String getUrl(){
    return _url;
  }
  public void setUrl(String url){
    _url=url;
  }
  public Byte getTop(){
    return _top;
  }
  public void setTop(Byte top){
    _top=top;
  }
  public Boolean gtTop(){
    return byteToBoolean(_top);
  }
  public void stTop(Boolean top){
    _top=booleanToByte(top);
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
