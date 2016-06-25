package irille.wx.wa;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WaActVotePhoto extends BeanInt<WaActVotePhoto>{
	  private static final Log LOG = new Log(WaActVotePhoto.class);
	  public static final Tb TB = new Tb(WaActVotePhoto.class, "照片").setAutoIncrement().addActIUDL();

	  public enum T implements IEnumFld {// @formatter:off
	    PKEY(TB.crtIntPkey()),//主键
	    VOTE_ENTRY(WaActVoteEntry.fldOutKey()),
	    PHOTO_URL(SYS.URL__NULL,"照片地址"),
	    SORT(SYS.SORT__SHORT, "排序", true),
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
	  public static Fld fldOutKey() {
			return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
		}

	  public static Fld fldOutKey(String code, String name) {
			return Tb.crtOutKey(TB, code, name);
		}
	  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _voteEntry;	// 报名记录 <表主键:WaActVoteEntry>  INT
  private String _photoUrl;	// 照片地址  STR(200)<null>
  private Short _sort;	// 排序  SHORT<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActVotePhoto init(){
		super.init();
    _voteEntry=null;	// 报名记录 <表主键:WaActVoteEntry>  INT
    _photoUrl=null;	// 照片地址  STR(200)
    _sort=null;	// 排序  SHORT
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
  public Integer getVoteEntry(){
    return _voteEntry;
  }
  public void setVoteEntry(Integer voteEntry){
    _voteEntry=voteEntry;
  }
  public WaActVoteEntry gtVoteEntry(){
    if(getVoteEntry()==null)
      return null;
    return (WaActVoteEntry)get(WaActVoteEntry.class,getVoteEntry());
  }
  public void stVoteEntry(WaActVoteEntry voteEntry){
    if(voteEntry==null)
      setVoteEntry(null);
    else
      setVoteEntry(voteEntry.getPkey());
  }
  public String getPhotoUrl(){
    return _photoUrl;
  }
  public void setPhotoUrl(String photoUrl){
    _photoUrl=photoUrl;
  }
  public Short getSort(){
    return _sort;
  }
  public void setSort(Short sort){
    _sort=sort;
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
