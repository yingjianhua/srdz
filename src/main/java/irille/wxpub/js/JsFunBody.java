package irille.wxpub.js;

import irille.pub.FileTools;
import irille.pub.html.Exts.ExtBase;

public class JsFunBody extends ExtBase {
	private String _content;
	
	public JsFunBody(String path, String name, String...params) {
		_content = FileTools.loadFileLines(path, "/** Begin " + name + " **/", "/** End " + name + " **/");
		int i = 0;
		if (params != null)
			for (String param : params)
				_content = _content.replaceAll("【" + (i++) + "】", param);
	}

	@Override
	public void out(int tabs, StringBuilder buf) {
		buf.append(_content.replace(LN, LN + T[tabs]));
	}

}
