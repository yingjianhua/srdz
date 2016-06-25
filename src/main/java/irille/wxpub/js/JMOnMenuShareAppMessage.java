package irille.wxpub.js;

import irille.pub.Log;

public class JMOnMenuShareAppMessage extends JMBase<JMOnMenuShareAppMessage> {
	public static final Log LOG = new Log(JMOnMenuShareAppMessage.class);
	protected JsFldStr _title;
	protected JsFldStr _desc;
	protected JsFldStr _link;
	protected JsFldStr _imgUrl;
	protected JsFldStr _type;
	protected JsFldStr _dataUrl;
	public JsFldStr getDesc() {
		return _desc;
	}
	public void setDesc(String desc) {
		_desc = new JsFldStr(desc);
	}
	public JsFldStr getType() {
		return _type;
	}
	public void setType(String type) {
		_type = new JsFldStr(type);
	}
	public JsFldStr getDataUrl() {
		return _dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		_dataUrl = new JsFldStr(dataUrl);
	}
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
		_imgUrl =  new JsFldStr(imgUrl);
	}
	
}