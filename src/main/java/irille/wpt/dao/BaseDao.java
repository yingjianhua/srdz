package irille.wpt.dao;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import irille.tools.GenericsUtils;
import irille.wpt.actions.resource.impl.ComboLineAction;
import irille.wpt.bean.Combo;
import irille.wpt.bean.ComboLine;
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
		testJson();
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
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
		filter.setMaxLevel(2);
		SerializeConfig.getGlobalInstance().addFilter(Object.class, filter);
		System.out.println("---");
		String json =  JSONObject.toJSONString(comboLine, SerializerFeature.PrettyFormat);
		System.out.println(json);
	}
}
