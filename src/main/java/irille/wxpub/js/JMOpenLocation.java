package irille.wxpub.js;

import irille.pub.Log;

public class JMOpenLocation extends JMBase<JMOpenLocation> {
	public static final Log LOG = new Log(JMOpenLocation.class);
	protected JsFldFloat _latitude;
	protected JsFldFloat _longitude;
	protected JsFldStr _name;
	protected JsFldStr _address;
	protected JsFldInt _scale;
	protected JsFldStr _infoUrl;
	public JsFldFloat getLatitude() {
		return _latitude;
	}
	public void setLatitude(JsFldFloat latitude) {
		_latitude = latitude;
	}
	public void setLatitude(String latitude) {
		_latitude = new JsFldFloat(latitude);
	}
	public JsFldFloat getLongitude() {
		return _longitude;
	}
	public void setLongitude(JsFldFloat longitude) {
		_latitude = longitude;
	}
	public void setLongitude(String longitude) {
		_longitude = new JsFldFloat(longitude);
	}
	public JsFldStr getName() {
		return _name;
	}
	public void setName(String name) {
		_name = new JsFldStr(name);
	}
	public void setName(JsExp exp) {
		_name = new JsFldStr(exp);
	}
	public JsFldStr getAddress() {
		return _address;
	}
	public void setAddress(String address) {
		_address = new JsFldStr(address);
	}
	public void setAddress(JsExp exp) {
		_address = new JsFldStr(exp);
	}
	public JsFldInt getScale() {
		return _scale;
	}
	public void setScale(int scale) {
		_scale = new JsFldInt(scale);
	}
	public void setScale(String exp) {
		_scale = new JsFldInt(exp);
	}
	public JsFldStr getInfoUrl() {
		return _infoUrl;
	}
	public void setInfoUrl(String infoUrl) {
		_infoUrl = new JsFldStr(infoUrl);
	}
	public void setInfoUrl(JsExp exp) {
		_infoUrl = new JsFldStr(exp);
	}
	
	public static void main(String[] args) {
		JMOpenLocation ol = new JMOpenLocation();
		ol.setLatitude("parseFloat($(this).children().attr('latitude'))");
		ol.setLongitude("parseFloat($(this).children().attr('longitude'))");
		ol.setName(new JsExp("$(this).children().attr('name')"));
		ol.setAddress(new JsExp("$(this).children().attr('address')"));
		ol.setScale(14);
		ol.setInfoUrl("http://baidu.com");
		StringBuilder buf = new StringBuilder();
		ol.out(1, buf);
		System.out.println(buf);
	}
	
}