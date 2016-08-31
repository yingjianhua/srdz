package irille.wpt.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import irille.wpt.dao.AbstractDao;
import irille.wpt.tools.Page;

@Repository
public class BaseDao extends AbstractDao<Object, Integer> {
	
	public <E> E load(Class<E> entity, Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		return (E)session.load(entity, id);
	}
	public <E> List<E> list(Class<E> entity, Integer start, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from "+entity.getSimpleName());
		if(start!=null) {
			query.setFirstResult(start);
		}
		if(limit!=null) {
			query.setMaxResults(limit);
		}
		return query.list();
	}
	public <E> Page<E> page(Class<E> entity, Integer start, Integer limit) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from "+entity.getSimpleName());
		Long total = (Long)session.createQuery("select count(*) from "+entity.getSimpleName()).uniqueResult();
		if(start!=null) {
			query.setFirstResult(start);
		}
		if(limit!=null) {
			query.setMaxResults(limit);
		}
		List<E> items = query.list();
		return new Page<E>(total, items);
	}
}
