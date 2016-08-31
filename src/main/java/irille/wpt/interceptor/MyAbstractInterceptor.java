package irille.wpt.interceptor;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public abstract class MyAbstractInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BeanFactory factory;
	
	protected synchronized BeanFactory getFactory() {
		if(factory == null) {
			factory = (WebApplicationContext)ServletActionContext.getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		}
		return factory;
	}

	protected void setFactory(BeanFactory factory) {
		this.factory = factory;
	}
}
