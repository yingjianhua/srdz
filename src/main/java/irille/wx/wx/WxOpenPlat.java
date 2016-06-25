package irille.wx.wx;

import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

public class WxOpenPlat extends BeanInt<WxOpenPlat>implements IExtName {
  private static final Log LOG = new Log(WxOpenPlat.class);
  public static final Tb TB = new Tb(WxOpenPlat.class, "开放平台").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()), // 主键
    EMAIL(SYS.STR__100, "注册邮箱"),  // 公众帐号名称
    NAME(SYS.NAME__100_NULL, "真实姓名"),
    USER_SYS(SYS.USER_SYS, "负责人"),//用户
    ROW_VERSION(SYS.ROW_VERSION),
    // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
    public static final Index IDX_E_MAIL = TB.addIndex("eMail", true, T.EMAIL);
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

  @Override
  public String getExtName() {
    return _name==null?"未知":_name;
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
  private String _email;	// 注册邮箱  STR(100)
  private String _name;	// 真实姓名  STR(100)<null>
  private Integer _userSys;	// 负责人 <表主键:SysUser>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxOpenPlat init(){
		super.init();
    _email=null;	// 注册邮箱  STR(100)
    _name=null;	// 真实姓名  STR(100)
    _userSys=null;	// 负责人 <表主键:SysUser>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WxOpenPlat loadUniqueEMail(boolean lockFlag,String email) {
    return (WxOpenPlat)loadUnique(T.IDX_E_MAIL,lockFlag,email);
  }
  public static WxOpenPlat chkUniqueEMail(boolean lockFlag,String email) {
    return (WxOpenPlat)chkUnique(T.IDX_E_MAIL,lockFlag,email);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getEmail(){
    return _email;
  }
  public void setEmail(String email){
    _email=email;
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
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
