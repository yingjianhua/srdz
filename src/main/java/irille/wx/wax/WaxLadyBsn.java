package irille.wx.wax;



import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wp.WpBsn;
import irille.wx.wx.WxAccount;

public class WaxLadyBsn extends BeanInt<WaxLadyBsn> {
	private static final Log LOG = new Log(WaxLadyBsn.class);
	public static final Tb TB = new Tb(WaxLadyBsn.class, "拍摄商家").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		LADY(WaxLady.fldOutKey().setName("享女郎")),
		BSN(WpBsn.fldOutKey().setName("拍摄商家")),
		CMB_WX(CmbWx.fldFlds()),

		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _lady;	// 享女郎 <表主键:WaxLady>  INT
  private Integer _bsn;	// 拍摄商家 <表主键:WpBsn>  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaxLadyBsn init(){
		super.init();
    _lady=null;	// 享女郎 <表主键:WaxLady>  INT
    _bsn=null;	// 拍摄商家 <表主键:WpBsn>  INT
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
  public Integer getLady(){
    return _lady;
  }
  public void setLady(Integer lady){
    _lady=lady;
  }
  public WaxLady gtLady(){
    if(getLady()==null)
      return null;
    return (WaxLady)get(WaxLady.class,getLady());
  }
  public void stLady(WaxLady lady){
    if(lady==null)
      setLady(null);
    else
      setLady(lady.getPkey());
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
