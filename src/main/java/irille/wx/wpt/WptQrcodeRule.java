package irille.wx.wpt;

import java.math.BigDecimal;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.wx.WxAccount;

public class WptQrcodeRule extends BeanInt<WptQrcodeRule> {
  private static final Log LOG = new Log(WptQrcodeRule.class);
  public static final Tb TB = new Tb(WptQrcodeRule.class, "推广二维码规则").setAutoLocal().addActList().addActOpt("set", "设置", "edit-icon");

  public enum T implements IEnumFld {// @formatter:off
	ACCOUNT(WxAccount.fldOneToOne()),
	SINGLE(SYS.AMT, "单笔消费金额"),
	AMOUNT(SYS.AMT, "累计消费金额"),
	VALIDITY_PERIOD(SYS.INT_PLUS_OR_ZERO, "有效天数"),
    ROW_VERSION(SYS.ROW_VERSION),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		PKEY(TB.get("pkey")),	//编号
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
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
    T.ACCOUNT.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
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
  
  public static Fld fldOneToOne() {
		return fldOneToOne(TB.getCodeNoPackage(), TB.getShortName());
	}

	public static Fld fldOneToOne(String code, String name) {
		return Tb.crtOneToOne(TB, code, name);
	}
  
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private BigDecimal _single;	// 单笔消费金额  DEC(16,2)
  private BigDecimal _amount;	// 累计消费金额  DEC(16,2)
  private Integer _validityPeriod;	// 有效天数  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptQrcodeRule init(){
		super.init();
    _single=ZERO;	// 单笔消费金额  DEC(16,2)
    _amount=ZERO;	// 累计消费金额  DEC(16,2)
    _validityPeriod=0;	// 有效天数  INT
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
  //取一对一表对象: WxAccount
  public WxAccount gtAccount(){
    return get(WxAccount.class,getPkey());
  }
  public void stAccount(WxAccount account){
      setPkey(account.getPkey());
  }
  public BigDecimal getSingle(){
    return _single;
  }
  public void setSingle(BigDecimal single){
    _single=single;
  }
  public BigDecimal getAmount(){
    return _amount;
  }
  public void setAmount(BigDecimal amount){
    _amount=amount;
  }
  public Integer getValidityPeriod(){
    return _validityPeriod;
  }
  public void setValidityPeriod(Integer validityPeriod){
    _validityPeriod=validityPeriod;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
