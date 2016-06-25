package irille.action.wp;

import irille.action.ActionWx;
import irille.core.sys.Sys;
import irille.core.sys.Sys.OEnabled;
import irille.core.sys.Sys.OYn;
import irille.wx.js.JsMenuShare;
import irille.wx.js.Js.OJsMenuType;
import irille.wx.wp.WpArtTheme;

import java.util.List;

public class WpArtThemeAction extends ActionWx<WpArtTheme,WpArtThemeAction> {
	public WpArtTheme getBean() {
		return _bean;
	}

	public void setBean(WpArtTheme bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WpArtTheme.class;
	}
}
