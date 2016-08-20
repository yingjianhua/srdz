package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Restaurant;
import irille.wpt.dao.BaseDao;

@Repository
public class RestaurantDao extends BaseDao {
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

}
