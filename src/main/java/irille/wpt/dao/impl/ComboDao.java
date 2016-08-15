package irille.wpt.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import irille.core.sys.Sys.OEnabled;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.wpt.bean.Combo;
import irille.wpt.dao.BaseDao;
import irille.wx.wpt.WptCityLine;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptRestaurantLine;
@Repository
public class ComboDao extends BaseDao<Combo, Integer>{
	private static final String pattern1 = ",(\\d)";
	private static final String pattern2 = "(\\d,)";
	private static final String pattern3 = "(\\d),(\\d)";
	private static final String pattern4 = "(\\d)";
	
	public static void main(String[] args) {
		String a = "1,4";
		Pattern pattern = Pattern.compile(pattern3);
		Matcher m = pattern.matcher(a);
		if(m.find()) {
			System.out.println(m.group(1));
			System.out.println(m.group(2));
		}
		System.out.println(a.matches(pattern1));
		System.out.println(a.matches(pattern2));
		System.out.println(a.matches(pattern3));
		System.out.println(a.matches(pattern4));
	}

	public List<WptCombo> findByCondition(String banquet, String pnum, String perCapitaBudget, String area) {
		StringBuilder where = new StringBuilder();
		where.append(WptCombo.T.ENABLED).append("=").append(OEnabled.TRUE.getLine().getKey());
		if(pnum != null && !pnum.equals("")) {
			if(pnum.matches(pattern1)) {
				where.append(" and ").append(WptCombo.T.NUMBER_MIN).append(" <= ").append(pnum.split(",")[0]);
			} else
			if(pnum.matches(pattern2)) {
				where.append(" and ").append(WptCombo.T.NUMBER_MAX).append(" >= ").append(pnum.split(",")[0]);
			} else
			if(pnum.matches(pattern3)) {
				where.append(" and (").append(WptCombo.T.NUMBER_MIN).append(" <= ").append(pnum.split(",")[1]);
				where.append(" or ").append(WptCombo.T.NUMBER_MAX).append(" >= ").append(pnum.split(",")[0]).append(")");
			} else
			if(pnum.matches(pattern4)) {
				where.append(" and ").append(WptCombo.T.NUMBER_MIN).append(" <= ").append(pnum);
				where.append(" and ").append(WptCombo.T.NUMBER_MAX).append(" >= ").append(pnum);
			}
		} else {
			result = Bean.executeQueryMap(where, OEnabled.TRUE.getLine().getKey(), city, area, banquetId, OEnabled.TRUE.getLine().getKey());
		}
		
		//Session session = sessionFactory.openSession();
		List list = session.getNamedQuery("Combo.findAll").list();
		
		String where = Idu.sqlString("{0} in (select {1} from {2} where {3}=? and {4}=? and {5}=? and {1} in (select {6} from {7} where {8}=?)) and {9}=?"
				, WptCombo.T.RESTAURANT, WptRestaurant.T.PKEY, WptRestaurant.class
				, WptRestaurant.T.ENABLED, WptRestaurant.T.CITY, WptRestaurant.T.CITYLINE, WptRestaurantLine.T.RESTAURANT
				, WptRestaurantLine.class, WptRestaurantLine.T.BANQUET, WptCombo.T.ENABLED);
		List<WptCombo> combos = Bean.list(WptCombo.class, where, false);
		Integer area = areaId;
		Integer city = WptCityLine.load(WptCityLine.class, areaId).getCity();
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
		
		
		return (List<Combo>)list;
	}
}
