package irille.wpt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.Special;
import irille.wpt.bean.SpecialLine;
import irille.wpt.dao.impl.CityDao;
import irille.wpt.dao.impl.SpecialDao;
import irille.wpt.dao.impl.SpecialLineDao;
import irille.wpt.exception.ExtjsException;

@Service
public class SpecialService {

	@Resource
	private SpecialDao specialDao;
	@Resource
	private SpecialLineDao specialLineDao;
	@Resource
	private CityDao cityDao;
	
	public void save(Special bean, List<SpecialLine> listLine, Integer account) {
		bean.setAccount(account);
		specialDao.save(bean);
		for(SpecialLine specialLine:listLine) {
			if(specialLine.getSort() == null) {
				throw new ExtjsException("餐厅排序不能为空");
			}
			specialLine.setAccount(account);
			specialLine.setSpecial(bean);
			specialLineDao.save(specialLine);
		}
		bean.setCity(cityDao.get(bean.getCity().getPkey()));
	}
	
	public void update(Special bean, List<SpecialLine> listLine, Integer account) {
		bean.setAccount(account);
		specialDao.update(bean);
		Map<Integer, SpecialLine> oMap = new HashMap<Integer, SpecialLine>();
		for(SpecialLine SpecialLine:specialLineDao.listBySpecial(bean.getPkey())) {
			oMap.put(SpecialLine.getRestaurant().getPkey(), SpecialLine);
		}
		Map<Integer, SpecialLine> nMap = new HashMap<Integer, SpecialLine>();
		for(SpecialLine specialLine:listLine) {
			if(specialLine == null) continue;
			nMap.put(specialLine.getRestaurant().getPkey(), specialLine);
		}
		//需要删除
		List<SpecialLine> needDel = new ArrayList<SpecialLine>();
		for(Integer restaurantId:oMap.keySet()) {
			if(!nMap.containsKey(restaurantId)) {
				needDel.add(oMap.get(restaurantId));
			}
		}
		//需要新增
		List<SpecialLine> needAdd = new ArrayList<SpecialLine>();
		for(Integer restaurantId:nMap.keySet()) {
			if(!oMap.containsKey(restaurantId)) {
				needAdd.add(nMap.get(restaurantId));
			}
		}
		//删除SpecialLine
		for(SpecialLine specialLine:needDel) {
			specialLineDao.delete(specialLine);
		}
		//新增SpecialLine
		for(SpecialLine specialLine:needAdd) {
			if(specialLine.getSort() == null) {
				throw new ExtjsException("餐厅排序不能为空");
			}
			specialLine.setAccount(account);
			specialLine.setSpecial(bean);
			specialLineDao.save(specialLine);
		}
		bean.setCity(cityDao.get(bean.getCity().getPkey()));
	}
	
	public void delete(Special bean) {
		specialLineDao.delete(specialLineDao.listBySpecial(bean.getPkey()));
		specialDao.delete(bean);
	}
	
	public List<Special> listByCity(Integer cityId) {
		return specialDao.listByCity(cityId);
	}
	public Special get(Integer id) {
		return specialDao.get(id);
	}
}
