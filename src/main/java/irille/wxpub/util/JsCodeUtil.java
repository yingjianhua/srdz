package irille.wxpub.util;

import irille.pub.FileTools;
import irille.pub.Log;

public class JsCodeUtil {
	private static final Log LOG = new Log(JsCodeUtil.class);
	private static final String WX_JS_FILE = "/js/wxfun.js";
	
	public static String getImageFun(int count) {
		return loadFunInline(WX_JS_FILE, "image", String.valueOf(count));
	}
	
//	public static String getImageFun(int count, String sizeType)
	
	/**
	 * 从指定文件中获取指定代码段
	 * @param fileName 文件名
	 * @param name 标记名称
	 * @param params 替换参数
	 * @return
	 */
	public static String loadFunCode(String fileName, String name, String...params) {
		String lns = FileTools.loadFileLines(fileName, "/** Begin " + name + " **/", "/** End " + name + " **/");
		int i = 0;
		for (String param : params) {
			lns = lns.replaceAll("【" + (i++) + "】", param);
		}
		return lns;
	}
	
	/**
	 * 从指定文件中获取指定代码段并压缩成一行
	 * @param fileName 文件名
	 * @param name 标记名称
	 * @param params 替换参数
	 * @return
	 */
	public static String loadFunInline(String fileName, String name, String...params) {
		return delLn(loadFunCode(fileName, name, params));
	}
	
	/**
	 * 去除所有换行
	 * @param lns
	 * @return
	 */
	public static String delLn(String lns) {
		return lns.replace("\r", "").replace("\n", "");
	}
	
}
