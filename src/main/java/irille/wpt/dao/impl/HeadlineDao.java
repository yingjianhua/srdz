package irille.wpt.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import irille.wpt.bean.Headline;
import irille.wpt.dao.AbstractDao;

@Repository
public class HeadlineDao extends AbstractDao<Headline, Integer>{
	
	public List<Headline> search(Integer cityId, Integer areaId, Integer banquetId, Integer accountId) {
		Session session = sessionFactory.getCurrentSession();
		StringBuilder where = new StringBuilder("select * from wpt_top where");
		where.append(" account=").append(accountId);
		where.append(" and city=").append(cityId);
		if (areaId != null) {
			where.append(" and cityline=").append(areaId);
		}
		if (banquetId != null) {
			where.append(" and banquet=").append(banquetId);
		}
		where.append(" order by top desc,sort");
		List<Headline> list = session.createSQLQuery(where.toString()).addEntity(Headline.class).list();
		return list;
	}

}
