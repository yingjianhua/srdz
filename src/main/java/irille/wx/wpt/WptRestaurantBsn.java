package irille.wx.wpt;



import java.util.Date;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wpt.WptCollect.T;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WptRestaurantBsn extends BeanInt<WptRestaurantBsn> {
	private static final Log LOG = new Log(WptRestaurantBsn.class);
	public static final Tb TB = new Tb(WptRestaurantBsn.class, "餐厅商家管理").setAutoIncrement().addActIns().addActDel().addActList();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		RESTAURANT(WptRestaurant.fldOutKey()),
		WXUSER(WxUser.fldOutKey()),
		OPENID(SYS.STR__40_NULL, "openId"),
		CREATE_TIME(SYS.CREATED_DATE_TIME, "创建时间"),
		CMB_WX(CmbWx.fldFlds()),


		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		public static final Index IDX_WX_USER_ACCOUNT = TB.addIndex("wxUserAccount", true, T.WXUSER, T.ACCOUNT);

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
  private Integer _restaurant;	// 餐厅 <表主键:WptRestaurant>  INT
  private Integer _wxuser;	// 关注用户 <表主键:WxUser>  INT
  private String _openid;	// openId  STR(40)<null>
  private Date _createTime;	// 创建时间  TIME
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptRestaurantBsn init(){
		super.init();
    _restaurant=null;	// 餐厅 <表主键:WptRestaurant>  INT
    _wxuser=null;	// 关注用户 <表主键:WxUser>  INT
    _openid=null;	// openId  STR(40)
    _createTime=Env.getTranBeginTime();	// 创建时间  TIME
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptRestaurantBsn loadUniqueWxUserAccount(boolean lockFlag,Integer wxuser,Integer account) {
    return (WptRestaurantBsn)loadUnique(T.IDX_WX_USER_ACCOUNT,lockFlag,wxuser,account);
  }
  public static WptRestaurantBsn chkUniqueWxUserAccount(boolean lockFlag,Integer wxuser,Integer account) {
    return (WptRestaurantBsn)chkUnique(T.IDX_WX_USER_ACCOUNT,lockFlag,wxuser,account);
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
  public String getOpenid(){
    return _openid;
  }
  public void setOpenid(String openid){
    _openid=openid;
  }
  public Date getCreateTime(){
    return _createTime;
  }
  public void setCreateTime(Date createTime){
    _createTime=createTime;
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
