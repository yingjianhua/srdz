package irille.wx.wx;

import java.math.BigDecimal;
import java.util.Date;

import irille.core.sys.Sys.OSex;
import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.wx.Wx.OStatus;

public class WxUser extends BeanInt<WxUser> implements IExtName{
  private static final Log LOG = new Log(WxUser.class);
  public static final Tb TB = new Tb(WxUser.class, "关注用户").setAutoIncrement().addActList()
  		.addActOpt("rem", "修改备注").addActOpt("move","移动到").addActOpt("toBlack", "加入黑名单")
  		.addActOpt("sync", "同步用户").addActOpt("refresh","更新用户基本信息").addActOpt("uqs", "专属二维码设置");

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    STATUS(TB.crt(Wx.OStatus.DEFAULT)),
    OPEN_ID(SYS.STR__100,"粉丝ID"),
    UNION_ID(SYS.STR__100_NULL, "唯一ID"),
    NICKNAME(SYS.STR__200_NULL,"昵称"),
    SEX(SYS.SEX,true),
    CITY(SYS.STR__100_NULL,"城市"),
    PROVINCE(SYS.STR__100_NULL,"省份"),
    COUNTRY(SYS.STR__100_NULL,"国家"),
    IMAGE_URL(SYS.PHOTO__NULL,"头像"),
    REM(SYS.STR__200_NULL,"备注"),
    USER_GROUP(WxUserGroup.fldOutKey()),
    SUBSCRIBE_TIME(SYS.TIME__NULL,"关注时间"),
    INVITED1(WxUser.fldOutKey("invited1", "一级邀请人").setNull()),
    INVITED2(WxUser.fldOutKey("invited2", "二级邀请人").setNull()),
    IS_MEMBER(SYS.NY, "是否会员"),
    QRCODE(SYS.PHOTO__NULL, "专属二维码"),
    QRCODE_EXPIRE_TIME(SYS.TIME__NULL, "二维码到期时间"),
    HISTORY_COMMISSION(SYS.AMT, "历史佣金"),
    CASHABLE_COMMISSION(SYS.AMT, "可提现佣金"),
    ACCOUNT(WxAccount.fldOutKey()),//公众账号
    SYNC_TIME(SYS.TIME__NULL,"同步时间"),
    SYNC_STATUS(SYS.YN, "是否同步"),
    ROW_VERSION(SYS.ROW_VERSION),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
	public static final Index IDX_INVITED1_ACCOUNT = TB.addIndex("invited1Account", false, T.INVITED1, T.ACCOUNT);
	public static final Index IDX_INVITED2_ACCOUNT = TB.addIndex("invited2Account", false, T.INVITED2, T.ACCOUNT);
    public static final Index IDX_OPEN_ID_ACCOUNT = TB.addIndex("openIdAccount", true, T.OPEN_ID, T.ACCOUNT);
    public static final Index IDX_UNION_ID_ACCOUNT = TB.addIndex("unionIdAccount", true, T.UNION_ID, T.ACCOUNT);
    public static final Index IDX_NICKNAME_ACCOUNT = TB.addIndex("nicknameAccount", false, T.NICKNAME, T.ACCOUNT);
    public static final Index IDX_REM_ACCOUNT = TB.addIndex("remAccount", false, T.REM, T.ACCOUNT);
    public static final Index IDX_ACCOUNT = TB.addIndex("account", false, T.ACCOUNT);
    
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
	  return _nickname;
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
  private Byte _status;	// 关注状态 <OStatus>  BYTE
	// FOLLOW:1,关注
	// NOFOLLOW:2,取消关注
  private String _openId;	// 粉丝ID  STR(100)
  private String _unionId;	// 唯一ID  STR(100)<null>
  private String _nickname;	// 昵称  STR(200)<null>
  private Byte _sex;	// 性别 <OSex>  BYTE<null>
	// UNKNOW:0,未知
	// MALE:1,男
	// FEMALE:2,女
  private String _city;	// 城市  STR(100)<null>
  private String _province;	// 省份  STR(100)<null>
  private String _country;	// 国家  STR(100)<null>
  private String _imageUrl;	// 头像  STR(200)<null>
  private String _rem;	// 备注  STR(200)<null>
  private Integer _userGroup;	// 用户分组 <表主键:WxUserGroup>  INT
  private Date _subscribeTime;	// 关注时间  TIME<null>
  private Integer _invited1;	// 一级邀请人 <表主键:WxUser>  INT<null>
  private Integer _invited2;	// 二级邀请人 <表主键:WxUser>  INT<null>
  private Byte _isMember;	// 是否会员 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _qrcode;	// 专属二维码  STR(200)<null>
  private Date _qrcodeExpireTime;	// 二维码到期时间  TIME<null>
  private BigDecimal _historyCommission;	// 历史佣金  DEC(16,2)
  private BigDecimal _cashableCommission;	// 可提现佣金  DEC(16,2)
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Date _syncTime;	// 同步时间  TIME<null>
  private Byte _syncStatus;	// 是否同步 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxUser init(){
		super.init();
    _status=OStatus.DEFAULT.getLine().getKey();	// 关注状态 <OStatus>  BYTE
    _openId=null;	// 粉丝ID  STR(100)
    _unionId=null;	// 唯一ID  STR(100)
    _nickname=null;	// 昵称  STR(200)
    _sex=OSex.DEFAULT.getLine().getKey();	// 性别 <OSex>  BYTE
    _city=null;	// 城市  STR(100)
    _province=null;	// 省份  STR(100)
    _country=null;	// 国家  STR(100)
    _imageUrl=null;	// 头像  STR(200)
    _rem=null;	// 备注  STR(200)
    _userGroup=null;	// 用户分组 <表主键:WxUserGroup>  INT
    _subscribeTime=null;	// 关注时间  TIME
    _invited1=null;	// 一级邀请人 <表主键:WxUser>  INT
    _invited2=null;	// 二级邀请人 <表主键:WxUser>  INT
    _isMember=OYn.DEFAULT.getLine().getKey();	// 是否会员 <OYn>  BYTE
    _qrcode=null;	// 专属二维码  STR(200)
    _qrcodeExpireTime=null;	// 二维码到期时间  TIME
    _historyCommission=ZERO;	// 历史佣金  DEC(16,2)
    _cashableCommission=ZERO;	// 可提现佣金  DEC(16,2)
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _syncTime=null;	// 同步时间  TIME
    _syncStatus=OYn.DEFAULT.getLine().getKey();	// 是否同步 <OYn>  BYTE
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WxUser loadUniqueOpenIdAccount(boolean lockFlag,String openId,Integer account) {
    return (WxUser)loadUnique(T.IDX_OPEN_ID_ACCOUNT,lockFlag,openId,account);
  }
  public static WxUser chkUniqueOpenIdAccount(boolean lockFlag,String openId,Integer account) {
    return (WxUser)chkUnique(T.IDX_OPEN_ID_ACCOUNT,lockFlag,openId,account);
  }
  public static WxUser loadUniqueUnionIdAccount(boolean lockFlag,String unionId,Integer account) {
    return (WxUser)loadUnique(T.IDX_UNION_ID_ACCOUNT,lockFlag,unionId,account);
  }
  public static WxUser chkUniqueUnionIdAccount(boolean lockFlag,String unionId,Integer account) {
    return (WxUser)chkUnique(T.IDX_UNION_ID_ACCOUNT,lockFlag,unionId,account);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OStatus gtStatus(){
    return (OStatus)(OStatus.FOLLOW.getLine().get(_status));
  }
  public void stStatus(OStatus status){
    _status=status.getLine().getKey();
  }
  public String getOpenId(){
    return _openId;
  }
  public void setOpenId(String openId){
    _openId=openId;
  }
  public String getUnionId(){
    return _unionId;
  }
  public void setUnionId(String unionId){
    _unionId=unionId;
  }
  public String getNickname(){
    return _nickname;
  }
  public void setNickname(String nickname){
    _nickname=nickname;
  }
  public Byte getSex(){
    return _sex;
  }
  public void setSex(Byte sex){
    _sex=sex;
  }
  public OSex gtSex(){
    return (OSex)(OSex.UNKNOW.getLine().get(_sex));
  }
  public void stSex(OSex sex){
    _sex=sex.getLine().getKey();
  }
  public String getCity(){
    return _city;
  }
  public void setCity(String city){
    _city=city;
  }
  public String getProvince(){
    return _province;
  }
  public void setProvince(String province){
    _province=province;
  }
  public String getCountry(){
    return _country;
  }
  public void setCountry(String country){
    _country=country;
  }
  public String getImageUrl(){
    return _imageUrl;
  }
  public void setImageUrl(String imageUrl){
    _imageUrl=imageUrl;
  }
  public String getRem(){
    return _rem;
  }
  public void setRem(String rem){
    _rem=rem;
  }
  public Integer getUserGroup(){
    return _userGroup;
  }
  public void setUserGroup(Integer userGroup){
    _userGroup=userGroup;
  }
  public WxUserGroup gtUserGroup(){
    if(getUserGroup()==null)
      return null;
    return (WxUserGroup)get(WxUserGroup.class,getUserGroup());
  }
  public void stUserGroup(WxUserGroup userGroup){
    if(userGroup==null)
      setUserGroup(null);
    else
      setUserGroup(userGroup.getPkey());
  }
  public Date getSubscribeTime(){
    return _subscribeTime;
  }
  public void setSubscribeTime(Date subscribeTime){
    _subscribeTime=subscribeTime;
  }
  public Integer getInvited1(){
    return _invited1;
  }
  public void setInvited1(Integer invited1){
    _invited1=invited1;
  }
  public WxUser gtInvited1(){
    if(getInvited1()==null)
      return null;
    return (WxUser)get(WxUser.class,getInvited1());
  }
  public void stInvited1(WxUser invited1){
    if(invited1==null)
      setInvited1(null);
    else
      setInvited1(invited1.getPkey());
  }
  public Integer getInvited2(){
    return _invited2;
  }
  public void setInvited2(Integer invited2){
    _invited2=invited2;
  }
  public WxUser gtInvited2(){
    if(getInvited2()==null)
      return null;
    return (WxUser)get(WxUser.class,getInvited2());
  }
  public void stInvited2(WxUser invited2){
    if(invited2==null)
      setInvited2(null);
    else
      setInvited2(invited2.getPkey());
  }
  public Byte getIsMember(){
    return _isMember;
  }
  public void setIsMember(Byte isMember){
    _isMember=isMember;
  }
  public Boolean gtIsMember(){
    return byteToBoolean(_isMember);
  }
  public void stIsMember(Boolean isMember){
    _isMember=booleanToByte(isMember);
  }
  public String getQrcode(){
    return _qrcode;
  }
  public void setQrcode(String qrcode){
    _qrcode=qrcode;
  }
  public Date getQrcodeExpireTime(){
    return _qrcodeExpireTime;
  }
  public void setQrcodeExpireTime(Date qrcodeExpireTime){
    _qrcodeExpireTime=qrcodeExpireTime;
  }
  public BigDecimal getHistoryCommission(){
    return _historyCommission;
  }
  public void setHistoryCommission(BigDecimal historyCommission){
    _historyCommission=historyCommission;
  }
  public BigDecimal getCashableCommission(){
    return _cashableCommission;
  }
  public void setCashableCommission(BigDecimal cashableCommission){
    _cashableCommission=cashableCommission;
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
  public Date getSyncTime(){
    return _syncTime;
  }
  public void setSyncTime(Date syncTime){
    _syncTime=syncTime;
  }
  public Byte getSyncStatus(){
    return _syncStatus;
  }
  public void setSyncStatus(Byte syncStatus){
    _syncStatus=syncStatus;
  }
  public Boolean gtSyncStatus(){
    return byteToBoolean(_syncStatus);
  }
  public void stSyncStatus(Boolean syncStatus){
    _syncStatus=booleanToByte(syncStatus);
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
