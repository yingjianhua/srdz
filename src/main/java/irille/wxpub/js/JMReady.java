package irille.wxpub.js;

import irille.pub.ext.Ext;
import irille.pub.html.ExtList;
import irille.pub.svr.Env;

public class JMReady extends JsFunDefine<JMReady> {
	
	public JMReady() {
	}
	
	public JMReady(String name, String[] paras) {
		super(name, paras);
	}

	private String getClazz() {
		return getClass().toString().split("\\.")[3];
	}
	
	private String getDefineName() {
		String funName = getClazz().replace("JM", "");
		return funName.substring(0, 1).toLowerCase() + funName.replaceFirst("\\w","");
	}
	
	@Override
	public String outSeparator() {
		return ";";
	}
	
	@Override
	public void out(int tabs, StringBuilder buf) {
		String dn = "wx." + getDefineName() + "(function() ";
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
	
	public static void main(String[] args) {
		JMReady ready = new JMReady();
		JsFunDefine fun = new JsFunDefine("upload","h123");
		StringBuilder buf = new StringBuilder();
		int tabs = 2;
		JMChooseImage ci = new JMChooseImage();
		ci.setCount(1);
		ci.stSizeType("whx", "sgxy");
		ci.setSuccess(new JsFldFun("image", false));
		ci.setCancel(new JsFldFun("cccccc"));
		fun.add(ci);
		fun.AddFunBody("image", "hahaha");
		ready.add(ci);
		ready.add(fun);
		ready.out(tabs, buf);
		System.out.println(buf);
	}

}
