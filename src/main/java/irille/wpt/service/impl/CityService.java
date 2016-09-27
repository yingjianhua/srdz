package irille.wpt.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import irille.pub.Log;
import irille.wpt.bean.City;
import irille.wpt.bean.CityLine;
import irille.wpt.exception.ExtjsException;
import irille.wpt.service.AbstractService;
@Service
public class CityService extends AbstractService<City> {
	public static final Log LOG = new Log(CityService.class);
	
	public void save(City bean, List<CityLine> listLine, Integer account) {
		if(cityDao.findByName(bean.getName(), account) != null) {
			throw new ExtjsException("{0} 已存在", bean.getName());
		};
		bean.setAccount(account);
		cityDao.save(bean);
		for(CityLine cityLine:listLine) {
			cityLine.setAccount(account);
			cityLine.setCity(bean);
			cityLineDao.save(cityLine);
		}
	}
	public void update(City bean, List<CityLine> listLine, Integer account) {
		bean.setAccount(account);
		cityDao.update(bean);
		if(listLine == null || listLine.size() == 0) {
			throw new ExtjsException("请设置区域");
		}
		Map<String, CityLine> oMap = new HashMap<String, CityLine>();
		for(CityLine cityLine:cityLineDao.listByCity(bean.getPkey())) {
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
			cityLine.setAccount(account);
			cityLine.setCity(bean);
			cityLineDao.save(cityLine);
		}
	}
	public void delete(City bean) {
		if(restaurantDao.countByCity(bean.getPkey()) > 0) {
			throw new ExtjsException("已有餐厅在 {0} 驻扎，不能删除", bean.getName());
		}
		cityLineDao.deleteByCity(bean.getPkey());
		cityDao.delete(bean);
	}
	public List<City> search(Integer account) {
		return cityDao.search(account);
	}
	
	public City findByName(String name, Integer account) {
		return cityDao.findByName(name, account);
	}
}
