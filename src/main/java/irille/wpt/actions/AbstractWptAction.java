package irille.wpt.actions;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import irille.wpt.bean.Member;
import irille.wpt.service.impl.BanquetService;
import irille.wpt.service.impl.CashJournalService;
import irille.wpt.service.impl.CityLineService;
import irille.wpt.service.impl.CityService;
import irille.wpt.service.impl.CollectService;
import irille.wpt.service.impl.ComboBannerService;
import irille.wpt.service.impl.ComboLineService;
import irille.wpt.service.impl.ComboService;
import irille.wpt.service.impl.CommissionJournalService;
import irille.wpt.service.impl.CustomFormService;
import irille.wpt.service.impl.CustomServiceService;
import irille.wpt.service.impl.DistributionRuleService;
import irille.wpt.service.impl.FeedbackService;
import irille.wpt.service.impl.HeadlineService;
import irille.wpt.service.impl.HotService;
import irille.wpt.service.impl.MemberService;
import irille.wpt.service.impl.OrderCustomServiceService;
import irille.wpt.service.impl.OrderDetailService;
import irille.wpt.service.impl.OrderPayJournalService;
import irille.wpt.service.impl.OrderService;
import irille.wpt.service.impl.PetitionCityService;
import irille.wpt.service.impl.QrcodeRuleService;
import irille.wpt.service.impl.RedPackRuleService;
import irille.wpt.service.impl.RestaurantBannerService;
import irille.wpt.service.impl.RestaurantBsnService;
import irille.wpt.service.impl.RestaurantCaseService;
import irille.wpt.service.impl.RestaurantLineService;
import irille.wpt.service.impl.RestaurantMenuService;
import irille.wpt.service.impl.RestaurantService;
import irille.wpt.service.impl.RestaurantTemplateService;
import irille.wpt.service.impl.ServiceCenterService;
import irille.wpt.service.impl.SpecialLineService;
import irille.wpt.service.impl.SpecialService;
import irille.wpt.service.impl.UserService;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;

public abstract class AbstractWptAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	protected static final long serialVersionUID = 675006873821315991L;
	protected static String domain = null;
	protected String requestUrl = null;
	protected Map<String, Object> session;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected WxAccount account;
	protected String result;
	public static final String TRENDS = "trends";
	public static final String RTRENDS = "rtrends";

	@Resource
	protected BanquetService banquetService;
	@Resource
	protected CashJournalService cashJournalService;
	@Resource
	protected CityLineService cityLineService;
	@Resource
	protected CityService cityService;
	@Resource
	protected CollectService collectService;
	@Resource
	protected ComboBannerService comboBannerService;
	@Resource
	protected ComboLineService comboLineService;
	@Resource
	protected ComboService comboService;
	@Resource
	protected CommissionJournalService commissionJournalService;
	@Resource
	protected CustomFormService customFormService;
	@Resource
	protected CustomServiceService customServiceService;
	@Resource
	protected DistributionRuleService distributionRuleService;
	@Resource
	protected FeedbackService feedbackService;
	@Resource
	protected HeadlineService headlineService;
	@Resource
	protected HotService hotService;
	@Resource
	protected MemberService memberService;
	@Resource
	protected OrderCustomServiceService orderCustomServiceService;
	@Resource
	protected OrderDetailService orderDetailService;
	@Resource
	protected OrderPayJournalService orderPayJournalService;
	@Resource
	protected OrderService orderService;
	@Resource
	protected PetitionCityService petitionCityService;
	@Resource
	protected QrcodeRuleService qrcodeRuleService;
	@Resource
	protected RedPackRuleService redPackRuleService;
	@Resource
	protected RestaurantBannerService restaurantBannerService;
	@Resource
	protected RestaurantBsnService restaurantBsnService;
	@Resource
	protected RestaurantCaseService restaurantCaseService;
	@Resource
	protected RestaurantLineService restaurantLineService;
	@Resource
	protected RestaurantMenuService restaurantMenuService;
	@Resource
	protected RestaurantService restaurantService;
	@Resource
	protected RestaurantTemplateService restaurantTemplateService;
	@Resource
	protected ServiceCenterService serviceCenterService;
	@Resource
	protected SpecialService specialService;
	@Resource
	protected SpecialLineService specialLineService;
	@Resource
	protected UserService userService;
	
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
	public static String getDomain() {
		if(domain == null) {
			domain = ServletActionContext.getServletContext().getInitParameter("webPath");
		}
		return domain;
	}
	public String getRequestUrl() {
		if(requestUrl == null) {
			String query = getRequest().getQueryString();
			requestUrl = getRequest().getRequestURL() + (query == null ? "" : "?" + query);
		}
		return requestUrl;
	}
	
	/**
	 * 网页授权后，session里保留了用户的基本信息，可以调用该方法，把微信用户对象从数据中提取出来
	 * @return
	 */
	public Member chkMember() {
		String openid = (String)getSession().get("openid");
		Integer accountPkey = (Integer)getSession().get("accountPkey");
		if(openid != null && accountPkey != null) {
			return memberService.findByOpenidInAccount(accountPkey, openid);
		} else {
			return null;
		}
	}
	/**
	 * 网页授权后，session里保留了用户的基本信息，可以调用该方法，把微信用户对象从数据中提取出来
	 * @return
	 */
	public WxUser chkWxUser() {
		String openid = (String)getSession().get("openid");
		Integer accountPkey = (Integer)getSession().get("accountPkey");
		if(openid != null && accountPkey != null) {
			return WxUser.chkUniqueOpenIdAccount(false, openid, accountPkey);
		} else {
			return null;
		}
	}
}
