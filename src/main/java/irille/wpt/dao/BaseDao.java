package irille.wpt.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import irille.tools.GenericsUtils;

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
		System.out.println("entityClass:"+entity.getName());
		return session.createQuery("from "+entity.getSimpleName()).setFirstResult(start).setMaxResults(limit).list();
	}
}
