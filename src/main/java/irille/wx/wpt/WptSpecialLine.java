package irille.wx.wpt;



import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WptSpecialLine extends BeanInt<WptSpecialLine> {
	private static final Log LOG = new Log(WptSpecialLine.class);
	public static final Tb TB = new Tb(WptSpecialLine.class, "餐厅").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		SPECIAL(WptSpecial.fldOutKey()),
		RESTAURANT(WptRestaurant.fldOutKey()),
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
		public static final Index IDX_SPECIAL_RESTAURANT = TB.addIndex("specialRestaurant", true, T.SPECIAL,T.RESTAURANT);
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
		 return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
		  }
	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _special;	// 专题 <表主键:WptSpecial>  INT
  private Integer _restaurant;	// 餐厅 <表主键:WptRestaurant>  INT
  private Integer _sort;	// 排序  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptSpecialLine init(){
		super.init();
    _special=null;	// 专题 <表主键:WptSpecial>  INT
    _restaurant=null;	// 餐厅 <表主键:WptRestaurant>  INT
    _sort=0;	// 排序  INT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptSpecialLine loadUniqueSpecialRestaurant(boolean lockFlag,Integer special,Integer restaurant) {
    return (WptSpecialLine)loadUnique(T.IDX_SPECIAL_RESTAURANT,lockFlag,special,restaurant);
  }
  public static WptSpecialLine chkUniqueSpecialRestaurant(boolean lockFlag,Integer special,Integer restaurant) {
    return (WptSpecialLine)chkUnique(T.IDX_SPECIAL_RESTAURANT,lockFlag,special,restaurant);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getSpecial(){
    return _special;
  }
  public void setSpecial(Integer special){
    _special=special;
  }
  public WptSpecial gtSpecial(){
    if(getSpecial()==null)
      return null;
    return (WptSpecial)get(WptSpecial.class,getSpecial());
  }
  public void stSpecial(WptSpecial special){
    if(special==null)
      setSpecial(null);
    else
      setSpecial(special.getPkey());
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
