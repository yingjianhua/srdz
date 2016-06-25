package irille.wxpub.js;

import irille.pub.ext.Ext;
import irille.pub.ext.Ext.IExtOut;
import irille.pub.html.ExtAttr;
import irille.pub.html.ExtDime;

public class JsFldDefine extends ExtAttr {

	public JsFldDefine(String name) {
		super(name);
	}
	
	public JsFldDefine(String name, Object value) {
		super(name, value);
	}
	
	/**
	 * 置表达式，在输出时两端不带“‘”
	 * @param value
	 * @return
	 */
	@Override
	public JsFldDefine setExp(String value) {
		return (JsFldDefine) super.setExp(value);
	}
	
	/**
	 * 将置设为数组，数组由“[”“]”括起来，以“,”分隔
	 * @param exps
	 * @return
	 */
	public JsFldDefine setDime(Object... exps) {
		this.SetDime(exps);
		return this;
	}
	
	@Override
	public void out(int tabs,StringBuilder buf) {
		if(get() instanceof IExtOut) {
			buf.append("var " + getName() + " = ");
			((IExtOut)get()).out(tabs,buf);
			return;
		}
		buf.append("var " + getName() + " = ");
		Ext.outObject(get(), tabs, buf);
	}

}
