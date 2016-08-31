package irille.wpt.interceptor;

import java.util.Date;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class StatisticsInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Date begin = new Date();
		String rtn = invocation.invoke();
		String path = invocation.getProxy().getActionName();
		Date end = new Date();
		System.err.println("【"+path+"】请求耗时【"+(end.getTime()-begin.getTime())+"】ms");
		return rtn;
	}

}
