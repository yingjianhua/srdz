package irille.wxpub.js;

import irille.pub.Log;
import irille.wx.wx.WxAccount;

public class JMCheckJsApi extends JMBase<JMCheckJsApi> {
	public static final Log LOG = new Log(JMCheckJsApi.class);
	protected JsFldStr[] _jsApiList;
	
	public JMCheckJsApi(Object...jsApis) {
		_jsApiList = new JsFldStr[jsApis.length];
		int i = 0;
		for (Object jsApi : jsApis)
			_jsApiList[i++] = new JsFldStr(jsApi);
	}
	
	public JsFldStr[] getJsApiList() {
		return _jsApiList;
	}
	
	public void setJsApiList(JsFldStr[] jsApiList) {
		_jsApiList = jsApiList;
	}
	
	public static void main(String[] args) {
		int tabs = 2;
		StringBuilder buf = new StringBuilder();
		WxAccount account = WxAccount.load(WxAccount.class, 1);
		JMCheckJsApi c = new JMCheckJsApi("onChoo","onUpd");
		c.setSuccess("chkApi", true);
		c.out(tabs, buf);
		System.out.println(buf);
	}
	
}