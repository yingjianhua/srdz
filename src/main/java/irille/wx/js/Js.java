package irille.wx.js;

import irille.core.sys.SysModule;
import irille.pub.Log;
import irille.pub.bean.PackageBase;
import irille.pub.tb.EnumLine;
import irille.pub.tb.IEnumOpt;
import irille.pub.tb.Tb;
import irille.pub.tb.TbBase;
import irille.wx.wx.Wx_ConfPackage;

public class Js extends PackageBase {
	private static final Log LOG = new Log(Js.class);
	public static final Js INST = new Js();
	public static TbBase TB = new TbBase<Tb>(Js.class, "共享类"); // 定义公共的Fld对象用
	public static final String ON_MENU_SHARE_TIME_LINE = "onMenuShareTimeline";
	public static final String ON_MENU_SHARE_APP_MESSAGE = "onMenuShareAppMessage";
	public static final String ON_MENU_SHARE_QQ = "onMenuShareQQ";
	public static final String ON_MENU_SHARE_WEIBO  = "onMenuShareWeibo";
	public static final String ON_MENU_SHARE_QZONE  = "onMenuShareQZone";
	public static final String START_RECORD  = "startRecord";
	public static final String STOP_RECORD  = "stopRecord";
	public static final String PLAY_VOICE  = "playVoice";
	public static final String PAUSE_VOICE  = "pauseVoice ";
	public static final String STOP_VOICE  = "stopVoice ";
	public static final String ON_VOICE_PLAY_END  = "onVoicePlayEnd";
	public static final String UPLOAD_VOICE  = "uploadVoice";
	public static final String DOWNLOAD_VOICE  = "downloadVoice";
	public static final String CHOOSE_IMAGE  = "chooseImage";
	public static final String PREVIEW_IMAGE  = "previewImage";
	public static final String UPLOAD_IMAGE  = "uploadImage";
	public static final String DOWNLOAD_IMAGE  = "downloadImage";
	public static final String TRANSLATE_VOICE  = "translateVoice";
	public static final String GET_NETWORK_TYPE  = "getNetworkType ";
	public static final String OPEN_LOCATION  = "openLocation";
	public static final String GET_LOCATION  = "getLocation";
	public static final String HIDE_OPTION_MENU  = "hideOptionMenu";
	public static final String SHOW_OPTION_MENU  = "showOptionMenu";
	public static final String HIDE_MENU_ITEMS  = "hideMenuItems";
	public static final String SHOW_MENU_ITEMS  = "showMenuItems";
	public static final String HIDE_ALL_NON_BASE_MENU_ITEM  = "hideAllNonBaseMenuItem";
	public static final String SHOW_ALL_NON_BASE_MENU_ITEM  = "showAllNonBaseMenuItem";
	public static final String CLOSE_WINDOW  = "closeWindow";
	public static final String SCAN_QR_CODE  = "scanQRCode";
	public static final String CHOOSE_WX_PAY  = "chooseWXPay";
	public static final String OPEN_PRODUCT_SPECIFIC_VIEW  = "openProductSpecificView";
	public static final String ADD_CARD  = "addCard";
	public static final String CHOOSE_CARD  = "chooseCard";
	public static final String OPEN_CARD  = "openCard";
	private Js() {
	}

	@Override
	public void initTbMsg() { // 初始化表信息
		addTb(1, JsMenuShare.class);
		
	}

	public void initTranData() { // 初始化PrvTranData表数据
	}

	@Override
	public SysModule initModule() {
		return iuModule(Js.TB, "js-微共享-419");
	}

	public int getPackageId() {
		return Wx_ConfPackage.INST.getPackageId(getClass());
	}

	/**
	 * 初始化，在运行期间仅执行一次
	 */
	public void initOnlyOne() { // 初始化方法，在每次启动时执行一次
		super.initOnlyOne();
	}
  // @formatter:on
	 public enum OJsMenuType implements IEnumOpt {
		    MUSIC(0, "音乐"), VIDEO(1, "视频"), LINK(2, "链接");
		    public static String NAME = "动作设置";
		    public static OJsMenuType DEFAULT = LINK; // 定义缺省值
		    private EnumLine _line;
		    private OJsMenuType(int key, String name)  {_line = new EnumLine(this, key, name); }
		    public EnumLine getLine() {
		      return _line;
		    }
		  }
}
