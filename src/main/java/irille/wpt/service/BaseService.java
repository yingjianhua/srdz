package irille.wpt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import irille.wpt.dao.BaseDao;

@Repository
public class BaseService {
	
	@Resource
	@Qualifier("baseDao")
	private BaseDao dao;

	@Transactional
	public List list(Class entityClass, Integer start, Integer limit) {
		return dao.list(entityClass, start, limit);
	}
}
