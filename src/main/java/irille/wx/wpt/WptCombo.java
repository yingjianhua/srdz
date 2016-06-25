package irille.wx.wpt;



import java.math.BigDecimal;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WptCombo extends BeanInt<WptCombo> implements IExtName {
	private static final Log LOG = new Log(WptCombo.class);
	public static final Tb TB = new Tb(WptCombo.class, "套餐").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		RESTAURANT(WptRestaurant.fldOutKey()),
		NAME(SYS.NAME__40,"名称"),
		IMG_URL(SYS.PHOTO__NULL,"图片"),
		DES(SYS.DESCRIP__200_NULL,"描述"),
		ORIG_PRICE(SYS.AMT, "原价"),
		PRICE(SYS.AMT,"价格"),
		NUMBER_MIN(SYS.INT,"最少人数"),
	    NUMBER_MAX(SYS.INT,"最大人数"),
	    SERVICE_DATE(SYS.STR__40_NULL, "使用日期"),
	    SERVICE_TIME(SYS.STR__40_NULL, "使用时段"),
	    REM(SYS.REM__200_NULL),
	    SORT(SYS.SORT__SHORT),
		CMB_WX(CmbWx.fldFlds()),


		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
        public static Index IDX_RESTAURANT_NAME=TB.addIndex("restaurantName", true, T.RESTAURANT,T.NAME);
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
  private Integer _restaurant;	// 餐厅 <表主键:WptRestaurant>  INT
  private String _name;	// 名称  STR(40)
  private String _imgUrl;	// 图片  STR(200)<null>
  private String _des;	// 描述  STR(200)<null>
  private BigDecimal _origPrice;	// 原价  DEC(16,2)
  private BigDecimal _price;	// 价格  DEC(16,2)
  private Integer _numberMin;	// 最少人数  INT
  private Integer _numberMax;	// 最大人数  INT
  private String _serviceDate;	// 使用日期  STR(40)<null>
  private String _serviceTime;	// 使用时段  STR(40)<null>
  private String _rem;	// 备注  STR(200)<null>
  private Short _sort;	// 排序号  SHORT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptCombo init(){
		super.init();
    _restaurant=null;	// 餐厅 <表主键:WptRestaurant>  INT
    _name=null;	// 名称  STR(40)
    _imgUrl=null;	// 图片  STR(200)
    _des=null;	// 描述  STR(200)
    _origPrice=ZERO;	// 原价  DEC(16,2)
    _price=ZERO;	// 价格  DEC(16,2)
    _numberMin=0;	// 最少人数  INT
    _numberMax=0;	// 最大人数  INT
    _serviceDate=null;	// 使用日期  STR(40)
    _serviceTime=null;	// 使用时段  STR(40)
    _rem=null;	// 备注  STR(200)
    _sort=0;	// 排序号  SHORT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptCombo loadUniqueRestaurantName(boolean lockFlag,Integer restaurant,String name) {
    return (WptCombo)loadUnique(T.IDX_RESTAURANT_NAME,lockFlag,restaurant,name);
  }
  public static WptCombo chkUniqueRestaurantName(boolean lockFlag,Integer restaurant,String name) {
    return (WptCombo)chkUnique(T.IDX_RESTAURANT_NAME,lockFlag,restaurant,name);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getRestaurant(){
    return _restaurant;
  }
  public void setRestaurant(Integer restaurant){
    _restaurant=restaurant;
  }
  public WptRestaurant gtRestaurant(){
    if(getRestaurant()==null)
      return null;
    return (WptRestaurant)get(WptRestaurant.class,getRestaurant());
  }
  public void stRestaurant(WptRestaurant restaurant){
    if(restaurant==null)
      setRestaurant(null);
    else
      setRestaurant(restaurant.getPkey());
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getImgUrl(){
    return _imgUrl;
  }
  public void setImgUrl(String imgUrl){
    _imgUrl=imgUrl;
  }
  public String getDes(){
    return _des;
  }
  public void setDes(String des){
    _des=des;
  }
  public BigDecimal getOrigPrice(){
    return _origPrice;
  }
  public void setOrigPrice(BigDecimal origPrice){
    _origPrice=origPrice;
  }
  public BigDecimal getPrice(){
    return _price;
  }
  public void setPrice(BigDecimal price){
    _price=price;
  }
  public Integer getNumberMin(){
    return _numberMin;
  }
  public void setNumberMin(Integer numberMin){
    _numberMin=numberMin;
  }
  public Integer getNumberMax(){
    return _numberMax;
  }
  public void setNumberMax(Integer numberMax){
    _numberMax=numberMax;
  }
  public String getServiceDate(){
    return _serviceDate;
  }
  public void setServiceDate(String serviceDate){
    _serviceDate=serviceDate;
  }
  public String getServiceTime(){
    return _serviceTime;
  }
  public void setServiceTime(String serviceTime){
    _serviceTime=serviceTime;
  }
  public String getRem(){
    return _rem;
  }
  public void setRem(String rem){
    _rem=rem;
  }
  public Short getSort(){
    return _sort;
  }
  public void setSort(Short sort){
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
