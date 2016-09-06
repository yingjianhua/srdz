package irille.wpt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Collect;
import irille.wpt.bean.Headline;
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
	public void collectOrCancel(Integer headlineId, Integer userId) {
		Headline headline = headlineDao.load(headlineId);
		if(headline == null) {
			throw new ExtjsException("头条不存在");
		}
		Collect collect = collectDao.findByHeadlineInUser(userId, headlineId);
		if (collect == null) {
			collect = new Collect();
			collect.setAccount(headline.getAccount());
			collect.setHeadline(headline);
			collect.setWxuser(userId);
			collectDao.save(collect);
		} else {
			collectDao.delete(collect);
		}
	}

}
