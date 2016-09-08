package irille.wpt.dao.impl;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Member;
import irille.wpt.dao.AbstractDao;

@Repository
public class MemberDao extends AbstractDao<Member, Integer> {

	public Member findByOpenidInAccount(Integer accountId, String openid) {
		return findUnique("select * from wx_user where account=? and open_id=?", accountId, openid);
	}
}
