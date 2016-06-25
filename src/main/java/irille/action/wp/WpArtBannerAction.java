package irille.action.wp;

import irille.action.ActionWx;
import irille.wx.wp.WpArtBanner;;

public class WpArtBannerAction extends ActionWx<WpArtBanner,WpArtBannerAction> {
	public WpArtBanner getBean() {
		return _bean;
	}

	public void setBean(WpArtBanner bean) {
		this._bean = bean;
	}
	
	@Override
	public Class beanClazz() {
		return WpArtBanner.class;
	}
}
