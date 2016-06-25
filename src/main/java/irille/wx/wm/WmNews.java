package irille.wx.wm;

import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wm.Wm.ONewsType;
import irille.wx.wx.Wx;
import irille.wx.wx.Wx.OSyncStatus;
import irille.wx.wx.WxAccount;

public class WmNews extends BeanInt<WmNews> implements IExtName {
  private static final Log LOG = new Log(WmNews.class);
  public static final Tb TB = new Tb(WmNews.class, "图文素材").setAutoIncrement().addActIUDL()
		  .addActOpt("sync", "同步").addActOpt("unsync", "取消同步").addActOpt("preview", "群发预览").addActOpt("mass", "群发"); 

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    TITLE(TB.crtStr("title", "标题", 64)),//标题
    IMAGE_URL(SYS.PHOTO__NULL,"封面"),//大图片建议尺寸为900*500，小图片建议尺寸为200*200
    SHOW_COVER_PIC(SYS.YN,"是否显示封面"),
    AUTHOR(SYS.STR__8_NULL,"作者"),
    SUMMARY(TB.crtStr("summary", "摘要", 120,true)),
    CONTENT(TB.crtText("content", "正文", 65535, true)),
    REL_URL(SYS.STR__200_NULL,"原文链接"),
    TYPE(Tb.crt(Wm.ONewsType.DEFAULT)),//图文类型
    EXP(Tb.crtLongTbObj("exp", "扩展链接").setNull()),
    URL(SYS.STR__200_NULL,"自定义链接"),
    PIC_UP(WmNews.class,"上级图文",true),
    SORT(SYS.SORT__INT),
    MEDIA_ID(SYS.STR__100_NULL,"图文ID"),
	STATUS(Tb.crt(Wx.OSyncStatus.DEFAULT).setNull()),
	CMB_WX(CmbWx.fldFlds()),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
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
    T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
  }
  @Override
  public String getExtName() {
    return getTitle();
  }
  //@formatter:on
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _title;	// 标题  STR(64)
  private String _imageUrl;	// 封面  STR(200)<null>
  private Byte _showCoverPic;	// 是否显示封面 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _author;	// 作者  STR(8)<null>
  private String _summary;	// 摘要  STR(120)<null>
  private String _content;	// 正文  TEXT(65535)<null>
  private String _relUrl;	// 原文链接  STR(200)<null>
  private Byte _type;	// 图文类型 <ONewsType>  BYTE
	// BASE:0,普通图文
	// URL:1,自定义链接
	// EXP:2,扩展链接
  private Long _exp;	// 扩展链接  LONG<null>
  private String _url;	// 自定义链接  STR(200)<null>
  private Integer _picUp;	// 上级图文 <表主键:WmNews>  INT<null>
  private Integer _sort;	// 排序号  INT
  private String _mediaId;	// 图文ID  STR(100)<null>
  private Byte _status;	// 同步状态 <OSyncStatus>  BYTE<null>
	// INIT:1,未同步
	// SYNC:2,已同步
	// DEL:9,已删除
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WmNews init(){
		super.init();
    _title=null;	// 标题  STR(64)
    _imageUrl=null;	// 封面  STR(200)
    _showCoverPic=OYn.DEFAULT.getLine().getKey();	// 是否显示封面 <OYn>  BYTE
    _author=null;	// 作者  STR(8)
    _summary=null;	// 摘要  STR(120)
    _content=null;	// 正文  TEXT(65535)
    _relUrl=null;	// 原文链接  STR(200)
    _type=ONewsType.DEFAULT.getLine().getKey();	// 图文类型 <ONewsType>  BYTE
    _exp=null;	// 扩展链接  LONG
    _url=null;	// 自定义链接  STR(200)
    _picUp=null;	// 上级图文 <表主键:WmNews>  INT
    _sort=0;	// 排序号  INT
    _mediaId=null;	// 图文ID  STR(100)
    _status=OSyncStatus.DEFAULT.getLine().getKey();	// 同步状态 <OSyncStatus>  BYTE
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
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public String getImageUrl(){
    return _imageUrl;
  }
  public void setImageUrl(String imageUrl){
    _imageUrl=imageUrl;
  }
  public Byte getShowCoverPic(){
    return _showCoverPic;
  }
  public void setShowCoverPic(Byte showCoverPic){
    _showCoverPic=showCoverPic;
  }
  public Boolean gtShowCoverPic(){
    return byteToBoolean(_showCoverPic);
  }
  public void stShowCoverPic(Boolean showCoverPic){
    _showCoverPic=booleanToByte(showCoverPic);
  }
  public String getAuthor(){
    return _author;
  }
  public void setAuthor(String author){
    _author=author;
  }
  public String getSummary(){
    return _summary;
  }
  public void setSummary(String summary){
    _summary=summary;
  }
  public String getContent(){
    return _content;
  }
  public void setContent(String content){
    _content=content;
  }
  public String getRelUrl(){
    return _relUrl;
  }
  public void setRelUrl(String relUrl){
    _relUrl=relUrl;
  }
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public ONewsType gtType(){
    return (ONewsType)(ONewsType.URL.getLine().get(_type));
  }
  public void stType(ONewsType type){
    _type=type.getLine().getKey();
  }
  public Long getExp(){
    return _exp;
  }
  public void setExp(Long exp){
    _exp=exp;
  }
  //外部主键对象: BeanLong
  public Bean gtExp(){
    return (Bean)gtLongTbObj(getExp());
  }
  public void stExp(Bean exp){
      setExp(exp.gtLongPkey());
  }
  public String getUrl(){
    return _url;
  }
  public void setUrl(String url){
    _url=url;
  }
  public Integer getPicUp(){
    return _picUp;
  }
  public void setPicUp(Integer picUp){
    _picUp=picUp;
  }
  public WmNews gtPicUp(){
    if(getPicUp()==null)
      return null;
    return (WmNews)get(WmNews.class,getPicUp());
  }
  public void stPicUp(WmNews picUp){
    if(picUp==null)
      setPicUp(null);
    else
      setPicUp(picUp.getPkey());
  }
  public Integer getSort(){
    return _sort;
  }
  public void setSort(Integer sort){
    _sort=sort;
  }
  public String getMediaId(){
    return _mediaId;
  }
  public void setMediaId(String mediaId){
    _mediaId=mediaId;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OSyncStatus gtStatus(){
    return (OSyncStatus)(OSyncStatus.INIT.getLine().get(_status));
  }
  public void stStatus(OSyncStatus status){
    _status=status.getLine().getKey();
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
