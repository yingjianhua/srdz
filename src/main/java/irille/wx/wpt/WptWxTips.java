package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public class WptWxTips extends BeanInt<WptWxTips> {
	 private static final Log LOG = new Log(WptWxTips.class);
	  public static final Tb TB = new Tb(WptWxTips.class, "微信提醒用户").setAutoLocal().addActList().addActIns().addActDel();

	  public enum T implements IEnumFld {// @formatter:off
		USER(WxUser.fldOneToOne()), //自动新建 PKEY字段
		CMB_WX(CmbWx.fldFlds()),
	    //>>>以下是自动产生的源代码行--内嵌字段定义--请保留此行用于识别>>>
			PKEY(TB.get("pkey")),	//编号
			ACCOUNT(TB.get("account")),	//公众帐号
			ROW_VERSION(TB.get("rowVersion")),	//版本
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
	    T.USER.getFld().getTb().lockAllFlds();// 加锁所有字段,不可以修改
	  }
	  //@formatter:on
	  public static Fld fldOutKey() {
		    Fld fld = fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
		    fld.setType(null);
		    return fld;
		  }

	  public static Fld fldOutKey(String code, String name) {
	    return Tb.crtOutKey(TB, code, name);
	  }
	  //>>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
	  //实例变量定义-----------------------------------------
	  private Integer _pkey;	// 编号  INT
	  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
	  private Short _rowVersion;	// 版本  SHORT

		@Override
	  public WptWxTips init(){
			super.init();
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
	  //取一对一表对象: WxUser
	  public WxUser gtUser(){
	    return get(WxUser.class,getPkey());
	  }
	  public void stUser(WxUser user){
	      setPkey(user.getPkey());
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
