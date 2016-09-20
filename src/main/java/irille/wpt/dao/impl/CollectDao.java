package irille.wpt.dao.impl;

import java.util.List;

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
	public List<Collect> listByMember(Integer memberId) {
		return list("select * from wpt_collect where wxuser=?", memberId);
	}
	public Collect findByHeadlineMember(Integer headlineId, Integer memberId) {
		return findUnique("select * from wpt_collect where top=? and wxuser=?", headlineId, memberId);
	}
}
