package irille.wpt.dao.impl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import irille.core.sys.Sys.OEnabled;
import irille.pub.bean.Bean;
import irille.wpt.bean.Combo;
import irille.wpt.dao.BaseDao;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptRestaurantLine;
@Repository
public class ComboDao extends BaseDao<Combo, Integer>{
	private static final String pattern1 = ",(\\d+)";
	private static final String pattern2 = "(\\d+,)";
	private static final String pattern3 = "(\\d+),(\\d+)";
	private static final String pattern4 = "(\\d+)";
	
	public List<Combo> pageByCondition(String banquet, String pnum, String budget, String city, String area, String longitude, String latitude, Integer start, Integer limit) {
		SQLQuery query = createQueryByConditionOrderByDistance(banquet, pnum, budget, city, area, longitude, latitude);
		if(start != null) {
			query.setFirstResult(start);
		}
		if(limit != null) {
			query.setMaxResults(limit);
		}
		return query.list();
	}
	private StringBuilder createQueryString(String banquet, String pnum, String budget, String city, String area) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder where = new StringBuilder();
		where.append("select c.* from ").append(WptCombo.TB.getCodeSqlTb()).append(" c left join ").append(WptRestaurant.TB.getCodeSqlTb()).append(" r ");
		where.append("on c.").append(WptCombo.T.RESTAURANT).append("=r.").append(WptCombo.T.PKEY).append(" where ");
		where.append("c.").append(WptCombo.T.ENABLED).append("=").append(OEnabled.TRUE.getLine().getKey());
		if(pnum != null && !pnum.equals("")) {
			if(pnum.matches(pattern1)) {
				where.append(" and c.").append(WptCombo.T.NUMBER_MIN).append(" <= ").append(pnum.split(",")[1]);
			} else
			if(pnum.matches(pattern2)) {
				where.append(" and c.").append(WptCombo.T.NUMBER_MAX).append(" >= ").append(pnum.split(",")[0]);
			} else
			if(pnum.matches(pattern3)) {
				where.append(" and (c.").append(WptCombo.T.NUMBER_MIN).append(" <= ").append(pnum.split(",")[1]);
				where.append(" or c.").append(WptCombo.T.NUMBER_MAX).append(" >= ").append(pnum.split(",")[0]).append(")");
			} else
			if(pnum.matches(pattern4)) {
				where.append(" and c.").append(WptCombo.T.NUMBER_MIN).append(" <= ").append(pnum);
				where.append(" and c.").append(WptCombo.T.NUMBER_MAX).append(" >= ").append(pnum);
			}
		}
		if(budget != null && !budget.equals("")) {
			if(budget.matches(pattern1)) {
				where.append(" and c.").append(WptCombo.T.PRICE).append(" <= ").append(budget.split(",")[1]);
			} else
			if(budget.matches(pattern2)) {
				where.append(" and c.").append(WptCombo.T.PRICE).append(" >= ").append(budget.split(",")[0]);
			} else
			if(budget.matches(pattern3)) {
				where.append(" and c.").append(WptCombo.T.PRICE).append(" <= ").append(budget.split(",")[1]);
				where.append(" and c.").append(WptCombo.T.PRICE).append(" >= ").append(budget.split(",")[0]);
			} else
			if(budget.matches(pattern4)) {
				where.append(" and c.").append(WptCombo.T.PRICE).append(" = ").append(budget);
			}
		}
		if(city != null && !city.equals("")) {
			where.append(" and r.").append(WptRestaurant.T.CITY).append("=").append(city);
		}
		if(area != null && !area.equals("")) {
			where.append(" and r.").append(WptRestaurant.T.CITYLINE).append("=").append(area);
		}
		if(banquet != null && !banquet.equals("")) {
			where.append(" and c.").append(WptCombo.T.RESTAURANT).append(" in (");
			where.append("select ").append(WptRestaurantLine.T.RESTAURANT).append(" from ").append(WptRestaurantLine.TB.getCodeSqlTb()).append(" where ").append(WptRestaurantLine.T.BANQUET).append("=").append(banquet);
			where.append(")");
		}
		return where;
	}
	private SQLQuery createQueryByConditionOrderByDistance(String banquet, String pnum, String budget, String city, String area, String longitude, String latitude) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder where = createQueryString(banquet, pnum, budget, city, area);
		if(longitude != null && !longitude.equals("") && latitude != null && !latitude.equals("")) {
			where.append(" order by getDistance(").append(latitude).append(",").append(longitude).append(",r.").append(WptRestaurant.T.LATITUDE).append(",").append(WptRestaurant.T.LONGITUDE).append(")");
		}
		System.out.println(where.toString());
		return session.createSQLQuery(where.toString()).addEntity(Combo.class);
	}
	public List<WptCombo> findByCondition(String banquet, String pnum, String budget, String city, String area) {
		StringBuilder where = new StringBuilder();
		where.append(WptCombo.T.ENABLED).append("=").append(OEnabled.TRUE.getLine().getKey());
		if(pnum != null && !pnum.equals("")) {
			if(pnum.matches(pattern1)) {
				where.append(" and ").append(WptCombo.T.NUMBER_MIN).append(" <= ").append(pnum.split(",")[1]);
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
		}
		if(budget != null && !budget.equals("")) {
			if(budget.matches(pattern1)) {
				where.append(" and ").append(WptCombo.T.PRICE).append(" <= ").append(budget.split(",")[1]);
			} else
			if(budget.matches(pattern2)) {
				where.append(" and ").append(WptCombo.T.PRICE).append(" >= ").append(budget.split(",")[0]);
			} else
			if(budget.matches(pattern3)) {
				where.append(" and ").append(WptCombo.T.PRICE).append(" <= ").append(budget.split(",")[1]);
				where.append(" and ").append(WptCombo.T.PRICE).append(" >= ").append(budget.split(",")[0]);
			} else
			if(budget.matches(pattern4)) {
				where.append(" and ").append(WptCombo.T.PRICE).append(" = ").append(budget);
			}
		}
		where.append(" and ").append(WptCombo.T.RESTAURANT).append(" in (select ").append(WptRestaurant.T.PKEY).append(" from ").append(WptRestaurant.TB.getCodeSqlTb()).append(" where 1=1");
		if(city != null && !city.equals("")) {
			where.append(" and ").append(WptRestaurant.T.CITY).append("=").append(city);
		}
		if(area != null && !area.equals("")) {
			where.append(" and ").append(WptRestaurant.T.CITYLINE).append("=").append(area);
		}
		if(banquet != null && !banquet.equals("")) {
			where.append(" and ").append(WptRestaurant.T.PKEY).append(" in (");
			where.append("select ").append(WptRestaurantLine.T.RESTAURANT).append(" from ").append(WptRestaurantLine.TB.getCodeSqlTb()).append(" where ").append(WptRestaurantLine.T.BANQUET).append("=").append(banquet);
			where.append(")");
		}
		where.append(")");
		System.out.println(where.toString());
		List<WptCombo> list = Bean.list(WptCombo.class, where.toString(), false);
		return list;
	}
}
