package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Collect;
import irille.wpt.dao.AbstractDao;

@Repository
public class CollectDao extends AbstractDao<Collect, Integer>{
	
	public Long countByMember(Integer memberId) {
		return count("select count(*) from wpt_collect where wxuser=?", memberId);
	}
	public List<Collect> listByMember(Integer memberId) {
		return list("select * from wpt_collect where wxuser=?", memberId);
	}
	public Collect findByMemberHeadline(Integer memberId, Integer headlineId) {
		return findUnique("select * from wpt_collect where wxuser=? and top=?", memberId, headlineId);
	}
}
