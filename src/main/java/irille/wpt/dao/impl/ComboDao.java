package irille.wpt.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import irille.wpt.bean.Combo;
import irille.wpt.dao.BaseDao;
@Repository
public class ComboDao extends BaseDao<Combo, Integer>{

	public List<Combo> findByCondition() {
		Session session = sessionFactory.getCurrentSession();
		//Session session = sessionFactory.openSession();
		List list = session.getNamedQuery("Combo.findAll").list();
		return (List<Combo>)list;
	}
}
