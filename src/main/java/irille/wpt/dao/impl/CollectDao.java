package irille.wpt.dao.impl;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Collect;
import irille.wpt.dao.AbstractDao;

@Repository
public class CollectDao extends AbstractDao<Collect, Integer>{
	
	public Collect findByHeadlineInUser(Integer userId, Integer headlineId) {
		return findUnique("select * from wpt_collect where user=? and top=?", userId, headlineId);
	}
}
