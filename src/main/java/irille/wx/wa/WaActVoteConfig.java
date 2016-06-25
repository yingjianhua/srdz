package irille.wx.wa;

import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WaActVoteConfig extends BeanInt<WaActVoteConfig> implements IExtName {
	  private static final Log LOG = new Log(WaActVoteConfig.class);
	  public static final Tb TB = new Tb(WaActVoteConfig.class, "参数设置").setAutoIncrement().addActIUDL();

	  public enum T implements IEnumFld {// @formatter:off
	    PKEY(TB.crtIntPkey()), // 主键
	    NAME(SYS.NAME__100, "名称"),//名称
	    ENTRY_APPR(SYS.NY, "报名审核"),//报名审核
	    //TODO REPLY_VOTE(SYS.NY, "回复投票"),//回复投票
	    PIC_LIMIT(SYS.INT_PLUS_OR_ZERO, "报名图片限制"),//报名图片限制
	    SHOW_RANKING(SYS.YN, "详情页显示排名"),//详情页显示排名
	    PIC_WIDTH(SYS.INT_PLUS_OR_ZERO, "图片宽度限制"),//图片宽度限制
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
	    return getName();
	  }

	  // >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 名称  STR(100)
  private Byte _entryAppr;	// 报名审核 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _picLimit;	// 报名图片限制  INT
  private Byte _showRanking;	// 详情页显示排名 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _picWidth;	// 图片宽度限制  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActVoteConfig init(){
		super.init();
    _name=null;	// 名称  STR(100)
    _entryAppr=OYn.DEFAULT.getLine().getKey();	// 报名审核 <OYn>  BYTE
    _picLimit=0;	// 报名图片限制  INT
    _showRanking=OYn.DEFAULT.getLine().getKey();	// 详情页显示排名 <OYn>  BYTE
    _picWidth=0;	// 图片宽度限制  INT
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
  public Byte getEntryAppr(){
    return _entryAppr;
  }
  public void setEntryAppr(Byte entryAppr){
    _entryAppr=entryAppr;
  }
  public Boolean gtEntryAppr(){
    return byteToBoolean(_entryAppr);
  }
  public void stEntryAppr(Boolean entryAppr){
    _entryAppr=booleanToByte(entryAppr);
  }
  public Integer getPicLimit(){
    return _picLimit;
  }
  public void setPicLimit(Integer picLimit){
    _picLimit=picLimit;
  }
  public Byte getShowRanking(){
    return _showRanking;
  }
  public void setShowRanking(Byte showRanking){
    _showRanking=showRanking;
  }
  public Boolean gtShowRanking(){
    return byteToBoolean(_showRanking);
  }
  public void stShowRanking(Boolean showRanking){
    _showRanking=booleanToByte(showRanking);
  }
  public Integer getPicWidth(){
    return _picWidth;
  }
  public void setPicWidth(Integer picWidth){
    _picWidth=picWidth;
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
