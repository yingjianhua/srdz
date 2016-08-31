package irille.wpt.actions.resource;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Qualifier;

import irille.pub.Str;
import irille.pub.svr.Env;
import irille.pub.tb.Tb;
import irille.tools.GenericsUtils;
import irille.wpt.actions.AbstractWptAction;
import irille.wpt.service.impl.BaseService;
import irille.wpt.tools.Page;
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
	
	//action的返回值
	protected static final String BEANS = "beans";
	protected static final String PAGES = "pages";
	protected static final String OBJECT = "object";
	
	//前台filter中的固定参数名
	private static final String QUERY_PROPERTY = "property";
	private static final String QUERY_VALUE = "value";
	
	protected Class<T> entityClass;
	
	//用于存储action返回值
	protected T bean;
	protected Object object;
	protected List<T> beans;
	protected Page<T> pages;
	
	//分页参数
	private Integer start;
	private Integer limit;
	private Integer page;
	
	//查询条件
	private String filter;
	private String query;
	
	@Resource
	@Qualifier("baseService")
	private BaseService service;
	
	public AbstractCRUDAction() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	@RolesAllowed("admin")
	public void add() {
		
	}
	
	//@MaxLevel(4)
	public String list() {
		beans = service.list(entityClass, start, limit);
		System.out.println(beans.size());
		return BEANS;
	}
	public String page() {
		if(filter != null && !filter.trim().isEmpty()) {
			// {"property":"flds","value":"1=1"} //初始化选择器的情况
			// [{"property":"flds","value":"pkey,name"},{"property":"param","value":"1111"}]
			JSONArray ja = new JSONArray(getQuery());
			String[] flds = null;
			String param = null;
			String diy = ""; // 获取前台EXT动态写死的SQL条件
			for (int i = 0; i < ja.length(); i++) {
				String pro = ja.getJSONObject(i).getString(QUERY_PROPERTY);
				if (pro.equals("flds")) {
					if (ja.getJSONObject(i).getString(QUERY_VALUE).equals("1=1") == false)
						flds = ja.getJSONObject(i).getString(QUERY_VALUE).split(",");
				} else if (pro.equals("param"))
					param = ja.getJSONObject(i).getString(QUERY_VALUE);
				else
					diy = ja.getJSONObject(i).getString(QUERY_VALUE);
			}
			if (flds == null && Str.isEmpty(diy)) {
			}
			String sql = "";
			Tb tb = tb();
			if (flds != null) {
				for (String line : flds)
					sql += " OR " + Env.INST.getDB().crtWhereSearch(tb.get(line), param);
				sql = sql.substring(4);
			}
			String where = crtQueryAll();
			if (Str.isEmpty(diy) == false)
				where += " AND " + diy;
			if (Str.isEmpty(sql) == false)
				where += " AND (" + sql + ")";
			if (tb().chk("enabled")) 
				where += " AND enabled = 1";
			return where + orderBy();
		}
		pages = service.page(entityClass, start, limit);
		return PAGES;
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
	public Page<T> getPages() {
		return pages;
	}
	public void setPages(Page<T> pages) {
		this.pages = pages;
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
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
}
