package irille.wxpub.js;

import irille.pub.Log;

public class JMChooseWXPay extends JMBase<JMChooseWXPay> {
	public static final Log LOG = new Log(JMChooseWXPay.class);
	protected JsFldInt _timestamp;
	protected JsFldStr _nonceStr;
	protected JsFldStr _package;
	protected JsFldStr _signType;
	protected JsFldStr _paySign;
	public JsFldInt getTimestamp() {
		return _timestamp;
	}
	public void setTimestamp(int timestamp) {
		_timestamp = new JsFldInt(timestamp);
	}
	public void setTimestamp(String exp) {
		_timestamp = new JsFldInt(exp);
	}
	public JsFldStr getNonceStr() {
		return _nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		_nonceStr = new JsFldStr(nonceStr);
	}
	public void setNonceStr(JsExp exp) {
		_nonceStr = new JsFldStr(exp);
	}
	public JsFldStr getPackage() {
		return _package;
	}
	public void setPackage(String package1) {
		_package = new JsFldStr(package1);
	}
	public void setPackage(JsExp exp) {
		_package = new JsFldStr(exp);
	}
	public JsFldStr getSignType() {
		return _signType;
	}
	public void setSignType(String signType) {
		_signType = new JsFldStr(signType);
	}
	public void setSignType(JsExp exp) {
		_signType = new JsFldStr(exp);
	}
	public JsFldStr getPaySign() {
		return _paySign;
	}
	public void setPaySign(String paySign) {
		_paySign = new JsFldStr(paySign);
	}
	public void setPaySign(JsExp exp) {
		_paySign = new JsFldStr(exp);
	}
    
}