package irille.wpt.dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.annotations.IncludeProperties;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import irille.tools.GenericsUtils;
import irille.wpt.actions.resource.impl.ComboLineAction;
import irille.wpt.bean.ComboLine;
import irille.wpt.json.MySimplePropertyPreFilter;
import irille.wpt.service.ComboService;

@Repository
public class BaseDao<T,ID extends Serializable> {
	@Resource
	protected SessionFactory sessionFactory;
	protected Class<T> entityClass;
	
	public T get(ID id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(entityClass, id);
	}
	
	public BaseDao() {
		this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());  
	}
	public T load(ID id) {
		Session session = sessionFactory.getCurrentSession();
		return session.load(entityClass, id);
	}
	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from "+entityClass.getName()).list();
	}
	
	public List<T> list(Class entity, Integer start, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from "+entity.getSimpleName());
		if(start!=null) {
			query.setFirstResult(start);
		}
		if(limit!=null) {
			query.setMaxResults(limit);
		}
		System.out.println("entityClass:"+entity.getName());
		return query.list();
	}
	
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		ComboService dao = bf.getBean(ComboService.class);
		ComboLineAction action = bf.getBean(ComboLineAction.class);
		action.list();
		SessionFactory sf = bf.getBean(SessionFactory.class);
		Session session = sf.openSession();
		Date point1 = new Date();
		ComboLine comboLine = session.get(ComboLine.class, 50);
		System.out.println("------------------------------------------");
		Date point2 = new Date();
		testStrutsJson(comboLine, 3);
		Date point3 = new Date();
		System.out.println(point2.getTime()-point1.getTime());
		System.out.println(point3.getTime()-point2.getTime());
	}
	@Transactional
	public static void testJson() {
		ClassPathXmlApplicationContext bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		ComboService dao = bf.getBean(ComboService.class);
		ComboLineAction action = bf.getBean(ComboLineAction.class);
		action.list();
		SessionFactory sf = bf.getBean(SessionFactory.class);
		Session session = sf.openSession();
		ComboLine comboLine = session.get(ComboLine.class, 50);
		MySimplePropertyPreFilter filter = new MySimplePropertyPreFilter();
		filter.setMaxLevel(1);
		SerializeConfig.getGlobalInstance().addFilter(Object.class, filter);
		System.out.println("---");
		String json =  JSONObject.toJSONString(comboLine, filter ,SerializerFeature.PrettyFormat);
		System.out.println(json);
		ObjectSerializer os1 = SerializeConfig.getGlobalInstance().get(ComboLine.class);
		System.out.println(os1);
		for(Class c:os1.getClass().getInterfaces()) {
			System.out.println(c.getName());
		}
		System.out.println(ObjectSerializer.class.isAssignableFrom(ComboLine.class));
		System.out.println(os1.getClass().getSuperclass().getName());
	}
	public static void testSerializer() {
		int a = 1;
		System.out.println(JSONObject.toJSONString(a));
		ObjectSerializer os1 = SerializeConfig.getGlobalInstance().get(ComboLine.class);
		System.out.println(os1);
		System.out.println(SerializeConfig.getGlobalInstance().get(Integer.class));
		System.out.println(SerializeConfig.getGlobalInstance().get(int.class));
		
	}
	public static void testFastJson(ComboLine comboLine) {
		String json =  JSONObject.toJSONString(comboLine);
		System.out.println(json);
	}
	@Transactional
	public static void testGson(ComboLine comboLine) {
		GsonBuilder builder = new GsonBuilder();
		Gson son = builder.create();
		System.out.println(son.toJson(comboLine));
	}
	public static void testStrutsJson(ComboLine comboLine, int maxLevel) {
		try {
			List<Pattern> excludeProperties = new ArrayList<Pattern>(maxLevel);
            if(maxLevel > 0) {
    			int i = 0;
    			String[] maxLevelPattern = new String[maxLevel];
    			StringBuilder sb = new StringBuilder("[^\\.]+");
    			while(++i<maxLevel){
    				sb.append("\\.[^\\.]+");
    				maxLevelPattern[i] = sb.toString();
    			}
    			maxLevelPattern[0] = "[^\\.]+";
    			for(String line:maxLevelPattern) {
    				for (String pattern : maxLevelPattern) {
    					excludeProperties.add(Pattern.compile(pattern));
    				}
    			}
    		}
			System.out.println(JSONUtil.serialize(comboLine, null, excludeProperties, true, false));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public static void ttt(int maxLevel) {
		if(maxLevel > 0) {
			int i = 0;
			String[] maxLevelPattern = new String[maxLevel];
			StringBuilder sb = new StringBuilder("[^\\.]+");
			while(++i<maxLevel){
				sb.append("\\.[^\\.]+");
				maxLevelPattern[i] = sb.toString();
			}
			maxLevelPattern[0] = "[^\\.]+";
			for(String line:maxLevelPattern) {
				System.out.println(line);
				Pattern.compile(line);
			}
		}
	}
}
