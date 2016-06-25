package irille.wx.wa;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wa.Wa.OActPageType;
import irille.wx.wx.WxAccount;

public class WaActTemplateMenu extends BeanInt<WaActTemplateMenu> {

	public static final Tb TB = new Tb(WaActTemplateMenu.class, "菜单设置").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		TEMP(WaActTemplate.fldOutKey()),
		NAME(SYS.NAME__40),
		CURR_IMG_URL(SYS.STR__200,"当前页图标"),
		IMG_URL(SYS.STR__200,"非当前图标"),		
		PAGE_TYPE(TB.crt(Wa.OActPageType.DEFAULT)),	
		CMB_WX(CmbWx.fldFlds()),
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		 public static final Index IDX_TEMP_PAGE_TYPE = TB.addIndex("tempPageType", true,T.TEMP, T.PAGE_TYPE);
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


	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _temp;	// 页面模板 <表主键:WaActTemplate>  INT
  private String _name;	// 名称  STR(40)
  private String _currImgUrl;	// 当前页图标  STR(200)
  private String _imgUrl;	// 非当前图标  STR(200)
  private Byte _pageType;	// 页面类型 <OActPageType>  BYTE
	// HOMEPAGE:1,首页
	// ENTRYPAGE:2,报名页
	// RANKINGPAGE:3,排行页
	// INFOPAGRE:4,详情页
	// CUSTOMPAGE:99,自定义页
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActTemplateMenu init(){
		super.init();
    _temp=null;	// 页面模板 <表主键:WaActTemplate>  INT
    _name=null;	// 名称  STR(40)
    _currImgUrl=null;	// 当前页图标  STR(200)
    _imgUrl=null;	// 非当前图标  STR(200)
    _pageType=OActPageType.DEFAULT.getLine().getKey();	// 页面类型 <OActPageType>  BYTE
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WaActTemplateMenu loadUniqueTempPageType(boolean lockFlag,Integer temp,Byte pageType) {
    return (WaActTemplateMenu)loadUnique(T.IDX_TEMP_PAGE_TYPE,lockFlag,temp,pageType);
  }
  public static WaActTemplateMenu chkUniqueTempPageType(boolean lockFlag,Integer temp,Byte pageType) {
    return (WaActTemplateMenu)chkUnique(T.IDX_TEMP_PAGE_TYPE,lockFlag,temp,pageType);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Integer getTemp(){
    return _temp;
  }
  public void setTemp(Integer temp){
    _temp=temp;
  }
  public WaActTemplate gtTemp(){
    if(getTemp()==null)
      return null;
    return (WaActTemplate)get(WaActTemplate.class,getTemp());
  }
  public void stTemp(WaActTemplate temp){
    if(temp==null)
      setTemp(null);
    else
      setTemp(temp.getPkey());
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getCurrImgUrl(){
    return _currImgUrl;
  }
  public void setCurrImgUrl(String currImgUrl){
    _currImgUrl=currImgUrl;
  }
  public String getImgUrl(){
    return _imgUrl;
  }
  public void setImgUrl(String imgUrl){
    _imgUrl=imgUrl;
  }
  public Byte getPageType(){
    return _pageType;
  }
  public void setPageType(Byte pageType){
    _pageType=pageType;
  }
  public OActPageType gtPageType(){
    return (OActPageType)(OActPageType.HOMEPAGE.getLine().get(_pageType));
  }
  public void stPageType(OActPageType pageType){
    _pageType=pageType.getLine().getKey();
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

