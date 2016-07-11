package irille.action.wpt;

import org.json.JSONObject;

import irille.action.ActionWx;
import irille.pub.bean.Bean;
import irille.wx.wpt.WptWxTips;
import irille.wx.wx.WxUser;

public class WptWxTipsAction extends ActionWx<WptWxTips, WptWxTipsAction> {
	public WptWxTips getBean() {
		return _bean;
	}

	public void setBean(WptWxTips bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptWxTips.class;
	}
	
	@Override
	public JSONObject crtJsonExt(JSONObject json, Bean bean, String pref)
			throws Exception {
		WxUser user = Bean.load(WxUser.class, bean.getPkey());
		json.put(pref+"nickname", user.getNickname());
		json.put(pref+"imageUrl", user.getImageUrl());
		json.put(pref+"rem", user.getRem());
		json.put(pref+"status", user.getStatus());
		return super.crtJsonExt(json, bean, pref);
	}

}
