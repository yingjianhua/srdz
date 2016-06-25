package irille.wx.pay;



import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

import java.math.BigDecimal;
import java.util.Date;

public class PayOrder extends BeanInt<PayOrder> implements IExtName,IOrder {
	private static final Log LOG = new Log(PayOrder.class);
	public static final Tb TB = new Tb(PayOrder.class, "商户订单").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		NUMBER(SYS.STR__100, "订单号"),
		DESCRIPTION(SYS.STR__100, "描述"),
		AMT(SYS.AMT, "金额"),
		NONCE_STR(SYS.STR__100_NULL, "随机字符串"),
		SIGN(SYS.STR__100_NULL, "签名"),
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
	    return "";
	  }
	@Override
	public String getBody() {
		// TODO Auto-generated method stub
		return "getBody";
	}
	@Override
	public String getDetail() {
		// TODO Auto-generated method stub
		return "getDetail";
	}
	@Override
	public String getOutTradeNo() {
		// TODO Auto-generated method stub
		return getNumber();
	}
	@Override
	public int getTotalFee() {
		// TODO Auto-generated method stub
		return getAmt().intValue();
	}
	@Override
	public String getOutRefundNo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPrepareId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setPrepareId(String prepareId, Date prepareTime) {
		// TODO Auto-generated method stub
		
	}
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _number;	// 订单号  STR(100)
  private String _description;	// 描述  STR(100)
  private BigDecimal _amt;	// 金额  DEC(16,2)
  private String _nonceStr;	// 随机字符串  STR(100)<null>
  private String _sign;	// 签名  STR(100)<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public PayOrder init(){
		super.init();
    _number=null;	// 订单号  STR(100)
    _description=null;	// 描述  STR(100)
    _amt=ZERO;	// 金额  DEC(16,2)
    _nonceStr=null;	// 随机字符串  STR(100)
    _sign=null;	// 签名  STR(100)
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
  public String getNumber(){
    return _number;
  }
  public void setNumber(String number){
    _number=number;
  }
  public String getDescription(){
    return _description;
  }
  public void setDescription(String description){
    _description=description;
  }
  public BigDecimal getAmt(){
    return _amt;
  }
  public void setAmt(BigDecimal amt){
    _amt=amt;
  }
  public String getNonceStr(){
    return _nonceStr;
  }
  public void setNonceStr(String nonceStr){
    _nonceStr=nonceStr;
  }
  public String getSign(){
    return _sign;
  }
  public void setSign(String sign){
    _sign=sign;
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
