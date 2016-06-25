package irille.wxpub.js;

import irille.pub.Log;

public class JMCloseWindow extends JMBase<JMCloseWindow> {
	public static final Log LOG = new Log(JMCloseWindow.class);
	
	/**
	 * 输出
	 */
	@Override
	public void out(int tabs, StringBuilder buf) {
		buf.append("wx." + getDefineName() + "();");
	}
	
	public static void main(String[] args) {
		StringBuilder buf = new StringBuilder();
		JMCloseWindow cw = new JMCloseWindow();
		cw.out(1, buf);
		System.out.println(buf);
	}
}