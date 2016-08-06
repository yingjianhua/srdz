package irille.wpt.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import irille.core.sys.Sys.OEnabled;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptCityLine;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptRestaurantLine;

public class RestaurantService {
	/**
	 * 
	 * @param areaId 区域id
	 * @param banquetId 宴会类型id
	 * @param pnum 参会人数
	 * @param perCapitaBudget 人均消费
	 * @return Map<餐厅对象,List<套餐pkey>>
	 */
	public Map<WptRestaurant, List<Object>> listRestaurant(Integer areaId, Integer banquetId, Integer pnum, Integer perCapitaBudget) {
		Integer area = areaId;
		Integer city = WptCityLine.load(WptCityLine.class, areaId).getCity();
		String where = Idu.sqlString("select {0},{1} from {2} where {1} in (select {3} from {4} where {5}=? and {6}=? and {7}=? and {3} in (select {8} from {9} where {10}=?)) and {11}=?",
				WptCombo.T.PKEY, WptCombo.T.RESTAURANT, WptCombo.class, WptRestaurant.T.PKEY, WptRestaurant.class
				, WptRestaurant.T.ENABLED, WptRestaurant.T.CITY, WptRestaurant.T.CITYLINE, WptRestaurantLine.T.RESTAURANT
				, WptRestaurantLine.class, WptRestaurantLine.T.BANQUET, WptCombo.T.ENABLED);
		Map<String, Object>[] result = null;
		if(!pnum.equals(0)) {
			where += Idu.sqlString(" and ?<={0} and ?>={1}", WptCombo.T.NUMBER_MAX, WptCombo.T.NUMBER_MIN);
			if(!perCapitaBudget.equals(0)) {
				where += Idu.sqlString(" and ?<=({0}+50) and ?>=({0}-50)", WptCombo.T.PRICE);
				result = Bean.executeQueryMap(where, OEnabled.TRUE.getLine().getKey(), city, area, banquetId, OEnabled.TRUE.getLine().getKey(), pnum, pnum, perCapitaBudget*pnum, perCapitaBudget*pnum);
			} else {
				result = Bean.executeQueryMap(where, OEnabled.TRUE.getLine().getKey(), city, area, banquetId, OEnabled.TRUE.getLine().getKey(), pnum, pnum);
			}
		} else {
			result = Bean.executeQueryMap(where, OEnabled.TRUE.getLine().getKey(), city, area, banquetId, OEnabled.TRUE.getLine().getKey());
		}
		String sqlRestaurant = WptCombo.T.RESTAURANT.getFld().getCodeSqlField();
		String sqlPkey = WptCombo.T.PKEY.getFld().getCodeSqlField();
		Map<Integer, WptRestaurant> mapRestaurant = new HashMap<Integer, WptRestaurant>();
		Map<WptRestaurant, List<Object>> mapCombo = new HashMap<WptRestaurant, List<Object>>();
		for(Map<String, Object> line:result) {
			if(!mapRestaurant.containsKey((Integer)line.get(sqlRestaurant))) {
				mapRestaurant.put((Integer)line.get(sqlRestaurant), WptRestaurant.load(WptRestaurant.class, (Integer)line.get(sqlRestaurant)));
				mapCombo.put(mapRestaurant.get((Integer)line.get(sqlRestaurant)), new ArrayList<Object>());
			}
			mapCombo.get(mapRestaurant.get((Integer)line.get(sqlRestaurant))).add((Integer)line.get(sqlPkey));
		}
		return mapCombo;
	}
}
