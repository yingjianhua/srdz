package irille.wpt.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import irille.wpt.bean.Combo;
import irille.wpt.bean.ComboLine;
import irille.wpt.exception.ExtjsException;
import irille.wpt.service.AbstractService;

@Service
public class ComboService extends AbstractService<Combo> {

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
	
	public void save(Combo bean, List<ComboLine> listLine, Integer account) {
		bean.setAccount(account);
		comboDao.save(bean);
		for(ComboLine comboLine:listLine) {
			comboLine.setAccount(account);
			comboLine.setCombo(bean);
			comboLineDao.save(comboLine);
		}
	}
	
	public void update(Combo bean, List<ComboLine> listLine, Integer account) {
		if(listLine == null || listLine.size() == 0) {
			throw new ExtjsException("请设置菜单");
		}
		Map<Integer, ComboLine> oMap = new HashMap<Integer, ComboLine>();
		for(ComboLine comboLine:comboLineDao.listByCombo(bean.getPkey())) {
			oMap.put(comboLine.getMenu().getPkey(), comboLine);
		}
		Map<Integer, ComboLine> nMap = new HashMap<Integer, ComboLine>();
		for(ComboLine comboLine:listLine) {
			if(comboLine == null) continue;
			nMap.put(comboLine.getMenu().getPkey(), comboLine);
		}
		//需要删除
		List<ComboLine> needDel = new ArrayList<ComboLine>();
		for(Integer menuId:oMap.keySet()) {
			if(!nMap.containsKey(menuId)) {
				needDel.add(oMap.get(menuId));
			}
		}
		//需要新增
		List<ComboLine> needAdd = new ArrayList<ComboLine>();
		for(Integer menuId:nMap.keySet()) {
			if(!oMap.containsKey(menuId)) {
				needAdd.add(nMap.get(menuId));
			}
		}
		//删除comboLine
		for(ComboLine comboLine:needDel) {
			comboLineDao.delete(comboLine);
		}
		//新增comboLine
		for(ComboLine comboLine:needAdd) {
			comboLine.setAccount(account);
			comboLine.setCombo(bean);
			comboLineDao.save(comboLine);
		}
		bean.setAccount(account);
		bean.setComboLines(comboLineDao.listByCombo(bean.getPkey()));
		comboDao.update(bean);
	}
	
	public void delete(Combo combo) {
		combo = comboDao.get(combo.getPkey());
		comboLineDao.delete(comboLineDao.listByCombo(combo.getPkey()));
		comboDao.delete(combo);
	}
	
	public Combo get(Integer id) {
		return comboDao.get(id);
	}
}
