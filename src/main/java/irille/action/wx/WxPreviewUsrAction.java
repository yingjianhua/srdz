package irille.action.wx;

import irille.action.ActionWx;
import irille.pub.bean.Bean;
import irille.wx.wx.WxPreviewUsr;
import irille.wx.wx.WxUser;

import org.json.JSONObject;

public class WxPreviewUsrAction extends ActionWx<WxPreviewUsr, WxPreviewUsrAction> {
	public WxPreviewUsr getBean() {
		return _bean;
	}

	public void setBean(WxPreviewUsr bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WxPreviewUsr.class;
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
