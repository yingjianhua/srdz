package irille.wx.wp;



import java.util.Date;

import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WpArt extends BeanInt<WpArt> implements IExtName {
	private static final Log LOG = new Log(WpArt.class);
	public static final Tb TB = new Tb(WpArt.class, "图文").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		ART_SHOW(WpArtShow.fldOutKey()),
		TITLE(SYS.STR__100,"标题"),
		IMG_URL(SYS.PHOTO__NULL,"图片"),
		URL(SYS.STR__1000_NULL, "链接"),
		DESCRIPTION(SYS.REM__200_NULL,"描述"),//描述
		DATE(SYS.DATE,"时间"),
	    THEME(WpArtTheme.fldOutKey().setName("主题")),
		BSN(WpBsn.fldOutKey().setName("商家")),
		CREATE_BY(SYS.CREATED_BY,"创建人"),
		CREATE_TIME(SYS.CREATED_DATE_TIME,"创建时间"),
		UPDATE_BY(SYS.UPDATED_BY,"更新人",true),
		UPDATE_TIME(SYS.UPDATED_DATE_TIME,"更新时间",true),
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
	    return getTitle();
	  }

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _artShow;	// 图文秀 <表主键:WpArtShow>  INT
  private String _title;	// 标题  STR(100)
  private String _imgUrl;	// 图片  STR(200)<null>
  private String _url;	// 链接  STR(200)<null>
  private String _description;	// 描述  STR(200)<null>
  private Date _date;	// 时间  DATE
  private Integer _theme;	// 主题 <表主键:WpArtTheme>  INT
  private Integer _bsn;	// 商家 <表主键:WpBsn>  INT
  private Integer _createBy;	// 创建人 <表主键:SysUser>  INT
  private Date _createTime;	// 创建时间  TIME
  private Integer _updateBy;	// 更新人 <表主键:SysUser>  INT<null>
  private Date _updateTime;	// 更新时间  TIME<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WpArt init(){
		super.init();
    _artShow=null;	// 图文秀 <表主键:WpArtShow>  INT
    _title=null;	// 标题  STR(100)
    _imgUrl=null;	// 图片  STR(200)
    _url=null;	// 链接  STR(200)
    _description=null;	// 描述  STR(200)
    _date=null;	// 时间  DATE
    _theme=null;	// 主题 <表主键:WpArtTheme>  INT
    _bsn=null;	// 商家 <表主键:WpBsn>  INT
    _createBy=Idu.getUser().getPkey();	// 创建人 <表主键:SysUser>  INT
    _createTime=Env.getTranBeginTime();	// 创建时间  TIME
    _updateBy=null;	// 更新人 <表主键:SysUser>  INT
    _updateTime=null;	// 更新时间  TIME
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
  public Integer getArtShow(){
    return _artShow;
  }
  public void setArtShow(Integer artShow){
    _artShow=artShow;
  }
  public WpArtShow gtArtShow(){
    if(getArtShow()==null)
      return null;
    return (WpArtShow)get(WpArtShow.class,getArtShow());
  }
  public void stArtShow(WpArtShow artShow){
    if(artShow==null)
      setArtShow(null);
    else
      setArtShow(artShow.getPkey());
  }
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public String getImgUrl(){
    return _imgUrl;
  }
  public void setImgUrl(String imgUrl){
    _imgUrl=imgUrl;
  }
  public String getUrl(){
    return _url;
  }
  public void setUrl(String url){
    _url=url;
  }
  public String getDescription(){
    return _description;
  }
  public void setDescription(String description){
    _description=description;
  }
  public Date getDate(){
    return _date;
  }
  public void setDate(Date date){
    _date=date;
  }
  public Integer getTheme(){
    return _theme;
  }
  public void setTheme(Integer theme){
    _theme=theme;
  }
  public WpArtTheme gtTheme(){
    if(getTheme()==null)
      return null;
    return (WpArtTheme)get(WpArtTheme.class,getTheme());
  }
  public void stTheme(WpArtTheme theme){
    if(theme==null)
      setTheme(null);
    else
      setTheme(theme.getPkey());
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
  public Integer getCreateBy(){
    return _createBy;
  }
  public void setCreateBy(Integer createBy){
    _createBy=createBy;
  }
  public SysUser gtCreateBy(){
    if(getCreateBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getCreateBy());
  }
  public void stCreateBy(SysUser createBy){
    if(createBy==null)
      setCreateBy(null);
    else
      setCreateBy(createBy.getPkey());
  }
  public Date getCreateTime(){
    return _createTime;
  }
  public void setCreateTime(Date createTime){
    _createTime=createTime;
  }
  public Integer getUpdateBy(){
    return _updateBy;
  }
  public void setUpdateBy(Integer updateBy){
    _updateBy=updateBy;
  }
  public SysUser gtUpdateBy(){
    if(getUpdateBy()==null)
      return null;
    return (SysUser)get(SysUser.class,getUpdateBy());
  }
  public void stUpdateBy(SysUser updateBy){
    if(updateBy==null)
      setUpdateBy(null);
    else
      setUpdateBy(updateBy.getPkey());
  }
  public Date getUpdateTime(){
    return _updateTime;
  }
  public void setUpdateTime(Date updateTime){
    _updateTime=updateTime;
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
