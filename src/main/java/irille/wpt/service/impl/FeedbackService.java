package irille.wpt.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import irille.pub.idu.Idu;
import irille.wpt.bean.Feedback;
import irille.wpt.service.AbstractService;
@Service
public class FeedbackService extends AbstractService<Feedback> {

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
	
	/**
	 * 反馈处理
	 */
	public Feedback toDo(Feedback feedback) {
		feedback = feedbackDao.get(feedback.getPkey());
		if(!feedback.getIsHandle()) {
			feedback.setHandleMan(Idu.getUser().getPkey());
			feedback.setHandleTime(new Date());
			feedback.setIsHandle(true);
			feedbackDao.update(feedback);
		}
		return feedback;
	}
}
