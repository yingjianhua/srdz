package irille.wx.wpt;



import java.text.SimpleDateFormat;
import java.util.Date;

import irille.core.sys.Sys.OSex;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wpt.Wpt.OContactStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WptCustomForm extends BeanInt<WptCustomForm> {
	private static final Log LOG = new Log(WptCustomForm.class);
	public static final Tb TB = new Tb(WptCustomForm.class, "私人订制表单").setAutoIncrement().addActUpd().addActDel().addActList();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		FORMID(SYS.STR__100, "表单编号"),
		BANQUET(SYS.STR__100, "宴会类型"),//宴会类型
		BUDGET(SYS.STR__100,"预算",true),
		NUMBER(SYS.STR__100,"宴会人数",true),
		TIME(SYS.DATE_TIME,"用餐时间"),
		CUSTOM_SERVICES(SYS.STR__100,"定制服务",true),
		CITY(WptCity.fldOutKey()),//城市
		CITYLINE(WptCityLine.fldOutKey()),//区域
		CONTACT_MAN(SYS.STR__10,"联系人"),
		CONTACT_SEX(SYS.SEX),
		CONTACT_WAY(SYS.STR__40,"联系方式"),
		CONTACT_TYPE(TB.crt(Wpt.OContactStatus.DEFAULT)),
		REM(SYS.REM__200_NULL,"备注"),
		MEMBER(WxUser.fldOutKey()),//微信用户
		CREATE_TIME(SYS.CREATED_DATE_TIME,"创建时间"),
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
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	/**
	 * 用于在页面上显示的格式的用餐时间
	 * @return
	 */
	public String getFormatDate() {
		return format.format(_time);
	}
	
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _formid;	// 表单编号  STR(100)
  private String _banquet;	// 宴会类型  STR(100)
  private String _budget;	// 预算  STR(100)<null>
  private String _number;	// 宴会人数  STR(100)<null>
  private Date _time;	// 用餐时间  TIME
  private String _customServices;	// 定制服务  STR(100)<null>
  private Integer _city;	// 城市 <表主键:WptCity>  INT
  private Integer _cityline;	// 区 <表主键:WptCityLine>  INT
  private String _contactMan;	// 联系人  STR(10)
  private Byte _contactSex;	// 性别 <OSex>  BYTE
	// UNKNOW:0,未知
	// MALE:1,男
	// FEMALE:2,女
  private String _contactWay;	// 联系方式  STR(40)
  private Byte _contactType;	// 联系方式类型 <OContactStatus>  BYTE
	// MOBILE:0,手机
	// WECHAT:1,微信
	// QQ:2,qq
	// OTHER:3,其他
  private String _rem;	// 备注  STR(200)<null>
  private Integer _member;	// 关注用户 <表主键:WxUser>  INT
  private Date _createTime;	// 创建时间  TIME
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptCustomForm init(){
		super.init();
    _formid=null;	// 表单编号  STR(100)
    _banquet=null;	// 宴会类型  STR(100)
    _budget=null;	// 预算  STR(100)
    _number=null;	// 宴会人数  STR(100)
    _time=Env.getTranBeginTime();	// 用餐时间  TIME
    _customServices=null;	// 定制服务  STR(100)
    _city=null;	// 城市 <表主键:WptCity>  INT
    _cityline=null;	// 区 <表主键:WptCityLine>  INT
    _contactMan=null;	// 联系人  STR(10)
    _contactSex=OSex.DEFAULT.getLine().getKey();	// 性别 <OSex>  BYTE
    _contactWay=null;	// 联系方式  STR(40)
    _contactType=OContactStatus.DEFAULT.getLine().getKey();	// 联系方式类型 <OContactStatus>  BYTE
    _rem=null;	// 备注  STR(200)
    _member=null;	// 关注用户 <表主键:WxUser>  INT
    _createTime=Env.getTranBeginTime();	// 创建时间  TIME
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
  public String getFormid(){
    return _formid;
  }
  public void setFormid(String formid){
    _formid=formid;
  }
  public String getBanquet(){
    return _banquet;
  }
  public void setBanquet(String banquet){
    _banquet=banquet;
  }
  public String getBudget(){
    return _budget;
  }
  public void setBudget(String budget){
    _budget=budget;
  }
  public String getNumber(){
    return _number;
  }
  public void setNumber(String number){
    _number=number;
  }
  public Date getTime(){
    return _time;
  }
  public void setTime(Date time){
    _time=time;
  }
  public String getCustomServices(){
    return _customServices;
  }
  public void setCustomServices(String customServices){
    _customServices=customServices;
  }
  public Integer getCity(){
    return _city;
  }
  public void setCity(Integer city){
    _city=city;
  }
  public WptCity gtCity(){
    if(getCity()==null)
      return null;
    return (WptCity)get(WptCity.class,getCity());
  }
  public void stCity(WptCity city){
    if(city==null)
      setCity(null);
    else
      setCity(city.getPkey());
  }
  public Integer getCityline(){
    return _cityline;
  }
  public void setCityline(Integer cityline){
    _cityline=cityline;
  }
  public WptCityLine gtCityline(){
    if(getCityline()==null)
      return null;
    return (WptCityLine)get(WptCityLine.class,getCityline());
  }
  public void stCityline(WptCityLine cityline){
    if(cityline==null)
      setCityline(null);
    else
      setCityline(cityline.getPkey());
  }
  public String getContactMan(){
    return _contactMan;
  }
  public void setContactMan(String contactMan){
    _contactMan=contactMan;
  }
  public Byte getContactSex(){
    return _contactSex;
  }
  public void setContactSex(Byte contactSex){
    _contactSex=contactSex;
  }
  public OSex gtContactSex(){
    return (OSex)(OSex.UNKNOW.getLine().get(_contactSex));
  }
  public void stContactSex(OSex contactSex){
    _contactSex=contactSex.getLine().getKey();
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
  public String getRem(){
    return _rem;
  }
  public void setRem(String rem){
    _rem=rem;
  }
  public Integer getMember(){
    return _member;
  }
  public void setMember(Integer member){
    _member=member;
  }
  public WxUser gtMember(){
    if(getMember()==null)
      return null;
    return (WxUser)get(WxUser.class,getMember());
  }
  public void stMember(WxUser member){
    if(member==null)
      setMember(null);
    else
      setMember(member.getPkey());
  }
  public Date getCreateTime(){
    return _createTime;
  }
  public void setCreateTime(Date createTime){
    _createTime=createTime;
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
