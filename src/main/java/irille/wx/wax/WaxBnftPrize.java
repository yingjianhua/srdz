package irille.wx.wax;



import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WaxBnftPrize extends BeanInt<WaxBnftPrize> implements IExtName {
	private static final Log LOG = new Log(WaxBnftPrize.class);
	public static final Tb TB = new Tb(WaxBnftPrize.class, "福利获奖").setAutoIncrement().addActIUDL().addActOpt("doSend", "发送短信", "green-icon");

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		BNFT(WaxBnft.fldOutKey().setName("福利活动")),
		ENTRY(WaxBnftEntry.fldOutKey().setName("中奖者")),
		SEND_SMS(SYS.NY,"已发短信"),
		CMB_WX(CmbWx.fldFlds()),

		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		public static final Index IDX_BNFT_ENTRY = TB.addIndex("bnftEntry", true, T.BNFT,T.ENTRY);
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
	    return getExtName();
	  }

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _bnft;	// 福利活动 <表主键:WaxBnft>  INT
  private Integer _entry;	// 中奖者 <表主键:WaxBnftEntry>  INT
  private Byte _sendSms;	// 已发短信 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaxBnftPrize init(){
		super.init();
    _bnft=null;	// 福利活动 <表主键:WaxBnft>  INT
    _entry=null;	// 中奖者 <表主键:WaxBnftEntry>  INT
    _sendSms=OYn.DEFAULT.getLine().getKey();	// 已发短信 <OYn>  BYTE
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WaxBnftPrize loadUniqueBnftEntry(boolean lockFlag,Integer bnft,Integer entry) {
    return (WaxBnftPrize)loadUnique(T.IDX_BNFT_ENTRY,lockFlag,bnft,entry);
  }
  public static WaxBnftPrize chkUniqueBnftEntry(boolean lockFlag,Integer bnft,Integer entry) {
    return (WaxBnftPrize)chkUnique(T.IDX_BNFT_ENTRY,lockFlag,bnft,entry);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getBnft(){
    return _bnft;
  }
  public void setBnft(Integer bnft){
    _bnft=bnft;
  }
  public WaxBnft gtBnft(){
    if(getBnft()==null)
      return null;
    return (WaxBnft)get(WaxBnft.class,getBnft());
  }
  public void stBnft(WaxBnft bnft){
    if(bnft==null)
      setBnft(null);
    else
      setBnft(bnft.getPkey());
  }
  public Integer getEntry(){
    return _entry;
  }
  public void setEntry(Integer entry){
    _entry=entry;
  }
  public WaxBnftEntry gtEntry(){
    if(getEntry()==null)
      return null;
    return (WaxBnftEntry)get(WaxBnftEntry.class,getEntry());
  }
  public void stEntry(WaxBnftEntry entry){
    if(entry==null)
      setEntry(null);
    else
      setEntry(entry.getPkey());
  }
  public Byte getSendSms(){
    return _sendSms;
  }
  public void setSendSms(Byte sendSms){
    _sendSms=sendSms;
  }
  public Boolean gtSendSms(){
    return byteToBoolean(_sendSms);
  }
  public void stSendSms(Boolean sendSms){
    _sendSms=booleanToByte(sendSms);
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
