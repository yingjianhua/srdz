package irille.wxpub.js;

import irille.pub.Log;

public class JMPreviewImage extends JMBase<JMPreviewImage> {
	public static final Log LOG = new Log(JMPreviewImage.class);
	protected JsFldStr _current;
	protected JsFldStr[] _urls;
	public JsFldStr getCurrent() {
		return _current;
	}
	public void setCurrent(String current) {
		_current = new JsFldStr(current);
	}
	public void setCurrent(JsExp exp) {
		_current = new JsFldStr(exp);
	}
	public JsFldStr[] getUrls() {
		return _urls;
	}
	public void setUrls(JsFldStr[] urls) {
		_urls = urls;
	}
	public void stUrls(Object... urls) {
		JsFldStr[] arr = new JsFldStr[urls.length];
		int i = 0;
		for (Object obj : urls)
			arr[i++] = new JsFldStr(obj);
		this._urls = arr;
	}
	
}