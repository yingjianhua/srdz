package irille.wxpub.js;

import irille.pub.Log;

public class JMGetLocation extends JMBase<JMGetLocation> {
	public static final Log LOG = new Log(JMGetLocation.class);
	protected JsFldStr _type;
	public JsFldStr getType() {
		return _type;
	}
	public void setType(String type) {
		_type = new JsFldStr(type);
	}
	public void setType(JsExp exp) {
		_type = new JsFldStr(exp);
	}
	
}