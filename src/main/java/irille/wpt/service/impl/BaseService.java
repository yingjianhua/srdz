package irille.wpt.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import irille.wpt.dao.impl.BaseDao;
import irille.wpt.tools.Page;

@Repository
public class BaseService {
	
	@Resource
	private BaseDao baseDao;
	
	public <E> List<E> list(Class<E> entityClass, Integer start, Integer limit, String where) {
		return baseDao.list(entityClass, start, limit, where);
	}
	
	public <E> E load(Class<E> entityClass, Serializable id) {
		return (E)baseDao.load(entityClass, id);
	}
	
	public <E> Page<E> page(Class<E> entityClass, Integer start, Integer limit, String where) {
		return baseDao.page(entityClass, start, limit, where);
	}
	
	public <E> void save(E bean) {
		baseDao.save(bean);
	}
	
	public <E> void update(E bean) {
		baseDao.update(bean);
	}
	
	public <E> void delete(E bean) {
		baseDao.delete(bean);
	}

}
