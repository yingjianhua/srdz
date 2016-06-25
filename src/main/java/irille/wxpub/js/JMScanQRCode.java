package irille.wxpub.js;

import irille.pub.Log;

public class JMScanQRCode extends JMBase<JMScanQRCode> {
	public static final Log LOG = new Log(JMScanQRCode.class);
	protected JsFldInt _needResult;
	protected JsFldStr[] _scanType;
	public JsFldInt getNeedResult() {
		return _needResult;
	}
	public void setNeedResult(int needResult) {
		_needResult = new JsFldInt(needResult);
	}
	public void setNeedResult(String exp) {
		_needResult = new JsFldInt(exp);
	}
	public JsFldStr[] getScanType() {
		return _scanType;
	}
	public void setScanType(JsFldStr[] scanType) {
		_scanType = scanType;
	}
	public void stScanType(Object... scanType) {
		JsFldStr[] arr = new JsFldStr[scanType.length];
		int i = 0;
		for (Object obj : scanType)
			arr[i++] = new JsFldStr(obj);
		this._scanType = arr;
	}
	

}