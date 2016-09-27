package irille.wpt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import irille.wpt.bean.CityLine;
import irille.wpt.service.AbstractService;

@Service
public class CityLineService extends AbstractService<CityLine> {
	
	public List<CityLine> listByCity(Integer cityId) {
		return cityLineDao.listByCity(cityId);
	}
	
}
