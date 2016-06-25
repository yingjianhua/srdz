package irille.wxpub.js;

import irille.pub.Log;

public class JMChooseImage extends JMBase<JMChooseImage> {
	public static final Log LOG = new Log(JMChooseImage.class);
	protected JsFldInt _count;
	protected JsFldStr[] _sizeType;
	protected JsFldStr[] _sourceType;

	public JsFldInt getCount() {
		return _count;
	}

	public void setCount(int count) {
		this._count = new JsFldInt(count);
	}
	
	public void setCount(String exp) {
		this._count = new JsFldInt(exp);
	}

	public JsFldStr[] getSizeType() {
		return _sizeType;
	}

	public void setSizeType(JsFldStr[] sizeType) {
		this._sizeType = sizeType;
	}

	public void stSizeType(Object... sizeType) {
		JsFldStr[] arr = new JsFldStr[sizeType.length];
		int i = 0;
		for (Object obj : sizeType)
			arr[i++] = new JsFldStr(obj);
		this._sizeType = arr;
	}

	public JsFldStr[] getSourceType() {
		return _sourceType;
	}

	public void setSourceType(JsFldStr[] sourceType) {
		this._sourceType = sourceType;
	}

	public void stSourceType(Object... sourceType) {
		JsFldStr[] arr = new JsFldStr[sourceType.length];
		int i = 0;
		for (Object obj : sourceType)
			arr[i++] = new JsFldStr(obj);
		this._sizeType = arr;
	}

	public static void main(String[] args) {
		int tabs = 2;
		StringBuilder buf = new StringBuilder();
		JMChooseImage ci = new JMChooseImage();
		ci.setCount("imgCount");
		ci.stSizeType("whx", new JsExp("wwwdd"), "sgxy");
		ci.setSuccess(new JsFldFun("image", false));
		ci.setCancel(new JsFldFun("cccccc"));
		ci.out(tabs, buf);
		System.out.println(buf);
	}
}