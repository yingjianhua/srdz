package irille.wx.wpt;



import java.math.BigDecimal;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WptComboLine extends BeanInt<WptComboLine>{
	private static final Log LOG = new Log(WptComboLine.class);
	public static final Tb TB = new Tb(WptComboLine.class, "套餐明细").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		COMBO(WptCombo.fldOutKey()),
		MENU(WptMenu.fldOutKey()),
		PRICE(SYS.AMT,"价格"),
		CMB_WX(CmbWx.fldFlds()),
		SORT(SYS.SORT__SHORT),

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
		 return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
		  }
	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _combo;	// 套餐 <表主键:WptCombo>  INT
  private Integer _menu;	// 菜品 <表主键:WptMenu>  INT
  private BigDecimal _price;	// 价格  DEC(16,2)
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT
  private Short _sort;	// 排序号  SHORT

	@Override
  public WptComboLine init(){
		super.init();
    _combo=null;	// 套餐 <表主键:WptCombo>  INT
    _menu=null;	// 菜品 <表主键:WptMenu>  INT
    _price=ZERO;	// 价格  DEC(16,2)
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    _sort=0;	// 排序号  SHORT
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getCombo(){
    return _combo;
  }
  public void setCombo(Integer combo){
    _combo=combo;
  }
  public WptCombo gtCombo(){
    if(getCombo()==null)
      return null;
    return (WptCombo)get(WptCombo.class,getCombo());
  }
  public void stCombo(WptCombo combo){
    if(combo==null)
      setCombo(null);
    else
      setCombo(combo.getPkey());
  }
  public Integer getMenu(){
    return _menu;
  }
  public void setMenu(Integer menu){
    _menu=menu;
  }
  public WptMenu gtMenu(){
    if(getMenu()==null)
      return null;
    return (WptMenu)get(WptMenu.class,getMenu());
  }
  public void stMenu(WptMenu menu){
    if(menu==null)
      setMenu(null);
    else
      setMenu(menu.getPkey());
  }
  public BigDecimal getPrice(){
    return _price;
  }
  public void setPrice(BigDecimal price){
    _price=price;
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
  public Short getSort(){
    return _sort;
  }
  public void setSort(Short sort){
    _sort=sort;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
