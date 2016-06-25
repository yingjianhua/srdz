package irille.wxpub.js;


public class JsFldStr extends JsFld {

	public JsFldStr(Object v) {
		setV(v);
	}
	
	@Override
	public String toString() {
		if (getV() instanceof String)
			return "'" + getV() + "'";
		return super.toString();
	}
}
