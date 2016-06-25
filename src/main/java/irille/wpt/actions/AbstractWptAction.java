package irille.wpt.actions;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import irille.wpt.tools.WxJsCreater;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public abstract class AbstractWptAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 675006873821315991L;
	private Map<String, Object> session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	protected WxAccount account;
	private String result;
	private String jsCode;
	@Resource(name="wxJsCreater")
	private WxJsCreater jsCreater;
	public static final String TRENDS = "trends";
	public static final String RTRENDS = "rtrends";
	
	public Map<String, Object> getSession() {
		if(session == null)
			session = ServletActionContext.getContext().getSession();
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public HttpServletRequest getRequest() {
		if(request == null) 
			 request = ServletActionContext.getRequest();
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	public HttpServletResponse getResponse() {
		if(response == null)
			 response = ServletActionContext.getResponse();
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public WxAccount getAccount() {
		return account;
	}
	public void setAccount(WxAccount account) {
		this.account = account;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getJsCode() {
		System.out.println("getJsCode()");
		return jsCode;
	}
	public void setJsCode(String jsCode) {
		System.out.println("setJsCode()");
		this.jsCode = jsCode;
	}
	public WxJsCreater getJsCreater() {
		return jsCreater;
	}
	public void setJsCreater(WxJsCreater jsCreater) {
		this.jsCreater = jsCreater;
	}
	/**
	 * 网页授权后，session里保留了用户的基本信息，可以调用该方法，把微信用户对象从数据中提取出来
	 * @return
	 */
	public WxUser chkWxUser() {
		String openid = (String)session.get("openid");
		Integer accountPkey = (Integer)session.get("accountPkey");
		if(openid != null && accountPkey != null) {
			return WxUser.chkUniqueOpenIdAccount(false, openid, accountPkey);
		} else {
			return null;
		}
	}
	public void addExtraWxJsCode()  {
	}
}
