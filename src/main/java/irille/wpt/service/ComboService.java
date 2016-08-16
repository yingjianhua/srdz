package irille.wpt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import irille.wpt.bean.Combo;
import irille.wpt.dao.impl.ComboDao;

@Service
public class ComboService {

	
	@Resource
	private ComboDao comboDao;
	
	@Transactional
	public Combo get(Integer id) {
		return comboDao.get(id);
	}
	/**
	 * 
	 * @param banquetId 宴会类型id
	 * @param pnum 参会人数
	 * @param perCapitaBudget 人均消费
	 * @param areaId 区域id
	 * @return List<套餐>
	 */
	@Transactional
	public List<Combo> findByCondition(String banquet, String pnum, String budget, String city, String area,String longitude,String latitude) {
		return comboDao.pageByCondition(banquet, pnum, budget, city, area, longitude, latitude, null, null);
	}
}
