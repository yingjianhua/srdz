package irille.wpt.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import irille.wpt.bean.City;
import irille.wpt.dao.AbstractDao;

@Repository
public class CityDao extends AbstractDao<City, Integer>{
	
	public List<City> search(Integer accountId) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder where = new StringBuilder("select * from wpt_city where");
		where.append(" account=").append(accountId);
		List<City> list = session.createSQLQuery(where.toString()).addEntity(City.class).list();
		return list;
	}
	
	public City findByName(String name, Integer accountId) {
		System.out.println("findByName.entityClass:"+entityClass.getName());
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("select * from wpt_city where name=? and account=?");
		query.setString(0, name);
		query.setInteger(1, accountId);
		return (City)query.addEntity(City.class).uniqueResult();
	}

}
