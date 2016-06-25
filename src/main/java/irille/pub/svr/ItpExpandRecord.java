package irille.pub.svr;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import irille.wx.wx.WxActionRecord;

public class ItpExpandRecord extends AbstractInterceptor{
//记录expand开头的action访问记录
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		boolean success = true;
		long start = System.currentTimeMillis();
		String rtn = null;
		HttpServletRequest request = (HttpServletRequest)actionInvocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		try {
			rtn = actionInvocation.invoke();
		} catch (Exception e) {
			success = false;
			throw e;
		} finally {
			if(request.getServletPath().startsWith("/expand_")) {
				long end = System.currentTimeMillis();
				WxActionRecord bean = new WxActionRecord();
				bean.setActionName(getRequestUrl(request, true));
				bean.setDealPriod((int)(end-start));
				bean.stSuccessFlag(success);
				bean.setVisitHost(request.getRemoteHost());
				bean.setVisitTime(new Date());
				bean.ins();
				DbPool.getInstance().getConn().commit(); //提交对日志的更新
				DbPool.getInstance().removeConn();
			}
		}
		return rtn;
	}
	public static String getRequestUrl(HttpServletRequest request, boolean haveQuery) {
		String url = request.getRequestURI().toString();
		if(!haveQuery) {
			return url;
		} else {
			String query = request.getQueryString();
			return url + (query == null ? "" : "?" + query);
		}
	}
	
}
