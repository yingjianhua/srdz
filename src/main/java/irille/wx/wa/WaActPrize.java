package irille.wx.wa;

import java.math.BigDecimal;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.wx.WxAccount;

public class WaActPrize extends BeanInt<WaActPrize> implements IExtName{
  private static final Log LOG = new Log(WaActPrize.class);
  public static final Tb TB = new Tb(WaActPrize.class, "奖品设置").setAutoIncrement().addActIUDL();

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    NAME(SYS.NAME__100,"奖品名称"),//奖品名称
    PRICE(SYS.AMT,"奖品价值"),
    COST(SYS.AMT,"奖品成本"),
    ACCOUNT(WxAccount.fldOutKey()),//公众账号
    ROW_VERSION(SYS.ROW_VERSION),
    
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
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
      _fld = TB.add(fld,this);
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
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 奖品名称  STR(100)
  private BigDecimal _price;	// 奖品价值  DEC(16,2)
  private BigDecimal _cost;	// 奖品成本  DEC(16,2)
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActPrize init(){
		super.init();
    _name=null;	// 奖品名称  STR(100)
    _price=ZERO;	// 奖品价值  DEC(16,2)
    _cost=ZERO;	// 奖品成本  DEC(16,2)
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
  public BigDecimal getPrice(){
    return _price;
  }
  public void setPrice(BigDecimal price){
    _price=price;
  }
  public BigDecimal getCost(){
    return _cost;
  }
  public void setCost(BigDecimal cost){
    _cost=cost;
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
