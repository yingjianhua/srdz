package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Member;
import irille.wpt.dao.AbstractDao;
import irille.wpt.tools.Page;

@Repository
public class MemberDao extends AbstractDao<Member, Integer> {

	public Member findByOpenidInAccount(Integer accountId, String openid) {
		return findUnique("select * from wx_user where account=? and open_id=?", accountId, openid);
	}
	//统计下一级粉丝的数量
	public Long countByInvited2(Integer memberId) {
		return count("select count(*) from wx_user where invited2=?", memberId);
	}
	
	//获取下一级粉丝
	public Page<Member> pageByInvited2(Integer start, Integer limit, Integer invitedId) {
		return page(start, limit, "from wx_user where invited2=?", invitedId);
	}
	//获取下下一级粉丝
	public Page<Member> pageByInvited1(Integer start, Integer limit, Integer invitedId) {
		return page(start, limit, "from wx_user where invited1=?", invitedId);
	}
	
	//获取下一级粉丝
	public Member findByInvited2(Integer invitedId, Integer memberId) {
		return findUnique("select * from wx_user where invited2=? and pkey=?", invitedId, memberId);
	}
	//获取下下一级粉丝
	public Member findByInvited1(Integer invitedId, Integer memberId) {
		return findUnique("select * from wx_user where invited1=? and pkey=?", invitedId, memberId);
	}
	//获取下一级粉丝
	public List<Member> listByInvited2(Integer invitedId) {
		return list("select * from wx_user where invited2=?", invitedId);
	}
	//获取下下一级粉丝
	public List<Member> listByInvited1(Integer invitedId) {
		return list("select from wx_user where invited1=?", invitedId);
	}
}
