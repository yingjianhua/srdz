package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.Combo;
import irille.wpt.bean.ComboLine;
import irille.wpt.dao.impl.ComboDao;
import irille.wpt.dao.impl.ComboLineDao;

@Service
public class ComboService {

	
	@Resource
	private ComboDao comboDao;
	@Resource
	private ComboLineDao comboLineDao;
	
	/**
	 * 
	 * @param banquetId 宴会类型id
	 * @param pnum 参会人数
	 * @param perCapitaBudget 人均消费
	 * @param areaId 区域id
	 * @return List<套餐>
	 */
	public List<Combo> findByCondition(String banquet, String pnum, String budget, String city, String area,String longitude,String latitude) {
		return comboDao.pageByCondition(banquet, pnum, budget, city, area, longitude, latitude, null, null);
	}
	
	public void save(Combo combo, List<ComboLine> listLine) {
		comboDao.save(combo);
		for(ComboLine comboLine:listLine) {
			comboLine.setCombo(combo);
			comboLineDao.save(comboLine);
		}
	}
}
