package irille.wxpub.js;

import irille.pub.ext.Ext;
import irille.pub.svr.Env;

public class JMError extends JsFunDefine<JMError> {
	
	public JMError() {
	}

	public JMError(String name, String[] paras) {
		super(name, paras);
	}
	
	private String getClazz() {
		return getClass().toString().split("\\.")[3];
	}
	
	private String getDefineName() {
		String funName = getClazz().replace("JM", "");
		return funName.substring(0, 1).toLowerCase() + funName.replaceFirst("\\w", "");
	}
	
	@Override
	public String outSeparator() {
		return ";";
	}
	
	@Override
	public void out(int tabs, StringBuilder buf) {
		String dn = "wx." + getDefineName() + "(function(res) ";
		buf.append(isTabs() ? Env.LN + T[tabs] + dn : dn);
		tabs++;
		buf.append(outBefore());
		String s = (isTabs() ? Env.LN + T[tabs] : "");
		for (Object ext : getNodes()) {
			buf.append(s);
			Ext.outObject(ext, tabs, buf);
			if (ext instanceof JsFldDefine && !ext.equals(getNodes().lastElement()))
				buf.append(outSeparator());
		}
		if (isTabs())
			buf.append(LN + T[tabs > 0 ? tabs - 1 : 0] + outAfter());
		else
			buf.append(outAfter() + LN + T[tabs > 0 ? tabs - 1 : 0]);
		buf.append(");");
	}
}
