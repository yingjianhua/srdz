package irille.wx.wp;

import java.math.BigDecimal;

import irille.core.sys.SysOrg;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.FldDec;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.wp.WpBsnCtg.T;

public class WpBsn extends BeanInt<WpBsn> implements IExtName{
  private static final Log LOG = new Log(WpBsn.class);
  public static final Tb TB = new Tb(WpBsn.class, "商家").setAutoIncrement().addActIUDL();
  
  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    NAME(SYS.NAME__40),
    CTG(WpBsnCtg.fldOutKey().setName("分类")),
    AREA(WpBsnArea.fldOutKey().setName("区域")),
    LATITUDE(new FldDec("latitude", "纬度", 10, 6).setNull()),
    LONGITUDE(new FldDec("longitude", "经度", 10, 6).setNull()),
    ADDR(SYS.ADDR__200_NULL),
    DES(SYS.STR__100_NULL,"描述"),
    ORG(SYS.ORG),
    ROW_VERSION(SYS.ROW_VERSION),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
	public static final Index IDX_NAME_ORG = TB.addIndex("nameOrg", true, T.NAME, T.ORG);
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
  @Override
  public String getExtName() {
    return getName();
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
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 名称  STR(40)
  private Integer _ctg;	// 分类 <表主键:WpBsnCtg>  INT
  private Integer _area;	// 区域 <表主键:WpBsnArea>  INT
  private BigDecimal _latitude;	// 纬度  DEC(10,6)<null>
  private BigDecimal _longitude;	// 经度  DEC(10,6)<null>
  private String _addr;	// 地址  STR(200)<null>
  private String _des;	// 描述  STR(100)<null>
  private Integer _org;	// 机构 <表主键:SysOrg>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WpBsn init(){
		super.init();
    _name=null;	// 名称  STR(40)
    _ctg=null;	// 分类 <表主键:WpBsnCtg>  INT
    _area=null;	// 区域 <表主键:WpBsnArea>  INT
    _latitude=null;	// 纬度  DEC(10,6)
    _longitude=null;	// 经度  DEC(10,6)
    _addr=null;	// 地址  STR(200)
    _des=null;	// 描述  STR(100)
    _org=null;	// 机构 <表主键:SysOrg>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WpBsn loadUniqueNameOrg(boolean lockFlag,String name,Integer org) {
    return (WpBsn)loadUnique(T.IDX_NAME_ORG,lockFlag,name,org);
  }
  public static WpBsn chkUniqueNameOrg(boolean lockFlag,String name,Integer org) {
    return (WpBsn)chkUnique(T.IDX_NAME_ORG,lockFlag,name,org);
  }
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
  public Integer getCtg(){
    return _ctg;
  }
  public void setCtg(Integer ctg){
    _ctg=ctg;
  }
  public WpBsnCtg gtCtg(){
    if(getCtg()==null)
      return null;
    return (WpBsnCtg)get(WpBsnCtg.class,getCtg());
  }
  public void stCtg(WpBsnCtg ctg){
    if(ctg==null)
      setCtg(null);
    else
      setCtg(ctg.getPkey());
  }
  public Integer getArea(){
    return _area;
  }
  public void setArea(Integer area){
    _area=area;
  }
  public WpBsnArea gtArea(){
    if(getArea()==null)
      return null;
    return (WpBsnArea)get(WpBsnArea.class,getArea());
  }
  public void stArea(WpBsnArea area){
    if(area==null)
      setArea(null);
    else
      setArea(area.getPkey());
  }
  public BigDecimal getLatitude(){
    return _latitude;
  }
  public void setLatitude(BigDecimal latitude){
    _latitude=latitude;
  }
  public BigDecimal getLongitude(){
    return _longitude;
  }
  public void setLongitude(BigDecimal longitude){
    _longitude=longitude;
  }
  public String getAddr(){
    return _addr;
  }
  public void setAddr(String addr){
    _addr=addr;
  }
  public String getDes(){
    return _des;
  }
  public void setDes(String des){
    _des=des;
  }
  public Integer getOrg(){
    return _org;
  }
  public void setOrg(Integer org){
    _org=org;
  }
  public SysOrg gtOrg(){
    if(getOrg()==null)
      return null;
    return (SysOrg)get(SysOrg.class,getOrg());
  }
  public void stOrg(SysOrg org){
    if(org==null)
      setOrg(null);
    else
      setOrg(org.getPkey());
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
