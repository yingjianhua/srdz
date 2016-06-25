package irille.wx.wa;

import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wa.Wa.OActImageShape;
import irille.wx.wa.Wa.OActUnit;
import irille.wx.wx.WxAccount;

public class WaActConfig extends BeanInt<WaActConfig> implements IExtName{
	private static final Log LOG = new Log(WaActConfig.class);
	public static final Tb TB = new Tb(WaActConfig.class, "参数设置").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		NAME(SYS.NAME__100, "名称"), // 名称
		CYCLE(SYS.INT_PLUS_OR_ZERO, "活动周期"), // 活动周期
		UNIT(TB.crt(Wa.OActUnit.DEFAULT)), // 单位
		CYCLE_LIMIT(SYS.INT_PLUS_OR_ZERO, "周期参与数"), // 周期参与数
		CYCLE_LIMIT_WORDS(SYS.STR__100, "周期参与数提示语"), // 周期参与数提示语
		IP_LIMIT(SYS.INT_PLUS_OR_ZERO, "同IP周期参与数"), // 同IP周期参与数
		IP_LIMIT_WORDS(SYS.STR__100, "同IP周期参与数提示语"), // 同IP周期参与数提示语
		VIEW_RATE(SYS.INT_PLUS_OR_ZERO, "浏览量系数"), // 浏览量系数
		ENTRY_RATE(SYS.INT_PLUS_OR_ZERO, "报名数系数"), // 报名数系数
		ACT_RATE(SYS.INT_PLUS_OR_ZERO, "参与数系数"), // 参与数系数
		IMAGE_SHAPE(TB.crt(Wa.OActImageShape.DEFAULT)),//图片格式
		CUSTOM_MENU(SYS.NY,"自定义菜单"),//自定义菜单
		RES_AREA(SYS.NY, "限制区域"), // 限制区域
		RES_AREA_WORDS(SYS.STR__100_NULL, "限制区域异常提示"), // 限制区域异常提示
		AREA(SYS.STR__100_NULL, "有效区域"),
		INVALID_AREA(SYS.STR__100_NULL, "无效区域"),
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
		return getName();
	}

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _name;	// 名称  STR(100)
  private Integer _cycle;	// 活动周期  INT
  private Byte _unit;	// 单位 <OActUnit>  BYTE
	// DAY:1,天
	// WEEK:2,周
  private Integer _cycleLimit;	// 周期参与数  INT
  private String _cycleLimitWords;	// 周期参与数提示语  STR(100)
  private Integer _ipLimit;	// 同IP周期参与数  INT
  private String _ipLimitWords;	// 同IP周期参与数提示语  STR(100)
  private Integer _viewRate;	// 浏览量系数  INT
  private Integer _entryRate;	// 报名数系数  INT
  private Integer _actRate;	// 参与数系数  INT
  private Byte _imageShape;	// 图片限制 <OActImageShape>  BYTE
	// SQ:0,方图
	// HO:1,横图
	// VE:2,竖图
	// UL:3,不限制
  private Byte _customMenu;	// 自定义菜单 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private Byte _resArea;	// 限制区域 <OYn>  BYTE
	// YES:1,是
	// NO:0,否
  private String _resAreaWords;	// 限制区域异常提示  STR(100)<null>
  private String _area;	// 有效区域  STR(100)<null>
  private String _invalidArea;	// 无效区域  STR(100)<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActConfig init(){
		super.init();
    _name=null;	// 名称  STR(100)
    _cycle=0;	// 活动周期  INT
    _unit=OActUnit.DEFAULT.getLine().getKey();	// 单位 <OActUnit>  BYTE
    _cycleLimit=0;	// 周期参与数  INT
    _cycleLimitWords=null;	// 周期参与数提示语  STR(100)
    _ipLimit=0;	// 同IP周期参与数  INT
    _ipLimitWords=null;	// 同IP周期参与数提示语  STR(100)
    _viewRate=0;	// 浏览量系数  INT
    _entryRate=0;	// 报名数系数  INT
    _actRate=0;	// 参与数系数  INT
    _imageShape=OActImageShape.DEFAULT.getLine().getKey();	// 图片限制 <OActImageShape>  BYTE
    _customMenu=OYn.DEFAULT.getLine().getKey();	// 自定义菜单 <OYn>  BYTE
    _resArea=OYn.DEFAULT.getLine().getKey();	// 限制区域 <OYn>  BYTE
    _resAreaWords=null;	// 限制区域异常提示  STR(100)
    _area=null;	// 有效区域  STR(100)
    _invalidArea=null;	// 无效区域  STR(100)
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
  public String getName(){
    return _name;
  }
  public void setName(String name){
    _name=name;
  }
  public Integer getCycle(){
    return _cycle;
  }
  public void setCycle(Integer cycle){
    _cycle=cycle;
  }
  public Byte getUnit(){
    return _unit;
  }
  public void setUnit(Byte unit){
    _unit=unit;
  }
  public OActUnit gtUnit(){
    return (OActUnit)(OActUnit.DAY.getLine().get(_unit));
  }
  public void stUnit(OActUnit unit){
    _unit=unit.getLine().getKey();
  }
  public Integer getCycleLimit(){
    return _cycleLimit;
  }
  public void setCycleLimit(Integer cycleLimit){
    _cycleLimit=cycleLimit;
  }
  public String getCycleLimitWords(){
    return _cycleLimitWords;
  }
  public void setCycleLimitWords(String cycleLimitWords){
    _cycleLimitWords=cycleLimitWords;
  }
  public Integer getIpLimit(){
    return _ipLimit;
  }
  public void setIpLimit(Integer ipLimit){
    _ipLimit=ipLimit;
  }
  public String getIpLimitWords(){
    return _ipLimitWords;
  }
  public void setIpLimitWords(String ipLimitWords){
    _ipLimitWords=ipLimitWords;
  }
  public Integer getViewRate(){
    return _viewRate;
  }
  public void setViewRate(Integer viewRate){
    _viewRate=viewRate;
  }
  public Integer getEntryRate(){
    return _entryRate;
  }
  public void setEntryRate(Integer entryRate){
    _entryRate=entryRate;
  }
  public Integer getActRate(){
    return _actRate;
  }
  public void setActRate(Integer actRate){
    _actRate=actRate;
  }
  public Byte getImageShape(){
    return _imageShape;
  }
  public void setImageShape(Byte imageShape){
    _imageShape=imageShape;
  }
  public OActImageShape gtImageShape(){
    return (OActImageShape)(OActImageShape.UL.getLine().get(_imageShape));
  }
  public void stImageShape(OActImageShape imageShape){
    _imageShape=imageShape.getLine().getKey();
  }
  public Byte getCustomMenu(){
    return _customMenu;
  }
  public void setCustomMenu(Byte customMenu){
    _customMenu=customMenu;
  }
  public Boolean gtCustomMenu(){
    return byteToBoolean(_customMenu);
  }
  public void stCustomMenu(Boolean customMenu){
    _customMenu=booleanToByte(customMenu);
  }
  public Byte getResArea(){
    return _resArea;
  }
  public void setResArea(Byte resArea){
    _resArea=resArea;
  }
  public Boolean gtResArea(){
    return byteToBoolean(_resArea);
  }
  public void stResArea(Boolean resArea){
    _resArea=booleanToByte(resArea);
  }
  public String getResAreaWords(){
    return _resAreaWords;
  }
  public void setResAreaWords(String resAreaWords){
    _resAreaWords=resAreaWords;
  }
  public String getArea(){
    return _area;
  }
  public void setArea(String area){
    _area=area;
  }
  public String getInvalidArea(){
    return _invalidArea;
  }
  public void setInvalidArea(String invalidArea){
    _invalidArea=invalidArea;
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
