package irille.wx.wa;


import java.util.Date;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WaActVoteRecord extends BeanInt<WaActVoteRecord>{
	  private static final Log LOG = new Log(WaActVoteRecord.class);
	  public static final Tb TB = new Tb(WaActVoteRecord.class, "投票记录").setAutoIncrement().addActList();

	  public enum T implements IEnumFld {// @formatter:off
	    PKEY(TB.crtIntPkey()),//主键
	    ACT(WaActVote.fldOutKey().setName("活动")),//活动
	    ENTRY_RECORD(WaActVoteEntry.fldOutKey().setName("参赛者")),//参赛者
	    WX_USER(WxUser.fldOutKey().setName("投票者")),//投票者
	    VOTE_TIME(SYS.DATE_TIME,"投票时间"),
	    IP(SYS.IP__NULL,"IP地址"),
	    CMB_WX(CmbWx.fldFlds()),
	    
	    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
	    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
	    ;
	    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
	    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
	    // 索引
//	    public static final Index IDX_ACT_WX_USER = TB.addIndex("actWxUser", true,T.ACT,T.WX_USER);
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
	  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _act;	// 活动 <表主键:WaActVote>  INT
  private Integer _entryRecord;	// 参赛者 <表主键:WaActVoteEntry>  INT
  private Integer _wxUser;	// 投票者 <表主键:WxUser>  INT
  private Date _voteTime;	// 投票时间  TIME
  private String _ip;	// IP地址  STR(30)<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActVoteRecord init(){
		super.init();
    _act=null;	// 活动 <表主键:WaActVote>  INT
    _entryRecord=null;	// 参赛者 <表主键:WaActVoteEntry>  INT
    _wxUser=null;	// 投票者 <表主键:WxUser>  INT
    _voteTime=Env.getTranBeginTime();	// 投票时间  TIME
    _ip=null;	// IP地址  STR(30)
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getAct(){
    return _act;
  }
  public void setAct(Integer act){
    _act=act;
  }
  public WaActVote gtAct(){
    if(getAct()==null)
      return null;
    return (WaActVote)get(WaActVote.class,getAct());
  }
  public void stAct(WaActVote act){
    if(act==null)
      setAct(null);
    else
      setAct(act.getPkey());
  }
  public Integer getEntryRecord(){
    return _entryRecord;
  }
  public void setEntryRecord(Integer entryRecord){
    _entryRecord=entryRecord;
  }
  public WaActVoteEntry gtEntryRecord(){
    if(getEntryRecord()==null)
      return null;
    return (WaActVoteEntry)get(WaActVoteEntry.class,getEntryRecord());
  }
  public void stEntryRecord(WaActVoteEntry entryRecord){
    if(entryRecord==null)
      setEntryRecord(null);
    else
      setEntryRecord(entryRecord.getPkey());
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
  public Date getVoteTime(){
    return _voteTime;
  }
  public void setVoteTime(Date voteTime){
    _voteTime=voteTime;
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

	  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
