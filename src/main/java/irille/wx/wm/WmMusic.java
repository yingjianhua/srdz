package irille.wx.wm;



import irille.pub.Log;
import irille.pub.bean.BeanInt;
import irille.pub.inf.IExtName;
import irille.pub.tb.Fld;
import irille.pub.tb.IEnumFld;
import irille.pub.tb.Tb;
import irille.wx.CmbWx;
import irille.wx.wx.Wx;
import irille.wx.wx.Wx.OSyncStatus;
import irille.wx.wx.WxAccount;

public class WmMusic extends BeanInt<WmMusic> implements IExtName {
	private static final Log LOG = new Log(WmMusic.class);
	public static final Tb TB = new Tb(WmMusic.class, "音乐消息").setAutoIncrement().addActIUDL().addActOpt("upload", "上传");

	public enum T implements IEnumFld {// @formatter:off
		PKEY(TB.crtIntPkey()), // 主键
		TITLE(SYS.STR__100,"音乐标题"),
		DESCRIPTION(SYS.STR__1000,"音乐描述"),
		MUSIC_URL(SYS.STR__100,"音乐"),
		HQMUSIC_URL(SYS.STR__100,"高质量音乐"),
		THUMB_MEDIA_ID(SYS.STR__100_NULL,"缩略图ID"),
		THUMB_URL(SYS.STR__100_NULL, "缩略图"),
		STATUS(Tb.crt(Wx.OSyncStatus.DEFAULT)),
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
		    return fldOutKey(TB.getCodeNoPackage(), TB.getShortName());
		  }
	public static Fld fldOutKey(String code, String name) {
		return Tb.crtOutKey(TB, code, name);
	}
	@Override
	  public String getExtName() {
	    return getTitle();
	  }

	// >>>以下是自动产生的源代码行--源代码--请保留此行用于识别>>>
  //实例变量定义-----------------------------------------
  private Integer _pkey;	// 编号  INT
  private String _title;	// 音乐标题  STR(100)
  private String _description;	// 音乐描述  STR(1000)
  private String _musicUrl;	// 音乐  STR(100)
  private String _hqmusicUrl;	// 高质量音乐  STR(100)
  private String _thumbMediaId;	// 缩略图ID  STR(100)<null>
  private String _thumbUrl;	// 缩略图  STR(100)<null>
  private Byte _status;	// 同步状态 <OSyncStatus>  BYTE
	// INIT:1,未同步
	// SYNC:2,已同步
	// DEL:9,已删除
  private Integer _account;	// 公众帐号 <表主键:WxAccount>  INT
  private Short _rowVersion;	// 版本  SHORT

	@Override
  public WmMusic init(){
		super.init();
    _title=null;	// 音乐标题  STR(100)
    _description=null;	// 音乐描述  STR(1000)
    _musicUrl=null;	// 音乐  STR(100)
    _hqmusicUrl=null;	// 高质量音乐  STR(100)
    _thumbMediaId=null;	// 缩略图ID  STR(100)
    _thumbUrl=null;	// 缩略图  STR(100)
    _status=OSyncStatus.DEFAULT.getLine().getKey();	// 同步状态 <OSyncStatus>  BYTE
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
  public String getTitle(){
    return _title;
  }
  public void setTitle(String title){
    _title=title;
  }
  public String getDescription(){
    return _description;
  }
  public void setDescription(String description){
    _description=description;
  }
  public String getMusicUrl(){
    return _musicUrl;
  }
  public void setMusicUrl(String musicUrl){
    _musicUrl=musicUrl;
  }
  public String getHqmusicUrl(){
    return _hqmusicUrl;
  }
  public void setHqmusicUrl(String hqmusicUrl){
    _hqmusicUrl=hqmusicUrl;
  }
  public String getThumbMediaId(){
    return _thumbMediaId;
  }
  public void setThumbMediaId(String thumbMediaId){
    _thumbMediaId=thumbMediaId;
  }
  public String getThumbUrl(){
    return _thumbUrl;
  }
  public void setThumbUrl(String thumbUrl){
    _thumbUrl=thumbUrl;
  }
  public Byte getStatus(){
    return _status;
  }
  public void setStatus(Byte status){
    _status=status;
  }
  public OSyncStatus gtStatus(){
    return (OSyncStatus)(OSyncStatus.INIT.getLine().get(_status));
  }
  public void stStatus(OSyncStatus status){
    _status=status.getLine().getKey();
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
