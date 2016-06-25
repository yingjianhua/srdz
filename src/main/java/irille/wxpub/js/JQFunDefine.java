package irille.wxpub.js;

import irille.pub.ext.Ext;
import irille.pub.html.ExtList;
import irille.pub.svr.DepConstants;
import irille.pub.svr.Env;

public class JQFunDefine extends ExtList<JQFunDefine>{
	private String _name;
	private String _event;
	private JsFunBody _body;
	private String _path = this.getClass().getClassLoader().getResource("").getPath().replace("WEB-INF" + DepConstants.FILE_SEPA + "classes" + DepConstants.FILE_SEPA, "js" + DepConstants.FILE_SEPA + "jsfuncode.js");

	public JQFunDefine(String name, String event) {
		_name = name;
		_event = event;
	}
	
	public String getPath() {
		return _path;
	}
	
	public JQFunDefine setPath(String path) {
		_path = path;
		return this;
	}
	
	public JQFunDefine AddFunBody(String name, String...params) {
		_body = new JsFunBody(getPath(), name, params);
		add(_body);
		return this;
	}
	
	@Override
	public String outSeparator() {
		return ";";
	}
	
	/**
	 * 输出
	 */
	@Override
	public void out(int tabs, StringBuilder buf) {
		String dn = "$(\"" + _name + "\")." + _event + "(function() "; 
		buf.append(isTabs() ? Env.LN + T[tabs] + dn : dn);
		
		tabs++;
		buf.append(outBefore());
		String s = (isTabs() ? Env.LN + T[tabs] : "");
		for (Object ext : getNodes()) {
			buf.append(s);
			Ext.outObject(ext, tabs, buf);
			if (!(ext instanceof JMBase) && !ext.equals(getNodes().lastElement()))
				buf.append(outSeparator());
		}
		if (isTabs())
			buf.append(LN + T[tabs > 0 ? tabs - 1 : 0] + outAfter());
		else
			buf.append(outAfter() + LN + T[tabs > 0 ? tabs - 1 : 0]);
		buf.append(");");
	}
	
	public static void main(String[] args) {
		JQFunDefine fun = new JQFunDefine("#aaa","click");
		StringBuilder buf = new StringBuilder();
		int tabs = 2;
		JMChooseImage ci = new JMChooseImage();
		ci.setCount(1);
		ci.stSizeType("whx", "sgxy");
		ci.setSuccess(new JsFldFun("image", false));
		ci.setCancel(new JsFldFun("cccccc"));
		fun.add(ci);
		fun.out(2, buf);
		System.out.println(buf);
	}
}
