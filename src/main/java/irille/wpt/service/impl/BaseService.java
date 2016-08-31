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
	
	public <E> List<E> list(Class<E> entityClass, Integer start, Integer limit) {
		return baseDao.list(entityClass, start, limit);
	}
	
	public <E> E load(Class<E> entityClass, Serializable id) {
		return (E)baseDao.load(entityClass, id);
	}
	
	public <E> Page<E> page(Class<E> entityClass, Integer start, Integer limit) {
		return baseDao.page(entityClass, start, limit);
	}

}
