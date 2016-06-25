package irille.wxpub.js;

import irille.pub.Log;

public class JMStopVoice extends JMBase<JMStopVoice> {
	public static final Log LOG = new Log(JMStopVoice.class);
	protected JsFldStr _localId;
	public JsFldStr getLocalId() {
		return _localId;
	}
	public void setLocalId(String localId) {
		_localId = new JsFldStr(localId);
	}
	public void setLocalId(JsExp exp) {
		_localId = new JsFldStr(exp);
	}
	
}