package irille.wx.wx;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;

import java.util.Date;

public class WxExpand extends BeanInt<WxExpand> implements IExtName{
  private static final Log LOG = new Log(WxExpand.class);
  public static final Tb TB = new Tb(WxExpand.class, "业务扩展接口").setAutoIncrement().addActList().addActUpd();

  public enum T implements IEnumFld {// @formatter:off
    PKEY(TB.crtIntPkey()),//主键
    ACTION(SYS.STR__100,"功能类"),
    NAME(SYS.STR__100,"名称"),
    CONTENT(SYS.STR__200_NULL,"描述"),
    IMAGE_URL(SYS.PHOTO__NULL,"封面"),
    ACCOUNT(WxAccount.fldOutKey()),//公众账号
    UPDATED_TIME(SYS.UPDATED_DATE_TIME,"更新时间"),
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
      _fld = TB.add(fld, this);
    }
    public Fld getFld() {
      return _fld;
    }
  }
  
  static { // 在此可以加一些对FLD进行特殊设定的代码
    T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
  }
  //@formatter:on
  @Override
  public String getExtName() {
    // TODO Auto-generated method stub
    return getName();
  }
  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _action;	// 功能类  STR(100)
  private String _name;	// 名称  STR(100)
  private String _content;	// 描述  STR(200)<null>
  private String _imageUrl;	// 封面  STR(200)<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Date _updatedTime;	// 更新时间  TIME
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WxExpand init(){
		super.init();
    _action=null;	// 功能类  STR(100)
    _name=null;	// 名称  STR(100)
    _content=null;	// 描述  STR(200)
    _imageUrl=null;	// 封面  STR(200)
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _updatedTime=Env.getTranBeginTime();	// 更新时间  TIME
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
  public String getAction(){
    return _action;
  }
  public void setAction(String action){
    _action=action;
  }
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public String getContent(){
    return _content;
  }
  public void setContent(String content){
    _content=content;
  }
  public String getImageUrl(){
    return _imageUrl;
  }
  public void setImageUrl(String imageUrl){
    _imageUrl=imageUrl;
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
  public Date getUpdatedTime(){
    return _updatedTime;
  }
  public void setUpdatedTime(Date updatedTime){
    _updatedTime=updatedTime;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
