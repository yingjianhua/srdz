package irille.wx.wpt;



import java.math.BigDecimal;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

public class WptOrderService extends BeanInt<WptOrderService> implements IExtName {
	private static final Log LOG = new Log(WptOrderService.class);
	public static final Tb TB = new Tb(WptOrderService.class, "订单服务").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		NAME(SYS.NAME__40,"名称"),
		PRICE(SYS.AMT),
		ORDER_ID(WptOrder.fldOutKey()),

		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
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
  private BigDecimal _price;	// 金额  DEC(16,2)
  private Integer _orderId;	// 订单 <表主键:WptOrder>  INT

	@Override
  public WptOrderService init(){
		super.init();
    _name=null;	// 名称  STR(40)
    _price=ZERO;	// 金额  DEC(16,2)
    _orderId=null;	// 订单 <表主键:WptOrder>  INT
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
  public Integer getOrderId(){
    return _orderId;
  }
  public void setOrderId(Integer orderId){
    _orderId=orderId;
  }
  public WptOrder gtOrderId(){
    if(getOrderId()==null)
      return null;
    return (WptOrder)get(WptOrder.class,getOrderId());
  }
  public void stOrderId(WptOrder orderId){
    if(orderId==null)
      setOrderId(null);
    else
      setOrderId(orderId.getPkey());
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
