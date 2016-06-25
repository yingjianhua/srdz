package irille.action;

import irille.core.sys.SysFileStock;
import irille.pub.Exp;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.Str;
import irille.pub.bean.BeanMain;
import irille.pub.ext.Ext.IExtOut;
import irille.pub.html.ExtList;
import irille.pub.idu.Idu;
import irille.pub.svr.DepConstants;
import irille.pub.svr.Env;
import irille.pub.uploads.FileSystemSaverNew;
import irille.pub.uploads.ImageUtils;
import irille.pub.uploads.Photo;
import irille.util.MD5Util;
import irille.wx.CmbWx;
import irille.wx.js.Js;
import irille.wx.js.JsMenuShare;
import irille.wx.wa.Wa.OActImageShape;
import irille.wx.wa.Wa.OActPageType;
import irille.wx.wa.WaActConfig;
import irille.wx.wa.WaActTemplateLine;
import irille.wx.wx.Wx;
import irille.wx.wx.Wx.OAccountType;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxUser;
import irille.wxpub.js.JMBase;
import irille.wxpub.js.JMCheckJsApi;
import irille.wxpub.js.JMChooseImage;
import irille.wxpub.js.JMConfig;
import irille.wxpub.js.JMError;
import irille.wxpub.js.JMOnMenuShareAppMessage;
import irille.wxpub.js.JMOnMenuShareQQ;
import irille.wxpub.js.JMOnMenuShareQZone;
import irille.wxpub.js.JMOnMenuShareTimeline;
import irille.wxpub.js.JMOnMenuShareWeibo;
import irille.wxpub.js.JMReady;
import irille.wxpub.js.JMUploadImage;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;
import irille.wxpub.js.JsFldDefine;
import irille.wxpub.js.JsFunDefine;
import irille.wxpub.util.Crypto;
import irille.wxpub.util.HttpRequestUtil;
import irille.wxpub.util.MchUtil;
import irille.wxpub.util.WeixinUtil;
import irille.wxpub.util.WxMaterialUtil;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ActionWx<MAIN extends BeanMain, THIS extends ActionWx> extends ActionUpload<MAIN>{
	public static final Log LOG = new Log(ActionWx.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		customErr("{0}"),
		shapeErr("图片形状不正确，请选择【{0}】上传!"),
		pageErr("页面异常，请联系管理员!"),
		classErr("类【{0}】非JMBase类型，无法创建!"),
		accessErr("没有对应类【0】的操作权限，无法创建!"),
		notImage("只支持bmp/png/jpeg/jpg/gif格式的图片文件"),
		notVoice("只支持mp3/wma/wav/amr格式的语音文件"),
		overLimit5M("语音大小不能超过5M"),
		overLimit2M("图片大小不能超过2M"),
		expire("网页超时，请刷新!"),
		unsbscribErr("qrerr"),
		oauthErr("订阅号没有绑定三方授权代理号，不能进行网页授权"),
		oauthErr2("网页授权失败，未知错误"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	
	public static final String UNEXIST_PAGE = "err/404.html";
	public static final String SIZE_TYPE_ORIGINAL = "original";
	public static final String SIZE_TYPE_COMPRESSED = "compressed";
	public static final String SOURCE_TYPE_ALBUM = "album";
	public static final String SOURCE_TYPE_CAMERA = "camera";
	public static final String LOCATION_TYPE_GPS = "wgs84";
	public static final String LOCATION_TYPE_MARS = "gcj02";
	public static final String SCAN_TYPE_QRCODE = "qrCode";
	public static final String SCAN_TYPE_BARCODE = "barCode";
	public static final String SHARE_TYPE_MUSIC = "music";
	public static final String SHARE_TYPE_VIDEO = "video";
	public static final String SERIAL_NUMBER = "serialNumber";
	
	/** 微信网页授权获取CODE **/
	public static final String oauth2_authorize_url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	/** 微信网页授权获取网页accesstoken和OPENID **/
	public static final String oauth2_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	private String[] _mediaIds;
	private String _rtnStr;
	private String _errMsg;
	private String _succMsg;
	private WxUser _wxUser;
	private WxAccount _account;
	private String _url;
	private JMConfig _config;
	private JMReady _ready = new JMReady();
	private JMError _error = new JMError();
	private Vector<IExtOut> _nodes = new Vector();
	private String _jsCode;
	private JSONObject _json = new JSONObject();
	private JSONArray _jsonList;
	private OActPageType _currPage;
	private String _code = null;
	private String serialNumber;//流水号
	
	/**
	 *  网页授权重定向后带上的state参数
	 * */
	private static final String STATE = "14hb1a5e4f15a";
	private String _state = null;
	private String _domain = ServletActionContext.getServletContext().getInitParameter("webPath");
	
	public String[] getMediaIds() {
		return _mediaIds;
	}

	public void setMediaIds(String[] mediaIds) {
		_mediaIds = mediaIds;
	}

	public String getRtnStr() {
		return _rtnStr;
	}

	public void setRtnStr(String rtnStr) {
		_rtnStr = rtnStr;
	}

	public String getErrMsg() {
		return _errMsg;
	}

	public void setErrMsg(String errMsg) {
		_errMsg = errMsg;
	}

	public String getSuccMsg() {
		return _succMsg;
	}

	public void setSuccMsg(String succMsg) {
		_succMsg = succMsg;
	}

	public WxUser getWxUser() {
		return _wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		_wxUser = wxUser;
	}

	public WxAccount getAccount() {
		return _account;
	}

	public void setAccount(WxAccount account) {
		_account = account;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}
	
	public String setUrl(boolean spec, String method) {
		String[] s = beanClazz().toString().split("\\.");
		_url = (spec ? DepConstants.FILE_SEPA : "") + "expand_" + s[2] + "_" + s[3] + "_" + method;
		return _url;
	}

	public JMConfig getConfig() {
		return _config;
	}

	public void setConfig(JMConfig config) {
		_config = config;
	}

	public JMReady getReady() {
		return _ready;
	}

	public void setReady(JMReady ready) {
		_ready = ready;
	}

	public JMError getError() {
		return _error;
	}

	public void setError(JMError error) {
		_error = error;
	}

	public Vector<IExtOut> getNodes() {
		return _nodes;
	}

	public void setNodes(Vector<IExtOut> nodes) {
		_nodes = nodes;
	}

	public String getJsCode() {
		return _jsCode;
	}

	public void setJsCode(String jsCode) {
		_jsCode = jsCode;
	}

	public JSONObject getJson() {
		return _json;
	}

	public void setJson(JSONObject json) {
		_json = json;
	}

	public JSONArray getJsonList() {
		return _jsonList;
	}

	public void setJsonList(JSONArray jsonList) {
		_jsonList = jsonList;
	}

	public OActPageType getCurrPage() {
		return _currPage;
	}

	public void setCurrPage(OActPageType currPage) {
		_currPage = currPage;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		this._code = code;
	}

	public String getState() {
		return _state;
	}

	public void setState(String state) {
		this._state = state;
	}

	public String getDomain() {
		return _domain;
	}

	public void setDomain(String domain) {
		_domain = domain;
	}

	public String show () {
		showBefore();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		showValid();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		showRun();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		showAfter();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		return null;
	}
	
	public void showBefore() {
		
	}
	
	public void showValid() {
		
	}
	
	public void showRun() {
		
	}
	
	public void showAfter() {
		
	}
	
	public String showList () {
		showListBefore();
		System.out.println(1);
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		System.out.println(2);
		showListValid();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		showListRun();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		showListAfter();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		return null;
	}
	
	public void showListBefore() {
		
	}
	
	public void showListValid() {
		
	}
	
	public void showListRun() {
		
	}
	
	public void showListAfter() {
		
	}
	
	public String showInfo () {
		showInfoBefore();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		showInfoValid();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		showInfoRun();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		showInfoAfter();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		return null;
	}
	
	public void showInfoBefore() {
		
	}
	
	public void showInfoValid() {
		
	}
	
	public void showInfoRun() {
		
	}
	
	public void showInfoAfter() {
		
	}
	
	/**
	 * ajax专用的执行方法
	 * @throws JSONException
	 * @throws IOException
	 */
	public void ajaxcute() throws JSONException, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			xcuteBefore();
			xcuteValid();
			xcuteRun();
			xcuteAfter();
			getJson().put("success", true);
			getJson().put("succMsg", getSuccMsg());
		} catch (Exp e) {
			getJson().put("error", e.getLastCode());
			getJson().put("errMsg", e.getLastMessage());
		}
		response.getWriter().print(getJson().toString());
	}
	
	public String xcute () {
		xcuteBefore();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		xcuteValid();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		xcuteRun();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		xcuteAfter();
		if (!Str.isEmpty(getResult()) && !Str.isEmpty(getRtnStr()))
			return getRtnStr();
		return null;
	}
	
	public void xcuteBefore() {
		
	}
	
	public void xcuteValid() {
		
	}
	
	public void xcuteRun() {
		
	}
	
	public void xcuteAfter() {
		
	}

	/**
	 * 返回模板类型对应的页面
	 * @param page 页面类型
	 * @param Template 模板主键
	 * @return
	 */
	public WaActTemplateLine getResultPage(OActPageType pageType, Integer Template) {
		WaActTemplateLine line = WaActTemplateLine.chkUniqueTypeMain(false, pageType.getLine().getKey(), Template);
		if (line == null)
			throw LOG.err(Msgs.pageErr);
		return line;
	}
	
	@Override
	  public String crtAll() {
	    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
	    if(account==null){
	      return "1=2";
	    }else{
	      return CmbWx.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
	    }
	  }

	public String getIp() {
		return ServletActionContext.getRequest().getRemoteAddr();
	}
	
	/**
	 * 解析JSON字符串
	 * 
	 * @param name
	 *            需要获取的key
	 * @param json
	 *            返回的JSON的字符串，"var name = {};"格式
	 * @return key对应的value值
	 * @throws JSONException
	 */
	protected String getValue(String name, String json) throws JSONException {
		// 将 "var name = {};"格式的JSON字符串转换成"{}"格式的JSON字符串
		StringBuffer sBuffer = new StringBuffer(json);
		int startIndex = sBuffer.indexOf("{");
		int endIndex = sBuffer.lastIndexOf("}");
		json = sBuffer.substring(startIndex, endIndex + 1);
		// 解析JSON,获取key对应的value
		JSONObject dataJson = new JSONObject(json);
		return dataJson.getString(name);
	}
	
	/**
	 * 验证访问IP地址是否在限制区域内
	 * @throws JSONException 
	 * 
	 * @throws Exception
	 */
	public void chkAreaRes(WaActConfig config) {
		try {
			if (!config.gtResArea())
				return;
			String jsonString = HttpRequestUtil
					.httpRequestPost("http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=js&ip=" + getIp());
			String country = getValue("country", jsonString);
			String province = getValue("province", jsonString);
			String city = getValue("city", jsonString);
			
			String area = config.getArea();
			String invalidArea = config.getInvalidArea();
			if (!area.contains(country) && !area.contains(province) && !area.contains(city) && (invalidArea.contains(country) || invalidArea.contains(province) || invalidArea.contains(city)))
				throw LOG.err(Msgs.customErr, config.getResAreaWords());
		} catch (IOException e) {
			//TODO 判读用户的ip地址所属区域出错的处理
			e.printStackTrace();
		} catch (JSONException e) {
			//TODO
			e.printStackTrace();
		}
	}
	
	/**
	 * 从微信下载图片到本地,不限制图片形状
	 * @param account
	 * @param maxWidth 图片压缩尺寸
	 * @return 本地图片url
	 * @throws IOException
	 */
	public String[] getImageUrls() throws IOException {
		return getImageUrls(0, OActImageShape.UL);
	}
	
	/**
	 * 从微信下载图片到本地
	 * @param maxWidth 图片压缩尺寸
	 * @param config 通用参数设置
	 * @return 本地图片url
	 * @throws IOException
	 */
	public String[] getImageUrls(int maxWidth, WaActConfig config) throws IOException {
		return getImageUrls(maxWidth, config.gtImageShape());
	}

	/**
	 * 从微信下载图片到本地
	 * @param maxWidth 图片压缩尺寸
	 * @param shape 形状
	 * @return 本地图片url
	 * @throws IOException
	 */
	public String[] getImageUrls(int maxWidth, OActImageShape shape) throws IOException {
		String[] urls = new String[getMediaIds().length];
		int i = 0;
		for (String mediaId : getMediaIds()) {
			setFileFileName(String.valueOf(Env.getSystemTime().getTime()) + ".jpg");
			setFile(new File(getUploadPath(true) + File.separator + getFileFileName()));//产生临时文件
			WeixinUtil.downloadMedia(getAccount(), mediaId, getFile());
			//验证上传图片是否符合限制规则
			chkImageShape(shape,getFile());
			
			int width = maxWidth == 0 ? ImageUtils.getImageWidth(getFile()) : maxWidth;
			int height = maxWidth == 0 ? ImageUtils.getImageHeight(getFile()) : getMaxHeight(getFile(), maxWidth);
			Photo file = saveImage("Wa", width, height);
			urls[i] = getUploadPath(false)+file.getPreviewURL();
			if (getFile().exists())
				getFile().delete();
			i++;
		}
		return urls;
	}
	
	public int getMaxHeight(File image, int widthLimit) throws IOException {
		return widthLimit * ImageUtils.getImageHeight(image) / ImageUtils.getImageWidth(image);
	}
	
	/**
	 * 验证上传图片是否符合限制规则
	 * 
	 * @param shape
	 * @param image
	 *            需要验证的图片文件
	 * @throws IOException
	 */
	private void chkImageShape(OActImageShape shape, File image) throws IOException {
		if (shape.equals(OActImageShape.UL))
			return;
		if (shape.equals(OActImageShape.SQ) && !ImageUtils.isSquare(image))
			throw LOG.err(Msgs.shapeErr, OActImageShape.SQ.getLine().getName());
		if (shape.equals(OActImageShape.HO) && !ImageUtils.isHorizontal(image))
			throw LOG.err(Msgs.shapeErr, OActImageShape.HO.getLine().getName());
		if (shape.equals(OActImageShape.VE) && !ImageUtils.isVertical(image))
			throw LOG.err(Msgs.shapeErr, OActImageShape.VE.getLine().getName());
	}
	
	/**
	 * 校验是否关注公众号
	 * @param template 模板pkey，用于设置未关注用户转发页面url
	 * @return
	 */
	public void chkSubscrib() {
		WxUser user = chkWxUser();
		if(user == null || user.gtStatus() == Wx.OStatus.NOFOLLOW)
			throw LOG.err(Msgs.unsbscribErr);
		setWxUser(user);
	}
	/**
	 * 进行网页授权
	 * 若返回false 表示 已经完成网页授权，session里面已经放置了用户相关的信息
	 * 若返回true，表示 需要做网页授权，需要进行跳转
	 * @param account
	 * @return
	 * @throws JSONException
	 */
	public boolean doAuthorize(WxAccount account) {
		WxAccount agent = null;
		if((agent = account.gtAgentAccount()) == null) {
			if(account.gtAccountType() == OAccountType.SUBSCRIPTION) {
				throw LOG.err(Msgs.oauthErr);
			} else {
				agent = account;
			}
		}
		if((getSession().get("openid") != null || getSession().get("unionid") != null) && (account.getPkey() == getSession().get("accountPkey"))) {
			//已经做过网页授权，不用再重复做了
			return false;
		} else if(getState()!=null && getState().equals(STATE)) {
			if(getCode() == null)
				throw LOG.err(Msgs.oauthErr2);
			//网页授权重定向回来 
			try {
				String requestUrl = oauth2_access_token_url.replace("APPID", agent.getAccountAppid()).replace("SECRET", agent.getAccountAppsecret())
						.replaceAll("CODE", getCode());
				JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", null);
				if (result.has("errcode")) { 
					throw LOG.err("" + result.get("errcode"), result.getString("errmsg"));
				}
				String unionid = result.has("unionid")?result.getString("unionid"):null;
				String openid = result.has("openid")?result.getString("openid"):null;
				getSession().put("unionid", unionid);
				getSession().put("openid", openid);
				getSession().put("accountPkey", account.getPkey());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			//进行网页授权的跳转
			try {
				String requestUrl = URLEncoder.encode(getRequestUrl(true).replace("+", "%2B").replace("*", "%2A").replace("~", "%7E").replace("#", "%23"), "UTF-8");
				String rtn_str = oauth2_authorize_url.replace("APPID", agent.getAccountAppid()).replace("REDIRECT_URI", requestUrl).replace("SCOPE", agent.equals(account)?"snsapi_base":"snsapi_userinfo").replace("STATE", STATE);
				setRtnStr(RTRENDS);
				setResult(rtn_str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	/**
	 * 网页授权后，session里保留了用户的基本信息，可以调用该方法，把微信用户对象从数据中提取出来
	 * @return
	 */
	public WxUser chkWxUser() {
		if(getSession().get("unionid") == null && getSession().get("openid") != null) {
			//说明不是通过代理的，openid就是该公众号的openid
			return WxUser.chkUniqueOpenIdAccount(false, (String)getSession().get("openid"), (Integer)getSession().get("accountPkey"));
		} else if(getSession().get("unionid") != null){
			//说明是通过代理的，只有unionid有效
			return WxUser.chkUniqueUnionIdAccount(false, (String)getSession().get("unionid"), (Integer)getSession().get("accountPkey"));
		} else {
			return null;
		}
	}
	/**
	 * 网页授权后，session里保留了用户的基本信息，可以调用该方法，把微信用户对象从数据中提取出来
	 * @return
	 */
	public WxUser loadWxUser() {
		if(getSession().get("unionid") == null && getSession().get("openid") != null) {
			//说明不是通过代理的，openid就是该公众号的openid
			return WxUser.loadUniqueOpenIdAccount(false, (String)getSession().get("openid"), (Integer)getSession().get("accountPkey"));
		} else if(getSession().get("unionid") != null){
			//说明是通过代理的，只有unionid有效
			return WxUser.loadUniqueUnionIdAccount(false, (String)getSession().get("unionid"), (Integer)getSession().get("accountPkey"));
		} else {
			throw LOG.err();
		}
	}
	
	
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("key", "key");
		map.put("key", null);
		System.out.println(map.get("key"));
	}
	/**
	 * 网页授权并同步用户信息
	 * @deprecated
	 * @param url 回调url
	 */
	public boolean chkAuthorize(String url) {
		return chkAuthorize(url, false);
	}
	
	/**
	 * 网页授权
	 * @deprecated
	 * @param url 回调url
	 * @param sync 是否同步用户信息
	 */
	public boolean chkAuthorize(String url, boolean sync) {
		if(getSession().get("openid")!=null || getSession().get("unionid")!=null)
			return true;
		JSONObject param = new JSONObject();
		try {
			param.put("url", url);
			param.put("sync", sync);
			setResult(WeixinUtil.authorizeCode(getAccount(), param));
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		setRtnStr(RTRENDS);
		return false;
	}
	
	public boolean chkWxUserExist() {
		setWxUser((WxUser) getSession().get("wxUser"));
		if (getWxUser() == null)
			return false;
		return true;
	}
	
	/**
	 * 创建默认的菜单分享接口
	 * @param share
	 */
	public void crtJsMenuShare(JsMenuShare share) {
		if (share == null || !share.isEnabeld())
			return;
		if (share.gtAppMessage()) {
			JMOnMenuShareAppMessage model = new JMOnMenuShareAppMessage();
			model.setTitle(share.getTitle());
			model.setDesc(share.getDes());
			model.setLink(getDomain() + share.getLink());
			model.setImgUrl(getDomain() + DepConstants.FILE_SEPA + share.getImgUrl());
			if (share.getType() != null && !share.gtType().equals(Js.OJsMenuType.LINK)) {
				if (share.gtType().equals(Js.OJsMenuType.MUSIC)) 
					model.setType(SHARE_TYPE_MUSIC);
				if (share.gtType().equals(Js.OJsMenuType.VIDEO))
					model.setType(SHARE_TYPE_VIDEO);
				model.setDataUrl(share.getDataUrl());
			}
			add(model);
		}
		if (share.gtTimeLine()) {
			JMOnMenuShareTimeline model = new JMOnMenuShareTimeline();
			model.setTitle(share.getTitle());
			model.setLink(getDomain() + share.getLink());
			model.setImgUrl(getDomain() + DepConstants.FILE_SEPA + share.getImgUrl());
			add(model);
		}
		if (share.gtQq()) {
			JMOnMenuShareQQ model = new JMOnMenuShareQQ();
			model.setTitle(share.getTitle());
			model.setDesc(share.getDes());
			model.setLink(getDomain() + share.getLink());
			model.setImgUrl(getDomain() + DepConstants.FILE_SEPA + share.getImgUrl());
			add(model);
		}
		if (share.gtWeibo()) {
			JMOnMenuShareWeibo model = new JMOnMenuShareWeibo();
			model.setTitle(share.getTitle());
			model.setDesc(share.getDes());
			model.setLink(getDomain() + share.getLink());
			model.setImgUrl(getDomain() + DepConstants.FILE_SEPA + share.getImgUrl());
			add(model);
		}
		if (share.gtQzone()) {
			JMOnMenuShareQZone model = new JMOnMenuShareQZone();
			model.setTitle(share.getTitle());
			model.setDesc(share.getDes());
			model.setLink(getDomain() + share.getLink());
			model.setImgUrl(getDomain() + DepConstants.FILE_SEPA + share.getImgUrl());
			add(model);
		}
	}
	
	public void crtJsImage(int imgCount) {
		// 1  全局变量
		add(new JsFldDefine("localIds").setDime());
		add(new JsFldDefine("imgCount", imgCount));
		add(new JsFldDefine("imgList").setDime());
		add(new JsFldDefine("mediaIds").setDime());
		
		// 2  chooseImage
		JQFunDefine chooseImage = new JQFunDefine("#chooseImage", "click");//2
		JMChooseImage ci = new JMChooseImage();
		ci.setCount("imgCount");
		ci.setSuccess("crtImgs");
		chooseImage.add(ci);
		
		// 3   crtImgs
		JsFunDefine crtImgs = new JsFunDefine("crtImgs", "res");//3
		crtImgs.AddFunBody("crtImgs");
		
		// 4   upload
		JsFunDefine upload = new JsFunDefine("upload", "i", "length", "imgList");//4
		JMUploadImage ui = new JMUploadImage();
		ui.setLocalId(new JsExp("localIds[i]"));
		ui.setIsShowProgressTips(true);
		ui.setSuccess("uploadSuccess", true);
		upload.add(ui);
		
		// 5    imgClick
		JsFunDefine imgClick = new JsFunDefine("imgClick", "img", "input");//5
		imgClick.AddFunBody("imgClick");
		
		add(  chooseImage, crtImgs, upload, imgClick);
	}
	
	/**
	 * 添加列表内容
	 * @param objs
	 * @return
	 */
	public JMReady add(IExtOut...objs) {
		for (IExtOut obj : objs)
			getReady().add(obj);
		return getReady();
	}
	
	/**
	 * 添加列表内容
	 * @param objs
	 * @return
	 */
	public THIS Add(IExtOut...objs) {
		for (IExtOut obj : objs)
			getNodes().add(obj);
		return (THIS) this;
	}
	
	/**
	 *  向列表添加接口名称
	 * @param nodes
	 * @param infs
	 */
	private void setWxInf(Vector<IExtOut> nodes, List<String> infs) {
		for (IExtOut node : nodes) {
			if (node instanceof JMBase) //如果是微信js接口类型，将接口名加入到列表
				infs.add(((JMBase) node).getDefineName());
			if (node instanceof ExtList) //如果是基础列表类型，递归继续寻找微信接口类
				setWxInf(((ExtList) node).getNodes(), infs);
		}
	}
	
	/**
	 * 输出js
	 * @return
	 */
	public String crtJs(boolean debug) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String query = request.getQueryString();
		String url = request.getRequestURL() + (query == null ? "" : "?" + query);
		int tabs = 1;
		int i = 0;
		StringBuilder buf = new StringBuilder();
		List<String> infs = new ArrayList(); //接口名称列表
		setWxInf(getReady().getNodes(), infs); //将接口名称添加至列表
		String[] fnames = new String[infs.size()];
		for (String name : infs)
			fnames[i++] = name;
		System.out.println("URL : " + url);
		setConfig(new JMConfig(getAccount(), url, fnames).setDebug(debug)); //初始化微信config
		JMCheckJsApi chkapi = new JMCheckJsApi(fnames);
		chkapi.setSuccess("chkApi", true);
		add(chkapi);
		getConfig().out(tabs, buf);
		getReady().out(tabs, buf);
		for (IExtOut node : getNodes()) //将与config和ready同级的代码对象输出到到buf
			node.out(tabs, buf);
		getError().AddFunBody("GErr"); //TODO 输出全局错误函数
		getError().out(tabs, buf);
		return buf.toString();
	}
	
	public String crtJs() {
		return crtJs(false);
	}
	
	/**
	 * 将上传的微信素材文件保存到本地，并返回该文件的保存路径
	 * @author yingjianhua
	 * */
	protected String saveMaterial(WxMaterialUtil.MaterialType type) {
		if(getFile() == null)
			return null;
		FileSystemSaverNew uploadSys = new FileSystemSaverNew();
		String filename = getFileFileName();
		//校验文件格式
		switch(type) {
		case IMAGE: {
			if(!filename.endsWith("jpg") && !filename.endsWith("png") && !filename.endsWith("gif") && !filename.endsWith("bmp") && !filename.endsWith("jpeg")) {
				throw LOG.err(Msgs.notImage);
			}
			if(getFile().length() >= 2097152) {
				throw LOG.err(Msgs.overLimit2M);
			}
			break;
		}
		case VOICE: {
			if(!filename.endsWith("mp3") && !filename.endsWith("wma") && !filename.endsWith("wav") && !filename.endsWith("amr")) {
				throw LOG.err(Msgs.notVoice);
			}
			if(getFile().length() >= 5242880) {
				throw LOG.err(Msgs.overLimit5M);
			}
			break;
		}
		case MUSIC:
			break;
		case NEWS:
			break;
		case VIDEO:
			break;
		default:
			break;
		}
		try {
			String md5 = MD5Util.getMD5(getFile());
			SysFileStock stock = SysFileStock.chkUniqueMd5(false, md5);
			if(stock==null) {
				Photo photo = uploadSys.saveFile(getUploadPath(true), getFile(), filename, "wm");
				stock = new SysFileStock();
				stock.setMd5(md5);
				stock.setPath(photo.getPreviewURL());
				stock.setRowVersion((short)1);
				stock.ins();
			}
			return stock.getPath();
		} catch (IOException e) {
			e.printStackTrace();
			throw LOG.err("upload", "上传失败");
		}
	}
	
	/**
	 * 获取访问的地址
	 * @param haveQuery
	 * 是否带参，即"?"后面的部分
	 * */
	public static String getRequestUrl(boolean haveQuery) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String url = request.getRequestURL().toString();
		if(!haveQuery) {
			return url;
		} else {
			String query = request.getQueryString();
			return url + (query == null ? "" : "?" + query);
		}
	}
	protected String createWxJs(boolean debug, WxAccount account, String tipFuntion, String... jsApiList) {
		return createWxConfig(debug, account, jsApiList)+createWxReady(tipFuntion, jsApiList)+createWxError();
	}
	protected String createWxConfig(boolean debug, WxAccount account, String... jsApiList) {
		Map<String, String> signParams = new TreeMap<String, String>();
		signParams.put("jsapi_ticket", WxAccountDAO.getJsapiTicket(account));
		signParams.put("timestamp", new Date().getTime()/1000+"");
		signParams.put("noncestr", MchUtil.createRandom(16));
		signParams.put("url", getRequestUrl(true));
		StringBuilder buffer = new StringBuilder();
		for(String key:signParams.keySet()) {
			buffer.append("&"+key+"="+signParams.get(key));
		}
		StringBuilder config = new StringBuilder();
		config.append("wx.config({");
		config.append("debug:"+debug+",");
		config.append("appId:'"+account.getAccountAppid()+"',");
		config.append("timestamp:"+signParams.get("timestamp")+",");
		config.append("nonceStr:'"+signParams.get("noncestr")+"',");
		config.append("signature:'"+Crypto.getSha1(buffer.substring(1))+"',");
		config.append("jsApiList:[");
		for(String jsApi:jsApiList) {
			config.append("'"+jsApi+"',");
		}
		config.append("]});");
		return config.toString();
	}
	protected String createWxReady(String tipFuntion, String... jsApiList) {
		StringBuilder ready = new StringBuilder();
		ready.append("wx.ready(function() {");
		ready.append("wx.checkJsApi({");
		ready.append("jsApiList:[");
		for(String jsApi:jsApiList) {
			ready.append("'"+jsApi+"',");
		}
		ready.append("],");
		ready.append("success : function(res) {");
		ready.append("var errMsg = res.errMsg.substring(11);");
		ready.append("if(errMsg != \"ok\") {");
		ready.append(tipFuntion+"(\"errMsg\");");
		ready.append("}}});});");
		return ready.toString();
	}
	protected String createWxError() {
		StringBuilder error = new StringBuilder();
		error.append("wx.error(function(res) {");
		error.append("alert(res);");
		error.append("});");
		return error.toString();
	}
} 
