package irille.wx.wx;

import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.wx.Wx.OSyncStatus;

import java.util.Date;

public class WxUserGroup extends BeanInt<WxUserGroup> implements IExtName {
	public static final Tb TB = new Tb(WxUserGroup.class, "用户分组").setAutoIncrement().addActIUDL()
	    .addActOpt("sync", "微信同步", "win-refresh-icon", false);

	public enum T implements IEnumFld {//@formatter:off
		PKEY(TB.crtIntPkey()),
		NAME(SYS.NAME__100,"名称"),
		SYNC_STATUS(TB.crt(Wx.OSyncStatus.INIT)),
		WXID(SYS.INT, "微信ID", true),
		ACCOUNT(WxAccount.fldOutKey()),//公众账号
		UPDATED_TIME(SYS.UPDATED_DATE_TIME,"更新时间"),
		ROW_VERSION(SYS.ROW_VERSION),
		//>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		//>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		//<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		public static final Index IDX_WXID_ACCOUNT = TB.addIndex("wxidAccount", true,T.WXID, T.ACCOUNT);
		private Fld _fld;
		private T(Class clazz,String name,boolean... isnull) 
			{_fld=TB.addOutKey(clazz,this,name,isnull);	}
		private T(IEnumFld fld,boolean... isnull) { this(fld,null,isnull); } 
		private T(IEnumFld fld, String name,boolean... null1) {
			_fld=TB.add(fld,this,name,null1);}
		private T(IEnumFld fld, String name,int strLen) {
			_fld=TB.add(fld,this,name,strLen);}
		private T(Fld fld) {_fld=TB.add(fld,this); }
		public Fld getFld(){return _fld;}
	}

	static { //在此可以加一些对FLD进行特殊设定的代码
		T.PKEY.getFld().getTb().lockAllFlds();//加锁所有字段,不可以修改
	}
	@Override
	public String getExtName() {
	  return getName();
	}
	
	//@formatter:on
	public static Fld fldOutKey() {
		return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}

	//>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 名称  STR(100)
  private Byte _syncStatus;	// 同步状态 <OSyncStatus>  BYTE
	// INIT:1,未同步
	// SYNC:2,已同步
	// DEL:9,已删除
  private Integer _wxid;	// 微信ID  INT<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Date _updatedTime;	// 更新时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxUserGroup init(){
		super.init();
    _name=null;	// 名称  STR(100)
    _syncStatus=OSyncStatus.DEFAULT.getLine().getKey();	// 同步状态 <OSyncStatus>  BYTE
    _wxid=null;	// 微信ID  INT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _updatedTime=Env.getTranBeginTime();	// 更新时间  TIME
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WxUserGroup loadUniqueWxidAccount(boolean lockFlag,Integer wxid,Integer account) {
    return (WxUserGroup)loadUnique(T.IDX_WXID_ACCOUNT,lockFlag,wxid,account);
  }
  public static WxUserGroup chkUniqueWxidAccount(boolean lockFlag,Integer wxid,Integer account) {
    return (WxUserGroup)chkUnique(T.IDX_WXID_ACCOUNT,lockFlag,wxid,account);
  }
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
  public Byte getSyncStatus(){
    return _syncStatus;
  }
  public void setSyncStatus(Byte syncStatus){
    _syncStatus=syncStatus;
  }
  public OSyncStatus gtSyncStatus(){
    return (OSyncStatus)(OSyncStatus.INIT.getLine().get(_syncStatus));
  }
  public void stSyncStatus(OSyncStatus syncStatus){
    _syncStatus=syncStatus.getLine().getKey();
  }
  public Integer getWxid(){
    return _wxid;
  }
  public void setWxid(Integer wxid){
    _wxid=wxid;
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
  public Date getUpdatedTime(){
    return _updatedTime;
  }
  public void setUpdatedTime(Date updatedTime){
    _updatedTime=updatedTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	//<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
