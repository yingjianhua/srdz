package irille.wpt.dao;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;

public class BaseDao {
	@Resource
	protected SessionFactory sessionFactory;
}
