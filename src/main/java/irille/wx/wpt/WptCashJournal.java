package irille.wx.wpt;

import java.math.BigDecimal;
import java.util.Date;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WptCashJournal extends BeanInt<WptCashJournal> {
  private static final Log LOG = new Log(WptCashJournal.class);
  public static final Tb TB = new Tb(WptCashJournal.class, "提现流水").setAutoIncrement().addActList();

  public enum T implements IEnumFld {// @formatter:off
	  PKEY(TB.crtIntPkey()),
	  PRICE(SYS.AMT, true),
	  WXUSER(WxUser.fldOutKey("wxuser", "微信用户")),
	  CREATE_TIME(SYS.CREATED_DATE_TIME,"提现时间"),
	  CMB_WX(CmbWx.fldFlds()),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
	public static final Index IDX_WXUSER = TB.addIndex("wxuser",true, WXUSER);
	  
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
  //@formatter:on
  public static Fld fldOutKey() {
	    Fld fld = fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	    fld.setType(null);
	    return fld;
	  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }
  
  public static Fld fldOneToOne() {
		return fldOneToOne(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOneToOne(String code, String name) {
		return Tb.crtOneToOne(TB, code, name);
	}
  
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private BigDecimal _price;	// 金额  DEC(16,2)<null>
  private Integer _wxuser;	// 微信用户 <表主键:WxUser>  INT
  private Date _createTime;	// 提现时间  TIME
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptCashJournal init(){
		super.init();
    _price=null;	// 金额  DEC(16,2)
    _wxuser=null;	// 微信用户 <表主键:WxUser>  INT
    _createTime=Env.getTranBeginTime();	// 提现时间  TIME
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptCashJournal loadUniqueWxuser(boolean lockFlag,Integer wxuser) {
    return (WptCashJournal)loadUnique(T.IDX_WXUSER,lockFlag,wxuser);
  }
  public static WptCashJournal chkUniqueWxuser(boolean lockFlag,Integer wxuser) {
    return (WptCashJournal)chkUnique(T.IDX_WXUSER,lockFlag,wxuser);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public BigDecimal getPrice(){
    return _price;
  }
  public void setPrice(BigDecimal price){
    _price=price;
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

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
