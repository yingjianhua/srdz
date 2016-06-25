package irille.wxpub.js;

public class JsFldBoolean extends JsFld {

	public JsFldBoolean(boolean v) {
		setV(v ? String.valueOf(1) : String.valueOf(0));
	}
	
	public JsFldBoolean(String v) {
		setV(v);
	}
}
