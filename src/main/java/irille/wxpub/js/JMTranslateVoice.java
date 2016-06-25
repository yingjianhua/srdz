package irille.wxpub.js;

import irille.pub.Log;

public class JMTranslateVoice extends JMBase<JMTranslateVoice> {
	public static final Log LOG = new Log(JMTranslateVoice.class);
	protected JsFldStr _serverId;
	protected JsFldBoolean _isShowProgressTips;
	public JsFldStr getServerId() {
		return _serverId;
	}
	public void setServerId(String serverId) {
		_serverId = new JsFldStr(serverId);
	}
	public void setServerId(JsExp exp) {
		_serverId = new JsFldStr(exp);
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