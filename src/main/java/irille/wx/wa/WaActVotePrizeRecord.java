package irille.wx.wa;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WaActVotePrizeRecord extends BeanInt<WaActVotePrizeRecord>{
	 private static final Log LOG = new Log(WaActVotePrizeRecord.class);
	  public static final Tb TB = new Tb(WaActVotePrizeRecord.class, "获奖记录").setAutoIncrement().addActList();

	  public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()),//主键
		VOTE(WaActVote.fldOutKey()),
		WINNER(WxUser.fldOutKey()), 
		PRIZEITEM(WaActItem.fldOutKey()),
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
  private Integer _winner;	// 关注用户 <表主键:WxUser>  INT
  private Integer _prizeitem;	// 奖项设置 <表主键:WaActItem>  INT
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActVotePrizeRecord init(){
		super.init();
    _vote=null;	// 投票活动 <表主键:WaActVote>  INT
    _winner=null;	// 关注用户 <表主键:WxUser>  INT
    _prizeitem=null;	// 奖项设置 <表主键:WaActItem>  INT
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
  public Integer getWinner(){
    return _winner;
  }
  public void setWinner(Integer winner){
    _winner=winner;
  }
  public WxUser gtWinner(){
    if(getWinner()==null)
      return null;
    return (WxUser)get(WxUser.class,getWinner());
  }
  public void stWinner(WxUser winner){
    if(winner==null)
      setWinner(null);
    else
      setWinner(winner.getPkey());
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
