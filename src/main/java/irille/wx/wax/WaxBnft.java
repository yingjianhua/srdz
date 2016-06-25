package irille.wx.wax;



import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wa.Wa;
import irille.wx.wa.Wa.OActVoteStatus;
import irille.wx.wp.WpBsn;
import irille.wx.wx.WxAccount;

public class WaxBnft extends BeanInt<WaxBnft> implements IExtName {
	private static final Log LOG = new Log(WaxBnft.class);
	public static final Tb TB = new Tb(WaxBnft.class, "福利").setAutoIncrement().addActIUDL().addActOpt("doClose", "关闭", "red-icon");

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		NAME(SYS.NAME__40), 
		IMG_URL(SYS.PHOTO__NULL,"首图"),
		DES(SYS.STR__2000_NULL,"介绍"),
		BSN(WpBsn.fldOutKey().setName("商家")),
		GIFT(SYS.STR__200, "奖品"),
		GET_DATE(SYS.STR__200, "领取时间"),
		REM(SYS.REM__200_NULL),
		STATUS(TB.crt(Wa.OActVoteStatus.DEFAULT)),
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
  private String _imgUrl;	// 首图  STR(200)<null>
  private String _des;	// 介绍  STR(2000)<null>
  private Integer _bsn;	// 商家 <表主键:WpBsn>  INT
  private String _gift;	// 奖品  STR(200)
  private String _getDate;	// 领取时间  STR(200)
  private String _rem;	// 备注  STR(200)<null>
  private Byte _status;	// 活动状态 <OActVoteStatus>  BYTE
	// INIT:0,初始
	// PUBLISH:1,已发布
	// ENTRYFIN:2,报名结束
	// ACTFIN:3,活动结束
	// CLOSE:4,已关闭
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaxBnft init(){
		super.init();
    _name=null;	// 名称  STR(40)
    _imgUrl=null;	// 首图  STR(200)
    _des=null;	// 介绍  STR(2000)
    _bsn=null;	// 商家 <表主键:WpBsn>  INT
    _gift=null;	// 奖品  STR(200)
    _getDate=null;	// 领取时间  STR(200)
    _rem=null;	// 备注  STR(200)
    _status=OActVoteStatus.DEFAULT.getLine().getKey();	// 活动状态 <OActVoteStatus>  BYTE
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
  public String getImgUrl(){
    return _imgUrl;
  }
  public void setImgUrl(String imgUrl){
    _imgUrl=imgUrl;
  }
  public String getDes(){
    return _des;
  }
  public void setDes(String des){
    _des=des;
  }
  public Integer getBsn(){
    return _bsn;
  }
  public void setBsn(Integer bsn){
    _bsn=bsn;
  }
  public WpBsn gtBsn(){
    if(getBsn()==null)
      return null;
    return (WpBsn)get(WpBsn.class,getBsn());
  }
  public void stBsn(WpBsn bsn){
    if(bsn==null)
      setBsn(null);
    else
      setBsn(bsn.getPkey());
  }
  public String getGift(){
    return _gift;
  }
  public void setGift(String gift){
    _gift=gift;
  }
  public String getGetDate(){
    return _getDate;
  }
  public void setGetDate(String getDate){
    _getDate=getDate;
  }
  public String getRem(){
    return _rem;
  }
  public void setRem(String rem){
    _rem=rem;
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
