package irille.wxpub.js;

import irille.pub.FileTools;
import irille.pub.svr.DepConstants;

public class JsFldFun extends JsFld {
	private String _path = this.getClass().getClassLoader().getResource("").getPath().replace("WEB-INF" + DepConstants.FILE_SEPA + "classes" + DepConstants.FILE_SEPA, "js" + DepConstants.FILE_SEPA + "wxfuncode.js");
	
	public JsFldFun(String v) {
		setV(v);
	}
	
	public JsFldFun(String v, boolean isCode, String...params) {
		setV(v);
		if (isCode)
			setV(getCode(params));
	}
	
	public String getPath() {
		return _path;
	}
	
	public JsFldFun setPath(String path) {
		_path = path;
		return this;
	}
	
	/**
	 * 从指定文件中获取指定代码段
	 * @param fileName 文件名
	 * @param name 标记名称
	 * @param params 替换参数
	 * @return
	 */
	private String getCode(String...params) {
		String lns = FileTools.loadFileLines(getPath(), "/** Begin " + getV() + " **/", "/** End " + getV() + " **/");
		int i = 0;
		if (params != null)
			for (String param : params)
				lns = lns.replaceAll("【" + (i++) + "】", param);
		return lns;
	}
}
