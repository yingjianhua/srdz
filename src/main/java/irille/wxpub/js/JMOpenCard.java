package irille.wxpub.js;

import irille.pub.Log;

public class JMOpenCard extends JMBase<JMOpenCard> {
	public static final Log LOG = new Log(JMOpenCard.class);
	protected JsFldStr[] _sizeType;
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
}