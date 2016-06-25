package irille.wx.wpt;



import java.util.Date;

import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wpt.Wpt.OContactStatus;
import irille.wx.wx.WxAccount;

public class WptFeedBack extends BeanInt<WptFeedBack> {
	private static final Log LOG = new Log(WptFeedBack.class);
	public static final Tb TB = new Tb(WptFeedBack.class, "意见反馈").setAutoIncrement().addActList().addActOpt("toDo", "处理", "edit-icon");

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		CONTENT(SYS.STR__200,"内容"),
		CONTACT_WAY(SYS.STR__40,"联系方式"),
		CONTACT_TYPE(TB.crt(Wpt.OContactStatus.DEFAULT)),
		HANDLE_MAN(SYS.CREATED_BY,"处理人", true),
		HANDLE_TIME(SYS.TIME__NULL,"处理时间"),
		IS_HANDLE(SYS.NY,"已处理"),
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

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _content;	// 内容  STR(200)
  private String _contactWay;	// 联系方式  STR(40)
  private Byte _contactType;	// 联系方式类型 <OContactStatus>  BYTE
	// MOBILE:0,手机
	// WECHAT:1,微信
	// QQ:2,qq
	// OTHER:3,其他
  private Integer _handleMan;	// 处理人 <表主键:SysUser>  INT<null>
  private Date _handleTime;	// 处理时间  TIME<null>
  private Byte _isHandle;	// 已处理 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptFeedBack init(){
		super.init();
    _content=null;	// 内容  STR(200)
    _contactWay=null;	// 联系方式  STR(40)
    _contactType=OContactStatus.DEFAULT.getLine().getKey();	// 联系方式类型 <OContactStatus>  BYTE
    _handleMan=null;	// 处理人 <表主键:SysUser>  INT
    _handleTime=null;	// 处理时间  TIME
    _isHandle=OYn.DEFAULT.getLine().getKey();	// 已处理 <OYn>  BYTE
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
  public String getContent(){
    return _content;
  }
  public void setContent(String content){
    _content=content;
  }
  public String getContactWay(){
    return _contactWay;
  }
  public void setContactWay(String contactWay){
    _contactWay=contactWay;
  }
  public Byte getContactType(){
    return _contactType;
  }
  public void setContactType(Byte contactType){
    _contactType=contactType;
  }
  public OContactStatus gtContactType(){
    return (OContactStatus)(OContactStatus.MOBILE.getLine().get(_contactType));
  }
  public void stContactType(OContactStatus contactType){
    _contactType=contactType.getLine().getKey();
  }
  public Integer getHandleMan(){
    return _handleMan;
  }
  public void setHandleMan(Integer handleMan){
    _handleMan=handleMan;
  }
  public SysUser gtHandleMan(){
    if(getHandleMan()==null)
      return null;
    return (SysUser)get(SysUser.class,getHandleMan());
  }
  public void stHandleMan(SysUser handleMan){
    if(handleMan==null)
      setHandleMan(null);
    else
      setHandleMan(handleMan.getPkey());
  }
  public Date getHandleTime(){
    return _handleTime;
  }
  public void setHandleTime(Date handleTime){
    _handleTime=handleTime;
  }
  public Byte getIsHandle(){
    return _isHandle;
  }
  public void setIsHandle(Byte isHandle){
    _isHandle=isHandle;
  }
  public Boolean gtIsHandle(){
    return byteToBoolean(_isHandle);
  }
  public void stIsHandle(Boolean isHandle){
    _isHandle=booleanToByte(isHandle);
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
