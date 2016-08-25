package irille.wpt.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import irille.tools.GenericsUtils;
import irille.wpt.actions.resource.impl.ComboLineAction;
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
	
	public static void main(String[] args) {
		ClassPathXmlApplicationContext bf = new ClassPathXmlApplicationContext("applicationContext.xml");
		ComboService dao = bf.getBean(ComboService.class);
		ComboLineAction action = bf.getBean(ComboLineAction.class);
		action.list();
		List<ComboLine> list = action.getBeans();
		ComboLine comboLine = list.get(0);
		String json = new Gson().toJson(comboLine);
		System.out.println(json);
		System.out.println(list);
	}
}
