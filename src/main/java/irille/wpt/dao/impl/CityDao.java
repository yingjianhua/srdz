package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.City;
import irille.wpt.dao.AbstractDao;

@Repository
public class CityDao extends AbstractDao<City, Integer>{
	
	public List<City> search(Integer accountId) {
		return list("select * from wpt_city where account=?", accountId);
	}
	
	public City findByName(String name, Integer accountId) {
		return findUnique("select * from wpt_city where name=? and account=?", name, accountId);
	}
	
}
