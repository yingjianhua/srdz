package irille.wxpub.js;

import irille.pub.Log;

public class JMChooseCard extends JMBase<JMChooseCard> {
	public static final Log LOG = new Log(JMChooseCard.class);
	protected JsFldStr _shopIdt;
	protected JsFldStr _cardType;
	protected JsFldStr  _cardId;
	protected JsFldStr _timestamp;
	protected JsFldStr _nonceStr;
	protected JsFldStr _signType;
	protected JsFldStr _cardSign;
	public JsFldStr getShopIdt() {
		return _shopIdt;
	}
	public void setShopIdt(String shopIdt) {
		_shopIdt = new JsFldStr(shopIdt);
	}
	public void setShopIdt(JsExp exp){
		_shopIdt = new JsFldStr(exp);
	}
	public JsFldStr getCardType() {
		return _cardType;
	}
	public void setCardType(String cardType) {
		_cardType = new JsFldStr(cardType);
	}
	public void setCardType(JsExp exp) {
		_cardType = new JsFldStr(exp);
	}
	public JsFldStr getCardId() {
		return _cardId;
	}
	public void setCardId(String cardId) {
		_cardId = new JsFldStr(cardId);
	}
	public void setCardId(JsExp exp) {
		_cardId = new JsFldStr(exp);
	}
	public JsFldStr getTimestamp() {
		return _timestamp;
	}
	public void setTimestamp(String timestamp) {
		_timestamp = new JsFldStr(timestamp);
	}
	public void setTimestamp(JsExp exp) {
		_timestamp = new JsFldStr(exp);
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
	public JsFldStr getSignType() {
		return _signType;
	}
	public void setSignType(String signType) {
		_signType = new JsFldStr(signType);
	}
	public void setSignType(JsExp exp) {
		_signType = new JsFldStr(exp);
	}
	public JsFldStr getCardSign() {
		return _cardSign;
	}
	public void setCardSign(String cardSign) {
		_cardSign = new JsFldStr(cardSign);
	}
	public void setCardSign(JsExp exp) {
		_cardSign = new JsFldStr(exp);
	}
}