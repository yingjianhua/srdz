package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.CityLine;
import irille.wpt.dao.AbstractDao;

@Repository
public class CityLineDao extends AbstractDao<CityLine, Integer>{
	
	public CityLine findByNameInCity(String name, Integer city) {
		return findUnique("select * from wpt_city_line where name=? and city=?", name, city);
	}
	public List<CityLine> listByCity(Integer city) {
		return list("select * from wpt_city_line where city=?", city);
	}
	public void deleteByCity(Integer city) {
		executeUpdate("delete from wpt_city_line where city=?", city);
	}
}
