package irille.wpt.dao;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import irille.tools.GenericsUtils;

public class BaseDao<T,ID extends Serializable> {
	@Resource
	protected SessionFactory sessionFactory;
	protected Class<T> entityClass;
	
	public T get(ID id) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("T--------------");
		System.out.println(session);
		System.out.println(entityClass);
		System.out.println(id);
		System.out.println("T--------------");
		return session.get(entityClass, id);
	}
	
	public BaseDao() {
		this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());  
	}
	public T load(ID id) {
		Session session = sessionFactory.getCurrentSession();
		return session.load(entityClass, id);
	}
}
