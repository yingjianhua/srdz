package irille.wx.wa;



import java.util.Date;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wa.Wa.OActVoteStatus;
import irille.wx.wx.WxAccount;
import irille.wx.js.JsMenuShare;

public class WaActVote extends BeanInt<WaActVote> implements IExtName {
	private static final Log LOG = new Log(WaActVote.class);
	public static final Tb TB = new Tb(WaActVote.class, "投票活动").setAutoIncrement().addActIUDL().addActOpt("doPublish", "发布", "green-icon")
		      .addActOpt("doClose", "关闭", "red-icon").addActOpt("edit", "编辑", "edit-icon").addActOpt("share", "分享设置", "edit-icon");

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		NAME(SYS.NAME__40), 
		ENTRY_START_TIME(SYS.DATE_TIME, "报名开始时间"), 
		ENTRY_END_TIME(SYS.DATE_TIME,"报名结束时间"), 
		ACT_START_TIME(SYS.DATE_TIME, "活动开始时间"), 
		ACT_END_TIME(SYS.DATE_TIME,"活动结束时间"),
		ACT_TEMPLATE(WaActTemplate.fldOutKey().setName("页面模板")),
		JS_MENUSHARE(JsMenuShare.fldOutKey().setName("分享")),
		ACT_Config(WaActConfig.fldOutKey().setName("参数设置")),
		VOTE_Config(WaActVoteConfig.fldOutKey().setName("投票设置")),
		STATUS(TB.crt(Wa.OActVoteStatus.DEFAULT)),
	    DES(TB.crtText("des", "描述", 65535, true)),
		CMB_WX(CmbWx.fldFlds()),

		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引

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
	@Override
	  public String getExtName() {
	    return getName();
	  }

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 名称  STR(40)
  private Date _entryStartTime;	// 报名开始时间  TIME
  private Date _entryEndTime;	// 报名结束时间  TIME
  private Date _actStartTime;	// 活动开始时间  TIME
  private Date _actEndTime;	// 活动结束时间  TIME
  private Integer _actTemplate;	// 页面模板 <表主键:WaActTemplate>  INT
  private Integer _jsMenushare;	// 分享 <表主键:JsMenuShare>  INT
  private Integer _actConfig;	// 参数设置 <表主键:WaActConfig>  INT
  private Integer _voteConfig;	// 投票设置 <表主键:WaActVoteConfig>  INT
  private Byte _status;	// 活动状态 <OActVoteStatus>  BYTE
	// INIT:0,初始
	// PUBLISH:1,已发布
	// ENTRYFIN:2,报名结束
	// ACTFIN:3,活动结束
	// CLOSE:4,已关闭
  private String _des;	// 描述  TEXT(65535)<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActVote init(){
		super.init();
    _name=null;	// 名称  STR(40)
    _entryStartTime=Env.getTranBeginTime();	// 报名开始时间  TIME
    _entryEndTime=Env.getTranBeginTime();	// 报名结束时间  TIME
    _actStartTime=Env.getTranBeginTime();	// 活动开始时间  TIME
    _actEndTime=Env.getTranBeginTime();	// 活动结束时间  TIME
    _actTemplate=null;	// 页面模板 <表主键:WaActTemplate>  INT
    _jsMenushare=null;	// 分享 <表主键:JsMenuShare>  INT
    _actConfig=null;	// 参数设置 <表主键:WaActConfig>  INT
    _voteConfig=null;	// 投票设置 <表主键:WaActVoteConfig>  INT
    _status=OActVoteStatus.DEFAULT.getLine().getKey();	// 活动状态 <OActVoteStatus>  BYTE
    _des=null;	// 描述  TEXT(65535)
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
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public Date getEntryStartTime(){
    return _entryStartTime;
  }
  public void setEntryStartTime(Date entryStartTime){
    _entryStartTime=entryStartTime;
  }
  public Date getEntryEndTime(){
    return _entryEndTime;
  }
  public void setEntryEndTime(Date entryEndTime){
    _entryEndTime=entryEndTime;
  }
  public Date getActStartTime(){
    return _actStartTime;
  }
  public void setActStartTime(Date actStartTime){
    _actStartTime=actStartTime;
  }
  public Date getActEndTime(){
    return _actEndTime;
  }
  public void setActEndTime(Date actEndTime){
    _actEndTime=actEndTime;
  }
  public Integer getActTemplate(){
    return _actTemplate;
  }
  public void setActTemplate(Integer actTemplate){
    _actTemplate=actTemplate;
  }
  public WaActTemplate gtActTemplate(){
    if(getActTemplate()==null)
      return null;
    return (WaActTemplate)get(WaActTemplate.class,getActTemplate());
  }
  public void stActTemplate(WaActTemplate actTemplate){
    if(actTemplate==null)
      setActTemplate(null);
    else
      setActTemplate(actTemplate.getPkey());
  }
  public Integer getJsMenushare(){
    return _jsMenushare;
  }
  public void setJsMenushare(Integer jsMenushare){
    _jsMenushare=jsMenushare;
  }
  public JsMenuShare gtJsMenushare(){
    if(getJsMenushare()==null)
      return null;
    return (JsMenuShare)get(JsMenuShare.class,getJsMenushare());
  }
  public void stJsMenushare(JsMenuShare jsMenushare){
    if(jsMenushare==null)
      setJsMenushare(null);
    else
      setJsMenushare(jsMenushare.getPkey());
  }
  public Integer getActConfig(){
    return _actConfig;
  }
  public void setActConfig(Integer actConfig){
    _actConfig=actConfig;
  }
  public WaActConfig gtActConfig(){
    if(getActConfig()==null)
      return null;
    return (WaActConfig)get(WaActConfig.class,getActConfig());
  }
  public void stActConfig(WaActConfig actConfig){
    if(actConfig==null)
      setActConfig(null);
    else
      setActConfig(actConfig.getPkey());
  }
  public Integer getVoteConfig(){
    return _voteConfig;
  }
  public void setVoteConfig(Integer voteConfig){
    _voteConfig=voteConfig;
  }
  public WaActVoteConfig gtVoteConfig(){
    if(getVoteConfig()==null)
      return null;
    return (WaActVoteConfig)get(WaActVoteConfig.class,getVoteConfig());
  }
  public void stVoteConfig(WaActVoteConfig voteConfig){
    if(voteConfig==null)
      setVoteConfig(null);
    else
      setVoteConfig(voteConfig.getPkey());
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OActVoteStatus gtStatus(){
    return (OActVoteStatus)(OActVoteStatus.INIT.getLine().get(_status));
  }
  public void stStatus(OActVoteStatus status){
    _status=status.getLine().getKey();
  }
  public String getDes(){
    return _des;
  }
  public void setDes(String des){
    _des=des;
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
