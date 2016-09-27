package irille.wpt.dao;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import irille.tools.GenericsUtils;
import irille.wpt.bean.SpecialLine;
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
	public Object uniqueResult(String sql, Object ... params) {
		SQLQuery query = createSQLQuery(sql, params);
		return query.uniqueResult();
	}
	public Long count(String sql, Object ... params) {
		SQLQuery query = createSQLQuery(sql, params);
		Object result = query.uniqueResult();
		if(result instanceof BigInteger) {
			return ((BigInteger)result).longValue();
		} else if(result instanceof Long) {
			return (Long)result;
		}
		return null;
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
	public List<T> list(Integer start, Integer limit, String where) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from "+entityClass.getName()+" this where "+where);
		if(start!=null) {
			query.setFirstResult(start);
		}
		if(limit!=null) {
			query.setMaxResults(limit);
		}
		return query.list();
	}
	public Page<T> pageHql(Integer start, Integer limit, String where) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from "+entityClass.getSimpleName()+" this where "+where);
		Long total = (Long)session.createQuery("select count(*) from "+entityClass.getSimpleName()+" this where "+where).uniqueResult();
		if(start!=null) {
			query.setFirstResult(start);
		}
		if(limit!=null) {
			query.setMaxResults(limit);
		}
		List<T> items = query.list();
		return new Page<T>(total, items);
	}
	public Page<T> pageSql(Integer start, Integer limit, String where, Object ... params) {
		SQLQuery totalQuery = createSQLQuery("select count(*) "+where, params);
		Long total = (Long)totalQuery.uniqueResult();
		SQLQuery itemsQuery = createSQLQuery("select * "+where, params);
		itemsQuery.setFirstResult(start).setMaxResults(limit);
		List<T> items = itemsQuery.list();
		Page<T> page = new Page<T>(total, items);
		return page;
	}
	public Page<T> pageSql(Integer start, Integer limit) {
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
	
	public static void main(String[] args) {
		BeanFactory bf = new ClassPathXmlApplicationContext("applicationContext.xml","spring-bean.xml");
		SessionFactory sf = bf.getBean(SessionFactory.class);
		Session session = sf.openSession();
		Query query = session.createSQLQuery("select * from wpt_special_line where special=?").addEntity(SpecialLine.class).setParameter(0, 68);
		List<Object> list = query.list();
		System.out.println(list.size());
		for(Object line:list) {
			System.out.println(line);
		}
	}
}
