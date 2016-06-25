package irille.wx.wa;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WaActVotePrize extends BeanInt<WaActVotePrize>{
	 private static final Log LOG = new Log(WaActVotePrize.class);
	  public static final Tb TB = new Tb(WaActVotePrize.class, "奖品配置").setAutoIncrement();

	  public enum T implements IEnumFld {// @formatter:off
	    PKEY(TB.crtIntPkey()),//主键
	    VOTE(WaActVote.fldOutKey()),
	    PRIZEITEM(WaActItem.fldOutKey()),
	    PRIZE(WaActPrize.fldOutKey()),
	    AMOUNT(SYS.INT,"数量"),
	    UNIT(SYS.STR__10,"单位"),
	    SORT(SYS.INT,"排序"),
	    CMB_WX(CmbWx.fldFlds()),	    
	    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
	    //<<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
	    ;
	    //>>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
	    //<<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
	    // 索引
//	    public static final Index IDX_ACT_WX_USER = TB.addIndex("actWxUser", true,T.ACT,T.WX_USER);
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
	    T.CMB_WX.getFld().getTb().lockAllFlds();
	  }
	  //@formatter:on
	  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _vote;	// 投票活动 <表主键:WaActVote>  INT
  private Integer _prizeitem;	// 奖项设置 <表主键:WaActItem>  INT
  private Integer _prize;	// 奖品设置 <表主键:WaActPrize>  INT
  private Integer _amount;	// 数量  INT
  private String _unit;	// 单位  STR(10)
  private Integer _sort;	// 排序  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActVotePrize init(){
		super.init();
    _vote=null;	// 投票活动 <表主键:WaActVote>  INT
    _prizeitem=null;	// 奖项设置 <表主键:WaActItem>  INT
    _prize=null;	// 奖品设置 <表主键:WaActPrize>  INT
    _amount=0;	// 数量  INT
    _unit=null;	// 单位  STR(10)
    _sort=0;	// 排序  INT
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
  public Integer getVote(){
    return _vote;
  }
  public void setVote(Integer vote){
    _vote=vote;
  }
  public WaActVote gtVote(){
    if(getVote()==null)
      return null;
    return (WaActVote)get(WaActVote.class,getVote());
  }
  public void stVote(WaActVote vote){
    if(vote==null)
      setVote(null);
    else
      setVote(vote.getPkey());
  }
  public Integer getPrizeitem(){
    return _prizeitem;
  }
  public void setPrizeitem(Integer prizeitem){
    _prizeitem=prizeitem;
  }
  public WaActItem gtPrizeitem(){
    if(getPrizeitem()==null)
      return null;
    return (WaActItem)get(WaActItem.class,getPrizeitem());
  }
  public void stPrizeitem(WaActItem prizeitem){
    if(prizeitem==null)
      setPrizeitem(null);
    else
      setPrizeitem(prizeitem.getPkey());
  }
  public Integer getPrize(){
    return _prize;
  }
  public void setPrize(Integer prize){
    _prize=prize;
  }
  public WaActPrize gtPrize(){
    if(getPrize()==null)
      return null;
    return (WaActPrize)get(WaActPrize.class,getPrize());
  }
  public void stPrize(WaActPrize prize){
    if(prize==null)
      setPrize(null);
    else
      setPrize(prize.getPkey());
  }
  public Integer getAmount(){
    return _amount;
  }
  public void setAmount(Integer amount){
    _amount=amount;
  }
  public String getUnit(){
    return _unit;
  }
  public void setUnit(String unit){
    _unit=unit;
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
