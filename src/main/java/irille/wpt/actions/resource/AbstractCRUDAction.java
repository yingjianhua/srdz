package irille.wpt.actions.resource;

import java.util.List;

import javax.annotation.Resource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Qualifier;

import irille.pub.Str;
import irille.tools.GenericsUtils;
import irille.wpt.actions.AbstractWptAction;
import irille.wpt.extjs.ComboTriggerBuilder;
import irille.wpt.extjs.IComboTrigger;
import irille.wpt.service.impl.BaseService;
import irille.wpt.tools.Page;
import irille.wpt.tools.SqlBuilder;
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
	public static final String BEAN = "bean";
	public static final String BEANS = "beans";
	public static final String PAGES = "pages";
	public static final String OBJECT = "object";
	
	//前台filter中的固定参数名
	private static final String QUERY_PROPERTY = "property";
	private static final String QUERY_VALUE = "value";
	
	protected Class<T> entityClass;
	
	//用于存储action返回值
	protected T bean;
	protected List<T> beans;
	protected Page<T> pages;
	protected Object object;
	
	//分页参数
	protected Integer start;
	protected Integer limit;
	protected Integer page;
	
	//查询条件
	private String filter;
	private String query;
	private String sarg1;//comboTrigger的查询条件
	
	@Resource
	@Qualifier("baseService")
	public BaseService service;
	
	//初始化action并根据泛型赋值bean的class
	public AbstractCRUDAction() {
		entityClass = GenericsUtils.getSuperClassGenricType(getClass());
	}

	public String ins() {
		service.save(bean);
		return BEAN;
	}
	public String upd() {
		service.update(bean);
		return BEAN;
	}
	public String del() {
		service.delete(bean);
		return BEAN;
	}
	
	//@MaxLevel(4)
	public String list() throws JSONException {
		System.out.println(service.getClass());
		String where = getFilter()==null?crtSqlByQuery():crtSqlByFilter();
		beans = service.list(entityClass, start, limit, where);
		return BEANS;
	}
	
	public String page() throws JSONException {
		System.out.println(service.getClass());
		String where = getFilter()==null?crtSqlByQuery():crtSqlByFilter();
		pages = service.page(entityClass, start, limit, where);
		return PAGES;
	}
	
	@SuppressWarnings("unchecked")
	public String getComboTrigger() throws JSONException {
		String where = crtQueryAll();
		if (!Str.isEmpty(getSarg1()))
			where += " and (" + getSarg1() + ")";
		System.out.println(where);
		beans = service.list(entityClass, null, null, where);
		if(IComboTrigger.class.isAssignableFrom(entityClass)) {
			ComboTriggerBuilder builder = new ComboTriggerBuilder((List<IComboTrigger>)beans);
			object = builder.build();
		}
		return OBJECT;
	}
	
	private String crtSqlByFilter() throws JSONException {
		if (Str.isEmpty(getFilter()))
			return crtFilterAll() + orderBy();
		JSONArray ja = new JSONArray(getFilter());
		String sql = "";
		for (int i = 0; i < ja.length(); i++) {
			JSONObject json = ja.getJSONObject(i);
			String fldName = json.getString(QUERY_PROPERTY);
			String param = json.getString(QUERY_VALUE);
			if (Str.isEmpty(param))
				continue;
			String condition = SqlBuilder.crtWhereSearch(entityClass, fldName, param);
			if(!Str.isEmpty(condition))
				sql += " AND " + condition;
		}
		return crtFilterAll() + sql + orderBy();
	}
	private String crtSqlByQuery() throws JSONException {
		if (Str.isEmpty(getQuery()))
			return crtQueryAll() + orderBy();
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
			return crtQueryAll() + orderBy();
		}
		String sql = "";
		if (flds != null) {
			for (String line : flds)
				sql += " OR " + SqlBuilder.crtWhereSearch(entityClass, line, param);
			sql = sql.substring(4);
		}
		String where = crtQueryAll();
		if (Str.isEmpty(diy) == false)
			where += " AND " + diy;
		if (Str.isEmpty(sql) == false)
			where += " AND (" + sql + ")";
		return where + orderBy();
	}
	
	public String orderBy() {
		return " ORDER BY PKEY DESC";
	}
	public String orderByAsc() {
		return " ORDER BY PKEY ASC";
	}
	public String crtQueryAll() {
		return crtAll();
	}
	public String crtFilterAll() {
		return crtAll();
	}
	public String crtAll() {
		return "1=1";
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
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
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
	public String getSarg1() {
		return sarg1;
	}
	public void setSarg1(String sarg1) {
		this.sarg1 = sarg1;
	}
	
}
