package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Hot;
import irille.wpt.dao.impl.HotDao;

@Repository
public class HotService {
	
	@Resource
	private HotDao hotDao;
	
	public List<Hot> listByCity(Integer cityId) {
		return hotDao.listByCity(cityId);
	}
}
