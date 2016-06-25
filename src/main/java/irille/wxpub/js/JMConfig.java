package irille.wxpub.js;

import java.util.Date;

import irille.pub.Log;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wxpub.util.Crypto;
import irille.wxpub.util.WeixinUtil;

public class JMConfig extends JMBase<JMConfig> {
	public static final Log LOG = new Log(JMConfig.class);
	protected boolean _debug;
	protected JsFldStr _appId;
	protected long _timestamp;
	protected JsFldStr _nonceStr;
	protected JsFldStr _signature;
	protected JsFldStr[] _jsApiList;

	public JMConfig(WxAccount account, String url, Object...jsApis) {
		_debug = false;
		_appId = new JsFldStr(account.getAccountAppid());
		_timestamp = tranDate(account.getAccessTime());
		_nonceStr = new JsFldStr(Crypto.encrypt(account.getJsapiTicket()).substring(0, 16));
		_signature = new JsFldStr(crtSignature(account, url));
		_jsApiList = new JsFldStr[jsApis.length];
		int i = 0;
		for (Object jsApi : jsApis)
			_jsApiList[i++] = new JsFldStr(jsApi);
	}
	
	public JMConfig setDebug(boolean debug) {
		this._debug = debug;
		return this;
	}
	
	/**
	 * 将java中的Date对象转换为微信的10位时间
	 * */
	private static long tranDate(Date date) {
		return Long.parseLong((date.getTime()+"").substring(0, 10));
	}
	
	@Override
	public void out(int tabs, StringBuilder buf) {
		super.out(tabs, buf);
	}
	
	/**
	 * 创建签名
	 * @param account
	 * @param url
	 * @return
	 */
	private String crtSignature(WxAccount account, String url) {
		String str = WeixinUtil.JS_SGIN_STR.replace("JSAPI_TICKET", WxAccountDAO.getJsapiTicket(account))
				.replace("NONCESTR", _nonceStr.getV().toString())
				.replace("TIMESTAMP", String.valueOf(_timestamp))
				.replace("URL", url);
		return Crypto.getSha1(str);
	}
	public static void main(String[] args) {
		int tabs = 2;
		StringBuilder buf = new StringBuilder();
		WxAccount account = WxAccount.load(WxAccount.class, 1);
		JMConfig c = new JMConfig(account, "http://xsgdev.wicp.net/wa/01/signUp.html", "onChoo","onUpd").setDebug(true);
		c.out(tabs, buf);
		System.out.println(buf);
	}
	

}
