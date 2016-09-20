package irille.wpt.dao.impl;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.RestaurantBsn;
import irille.wpt.dao.AbstractDao;

@Repository
public class RestaurantBsnDao extends AbstractDao<RestaurantBsn, Integer>{
	
	public RestaurantBsn findByMember(Integer memberId) {
		return findUnique("select * from wpt_restaurant_bsn where wxuser=?", memberId);
	}
	
}
