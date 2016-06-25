package irille.wx.wax;



import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WaxLady extends BeanInt<WaxLady> implements IExtName {
	private static final Log LOG = new Log(WaxLady.class);
	public static final Tb TB = new Tb(WaxLady.class, "享女郎").setAutoIncrement().addActList();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		NAME(SYS.NAME__PERSON_40),
		MOBILE(SYS.MOBILE__NULL,"手机",true),
		WX_USER(WxUser.fldOutKey()),
		CMB_WX(CmbWx.fldFlds()),

		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		public static final Index IDX_WX_USER_ACCOUNT = TB.addIndex("wxUserAccount", true, T.WX_USER, T.ACCOUNT);
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
  private String _name;	// 姓名  STR(40)
  private String _mobile;	// 手机  STR(20)<null>
  private Integer _wxUser;	// 关注用户 <表主键:WxUser>  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaxLady init(){
		super.init();
    _name=null;	// 姓名  STR(40)
    _mobile=null;	// 手机  STR(20)
    _wxUser=null;	// 关注用户 <表主键:WxUser>  INT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WaxLady loadUniqueWxUserAccount(boolean lockFlag,Integer wxUser,Integer account) {
    return (WaxLady)loadUnique(T.IDX_WX_USER_ACCOUNT,lockFlag,wxUser,account);
  }
  public static WaxLady chkUniqueWxUserAccount(boolean lockFlag,Integer wxUser,Integer account) {
    return (WaxLady)chkUnique(T.IDX_WX_USER_ACCOUNT,lockFlag,wxUser,account);
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
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
