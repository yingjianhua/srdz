package irille.wpt.pay;

public class WXPay {
	private String payAppId;
	private String payTimestamp;
	private String payNonceStr;
	private String payPackage;
	private String paySignType;
	private String payPaySign;
	
	public String getPayAppId() {
		return payAppId;
	}
	public void setPayAppId(String payAppId) {
		this.payAppId = payAppId;
	}
	public String getPayTimestamp() {
		return payTimestamp;
	}
	public void setPayTimestamp(String payTimestamp) {
		this.payTimestamp = payTimestamp;
	}
	public String getPayNonceStr() {
		return payNonceStr;
	}
	public void setPayNonceStr(String payNonceStr) {
		this.payNonceStr = payNonceStr;
	}
	public String getPayPackage() {
		return payPackage;
	}
	public void setPayPackage(String payPackage) {
		this.payPackage = payPackage;
	}
	public String getPaySignType() {
		return paySignType;
	}
	public void setPaySignType(String paySignType) {
		this.paySignType = paySignType;
	}
	public String getPayPaySign() {
		return payPaySign;
	}
	public void setPayPaySign(String payPaySign) {
		this.payPaySign = payPaySign;
	}
	
}
