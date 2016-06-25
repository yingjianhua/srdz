package irille.wx.wa;

import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.wa.Wa.OActPageType;
import irille.wx.wx.WxAccount;

public class WaActTemplateLine extends BeanInt<WaActTemplateLine> {

	public static final Tb TB = new Tb(WaActTemplateLine.class, "页面配置").setAutoIncrement().addActIUDL();
	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		TYPE(TB.crt(Wa.OActPageType.DEFAULT)),
		MAIN(WaActTemplate.fldOutKey()),
		URL(SYS.URL__NULL,"URL"),
		CMB_WX(CmbWx.fldFlds()),
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		 public static final Index IDX_TYPE_MAIN = TB.addIndex("typeMain",true,T.TYPE,T.MAIN);
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
  private Byte _type;	// 页面类型 <OActPageType>  BYTE
	// HOMEPAGE:1,首页
	// ENTRYPAGE:2,报名页
	// RANKINGPAGE:3,排行页
	// INFOPAGRE:4,详情页
	// CUSTOMPAGE:99,自定义页
  private Integer _main;	// 页面模板 <表主键:WaActTemplate>  INT
  private String _url;	// URL  STR(200)<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActTemplateLine init(){
		super.init();
    _type=OActPageType.DEFAULT.getLine().getKey();	// 页面类型 <OActPageType>  BYTE
    _main=null;	// 页面模板 <表主键:WaActTemplate>  INT
    _url=null;	// URL  STR(200)
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WaActTemplateLine loadUniqueTypeMain(boolean lockFlag,Byte type,Integer main) {
    return (WaActTemplateLine)loadUnique(T.IDX_TYPE_MAIN,lockFlag,type,main);
  }
  public static WaActTemplateLine chkUniqueTypeMain(boolean lockFlag,Byte type,Integer main) {
    return (WaActTemplateLine)chkUnique(T.IDX_TYPE_MAIN,lockFlag,type,main);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public OActPageType gtType(){
    return (OActPageType)(OActPageType.HOMEPAGE.getLine().get(_type));
  }
  public void stType(OActPageType type){
    _type=type.getLine().getKey();
  }
  public Integer getMain(){
    return _main;
  }
  public void setMain(Integer main){
    _main=main;
  }
  public WaActTemplate gtMain(){
    if(getMain()==null)
      return null;
    return (WaActTemplate)get(WaActTemplate.class,getMain());
  }
  public void stMain(WaActTemplate main){
    if(main==null)
      setMain(null);
    else
      setMain(main.getPkey());
  }
  public String getUrl(){
    return _url;
  }
  public void setUrl(String url){
    _url=url;
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
