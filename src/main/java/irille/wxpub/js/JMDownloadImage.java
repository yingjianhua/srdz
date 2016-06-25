package irille.wxpub.js;

import irille.pub.Log;

public class JMDownloadImage extends JMBase<JMDownloadImage> {
	public static final Log LOG = new Log(JMDownloadImage.class);
	protected JsFldStr _serverId;
	protected JsFldInt _isShowProgressTips;
	public JsFldStr getServerId() {
		return _serverId;
	}
	public void setServerId(String serverId) {
		_serverId = new JsFldStr(serverId);
	}
	public void setServerId(JsExp exp) {
		_serverId = new JsFldStr(exp);
	}
	public JsFldInt getIsShowProgressTips() {
		return _isShowProgressTips;
	}
	public void setIsShowProgressTips(int isShowProgressTips) {
		_isShowProgressTips = new JsFldInt(isShowProgressTips);
	}
	public void setIsShowProgressTips(String exp) {
		_isShowProgressTips = new JsFldInt(exp);
	}
	
	
}