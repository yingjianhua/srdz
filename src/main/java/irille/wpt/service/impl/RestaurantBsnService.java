package irille.wpt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.RestaurantBsn;
import irille.wpt.dao.impl.RestaurantBsnDao;

@Service
public class RestaurantBsnService {
	
	@Resource
	private RestaurantBsnDao restaurantBsnDao;

	public RestaurantBsn findByMember(Integer memberId) {
		return restaurantBsnDao.findByMember(memberId);
	}
}
