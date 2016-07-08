package irille.wx.wpt;

import java.math.BigDecimal;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.wx.WxAccount;

public class WptRedPackRule extends BeanInt<WptRedPackRule> {
	private static final Log LOG = new Log(WptRedPackRule.class);
	public static final Tb TB = new Tb(WptRedPackRule.class, "红包规则").setAutoLocal().addActList().addActOpt("set", "设置", "edit-icon");

	public enum T implements IEnumFld {// @formatter:off
		ACCOUNT(WxAccount.fldOneToOne()),
		SEND_NAME(SYS.STR__100, "商户名称"),
		WISHING(SYS.STR__200, "红包祝福语"),
		ACT_NAME(SYS.STR__100, "活动名称"),
		remark(SYS.STR__200, "备注"),
		LEAST_AMT(SYS.AMT, "最少提现金额"),
		ROW_VERSION(SYS.ROW_VERSION),

		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		PKEY(TB.get("pkey")),	//编号
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
		T.ACCOUNT.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
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
  private String _sendName;	// 商户名称  STR(100)
  private String _wishing;	// 红包祝福语  STR(200)
  private String _actName;	// 活动名称  STR(100)
  private String _remark;	// 备注  STR(200)
  private BigDecimal _leastAmt;	// 最少提现金额  DEC(16,2)
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WptRedPackRule init(){
		super.init();
    _sendName=null;	// 商户名称  STR(100)
    _wishing=null;	// 红包祝福语  STR(200)
    _actName=null;	// 活动名称  STR(100)
    _remark=null;	// 备注  STR(200)
    _leastAmt=ZERO;	// 最少提现金额  DEC(16,2)
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
  //取一对一表对象: WxAccount
  public WxAccount gtAccount(){
    return get(WxAccount.class,getPkey());
  }
  public void stAccount(WxAccount account){
      setPkey(account.getPkey());
  }
  public String getSendName(){
    return _sendName;
  }
  public void setSendName(String sendName){
    _sendName=sendName;
  }
  public String getWishing(){
    return _wishing;
  }
  public void setWishing(String wishing){
    _wishing=wishing;
  }
  public String getActName(){
    return _actName;
  }
  public void setActName(String actName){
    _actName=actName;
  }
  public String getRemark(){
    return _remark;
  }
  public void setRemark(String remark){
    _remark=remark;
  }
  public BigDecimal getLeastAmt(){
    return _leastAmt;
  }
  public void setLeastAmt(BigDecimal leastAmt){
    _leastAmt=leastAmt;
  }
  public Short getRowVersion(){
    return _rowVersion;
  }
  public void setRowVersion(Short rowVersion){
    _rowVersion=rowVersion;
  }

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
