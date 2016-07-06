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
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WptCommissionJournal extends BeanInt<WptCommissionJournal> {
  private static final Log LOG = new Log(WptCommissionJournal.class);
  public static final Tb TB = new Tb(WptCommissionJournal.class, "佣金流水").setAutoIncrement().addActList();

  public enum T implements IEnumFld {// @formatter:off
	  // 主键
	  PKEY(TB.crtIntPkey()),
	  //订单编号
	  ORDERID(SYS.STR__40,"订单号"),
	  //完成日期
	  CREATE_TIME(SYS.CREATED_DATE_TIME,"创建时间"),
	  //订单金额
	  PRICE(SYS.AMT, true),//后加
	  //订单佣金
	  COMMISSION(SYS.AMT, true),//后加
	  //粉丝pkey
	  FANS(WxUser.fldOutKey("fans", "粉丝ID")),
	  //粉丝头像
	  IMAGE_URL(SYS.PHOTO__NULL,"头像"),
	  //粉丝昵称
	  NICKNAME(SYS.STR__200_NULL,"昵称"),
	  //微信用户
	  WXUSER(WxUser.fldOutKey("wxuser", "微信用户")),
	  //订单状态
	  STATUS(TB.crt(Wpt.OStatus.DEFAULT)),
	  CMB_WX(CmbWx.fldFlds()),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
	public static final Index IDX_WXUSER = TB.addIndex("wxuser",false, WXUSER);
	public static final Index IDX_ORDERID = TB.addIndex("orderid", false, ORDERID);
	public static final Index IDX_ORDERID_WXUSER = TB.addIndex("orderid_wxuser", true, WXUSER, ORDERID);
	  
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
  private String _orderid;	// 订单号  STR(40)
  private Date _createTime;	// 创建时间  TIME
  private BigDecimal _price;	// 金额  DEC(16,2)<null>
  private BigDecimal _commission;	// 金额  DEC(16,2)<null>
  private Integer _fans;	// 粉丝ID <表主键:WxUser>  INT
  private String _imageUrl;	// 头像  STR(200)<null>
  private String _nickname;	// 昵称  STR(200)<null>
  private Integer _wxuser;	// 微信用户 <表主键:WxUser>  INT
  private Byte _status;	// 订单状态 <OStatus>  BYTE
	// UNPAYMENT:0,未付款
	// NOTACCEPTED:1,未受理
	// ACCEPTED:2,已受理
	// DEPOSIT:3,已付定金
	// PAYMENT:4,已付款
	// FINISH:5,已完成
	// CLOSE:6,已关闭
	// CANCEL:7,申请取消订单
	// REFUND:8,申请退款
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptCommissionJournal init(){
		super.init();
    _orderid=null;	// 订单号  STR(40)
    _createTime=Env.getTranBeginTime();	// 创建时间  TIME
    _price=null;	// 金额  DEC(16,2)
    _commission=null;	// 金额  DEC(16,2)
    _fans=null;	// 粉丝ID <表主键:WxUser>  INT
    _imageUrl=null;	// 头像  STR(200)
    _nickname=null;	// 昵称  STR(200)
    _wxuser=null;	// 微信用户 <表主键:WxUser>  INT
    _status=OStatus.DEFAULT.getLine().getKey();	// 订单状态 <OStatus>  BYTE
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptCommissionJournal loadUniqueOrderid_wxuser(boolean lockFlag,Integer wxuser,String orderid) {
    return (WptCommissionJournal)loadUnique(T.IDX_ORDERID_WXUSER,lockFlag,wxuser,orderid);
  }
  public static WptCommissionJournal chkUniqueOrderid_wxuser(boolean lockFlag,Integer wxuser,String orderid) {
    return (WptCommissionJournal)chkUnique(T.IDX_ORDERID_WXUSER,lockFlag,wxuser,orderid);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getOrderid(){
    return _orderid;
  }
  public void setOrderid(String orderid){
    _orderid=orderid;
  }
  public Date getCreateTime(){
    return _createTime;
  }
  public void setCreateTime(Date createTime){
    _createTime=createTime;
  }
  public BigDecimal getPrice(){
    return _price;
  }
  public void setPrice(BigDecimal price){
    _price=price;
  }
  public BigDecimal getCommission(){
    return _commission;
  }
  public void setCommission(BigDecimal commission){
    _commission=commission;
  }
  public Integer getFans(){
    return _fans;
  }
  public void setFans(Integer fans){
    _fans=fans;
  }
  public WxUser gtFans(){
    if(getFans()==null)
      return null;
    return (WxUser)get(WxUser.class,getFans());
  }
  public void stFans(WxUser fans){
    if(fans==null)
      setFans(null);
    else
      setFans(fans.getPkey());
  }
  public String getImageUrl(){
    return _imageUrl;
  }
  public void setImageUrl(String imageUrl){
    _imageUrl=imageUrl;
  }
  public String getNickname(){
    return _nickname;
  }
  public void setNickname(String nickname){
    _nickname=nickname;
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
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OStatus gtStatus(){
    return (OStatus)(OStatus.UNPAYMENT.getLine().get(_status));
  }
  public void stStatus(OStatus status){
    _status=status.getLine().getKey();
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
