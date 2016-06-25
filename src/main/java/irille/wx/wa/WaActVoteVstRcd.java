package irille.wx.wa;

import java.util.Date;

import irille.pub.Log;
import irille.pub.bean.BeanLong;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wa.Wa.OActPageType;
import irille.wx.wa.Wa.OActVisitType;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WaActVoteVstRcd extends BeanLong<WaActVoteVstRcd> {
	private static final Log LOG = new Log(WaActVoteVstRcd.class);
	public static final Tb TB = new Tb(WaActVoteVstRcd.class, "投票活动访问记录", "访问记录").setAutoIncrement().addActIUDL();
	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtLongPkey()), // 主键
		VOTE(WaActVote.fldOutKey()),
		USER(WxUser.fldOutKey().setNull()),
		PAGETYPE(TB.crt(Wa.OActPageType.DEFAULT)),//页面类型
		VISITTYPE(TB.crt(Wa.OActVisitType.DEFAULT)),//访问类型
		CREATED_TIME(SYS.CREATED_DATE_TIME, "访问时间"),
		IP(SYS.IP__NULL),
		CMB_WX(CmbWx.fldFlds()),
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		// public static final Index IDX_ACT_WX_USER = TB.addIndex("actWxUser",
		// true,T.ACT,T.WX_USER);
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
	  //@formatter:on
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
  private Long _pkey;	// 编号  LONG
  private Integer _vote;	// 投票活动 <表主键:WaActVote>  INT
  private Integer _user;	// 关注用户 <表主键:WxUser>  INT<null>
  private Byte _pagetype;	// 页面类型 <OActPageType>  BYTE
	// HOMEPAGE:1,首页
	// ENTRYPAGE:2,报名页
	// RANKINGPAGE:3,排行页
	// INFOPAGRE:4,详情页
	// CUSTOMPAGE:99,自定义页
  private Byte _visittype;	// 访问类型 <OActVisitType>  BYTE
	// VISIT:1,访问
	// ENTRY:2,报名
	// VOTE:3,投票
	// OTHER:99,操作
  private Date _createdTime;	// 访问时间  TIME
  private String _ip;	// IP地址  STR(30)<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActVoteVstRcd init(){
		super.init();
    _vote=null;	// 投票活动 <表主键:WaActVote>  INT
    _user=null;	// 关注用户 <表主键:WxUser>  INT
    _pagetype=OActPageType.DEFAULT.getLine().getKey();	// 页面类型 <OActPageType>  BYTE
    _visittype=OActVisitType.DEFAULT.getLine().getKey();	// 访问类型 <OActVisitType>  BYTE
    _createdTime=Env.getTranBeginTime();	// 访问时间  TIME
    _ip=null;	// IP地址  STR(30)
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Long getPkey(){
    return _pkey;
  }
  public void setPkey(Long pkey){
    _pkey=pkey;
  }
  public Integer getVote(){
    return _vote;
  }
  public void setVote(Integer vote){
    _vote=vote;
  }
  public WaActVote gtVote(){
    if(getVote()==null)
      return null;
    return (WaActVote)get(WaActVote.class,getVote());
  }
  public void stVote(WaActVote vote){
    if(vote==null)
      setVote(null);
    else
      setVote(vote.getPkey());
  }
  public Integer getUser(){
    return _user;
  }
  public void setUser(Integer user){
    _user=user;
  }
  public WxUser gtUser(){
    if(getUser()==null)
      return null;
    return (WxUser)get(WxUser.class,getUser());
  }
  public void stUser(WxUser user){
    if(user==null)
      setUser(null);
    else
      setUser(user.getPkey());
  }
  public Byte getPagetype(){
    return _pagetype;
  }
  public void setPagetype(Byte pagetype){
    _pagetype=pagetype;
  }
  public OActPageType gtPagetype(){
    return (OActPageType)(OActPageType.HOMEPAGE.getLine().get(_pagetype));
  }
  public void stPagetype(OActPageType pagetype){
    _pagetype=pagetype.getLine().getKey();
  }
  public Byte getVisittype(){
    return _visittype;
  }
  public void setVisittype(Byte visittype){
    _visittype=visittype;
  }
  public OActVisitType gtVisittype(){
    return (OActVisitType)(OActVisitType.VISIT.getLine().get(_visittype));
  }
  public void stVisittype(OActVisitType visittype){
    _visittype=visittype.getLine().getKey();
  }
  public Date getCreatedTime(){
    return _createdTime;
  }
  public void setCreatedTime(Date createdTime){
    _createdTime=createdTime;
  }
  public String getIp(){
    return _ip;
  }
  public void setIp(String ip){
    _ip=ip;
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
