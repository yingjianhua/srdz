package irille.wpt.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import irille.pub.Log;
import irille.wpt.tools.SinaLocationTool;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptCityDAO;

public class CityInterceptor extends AbstractInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4027438797941908882L;
	public static final Log LOG = new Log(CityInterceptor.class);
	public static final String CITY = "city";
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		System.out.println("--------------------CityInterceptor.intercept():--------------------");
		ActionContext actionContext = actionInvocation.getInvocationContext();
		Map<String, Object> session = actionContext.getSession();
		if(session.get(CITY) == null) {
			HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
			Map<String, Object> map = actionContext.getParameters();
			Integer account = Integer.parseInt(((String[])map.get("account.pkey"))[0]);
			try {
				String city = SinaLocationTool.findCityByIp(request.getRemoteAddr());
				WptCity wptCity = WptCityDAO.findByName(account, city);
				if(wptCity != null){
					session.put(CITY, wptCity);
				}else{
					session.put(CITY, WptCityDAO.findByName(account, "温州"));
				}
			} catch (Exception e) {
				session.put(CITY, WptCityDAO.findByName(account, "温州"));
				e.printStackTrace();
			}
		}
		String rtn = actionInvocation.invoke();
		System.out.println("--------------------CityInterceptor.intercept():--------------------");
		return rtn;
	}

}
