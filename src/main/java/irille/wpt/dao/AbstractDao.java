package irille.wpt.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import irille.tools.GenericsUtils;
import irille.wpt.tools.Page;

@Repository
public abstract class AbstractDao<T,ID extends Serializable> {
	@Resource
	protected SessionFactory sessionFactory;
	protected Class<T> entityClass;
	
	public AbstractDao() {
		this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());  
	}
	public void executeUpdate(String sql, Object ... params) {
		SQLQuery query = createSQLQuery(sql, params);
		query.executeUpdate();
	}
	public Long count(String sql, Object ... params) {
		SQLQuery query = createSQLQuery(sql, params);
		return (Long)query.uniqueResult();
	}
	public T get(ID id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(entityClass, id);
	}
	public T load(ID id) {
		Session session = sessionFactory.getCurrentSession();
		return session.load(entityClass, id);
	}
	public T findUnique(String sql, Object ... params) {
		SQLQuery query = createSQLQuery(sql, params);
		query.addEntity(entityClass);
		return (T)query.uniqueResult();
	}
	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from "+entityClass.getName()).list();
	}
	public List<T> list(String sql, Object ... params) {
		SQLQuery query = createSQLQuery(sql, params);
		query.addEntity(entityClass);
		return query.list();
	}
	public Page<T> page(Integer start, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		Long total = (Long)session.createQuery("select count(*) from "+entityClass.getName()).uniqueResult();
		Query query = session.createQuery("from "+entityClass.getName());
		query.setFirstResult(start).setMaxResults(limit);
		List<T> items = query.list();
		Page<T> page = new Page<T>(total, items);
		return page;
	}
	
	public void save(T bean) {
		Session session = sessionFactory.getCurrentSession();
		session.save(bean);
	}
	public void update(T bean) {
		Session session = sessionFactory.getCurrentSession();
		session.update(bean);
	}
	public void delete(T bean) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(bean);
	}
	public void delete(List<T> beans) {
		Session session = sessionFactory.getCurrentSession();
		for(T bean:beans) {
			session.delete(bean);
		}
	}
	
	private SQLQuery createSQLQuery(String sql, Object ... params) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		for(int i=0;i<params.length;i++) {
			query.setParameter(i, params[i]);
		}
		return query;
	}
}
