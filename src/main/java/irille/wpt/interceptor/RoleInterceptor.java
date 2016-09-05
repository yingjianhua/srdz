package irille.wpt.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;
import org.glassfish.jersey.server.model.AnnotatedMethod;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import irille.action.sys.SysUserAction;
import irille.pub.svr.Env;
import irille.pub.svr.LoginUserMsg;
import irille.wpt.actions.AbstractWptAction;
import irille.wpt.exception.ExtjsException;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class RoleInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String LOGIN = "login";

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Method method = invocation.getProxy().getAction().getClass().getMethod(invocation.getProxy().getMethod());
		AnnotatedMethod am = new AnnotatedMethod(method);
		if (am.isAnnotationPresent(PermitAll.class)) {
			return invocation.invoke();
		}

		if (am.isAnnotationPresent(DenyAll.class)) {
			return null;
		}

		if (am.isAnnotationPresent(RolesAllowed.class)) {
			System.out.println(method.getAnnotation(RolesAllowed.class).value());
		}

		String result = "";
		try {
			result = checkLogin(invocation);
		} catch (ExtjsException e) {
			ActionContext actionContext = invocation.getInvocationContext();
			HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("msg", e.getMessage());
			jsonObject.put("success", false);
			response.getWriter().print(jsonObject.toString());
			return null;
		}
		return result;
	}

	public String checkLogin(ActionInvocation invocation) throws Exception {
		// 确认Session中是否存在LOGIN
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		LoginUserMsg umsg = (LoginUserMsg) session.get(LOGIN);
		if (umsg != null) {
			WxAccount account = WxAccountDAO.getByUser(umsg.getUser());
			if(account != null && invocation.getAction() instanceof AbstractWptAction) {
				AbstractWptAction action = (AbstractWptAction)invocation.getAction();
				action.setAccount(account);
			}
			// 这里缓存的线程级对象会在请求结束后在DBITP中释放
			Env.INST.initTran(umsg, invocation.getProxy().getActionName());
			// 存在的情况下进行后续操作。
			return invocation.invoke();
		} else {
			// 对LoginAction不做该项拦截
			Object action = invocation.getAction();
			if (action instanceof SysUserAction)
				if (invocation.getProxy().getActionName().equals("sys_SysUser_login")
						|| invocation.getProxy().getActionName().equals("sys_SysUser_loginTest"))
					return invocation.invoke();
			// 否则终止后续操作
			ServletActionContext.getResponse().setHeader("sessionstatus", "timeout");
			return LOGIN;
		}
	}
}
