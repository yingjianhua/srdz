package irille.wpt.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.wx.wx.Wx.OAccountType;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxMessageDAO;
import irille.wx.wx.WxUser;
import irille.wx.wx.WxUserDAO;
import irille.wxpub.util.WeixinUtil;

public class AuthorizeInterceptor extends AbstractInterceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1589732558268724276L;
	private static final Log LOG = new Log(AuthorizeInterceptor.class);
	
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		AccountNotExist("没有该公众号"),
		noAccountPkey("缺少公众号参数"),
		oauthErr("订阅号没有绑定三方授权代理号，不能进行网页授权"),
		oauthErr2("网页授权失败，未知错误"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} 
	
	/** 微信网页授权获取CODE **/
	public static final String OAUTH2_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
	/** 微信网页授权获取网页accesstoken和OPENID **/
	public static final String OAUTH2_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	/** 拉取用户信息(需scope为 snsapi_userinfo) **/
	public static final String USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	/** 网页授权重定向后带上的state参数 **/
	private static final String STATE = "14hb1a5e4f15a";
	
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		WxAccount account = null;
		String state = null;
		String code = null;
		String invitedOpenid = null;
		String requestUrl = null;
		Map<String, Object> session = null;
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		
		ActionContext actionContext = actionInvocation.getInvocationContext();
		Map<String, Object> map = actionContext.getParameters();
		String[] accountPkeys = (String[])map.get("account.pkey");
		if(accountPkeys != null && accountPkeys.length > 0) {
			account = WxAccount.get(WxAccount.class, Integer.parseInt(accountPkeys[0]));
			if(account == null) {
				throw LOG.err(Msgs.AccountNotExist);
			}
		} else {
			throw LOG.err(Msgs.noAccountPkey);
		}
		String[] states = (String[])map.get("state");
		if(states != null && states.length > 0) {
			state = states[0];
		}
		String[] codes = (String[])map.get("code");
		if(codes != null && codes.length > 0) {
			code = codes[0];
		}
		String[] invitedOpenids = (String[])map.get("invitedId");
		if(invitedOpenids != null && invitedOpenids.length > 0) {
			invitedOpenid = invitedOpenids[0];
		}
		session = actionContext.getSession();
		request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
		response = (HttpServletResponse)actionContext.get(ServletActionContext.HTTP_RESPONSE);
		String query = request.getQueryString();
		requestUrl = request.getRequestURL() + (query == null ? "" : "?" + query);
		if(doAuthorize(account, state, code, invitedOpenid, requestUrl, session, request, response)) {
			return null;
		}
		LOG.info("--------------intercept():start--------------");
		LOG.info("openid:{0}", session.get("openid"));
		String result = actionInvocation.invoke();
		LOG.info("--------------intercept():end--------------");
		return result;
	}
	/**
	 * 进行网页授权
	 * 若返回false 表示 已经完成网页授权，session里面已经放置了用户相关的信息
	 * 若返回true，表示 需要做网页授权，需要进行跳转
	 * @param account
	 * @return
	 * @throws JSONException
	 */
	public boolean doAuthorize(WxAccount account, String state, String code, String invitedId, String requestUrl, Map<String, Object> session, HttpServletRequest request, HttpServletResponse response) {
		if(account.gtAccountType() == OAccountType.SUBSCRIPTION) {
			throw LOG.err(Msgs.oauthErr);
		}
		session.put("openid", "oPhA9t6_sV4FZATKsv1iecFwjdUA");
		session.put("accountPkey", 10);
		if(session.get("openid") != null && account.getPkey().equals(session.get("accountPkey"))) {
			//已经做过网页授权，不用再重复做了
			return false;
		} else if(state!=null && state.equals(STATE)) {
			if(code == null)
				throw LOG.err(Msgs.oauthErr2);
			//网页授权重定向回来 
			try {
				LOG.info("--------------doAuthorize():start--------------");
				requestUrl = OAUTH2_ACCESS_TOKEN_URL.replace("APPID", account.getAccountAppid()).replace("SECRET", account.getAccountAppsecret())
						.replaceAll("CODE", code);
				JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", null);
				if (result.has("errcode")) { 
					throw LOG.err("" + result.get("errcode"), result.getString("errmsg"));
				}
				String openid = result.has("openid")?result.getString("openid"):null;
				String access_token = result.has("access_token")?result.getString("access_token"):null;
				session.put("openid", openid);
				session.put("accountPkey", account.getPkey());
				WxUser user = WxUser.chkUniqueOpenIdAccount(false, openid, account.getPkey());
				if(user == null) {
					user = WxUserDAO.insByAuthorize(access_token, openid, account, invitedId==null?null:Long.parseLong(invitedId));
					//公众号提醒邀请人 有一个新的粉丝加入了
					WxMessageDAO.notifyInvited(WxAccountDAO.getAccessToken(account), user);
				}
				LOG.info("--------------doAuthorize():end--------------");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return false;
		} else {
			//进行网页授权的跳转
			try {
				requestUrl = URLEncoder.encode(requestUrl.replace("+", "%2B").replace("*", "%2A").replace("~", "%7E").replace("#", "%23"), "UTF-8");
				String rtn_str = OAUTH2_AUTHORIZE_URL.replace("APPID", account.getAccountAppid()).replace("REDIRECT_URI", requestUrl).replace("SCOPE", "snsapi_userinfo").replace("STATE", STATE);
				response.sendRedirect(rtn_str);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}
}
