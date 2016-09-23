package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.CityLine;
import irille.wpt.dao.impl.CityLineDao;

@Service
public class CityLineService {
	
	@Resource
	private CityLineDao cityLineDao;
	
	public List<CityLine> listByCity(Integer cityId) {
		return cityLineDao.listByCity(cityId);
	}
	
}
