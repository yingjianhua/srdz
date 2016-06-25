package irille.wx.wx;

import irille.core.sys.SysDept;
import irille.core.sys.SysOrg;
import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

public class WxSetting extends BeanInt<WxSetting> {
	private static final Log LOG = new Log(WxSetting.class);
	public static final Tb TB = new Tb(WxSetting.class, "帐号设置")
			.setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
	    PKEY(TB.crtIntPkey()), // 主键
	    ACCOUNT(WxAccount.fldOutKey()),//公众账号
	    USER_SYS(SYS.USER_SYS), // 用户
	    ROW_VERSION(SYS.ROW_VERSION),
	    // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
	    // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
	    ;
	    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
	    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
	    // 索引
	    public static final Index IDX_USER_SYS = TB.addIndex("userSys", true, T.USER_SYS);
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
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Integer _userSys;	// 用户 <表主键:SysUser>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxSetting init(){
		super.init();
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _userSys=null;	// 用户 <表主键:SysUser>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WxSetting loadUniqueUserSys(boolean lockFlag,Integer userSys) {
    return (WxSetting)loadUnique(T.IDX_USER_SYS,lockFlag,userSys);
  }
  public static WxSetting chkUniqueUserSys(boolean lockFlag,Integer userSys) {
    return (WxSetting)chkUnique(T.IDX_USER_SYS,lockFlag,userSys);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
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
  public Integer getUserSys(){
    return _userSys;
  }
  public void setUserSys(Integer userSys){
    _userSys=userSys;
  }
  public SysUser gtUserSys(){
    if(getUserSys()==null)
      return null;
    return (SysUser)get(SysUser.class,getUserSys());
  }
  public void stUserSys(SysUser userSys){
    if(userSys==null)
      setUserSys(null);
    else
      setUserSys(userSys.getPkey());
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
