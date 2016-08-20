package irille.wpt.interceptor;

import java.lang.reflect.Method;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;

import org.glassfish.jersey.server.model.AnnotatedMethod;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class RoleInterceptor extends AbstractInterceptor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Method method = invocation.getProxy().getAction().getClass().getMethod(invocation.getProxy().getMethod());
		AnnotatedMethod am = new AnnotatedMethod(method);
		if(am.isAnnotationPresent(PermitAll.class)) {
			return invocation.invoke();
		};
		
		if(am.isAnnotationPresent(DenyAll.class)) {
			return null;
		};
		
		if(am.isAnnotationPresent(RolesAllowed.class)) {
			System.out.println(method.getAnnotation(RolesAllowed.class).value());
		};
		return invocation.invoke();
	}
}
