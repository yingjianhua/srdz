package irille.wpt.actions.resource;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;

import org.apache.struts2.json.annotations.ExcludeProperties;
import org.apache.struts2.json.annotations.IncludeProperties;
import org.apache.struts2.json.annotations.MaxLevel;
import org.springframework.beans.factory.annotation.Qualifier;

import irille.tools.GenericsUtils;
import irille.wpt.actions.AbstractWptAction;
import irille.wpt.service.BaseService;
/**
 * 用于对资源做增删改查
 * @author Yingjianhua
 *
 */
public abstract class AbstractCRUDAction<T> extends AbstractWptAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected T bean;
	protected List<T> beans;
	protected Integer page;
	protected Integer start;
	protected Integer limit;
	protected Class<T> entityClass;
	
	@Resource
	@Qualifier("baseService")
	private BaseService service;
	
	public AbstractCRUDAction() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());  
	}

	@RolesAllowed("admin")
	public void add() {
		
	}

	//@MaxLevel(3)
	public String list() {
		beans = service.list(entityClass, start, limit);
		System.out.println(beans.size());
		return "json";
	}

	public T getBean() {
		return bean;
	}
	public void setBean(T bean) {
		this.bean = bean;
	}
	public List<T> getBeans() {
		return beans;
	}
	public void setBeans(List<T> beans) {
		this.beans = beans;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
}
