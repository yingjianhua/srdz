package irille.wxpub.js;

import irille.pub.Log;

public class JMStartSearchBeacons extends JMBase<JMStartSearchBeacons> {
	public static final Log LOG = new Log(JMStartSearchBeacons.class);
	protected JsFldStr _ticket;
	public JsFldStr getTicket() {
		return _ticket;
	}
	public void setTicket(String ticket) {
		_ticket = new JsFldStr(ticket);
	}
	public void setTicket(JsExp exp) {
		_ticket = new JsFldStr(exp);
	}
	
	
}