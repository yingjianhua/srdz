package irille.wx.wa;



import java.util.Date;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wa.Wa.OActEntryStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WaActVoteEntry extends BeanInt<WaActVoteEntry> implements IExtName{
	  private static final Log LOG = new Log(WaActVoteEntry.class);
	  public static final Tb TB = new Tb(WaActVoteEntry.class, "报名记录").setAutoIncrement().addActIns().addActUpd().addActList().addActApprove().addActOpt("block", "不通过","greean-icon").addActExport();

	  public enum T implements IEnumFld {// @formatter:off
	    PKEY(TB.crtIntPkey()),//主键
	    VOTE(WaActVote.fldOutKey().setName("活动")),
	    WX_USER(WxUser.fldOutKey().setName("参赛者")),
	    NUMBER(SYS.INT,"编号"),
	    NAME_PERSON(SYS.NAME__PERSON_40,"姓名"),
	    PHONE_PERSON(SYS.MOBILE__NULL,"电话"),
	    DES(SYS.DESCRIP__200_NULL,"描述"),
	    ENTRY_TIME(SYS.DATE_TIME,"报名时间"),
	    VOTE_COUNT(SYS.INT,"投票数"),
	    STATUS(TB.crt(Wa.OActEntryStatus.DEFAULT)),
	    CMB_WX(CmbWx.fldFlds()),
	    
	    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
	    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
	    ;
	    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
	    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
	    // 索引
	    public static final Index IDX_VOTE_WX_USER = TB.addIndex("voteWxUser", true,T.VOTE,T.WX_USER);
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
	      _fld = TB.add(fld,this);
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
	  @Override
	  public String getExtName() {
	    return gtWxUser().getExtName();
	  }
	  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _vote;	// 活动 <表主键:WaActVote>  INT
  private Integer _wxUser;	// 参赛者 <表主键:WxUser>  INT
  private Integer _number;	// 编号  INT
  private String _namePerson;	// 姓名  STR(40)
  private String _phonePerson;	// 电话  STR(20)<null>
  private String _des;	// 描述  STR(200)<null>
  private Date _entryTime;	// 报名时间  TIME
  private Integer _voteCount;	// 投票数  INT
  private Byte _status;	// 报名状态 <OActEntryStatus>  BYTE
	// INIT:0,初始
	// APPR:1,已审核
	// FAILED:2,不通过
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActVoteEntry init(){
		super.init();
    _vote=null;	// 活动 <表主键:WaActVote>  INT
    _wxUser=null;	// 参赛者 <表主键:WxUser>  INT
    _number=0;	// 编号  INT
    _namePerson=null;	// 姓名  STR(40)
    _phonePerson=null;	// 电话  STR(20)
    _des=null;	// 描述  STR(200)
    _entryTime=Env.getTranBeginTime();	// 报名时间  TIME
    _voteCount=0;	// 投票数  INT
    _status=OActEntryStatus.DEFAULT.getLine().getKey();	// 报名状态 <OActEntryStatus>  BYTE
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WaActVoteEntry loadUniqueVoteWxUser(boolean lockFlag,Integer vote,Integer wxUser) {
    return (WaActVoteEntry)loadUnique(T.IDX_VOTE_WX_USER,lockFlag,vote,wxUser);
  }
  public static WaActVoteEntry chkUniqueVoteWxUser(boolean lockFlag,Integer vote,Integer wxUser) {
    return (WaActVoteEntry)chkUnique(T.IDX_VOTE_WX_USER,lockFlag,vote,wxUser);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
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
  public Integer getNumber(){
    return _number;
  }
  public void setNumber(Integer number){
    _number=number;
  }
  public String getNamePerson(){
    return _namePerson;
  }
  public void setNamePerson(String namePerson){
    _namePerson=namePerson;
  }
  public String getPhonePerson(){
    return _phonePerson;
  }
  public void setPhonePerson(String phonePerson){
    _phonePerson=phonePerson;
  }
  public String getDes(){
    return _des;
  }
  public void setDes(String des){
    _des=des;
  }
  public Date getEntryTime(){
    return _entryTime;
  }
  public void setEntryTime(Date entryTime){
    _entryTime=entryTime;
  }
  public Integer getVoteCount(){
    return _voteCount;
  }
  public void setVoteCount(Integer voteCount){
    _voteCount=voteCount;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OActEntryStatus gtStatus(){
    return (OActEntryStatus)(OActEntryStatus.INIT.getLine().get(_status));
  }
  public void stStatus(OActEntryStatus status){
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
