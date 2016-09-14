package irille.wpt.dao.impl;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Collect;
import irille.wpt.dao.AbstractDao;

@Repository
public class CollectDao extends AbstractDao<Collect, Integer>{
	
	public Collect findByHeadlineInUser(Integer userId, Integer headlineId) {
		return findUnique("select * from wpt_collect where wxuser=? and top=?", userId, headlineId);
	}
	public Long countByMember(Integer memberId) {
		return count("select count(*) from wpt_collect where wxuser=?", memberId);
	}
}
