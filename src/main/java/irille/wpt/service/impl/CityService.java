package irille.wpt.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.Log;
import irille.wpt.bean.City;
import irille.wpt.bean.CityLine;
import irille.wpt.dao.impl.CityDao;
import irille.wpt.dao.impl.CityLineDao;
import irille.wpt.dao.impl.RestaurantDao;
import irille.wpt.exception.ExtjsException;
@Service
public class CityService {
	public static final Log LOG = new Log(CityService.class);
	
	@Resource
	private CityDao cityDao;
	@Resource
	private CityLineDao cityLineDao;
	@Resource
	private RestaurantDao restaurantDao;
	
	public void save(City city, List<CityLine> listLine, Integer accountId) {
		if(cityDao.findByName(city.getName(), accountId) != null) {
			throw new ExtjsException("{0} 已存在", city.getName());
		};
		city.setAccount(accountId);
		cityDao.save(city);
		for(CityLine cityLine:listLine) {
			cityLine.setAccount(accountId);
			cityLine.setCity(city);
			cityLineDao.save(cityLine);
		}
	}
	public void update(City city, List<CityLine> listLine, Integer accountId) {
		city.setAccount(accountId);
		cityDao.update(city);
		if(listLine == null || listLine.size() == 0) {
			throw new ExtjsException("请设置区域");
		}
		Map<String, CityLine> oMap = new HashMap<String, CityLine>();
		for(CityLine cityLine:cityLineDao.listByCity(city.getPkey())) {
			oMap.put(cityLine.getName(), cityLine);
		}
		Map<String, CityLine> nMap = new HashMap<String, CityLine>();
		for(CityLine cityLine:listLine) {
			if(cityLine == null) continue;
			nMap.put(cityLine.getName(), cityLine);
		}
		//需要删除
		List<CityLine> needDel = new ArrayList<CityLine>();
		for(String name:oMap.keySet()) {
			if(!nMap.containsKey(name)) {
				needDel.add(oMap.get(name));
			}
		}
		//需要新增
		List<CityLine> needAdd = new ArrayList<CityLine>();
		for(String name:nMap.keySet()) {
			if(!oMap.containsKey(name)) {
				needAdd.add(nMap.get(name));
			}
		}
		//删除cityLine
		for(CityLine cityLine:needDel) {
			cityLineDao.delete(cityLine);
		}
		//新增cityLine
		for(CityLine cityLine:needAdd) {
			cityLine.setAccount(accountId);
			cityLine.setCity(city);
			cityLineDao.save(cityLine);
		}
	}
	public void delete(City city) {
		if(restaurantDao.countByCity(city.getPkey()) > 0) {
			throw new ExtjsException("已有餐厅在 {0} 驻扎，不能删除", city.getName());
		}
		cityLineDao.deleteByCity(city.getPkey());
		cityDao.delete(city);
	}
	public List<City> search(Integer accountId) {
		return cityDao.search(accountId);
	}
	
	public City findByName(String name, Integer accountId) {
		return cityDao.findByName(name, accountId);
	}
}
