package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Headline;
import irille.wpt.dao.AbstractDao;

@Repository
public class HeadlineDao extends AbstractDao<Headline, Integer>{
	
	public List<Headline> search(Integer cityId, Integer areaId, Integer banquetId, Integer account) {
		if(areaId == null && banquetId == null) {
			return list("select * from wpt_top where account=? and city=? order by top desc,sort", account, cityId);
		} else if(areaId != null && banquetId != null) {
			return list("select * from wpt_top where account=? and city=? and cityline=? and banquet=? order by top desc,sort", account, cityId, areaId, banquetId);
		} else if(areaId != null) {
			return list("select * from wpt_top where account=? and city=? and cityline=? order by top desc,sort", account, cityId, areaId);
		} else {
			return list("select * from wpt_top where account=? and city=? and banquet=? order by top desc,sort", account, cityId, banquetId);
		}
	}

}
