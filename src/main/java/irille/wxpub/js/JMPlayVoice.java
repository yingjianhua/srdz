package irille.wxpub.js;

import irille.pub.Log;

public class JMPlayVoice extends JMBase<JMPlayVoice> {
	public static final Log LOG = new Log(JMPlayVoice.class);
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