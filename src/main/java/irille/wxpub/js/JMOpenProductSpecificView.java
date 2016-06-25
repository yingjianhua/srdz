package irille.wxpub.js;

import irille.pub.Log;

public class JMOpenProductSpecificView extends JMBase<JMOpenProductSpecificView> {
	public static final Log LOG = new Log(JMOpenProductSpecificView.class);
	protected JsFldStr _productId;
	protected JsFldStr _viewType;
	public JsFldStr getProductId() {
		return _productId;
	}
	public void setProductId(String productId) {
		_productId = new JsFldStr(productId);
	}
	public void setProductId(JsExp exp) {
		_productId = new JsFldStr(exp);
	}
	public JsFldStr getViewType() {
		return _viewType;
	}
	public void setViewType(String viewType) {
		_viewType =  new JsFldStr(viewType);
	}
	public void setViewType(JsExp exp) {
		_viewType =  new JsFldStr(exp);
	}
}