package irille.wpt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.Feedback;
import irille.wpt.dao.impl.FeedbackDao;
@Service
public class FeedbackService {
	
	@Resource
	private FeedbackDao feedbackDao;

	/**
	 * 把反馈插入到数据库
	 */
	public void suggest(String content, Byte contactType, String contactWay, Integer accountId){
		Feedback feedback = new Feedback();
		feedback.setAccount(accountId);
		feedback.setContactType(contactType);
		feedback.setContactWay(contactWay);
		feedback.setContent(content);
		feedback.setIsHandle(false);
		feedbackDao.save(feedback);
	}
}
