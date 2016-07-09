package irille.action.wpt;

import java.util.ArrayList;
import java.util.List;

import irille.action.ActionBase;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptComboBanner;

public class WptComboBannerAction extends ActionBase<WptComboBanner> {
	private List<WptComboBanner> _bannerList;

	public WptComboBanner getBean() {
		return _bean;
	}

	public void setBean(WptComboBanner bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptComboBanner.class;
	}

	public List<WptComboBanner> getBannerList() {
		return _bannerList;
	}

	public void setBannerList(List<WptComboBanner> _bannerList) {
		this._bannerList = _bannerList;
	}
	/**
	 * 新增或删除
	 */
	public void insOrUpd(){
		WptCombo combo = WptCombo.load(WptCombo.class, getBean().getPkey());
		List<WptComboBanner> bannerList = getBannerList();
		if(bannerList == null)
			bannerList = new ArrayList();
//		for(WptRestaurantBanner banner : bannerList){
//			banner.setAccount(res.getAccount());
//		}
		for(int i=0; i<bannerList.size(); i++){
			bannerList.get(i).setAccount(combo.getAccount());
			bannerList.get(i).setSort(i);
		}
		Idu.updLine(combo, bannerList, WptComboBanner.T.COMBO.getFld());
	}
}
