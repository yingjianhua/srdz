package irille.wx.wa;


import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;

public class WaActVoteBanner extends BeanInt<WaActVoteBanner> {

	public static final Tb TB = new Tb(WaActVoteBanner.class, "滚动图").setAutoIncrement().addActIUDL();

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		VOTE(WaActVote.fldOutKey()),
		PIC_URL(SYS.PHOTO__NULL,"图片"),
		URL(SYS.STR__100_NULL,"链接"),
		DESCRIPTION(SYS.STR__100_NULL,"描述"),//描述
		SORT(SYS.INT_PLUS_OR_ZERO, "排序", true),
		CMB_WX(CmbWx.fldFlds()),
		// >>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
		ACCOUNT(TB.get("account")),	//公众帐号
		ROW_VERSION(TB.get("rowVersion")),	//版本
		// <<<以上是自动产生的源代码行--内嵌字段定义--请保留此行用于识别<<<
		;
		// >>>以下是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别>>>
		// <<<以上是自动产生的源代码行--自动建立的索引定义--请保留此行用于识别<<<
		// 索引
		// public static final Index IDX_ACT_WX_USER = TB.addIndex("actWxUser",
		// true,T.ACT,T.WX_USER);
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


	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private Integer _vote;	// 投票活动 <表主键:WaActVote>  INT
  private String _picUrl;	// 图片  STR(200)<null>
  private String _url;	// 链接  STR(100)<null>
  private String _description;	// 描述  STR(100)<null>
  private Integer _sort;	// 排序  INT<null>
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WaActVoteBanner init(){
		super.init();
    _vote=null;	// 投票活动 <表主键:WaActVote>  INT
    _picUrl=null;	// 图片  STR(200)
    _url=null;	// 链接  STR(100)
    _description=null;	// 描述  STR(100)
    _sort=null;	// 排序  INT
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
  public String getPicUrl(){
    return _picUrl;
  }
  public void setPicUrl(String picUrl){
    _picUrl=picUrl;
  }
  public String getUrl(){
    return _url;
  }
  public void setUrl(String url){
    _url=url;
  }
  public String getDescription(){
    return _description;
  }
  public void setDescription(String description){
    _description=description;
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

	// <<<以上是自动产生的源代码行--源代码--请保留此行用于识别<<<
}
