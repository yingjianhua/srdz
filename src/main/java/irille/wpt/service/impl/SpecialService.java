package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.Special;
import irille.wpt.dao.impl.SpecialDao;

@Service
public class SpecialService {

	@Resource
	private SpecialDao specialDao;
	
	public List<Special> listByCity(Integer cityId) {
		return specialDao.listByCity(cityId);
	}
	public Special get(Integer id) {
		return specialDao.get(id);
	}
}
