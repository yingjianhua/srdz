package irille.wx.wx;

import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.wx.Wx.OAccountType;

import java.util.Date;

public class WxAccount extends BeanInt<WxAccount>implements IExtName {
  private static final Log LOG = new Log(WxAccount.class);
  public static final Tb TB = new Tb(WxAccount.class, "公众帐号").setAutoIncrement().addActIUDL().addActOpt("cert", "上传商户证书");

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()), // 主键
    ACCOUNT_NAME(SYS.STR__100, "名称"), // 公众帐号名称
    ACCOUNT_TOKEN(SYS.STR__100, "TOKEN"), // 公众帐号TOKEN
    ACCOUNT_NUMBER(SYS.STR__100, "微信号"), // 公众微信号
    ACCOUNT_ID(SYS.STR__100, "原始ID"), // 原始ID
    ACCOUNT_TYPE(TB.crt(Wx.OAccountType.DEFAULT)), // 公众帐号类型
    OPEN_PLAT(WxOpenPlat.fldOutKey().setNull()),//绑定的开放平台
    AGENT_ACCOUNT(WxAccount.fldOutKey("agentAccount", "代理服务号").setNull()),//代理服务号
    ACCOUNT_EMAIL(SYS.STR__100_NULL, "电子邮箱"), // 电子邮箱
    ACCOUNT_DESC(SYS.STR__200_NULL, "描述"), // 公众帐号描述
    ACCOUNT_APPID(SYS.STR__200, "APPID"), // 公众帐号APPID
    ACCOUNT_APPSECRET(SYS.STR__200, "APPSECRET"), // 公众帐号APPSECRET
    ACCESS_TOKEN(SYS.STR__200_NULL, "ACCESS_TOKEN"), // ACCESS_TOKEN
    ACCESS_TIME(SYS.TIME__NULL, "TOKEN获取时间"), // TOKEN获取时间
    JSAPI_TICKET(SYS.STR__200_NULL, "JSAPI_TICKET"), //JSAPI_TICKET
    MCH_ID(SYS.STR__200_NULL, "商户号"), //商户号
    MCH_KEY(SYS.STR__100_NULL, "商户平台密钥"),//商户平台密钥
    MCH_PAY_CERT(SYS.STR__100_NULL, "商户平台支付证书路径"),//商户平台支付证书路径
    USER_SYS(SYS.USER_SYS, "负责人"),//用户
    ROW_VERSION(SYS.ROW_VERSION),
    // >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    // >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    // <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
    public static final Index IDX_ACCOUNT_ID = TB.addIndex("accountId", true, T.ACCOUNT_ID);
    public static final Index IDX_ACCOUNT_APPID = TB.addIndex("accountAppid", true, T.ACCOUNT_APPID);
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
    return getAccountName();
  }

  static { // 在此可以加一些对FLD进行特殊设定的代码
    T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
  }

  // @formatter:on
  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName()).setType(null);
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name).setType(null);
  }
  
  public static Fld fldOneToOne() {
	return fldOneToOne(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOneToOne(String code, String name) {
	return Tb.crtOneToOne(TB, code, name);
  }

  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _accountName;	// 名称  STR(100)
  private String _accountToken;	// TOKEN  STR(100)
  private String _accountNumber;	// 微信号  STR(100)
  private String _accountId;	// 原始ID  STR(100)
  private Byte _accountType;	// 公众号类型 <OAccountType>  BYTE
	// SERVICE:1,服务号
	// SUBSCRIPTION:2,订阅号
  private Integer _openPlat;	// 开放平台 <表主键:WxOpenPlat>  INT<null>
  private Integer _agentAccount;	// 代理服务号 <表主键:WxAccount>  INT<null>
  private String _accountEmail;	// 电子邮箱  STR(100)<null>
  private String _accountDesc;	// 描述  STR(200)<null>
  private String _accountAppid;	// APPID  STR(200)
  private String _accountAppsecret;	// APPSECRET  STR(200)
  private String _accessToken;	// ACCESS_TOKEN  STR(200)<null>
  private Date _accessTime;	// TOKEN获取时间  TIME<null>
  private String _jsapiTicket;	// JSAPI_TICKET  STR(200)<null>
  private String _mchId;	// 商户号  STR(200)<null>
  private String _mchKey;	// 商户平台密钥  STR(100)<null>
  private String _mchPayCert;	// 商户平台支付证书路径  STR(100)<null>
  private Integer _userSys;	// 负责人 <表主键:SysUser>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxAccount init(){
		super.init();
    _accountName=null;	// 名称  STR(100)
    _accountToken=null;	// TOKEN  STR(100)
    _accountNumber=null;	// 微信号  STR(100)
    _accountId=null;	// 原始ID  STR(100)
    _accountType=OAccountType.DEFAULT.getLine().getKey();	// 公众号类型 <OAccountType>  BYTE
    _openPlat=null;	// 开放平台 <表主键:WxOpenPlat>  INT
    _agentAccount=null;	// 代理服务号 <表主键:WxAccount>  INT
    _accountEmail=null;	// 电子邮箱  STR(100)
    _accountDesc=null;	// 描述  STR(200)
    _accountAppid=null;	// APPID  STR(200)
    _accountAppsecret=null;	// APPSECRET  STR(200)
    _accessToken=null;	// ACCESS_TOKEN  STR(200)
    _accessTime=null;	// TOKEN获取时间  TIME
    _jsapiTicket=null;	// JSAPI_TICKET  STR(200)
    _mchId=null;	// 商户号  STR(200)
    _mchKey=null;	// 商户平台密钥  STR(100)
    _mchPayCert=null;	// 商户平台支付证书路径  STR(100)
    _userSys=null;	// 负责人 <表主键:SysUser>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WxAccount loadUniqueAccountId(boolean lockFlag,String accountId) {
    return (WxAccount)loadUnique(T.IDX_ACCOUNT_ID,lockFlag,accountId);
  }
  public static WxAccount chkUniqueAccountId(boolean lockFlag,String accountId) {
    return (WxAccount)chkUnique(T.IDX_ACCOUNT_ID,lockFlag,accountId);
  }
  public static WxAccount loadUniqueAccountAppid(boolean lockFlag,String accountAppid) {
    return (WxAccount)loadUnique(T.IDX_ACCOUNT_APPID,lockFlag,accountAppid);
  }
  public static WxAccount chkUniqueAccountAppid(boolean lockFlag,String accountAppid) {
    return (WxAccount)chkUnique(T.IDX_ACCOUNT_APPID,lockFlag,accountAppid);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getAccountName(){
    return _accountName;
  }
  public void setAccountName(String accountName){
    _accountName=accountName;
  }
  public String getAccountToken(){
    return _accountToken;
  }
  public void setAccountToken(String accountToken){
    _accountToken=accountToken;
  }
  public String getAccountNumber(){
    return _accountNumber;
  }
  public void setAccountNumber(String accountNumber){
    _accountNumber=accountNumber;
  }
  public String getAccountId(){
    return _accountId;
  }
  public void setAccountId(String accountId){
    _accountId=accountId;
  }
  public Byte getAccountType(){
    return _accountType;
  }
  public void setAccountType(Byte accountType){
    _accountType=accountType;
  }
  public OAccountType gtAccountType(){
    return (OAccountType)(OAccountType.SERVICE.getLine().get(_accountType));
  }
  public void stAccountType(OAccountType accountType){
    _accountType=accountType.getLine().getKey();
  }
  public Integer getOpenPlat(){
    return _openPlat;
  }
  public void setOpenPlat(Integer openPlat){
    _openPlat=openPlat;
  }
  public WxOpenPlat gtOpenPlat(){
    if(getOpenPlat()==null)
      return null;
    return (WxOpenPlat)get(WxOpenPlat.class,getOpenPlat());
  }
  public void stOpenPlat(WxOpenPlat openPlat){
    if(openPlat==null)
      setOpenPlat(null);
    else
      setOpenPlat(openPlat.getPkey());
  }
  public Integer getAgentAccount(){
    return _agentAccount;
  }
  public void setAgentAccount(Integer agentAccount){
    _agentAccount=agentAccount;
  }
  public WxAccount gtAgentAccount(){
    if(getAgentAccount()==null)
      return null;
    return (WxAccount)get(WxAccount.class,getAgentAccount());
  }
  public void stAgentAccount(WxAccount agentAccount){
    if(agentAccount==null)
      setAgentAccount(null);
    else
      setAgentAccount(agentAccount.getPkey());
  }
  public String getAccountEmail(){
    return _accountEmail;
  }
  public void setAccountEmail(String accountEmail){
    _accountEmail=accountEmail;
  }
  public String getAccountDesc(){
    return _accountDesc;
  }
  public void setAccountDesc(String accountDesc){
    _accountDesc=accountDesc;
  }
  public String getAccountAppid(){
    return _accountAppid;
  }
  public void setAccountAppid(String accountAppid){
    _accountAppid=accountAppid;
  }
  public String getAccountAppsecret(){
    return _accountAppsecret;
  }
  public void setAccountAppsecret(String accountAppsecret){
    _accountAppsecret=accountAppsecret;
  }
  public String getAccessToken(){
    return _accessToken;
  }
  public void setAccessToken(String accessToken){
    _accessToken=accessToken;
  }
  public Date getAccessTime(){
    return _accessTime;
  }
  public void setAccessTime(Date accessTime){
    _accessTime=accessTime;
  }
  public String getJsapiTicket(){
    return _jsapiTicket;
  }
  public void setJsapiTicket(String jsapiTicket){
    _jsapiTicket=jsapiTicket;
  }
  public String getMchId(){
    return _mchId;
  }
  public void setMchId(String mchId){
    _mchId=mchId;
  }
  public String getMchKey(){
    return _mchKey;
  }
  public void setMchKey(String mchKey){
    _mchKey=mchKey;
  }
  public String getMchPayCert(){
    return _mchPayCert;
  }
  public void setMchPayCert(String mchPayCert){
    _mchPayCert=mchPayCert;
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
