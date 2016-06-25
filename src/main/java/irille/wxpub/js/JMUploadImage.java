package irille.wxpub.js;

import irille.pub.Log;

public class JMUploadImage extends JMBase<JMUploadImage> {
	public static final Log LOG = new Log(JMUploadImage.class);
	protected JsFldStr _localId;
	protected JsFldBoolean _isShowProgressTips;
	public JsFldStr getLocalId() {
		return _localId;
	}
	public void setLocalId(String localId) {
		_localId = new JsFldStr(localId);
	}
	public void setLocalId(JsExp exp) {
		_localId = new JsFldStr(exp);
	}
	public JsFldBoolean getIsShowProgressTips() {
		return _isShowProgressTips;
	}
	public void setIsShowProgressTips(boolean isShowProgressTips) {
		_isShowProgressTips = new JsFldBoolean(isShowProgressTips);
	}
	public void setIsShowProgressTips(String exp) {
		_isShowProgressTips = new JsFldBoolean(exp);
	}
	
	public static void main(String[] args) {
		JMUploadImage model = new JMUploadImage();
		model.setLocalId("aaa");
		model.setIsShowProgressTips(false);
		StringBuilder buf = new StringBuilder();
		int tabs = 1;
		model.out(tabs, buf);
		System.out.println(buf);
	}
}