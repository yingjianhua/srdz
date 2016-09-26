package irille.wpt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.RestaurantCase;
import irille.wpt.dao.impl.RestaurantCaseDao;
import irille.wpt.dao.impl.RestaurantDao;

@Service
public class RestaurantCaseService {
	
	@Resource
	private RestaurantCaseDao restaurantCaseDao;
	
	@Resource
	private RestaurantDao restaurantDao;
	
	public void save(RestaurantCase bean, Integer account) {
		bean.setAccount(account);
		restaurantCaseDao.save(bean);
		bean.setRestaurant(restaurantDao.get(bean.getRestaurant().getPkey()));
	}
	
	public void update(RestaurantCase bean, Integer account) {
		bean.setAccount(account);
		restaurantCaseDao.update(bean);
		bean.setRestaurant(restaurantDao.get(bean.getRestaurant().getPkey()));
	}

}
