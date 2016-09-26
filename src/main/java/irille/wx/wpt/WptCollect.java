package irille.wx.wpt;



import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WptCollect extends BeanInt<WptCollect> {
	private static final Log LOG = new Log(WptCollect.class);
	public static final Tb TB = new Tb(WptCollect.class, "收藏").setAutoIncrement().addActList();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		WXUSER(WxUser.fldOutKey()),//微信用户
		TOP(WptHeadline.fldOutKey()),//头条
		CMB_WX(CmbWx.fldFlds()),


		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		public static final Index IDX_WX_USER_TOP = TB.addIndex("wxUserTop", true, T.WXUSER, T.TOP);

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

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _wxuser;	// 关注用户 <表主键:WxUser>  INT
  private Integer _top;	// 头条 <表主键:WptHeadline>  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptCollect init(){
		super.init();
    _wxuser=null;	// 关注用户 <表主键:WxUser>  INT
    _top=null;	// 头条 <表主键:WptHeadline>  INT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptCollect loadUniqueWxUserTop(boolean lockFlag,Integer wxuser,Integer top) {
    return (WptCollect)loadUnique(T.IDX_WX_USER_TOP,lockFlag,wxuser,top);
  }
  public static WptCollect chkUniqueWxUserTop(boolean lockFlag,Integer wxuser,Integer top) {
    return (WptCollect)chkUnique(T.IDX_WX_USER_TOP,lockFlag,wxuser,top);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getWxuser(){
    return _wxuser;
  }
  public void setWxuser(Integer wxuser){
    _wxuser=wxuser;
  }
  public WxUser gtWxuser(){
    if(getWxuser()==null)
      return null;
    return (WxUser)get(WxUser.class,getWxuser());
  }
  public void stWxuser(WxUser wxuser){
    if(wxuser==null)
      setWxuser(null);
    else
      setWxuser(wxuser.getPkey());
  }
  public Integer getTop(){
    return _top;
  }
  public void setTop(Integer top){
    _top=top;
  }
  public WptHeadline gtTop(){
    if(getTop()==null)
      return null;
    return (WptHeadline)get(WptHeadline.class,getTop());
  }
  public void stTop(WptHeadline top){
    if(top==null)
      setTop(null);
    else
      setTop(top.getPkey());
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
