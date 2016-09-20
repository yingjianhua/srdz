package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.CityLine;
import irille.wpt.dao.impl.CityLineDao;

@Repository
public class CityLineService {
	
	@Resource
	private CityLineDao cityLineDao;
	
	public List<CityLine> listByCity(Integer cityId) {
		return cityLineDao.listByCity(cityId);
	}
	
}
