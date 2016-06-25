package irille.wxpub.js;

import irille.pub.ext.Ext;
import irille.pub.html.ExtDime;
import irille.pub.html.ExtExp;
import irille.pub.html.ExtList;
import irille.pub.svr.DepConstants;
import irille.pub.svr.Env;

public class JsFunDefine<THIS extends ExtList> extends ExtList<JsFunDefine>{
	private String _name;
	private ExtDime _paras;
	private JsFunBody _body;
	private String _path = this.getClass().getClassLoader().getResource("").getPath().replace("WEB-INF" + DepConstants.FILE_SEPA + "classes" + DepConstants.FILE_SEPA, "js" + DepConstants.FILE_SEPA + "jsfuncode.js");
	
	public JsFunDefine() {
	}
	
	public JsFunDefine(String name, String...paras) {
		_name = name;
		int i = 0;
		ExtExp[] exps = new ExtExp[paras.length];
		for (String para : paras)
			exps[i++] = new ExtExp(para);
		_paras = new ExtDime(exps);
		_paras.setCloseStrToParas();
	}
	
	public String getPath() {
		return _path;
	}

	public JsFunDefine setPath(String path) {
		_path = path;
		return this;
	}
	
	public JsFunDefine AddFunBody(String name, String...params) {
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
		String dn = "function " + _name; 
		buf.append(isTabs() ? Env.LN + T[tabs] + dn : dn);
		_paras.out(tabs, buf);
		
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
	}
	
	public static void main(String[] args) {
		JsFunDefine fun = new JsFunDefine("upload","h123", "hhee22");
		StringBuilder buf = new StringBuilder();
		int tabs = 2;
		JMChooseImage ci = new JMChooseImage();
		ci.setCount("hahah");
		ci.stSizeType("whx", "sgxy");
		ci.setSuccess(new JsFldFun("image", false));
		ci.setCancel(new JsFldFun("cccccc"));
		fun.add(new JsFldDefine("id", "1"));
		fun.add(new JsFldDefine("id2").setExp("1"));
		fun.add(new JsFldDefine("id3").setDime());
		fun.add(new JsFldDefine("id4").setDime("abc","ssss"));
		fun.add(ci);
		fun.AddFunBody("image", "hahaha");
		fun.out(2, buf);
		System.out.println(buf);
	}
}
