package irille.wpt.service;

import irille.wx.wpt.WptFeedBack;

public class FeedBackService {

	/**
	 * 把反馈插入到数据库
	 */
	public WptFeedBack suggest(String content, Byte contactType, String contactWay, Integer accountId){
		WptFeedBack feedBack = new WptFeedBack();
		feedBack.setAccount(accountId);
		feedBack.setContactType(contactType);
		feedBack.setContactWay(contactWay);
		feedBack.setContent(content);
		feedBack.stIsHandle(false);
		feedBack.ins();
		return feedBack;
	}
}
