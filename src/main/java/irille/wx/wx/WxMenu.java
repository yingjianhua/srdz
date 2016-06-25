package irille.wx.wx;

import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.wx.Wx.OMotionType;
import irille.wx.wx.Wx.OMsgType;

public class WxMenu extends BeanInt<WxMenu> implements IExtName {
  private static final Log LOG = new Log(WxMenu.class);
  public static final Tb TB = new Tb(WxMenu.class, "自定义菜单").setAutoIncrement().addActIUDL().addActOpt("sync","同步到微信","upd-icon", false);

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    NAME(SYS.STR__100,"菜单名称"),//菜单名称
    MENU_UP(WxMenu.fldOutKey().setNull().setName("上级菜单")),//上级菜单
    TYPE(TB.crt(Wx.OMotionType.DEFAULT)),//动作设置
    URL(SYS.STR__200_NULL,"链接URL"),//链接url
    MSG_TYPE(TB.crt(Wx.OMsgType.DEFAULT).setNull()),//消息类型
    TEMPLATE(Tb.crtLongTbObj("template", "消息模板").setNull()),//消息模板
    MENU_KEY(SYS.STR__40,"菜单标识"),//菜单标示
    SORT(SYS.SORT__INT,"顺序"),//顺序
    ACCOUNT(WxAccount.fldOutKey()),//公众账号
    ROW_VERSION(SYS.ROW_VERSION),
    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
    ;
    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
    // 索引
    public static final Index IDX_ACCOUNT_KEY = TB.addIndex("accountKey", true,T.ACCOUNT,T.MENU_KEY);
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
    return getName();
  }

  //@formatter:on
  public static Fld fldOutKey() {
    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
  }

  public static Fld fldOutKey(String code, String name) {
    return Tb.crtOutKey(TB, code, name);
  }
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 菜单名称  STR(100)
  private Integer _menuUp;	// 上级菜单 <表主键:WxMenu>  INT<null>
  private Byte _type;	// 动作设置 <OMotionType>  BYTE
	// MESSAGECLASS:1,消息触发类
	// WEBLINKCLASS:2,网页链接类
  private String _url;	// 链接URL  STR(200)<null>
  private Byte _msgType;	// 消息类型 <OMsgType>  BYTE<null>
	// TEXT:1,文本消息
	// IMAGE:2,图片消息
	// MUSIC:3,音乐消息
	// VIDEO:4,视频消息
	// VOICE:5,语音消息
	// NEWS:6,图文消息
  private Long _template;	// 消息模板  LONG<null>
  private String _menuKey;	// 菜单标识  STR(40)
  private Integer _sort;	// 顺序  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxMenu init(){
		super.init();
    _name=null;	// 菜单名称  STR(100)
    _menuUp=null;	// 上级菜单 <表主键:WxMenu>  INT
    _type=OMotionType.DEFAULT.getLine().getKey();	// 动作设置 <OMotionType>  BYTE
    _url=null;	// 链接URL  STR(200)
    _msgType=OMsgType.DEFAULT.getLine().getKey();	// 消息类型 <OMsgType>  BYTE
    _template=null;	// 消息模板  LONG
    _menuKey=null;	// 菜单标识  STR(40)
    _sort=0;	// 顺序  INT
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WxMenu loadUniqueAccountKey(boolean lockFlag,Integer account,String menuKey) {
    return (WxMenu)loadUnique(T.IDX_ACCOUNT_KEY,lockFlag,account,menuKey);
  }
  public static WxMenu chkUniqueAccountKey(boolean lockFlag,Integer account,String menuKey) {
    return (WxMenu)chkUnique(T.IDX_ACCOUNT_KEY,lockFlag,account,menuKey);
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
  public Integer getMenuUp(){
    return _menuUp;
  }
  public void setMenuUp(Integer menuUp){
    _menuUp=menuUp;
  }
  public WxMenu gtMenuUp(){
    if(getMenuUp()==null)
      return null;
    return (WxMenu)get(WxMenu.class,getMenuUp());
  }
  public void stMenuUp(WxMenu menuUp){
    if(menuUp==null)
      setMenuUp(null);
    else
      setMenuUp(menuUp.getPkey());
  }
  public Byte getType(){
    return _type;
  }
  public void setType(Byte type){
    _type=type;
  }
  public OMotionType gtType(){
    return (OMotionType)(OMotionType.MESSAGECLASS.getLine().get(_type));
  }
  public void stType(OMotionType type){
    _type=type.getLine().getKey();
  }
  public String getUrl(){
    return _url;
  }
  public void setUrl(String url){
    _url=url;
  }
  public Byte getMsgType(){
    return _msgType;
  }
  public void setMsgType(Byte msgType){
    _msgType=msgType;
  }
  public OMsgType gtMsgType(){
    return (OMsgType)(OMsgType.TEXT.getLine().get(_msgType));
  }
  public void stMsgType(OMsgType msgType){
    _msgType=msgType.getLine().getKey();
  }
  public Long getTemplate(){
    return _template;
  }
  public void setTemplate(Long template){
    _template=template;
  }
  //外部主键对象: BeanLong
  public Bean gtTemplate(){
    return (Bean)gtLongTbObj(getTemplate());
  }
  public void stTemplate(Bean template){
      setTemplate(template.gtLongPkey());
  }
  public String getMenuKey(){
    return _menuKey;
  }
  public void setMenuKey(String menuKey){
    _menuKey=menuKey;
  }
  public Integer getSort(){
    return _sort;
  }
  public void setSort(Integer sort){
    _sort=sort;
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
