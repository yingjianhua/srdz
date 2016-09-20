package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Restaurant;
import irille.wpt.dao.AbstractDao;

@Repository
public class RestaurantDao extends AbstractDao<Restaurant, Integer> {
	/**
	 * 
	 * @param cityId 城市id
	 * @param areaId 区域id
	 * @param pnum 宴会人数
	 * @param perCapitaBudget 人均预算
	 * @return
	 */
	public List<Restaurant> findByCondition(Integer cityId, Integer areaId, Integer pnum, Integer perCapitaBudget) {
		return null;
	}
	
	public Long countByCity(Integer city) {
		return count("select count(*) from wpt_restaurant where city=?", city);
	}

	public List<Restaurant> listByManger(Integer account, String manager) {
		return list("select * from wpt_restaurant where account=? and manger=?", account, manager);
	}
}
