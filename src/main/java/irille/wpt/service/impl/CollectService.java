package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Collect;
import irille.wpt.bean.Headline;
import irille.wpt.bean.Member;
import irille.wpt.dao.impl.CollectDao;
import irille.wpt.dao.impl.HeadlineDao;
import irille.wpt.exception.ExtjsException;

@Repository
public class CollectService {
	
	@Resource
	private CollectDao collectDao;
	@Resource
	private HeadlineDao headlineDao;
	

	/**
	 * 若未收藏，收藏 若已收藏，取消收藏
	 * 
	 * @param topId
	 * @param userId
	 */
	public void collectOrCancel(Integer headlineId, Member member) {
		Headline headline = headlineDao.load(headlineId);
		if(headline == null) {
			throw new ExtjsException("头条不存在");
		}
		Collect collect = collectDao.findByMemberHeadline(member.getPkey(), headlineId);
		if (collect == null) {
			collect = new Collect();
			collect.setAccount(headline.getAccount());
			collect.setHeadline(headline);
			collect.setMember(member);
			collectDao.save(collect);
		} else {
			collectDao.delete(collect);
		}
	}
	public Long countByMember(Integer memberId) {
		return collectDao.countByMember(memberId);
	}
	
	public List<Collect> listByMember(Integer memberId) {
		return collectDao.listByMember(memberId);
	}

	public Collect findByHeadlineMember(Integer headlineId, Integer memberId) {
		return collectDao.findByMemberHeadline(memberId, headlineId);
	}
}
