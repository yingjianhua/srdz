package irille.wx.wx;

import java.util.Date;

import irille.core.sys.Sys.OYn;
import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.pub.tb.Tb.Index;

public class WxActionRecord extends BeanInt<WxActionRecord>{
	  private static final Log LOG = new Log(WxActionRecord.class);
	  public static final Tb TB = new Tb(WxActionRecord.class, "扩展访问记录").setAutoIncrement().addActList();

	  public enum T implements IEnumFld {// @formatter:off
	    PKEY(TB.crtIntPkey()),//主键
	    ACTION_NAME(SYS.STR__500, "访问地址"),
	    DEAL_PRIOD(SYS.INT_PLUS_OR_ZERO, "处理时长"),
	    VISIT_TIME(SYS.CREATED_DATE_TIME, "访问时间"),
	    VISIT_HOST(SYS.STR__100, "访问ip"),
	    SUCCESS_FLAG(SYS.YN, "访问结果"),
	    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
	    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
	    ;
	    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
	    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
	    // 索引
		public static final Index IDX_ACTION_NAME = TB.addIndex("actionName", false, T.ACTION_NAME);
		public static final Index IDX_VISIT_TIME = TB.addIndex("visitTime", false, T.VISIT_TIME);
		public static final Index IDX_VISIT_HOST = TB.addIndex("visitHost", false, T.VISIT_HOST);
		public static final Index IDX_SUCCESS_FLAG = TB.addIndex("successFlag", false, T.SUCCESS_FLAG);
		
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
	      _fld = TB.add(fld,this);
	    }
	    public Fld getFld() {
	      return _fld;
	    }
	  }
	  
	  static { // 在此可以加一些对FLD进行特殊设定的代码
	    T.PKEY.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
	  }
	  //@formatter:on
	  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _actionName;	// 访问地址  STR(100)
  private Integer _dealPriod;	// 处理时长  INT
  private Date _visitTime;	// 访问时间  TIME
  private String _visitHost;	// 访问ip  STR(100)
  private Byte _successFlag;	// 访问结果 <OYn>  BYTE
	// YES:1,是
	// NO:0,否

	@Override
  public WxActionRecord init(){
		super.init();
    _actionName=null;	// 访问地址  STR(100)
    _dealPriod=0;	// 处理时长  INT
    _visitTime=Env.getTranBeginTime();	// 访问时间  TIME
    _visitHost=null;	// 访问ip  STR(100)
    _successFlag=OYn.DEFAULT.getLine().getKey();	// 访问结果 <OYn>  BYTE
    return this;
  }

  //方法----------------------------------------------
  public Integer getPkey(){
    return _pkey;
  }
  public void setPkey(Integer pkey){
    _pkey=pkey;
  }
  public String getActionName(){
    return _actionName;
  }
  public void setActionName(String actionName){
    _actionName=actionName;
  }
  public Integer getDealPriod(){
    return _dealPriod;
  }
  public void setDealPriod(Integer dealPriod){
    _dealPriod=dealPriod;
  }
  public Date getVisitTime(){
    return _visitTime;
  }
  public void setVisitTime(Date visitTime){
    _visitTime=visitTime;
  }
  public String getVisitHost(){
    return _visitHost;
  }
  public void setVisitHost(String visitHost){
    _visitHost=visitHost;
  }
  public Byte getSuccessFlag(){
    return _successFlag;
  }
  public void setSuccessFlag(Byte successFlag){
    _successFlag=successFlag;
  }
  public Boolean gtSuccessFlag(){
    return byteToBoolean(_successFlag);
  }
  public void stSuccessFlag(Boolean successFlag){
    _successFlag=booleanToByte(successFlag);
  }

	  //<<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
	}
