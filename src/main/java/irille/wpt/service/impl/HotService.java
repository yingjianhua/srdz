package irille.wpt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import irille.pub.idu.Idu;
import irille.wpt.bean.Hot;
import irille.wpt.service.AbstractService;
import irille.wx.wx.WxAccountDAO;

@Service
public class HotService extends AbstractService<Hot> {
	
	public void save(Hot bean) {
		bean.setAccount(WxAccountDAO.getByUser(Idu.getUser()).getPkey());
		hotDao.save(bean);
		bean.setCity(cityDao.get(bean.getCity().getPkey()));
		bean.setRestaurant(restaurantDao.get(bean.getRestaurant().getPkey()));
	}
	
	public void update(Hot bean) {
		bean.setAccount(WxAccountDAO.getByUser(Idu.getUser()).getPkey());
		hotDao.update(bean);
		bean.setCity(cityDao.get(bean.getCity().getPkey()));
		bean.setRestaurant(restaurantDao.get(bean.getRestaurant().getPkey()));
	}
	
	public List<Hot> listByCity(Integer cityId) {
		return hotDao.listByCity(cityId);
	}
}
