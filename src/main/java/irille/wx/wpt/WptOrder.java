package irille.wx.wpt;



import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import irille.core.sys.Sys.OSex;
import irille.core.sys.Sys.OYn;
import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;
import irille.wx.CmbWx;
import irille.wx.pay.IOrder;
import irille.wx.wpt.Wpt.OContactStatus;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;
import irille.wxpub.util.mch.MchUtil;

public class WptOrder extends BeanInt<WptOrder> implements IOrder {
	private static final Log LOG = new Log(WptOrder.class);
	public static final Tb TB = new Tb(WptOrder.class, "订单").setAutoIncrement().addActUpd().addActDel().addActList()
			.addActOpt("service", "服务", "edit-icon").addActOpt("accept", "确认").addActOpt("residue", "收取余款")
			.addActOpt("agreeRefund", "同意退款");

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		ORDERID(SYS.STR__40,"订单号"),
		DEP_PAY_ID(SYS.STR__40, "定金订单号",true),
		PREPARE_CODE(SYS.STR__40, "预支付交易会话标识", true),
		DEP_PREPARE_CODE(SYS.STR__40, "定金预支付交易会话标识", true),
		PREPARE_CREATETIME(SYS.DATE_TIME__NULL, "预支付交易会话标识产生时间", true),
		DEP_PREPARE_CREATETIME(SYS.DATE_TIME__NULL, "定金预支付交易会话标识产生时间", true),
		OUT_REFUND_NO(SYS.STR__40, "退款单号", true),
		WXUSER(WxUser.fldOutKey()),//微信用户
		RESTAURANT(WptRestaurant.fldOutKey().setNull()),
		BANQUET(WptBanquet.fldOutKey().setNull()),//宴会类型
		TIME(SYS.DATE_TIME,"用餐时间"),
		NUMBER(SYS.INT,"宴会人数",true),
		CONSUMPTION(SYS.AMT,"人均预算",true),
		CITY(WptCity.fldOutKey()),//城市
		CREATE_TIME(SYS.CREATED_DATE_TIME,"创建时间"),
		STATUS(TB.crt(Wpt.OStatus.DEFAULT)),
		DEPOSIT(SYS.AMT,"定金", true),
		RESIDUE(SYS.AMT,"余款",true),
		RESIDUE_IS_WXPAY(SYS.YN, "微信支付", true),//余款的支付方式是否为微信支付
		RESIDUE_MAN(SYS.USER_SYS, "余款收取人", true),//收取余款的操作人员
		CONTACT_MAN(SYS.STR__10,"联系人"),
		CONTACT_SEX(SYS.SEX),
		CONTACT_WAY(SYS.STR__40,"联系方式"),
		CONTACT_TYPE(TB.crt(Wpt.OContactStatus.DEFAULT)),
		REM(SYS.REM__200_NULL,"备注"),
		COMBO_NAME(SYS.STR__40_NULL, "套餐名称"),//冗余字段
		PRICE(SYS.AMT, true),//后加
		IS_PT(SYS.NY, "是否私人定制 "),
		CHECKCODE(SYS.STR__10_NULL, "核验码"),
		CMB_WX(CmbWx.fldFlds()),


		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		public static final Index IDX_ORDERID = TB.addIndex("orderid",true, ORDERID);
		public static final Index IDX_DEP_PAY_ID = TB.addIndex("depPayId", true, DEP_PAY_ID);
		public static final Index IDX_OUT_REFUND_NO = TB.addIndex("outRefundNo",true, OUT_REFUND_NO);
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
	public String getBody() {
		if(gtIsPt() == true) return "私人定制";
		else return gtRestaurant().getName()+"|"+getComboName();
	}
	public String getDetail() {
		return null;
	}
	public String getOutTradeNo() {
		if(gtStatus() == OStatus.ACCEPTED) {
			return getDepPayId();
		} else if(gtStatus() == OStatus.UNPAYMENT || gtStatus() == OStatus.DEPOSIT){
			return getOrderid();
		} else if(gtStatus() == OStatus.REFUND){
			return getOrderid();
		}
		return null;
	}
	public String getPrepareId() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, -2);
		if(gtStatus() == OStatus.UNPAYMENT || gtStatus() == OStatus.DEPOSIT) {
			if(getPrepareCreatetime() != null && cal.getTime().before(getPrepareCreatetime())) {
				return getPrepareCode();
			} else {
				return null;
			}
		} else if(gtStatus() == OStatus.ACCEPTED){
			if(getDepPrepareCreatetime() != null && cal.getTime().before(getDepPrepareCreatetime())) {
				return getDepPrepareCode();
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	public void setPrepareId(String prepareId, Date prepareTime) {
		if(gtStatus() == OStatus.UNPAYMENT || gtStatus() == OStatus.DEPOSIT) {
			setPrepareCode(prepareId);
			setPrepareCreatetime(prepareTime);
		} else if(gtStatus() == OStatus.ACCEPTED){
			setDepPrepareCode(prepareId);
			setDepPrepareCreatetime(prepareTime);
		}
	}
	public int getTotalFee() {
		if(gtStatus() == OStatus.UNPAYMENT) {
			return getPrice().multiply(BigDecimal.valueOf(100)).intValue();
		} else if(gtStatus() == OStatus.ACCEPTED) {
			return getDeposit().multiply(BigDecimal.valueOf(100)).intValue();
		} else if(gtStatus() == OStatus.DEPOSIT) {
			return getResidue().multiply(BigDecimal.valueOf(100)).intValue();
		} else if(gtStatus() == OStatus.REFUND) {
			return gtIsPt()?getResidue().multiply(BigDecimal.valueOf(100)).intValue():getPrice().multiply(BigDecimal.valueOf(100)).intValue();
		}
 		return 0;
	}
	
	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _orderid;	// 订单号  STR(40)
  private String _depPayId;	// 定金订单号  STR(40)<null>
  private String _prepareCode;	// 预支付交易会话标识  STR(40)<null>
  private String _depPrepareCode;	// 定金预支付交易会话标识  STR(40)<null>
  private Date _prepareCreatetime;	// 预支付交易会话标识产生时间  TIME<null>
  private Date _depPrepareCreatetime;	// 定金预支付交易会话标识产生时间  TIME<null>
  private String _outRefundNo;	// 退款单号  STR(40)<null>
  private Integer _wxuser;	// 关注用户 <表主键:WxUser>  INT
  private Integer _restaurant;	// 餐厅 <表主键:WptRestaurant>  INT<null>
  private Integer _banquet;	// 宴会类型 <表主键:WptBanquet>  INT<null>
  private Date _time;	// 用餐时间  TIME
  private Integer _number;	// 宴会人数  INT<null>
  private BigDecimal _consumption;	// 人均预算  DEC(16,2)<null>
  private Integer _city;	// 城市 <表主键:WptCity>  INT
  private Date _createTime;	// 创建时间  TIME
  private Byte _status;	// 订单状态 <OStatus>  BYTE
	// UNPAYMENT:0,未付款
	// NOTACCEPTED:1,未受理
	// ACCEPTED:2,已受理
	// DEPOSIT:3,已付定金
	// PAYMENT:4,已付款
	// FINISH:5,已完成
	// CLOSE:6,已关闭
	// CANCEL:7,申请取消订单
	// REFUND:8,申请退款
  private BigDecimal _deposit;	// 定金  DEC(16,2)<null>
  private BigDecimal _residue;	// 余款  DEC(16,2)<null>
  private Byte _residueIsWxpay;	// 微信支付 <OYn>  BYTE<null>
	// YES:1,是
	// NO:0,否
  private Integer _residueMan;	// 余款收取人 <表主键:SysUser>  INT<null>
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
  private String _comboName;	// 套餐名称  STR(40)<null>
  private BigDecimal _price;	// 金额  DEC(16,2)<null>
  private Byte _isPt;	// 是否私人定制  <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _checkcode;	// 核验码  STR(10)<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptOrder init(){
		super.init();
    _orderid=null;	// 订单号  STR(40)
    _depPayId=null;	// 定金订单号  STR(40)
    _prepareCode=null;	// 预支付交易会话标识  STR(40)
    _depPrepareCode=null;	// 定金预支付交易会话标识  STR(40)
    _prepareCreatetime=null;	// 预支付交易会话标识产生时间  TIME
    _depPrepareCreatetime=null;	// 定金预支付交易会话标识产生时间  TIME
    _outRefundNo=null;	// 退款单号  STR(40)
    _wxuser=null;	// 关注用户 <表主键:WxUser>  INT
    _restaurant=null;	// 餐厅 <表主键:WptRestaurant>  INT
    _banquet=null;	// 宴会类型 <表主键:WptBanquet>  INT
    _time=Env.getTranBeginTime();	// 用餐时间  TIME
    _number=null;	// 宴会人数  INT
    _consumption=null;	// 人均预算  DEC(16,2)
    _city=null;	// 城市 <表主键:WptCity>  INT
    _createTime=Env.getTranBeginTime();	// 创建时间  TIME
    _status=OStatus.DEFAULT.getLine().getKey();	// 订单状态 <OStatus>  BYTE
    _deposit=null;	// 定金  DEC(16,2)
    _residue=null;	// 余款  DEC(16,2)
    _residueIsWxpay=OYn.DEFAULT.getLine().getKey();	// 微信支付 <OYn>  BYTE
    _residueMan=null;	// 余款收取人 <表主键:SysUser>  INT
    _contactMan=null;	// 联系人  STR(10)
    _contactSex=OSex.DEFAULT.getLine().getKey();	// 性别 <OSex>  BYTE
    _contactWay=null;	// 联系方式  STR(40)
    _contactType=OContactStatus.DEFAULT.getLine().getKey();	// 联系方式类型 <OContactStatus>  BYTE
    _rem=null;	// 备注  STR(200)
    _comboName=null;	// 套餐名称  STR(40)
    _price=null;	// 金额  DEC(16,2)
    _isPt=OYn.DEFAULT.getLine().getKey();	// 是否私人定制  <OYn>  BYTE
    _checkcode=null;	// 核验码  STR(10)
    _account=null;	// 公众帐号 <表主键:WxAccount>  INT
    _rowVersion=0;	// 版本  SHORT
    return this;
  }

  //方法----------------------------------------------
  public static WptOrder loadUniqueOrderid(boolean lockFlag,String orderid) {
    return (WptOrder)loadUnique(T.IDX_ORDERID,lockFlag,orderid);
  }
  public static WptOrder chkUniqueOrderid(boolean lockFlag,String orderid) {
    return (WptOrder)chkUnique(T.IDX_ORDERID,lockFlag,orderid);
  }
  public static WptOrder loadUniqueDepPayId(boolean lockFlag,String depPayId) {
    return (WptOrder)loadUnique(T.IDX_DEP_PAY_ID,lockFlag,depPayId);
  }
  public static WptOrder chkUniqueDepPayId(boolean lockFlag,String depPayId) {
    return (WptOrder)chkUnique(T.IDX_DEP_PAY_ID,lockFlag,depPayId);
  }
  public static WptOrder loadUniqueOutRefundNo(boolean lockFlag,String outRefundNo) {
    return (WptOrder)loadUnique(T.IDX_OUT_REFUND_NO,lockFlag,outRefundNo);
  }
  public static WptOrder chkUniqueOutRefundNo(boolean lockFlag,String outRefundNo) {
    return (WptOrder)chkUnique(T.IDX_OUT_REFUND_NO,lockFlag,outRefundNo);
  }
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getOrderid(){
    return _orderid;
  }
  public void setOrderid(String orderid){
    _orderid=orderid;
  }
  public String getDepPayId(){
    return _depPayId;
  }
  public void setDepPayId(String depPayId){
    _depPayId=depPayId;
  }
  public String getPrepareCode(){
    return _prepareCode;
  }
  public void setPrepareCode(String prepareCode){
    _prepareCode=prepareCode;
  }
  public String getDepPrepareCode(){
    return _depPrepareCode;
  }
  public void setDepPrepareCode(String depPrepareCode){
    _depPrepareCode=depPrepareCode;
  }
  public Date getPrepareCreatetime(){
    return _prepareCreatetime;
  }
  public void setPrepareCreatetime(Date prepareCreatetime){
    _prepareCreatetime=prepareCreatetime;
  }
  public Date getDepPrepareCreatetime(){
    return _depPrepareCreatetime;
  }
  public void setDepPrepareCreatetime(Date depPrepareCreatetime){
    _depPrepareCreatetime=depPrepareCreatetime;
  }
  public String getOutRefundNo(){
    return _outRefundNo;
  }
  public void setOutRefundNo(String outRefundNo){
    _outRefundNo=outRefundNo;
  }
  public Integer getWxuser(){
    return _wxuser;
  }
  public void setWxuser(Integer wxuser){
    _wxuser=wxuser;
  }
  public WxUser gtWxuser(){
    if(getWxuser()==null)
      return null;
    return (WxUser)get(WxUser.class,getWxuser());
  }
  public void stWxuser(WxUser wxuser){
    if(wxuser==null)
      setWxuser(null);
    else
      setWxuser(wxuser.getPkey());
  }
  public Integer getRestaurant(){
    return _restaurant;
  }
  public void setRestaurant(Integer restaurant){
    _restaurant=restaurant;
  }
  public WptRestaurant gtRestaurant(){
    if(getRestaurant()==null)
      return null;
    return (WptRestaurant)get(WptRestaurant.class,getRestaurant());
  }
  public void stRestaurant(WptRestaurant restaurant){
    if(restaurant==null)
      setRestaurant(null);
    else
      setRestaurant(restaurant.getPkey());
  }
  public Integer getBanquet(){
    return _banquet;
  }
  public void setBanquet(Integer banquet){
    _banquet=banquet;
  }
  public WptBanquet gtBanquet(){
    if(getBanquet()==null)
      return null;
    return (WptBanquet)get(WptBanquet.class,getBanquet());
  }
  public void stBanquet(WptBanquet banquet){
    if(banquet==null)
      setBanquet(null);
    else
      setBanquet(banquet.getPkey());
  }
  public Date getTime(){
    return _time;
  }
  public void setTime(Date time){
    _time=time;
  }
  public Integer getNumber(){
    return _number;
  }
  public void setNumber(Integer number){
    _number=number;
  }
  public BigDecimal getConsumption(){
    return _consumption;
  }
  public void setConsumption(BigDecimal consumption){
    _consumption=consumption;
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
  public Date getCreateTime(){
    return _createTime;
  }
  public void setCreateTime(Date createTime){
    _createTime=createTime;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OStatus gtStatus(){
    return (OStatus)(OStatus.UNPAYMENT.getLine().get(_status));
  }
  public void stStatus(OStatus status){
    _status=status.getLine().getKey();
  }
  public BigDecimal getDeposit(){
    return _deposit;
  }
  public void setDeposit(BigDecimal deposit){
    _deposit=deposit;
  }
  public BigDecimal getResidue(){
    return _residue;
  }
  public void setResidue(BigDecimal residue){
    _residue=residue;
  }
  public Byte getResidueIsWxpay(){
    return _residueIsWxpay;
  }
  public void setResidueIsWxpay(Byte residueIsWxpay){
    _residueIsWxpay=residueIsWxpay;
  }
  public Boolean gtResidueIsWxpay(){
    return byteToBoolean(_residueIsWxpay);
  }
  public void stResidueIsWxpay(Boolean residueIsWxpay){
    _residueIsWxpay=booleanToByte(residueIsWxpay);
  }
  public Integer getResidueMan(){
    return _residueMan;
  }
  public void setResidueMan(Integer residueMan){
    _residueMan=residueMan;
  }
  public SysUser gtResidueMan(){
    if(getResidueMan()==null)
      return null;
    return (SysUser)get(SysUser.class,getResidueMan());
  }
  public void stResidueMan(SysUser residueMan){
    if(residueMan==null)
      setResidueMan(null);
    else
      setResidueMan(residueMan.getPkey());
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
  public String getComboName(){
    return _comboName;
  }
  public void setComboName(String comboName){
    _comboName=comboName;
  }
  public BigDecimal getPrice(){
    return _price;
  }
  public void setPrice(BigDecimal price){
    _price=price;
  }
  public Byte getIsPt(){
    return _isPt;
  }
  public void setIsPt(Byte isPt){
    _isPt=isPt;
  }
  public Boolean gtIsPt(){
    return byteToBoolean(_isPt);
  }
  public void stIsPt(Boolean isPt){
    _isPt=booleanToByte(isPt);
  }
  public String getCheckcode(){
    return _checkcode;
  }
  public void setCheckcode(String checkcode){
    _checkcode=checkcode;
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
