package irille.wxpub.js;

import irille.pub.Log;

public class JMOnMenuShareQQ extends JMBase<JMOnMenuShareQQ> {
	public static final Log LOG = new Log(JMOnMenuShareQQ.class);
	protected JsFldStr _title;
	protected JsFldStr _desc;
	protected JsFldStr _link;
	protected JsFldStr _imgUrl;
	public JsFldStr getDesc() {
		return _desc;
	}
	public void setDesc(String desc) {
		_desc = new JsFldStr(desc);
	}
	public void setDesc(JsExp exp) {
		_desc = new JsFldStr(exp);
	}
	public JsFldStr getTitle() {
		return _title;
	}
	public void setTitle(String title) {
		_title = new JsFldStr(title);
	}
	public void setTitle(JsExp exp) {
		_title = new JsFldStr(exp);
	}
	public JsFldStr getLink() {
		return _link;
	}
	public void setLink(String link) {
		_link = new JsFldStr(link);
	}
	public void setLink(JsExp exp) {
		_link = new JsFldStr(exp);
	}
	public JsFldStr getImgUrl() {
		return _imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		_imgUrl = new JsFldStr(imgUrl);
	}
	public void setImgUrl(JsExp exp) {
		_imgUrl = new JsFldStr(exp);
	}
	
}