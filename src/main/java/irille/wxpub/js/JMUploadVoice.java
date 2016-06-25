package irille.wxpub.js;

import irille.pub.Log;

public class JMUploadVoice extends JMBase<JMUploadVoice> {
	public static final Log LOG = new Log(JMUploadVoice.class);
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
}