package irille.wxpub.js;

import irille.pub.Log;

public class JMOnMenuShareTimeline extends JMBase<JMOnMenuShareTimeline> {
	public static final Log LOG = new Log(JMOnMenuShareTimeline.class);
	protected JsFldStr _title;
	protected JsFldStr _link;
	protected JsFldStr _imgUrl;
	
	public JsFldStr getTitle() {
		return _title;
	}
	public void setTitle(String title) {
		_title = new JsFldStr(title);
	}
	public JsFldStr getLink() {
		return _link;
	}
	public void setLink(String link) {
		_link = new JsFldStr(link);
	}
	public JsFldStr getImgUrl() {
		return _imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		_imgUrl = new JsFldStr(imgUrl);
	}
	
}