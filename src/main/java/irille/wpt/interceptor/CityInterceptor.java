package irille.wpt.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

import irille.pub.Log;
import irille.wpt.bean.City;
import irille.wpt.service.impl.CityService;
import irille.wpt.tools.SinaLocationTool;

public class CityInterceptor extends MyAbstractInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4027438797941908882L;
	private static final Log LOG = new Log(CityInterceptor.class);
	public static final String CITY = "city";
	
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		ActionContext actionContext = actionInvocation.getInvocationContext();
		Map<String, Object> session = actionContext.getSession();
		if(session.get(CITY) == null) {
			HttpServletRequest request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
			Map<String, Object> map = actionContext.getParameters();
			Integer account = Integer.parseInt(((String[])map.get("account.pkey"))[0]);
			try {
				String cityName = SinaLocationTool.findCityByIp(request.getRemoteAddr());
				City city = getFactory().getBean(CityService.class).findByName(cityName, account);
				if(city != null){
					session.put(CITY, city);
				}else{
					session.put(CITY, getFactory().getBean(CityService.class).findByName("温州", account));
				}
			} catch (Exception e) {
				session.put(CITY, getFactory().getBean(CityService.class).findByName("温州", account));
				e.printStackTrace();
			}
		}
		LOG.info("cityInterceptor:"+((City)session.get(CITY)).getName());
		String rtn = actionInvocation.invoke();
		return rtn;
	}

}
