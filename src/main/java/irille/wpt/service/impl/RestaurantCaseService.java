package irille.wpt.service.impl;

import org.springframework.stereotype.Service;

import irille.wpt.bean.RestaurantCase;
import irille.wpt.service.AbstractService;

@Service
public class RestaurantCaseService extends AbstractService<RestaurantCase> {
	
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
