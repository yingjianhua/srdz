package irille.action.wpt;

import java.util.ArrayList;
import java.util.List;

import irille.action.ActionBase;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptRestaurantBanner;

public class WptRestaurantBannerAction extends ActionBase<WptRestaurantBanner> {
	private List<WptRestaurantBanner> _bannerList;

	public WptRestaurantBanner getBean() {
		return _bean;
	}

	public void setBean(WptRestaurantBanner bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptRestaurantBanner.class;
	}

	public List<WptRestaurantBanner> getBannerList() {
		return _bannerList;
	}

	public void setBannerList(List<WptRestaurantBanner> _bannerList) {
		this._bannerList = _bannerList;
	}
	/**
	 * 新增或删除
	 */
	public void insOrUpd(){
		WptRestaurant res = WptRestaurant.load(WptRestaurant.class, getBean().getPkey());
		List<WptRestaurantBanner> bannerList = getBannerList();
		if(bannerList == null)
			bannerList = new ArrayList();
//		for(WptRestaurantBanner banner : bannerList){
//			banner.setAccount(res.getAccount());
//		}
		for(int i=0; i<bannerList.size(); i++){
			bannerList.get(i).setAccount(res.getAccount());
			bannerList.get(i).setSort(i);
		}
		Idu.updLine(res, bannerList, WptRestaurantBanner.T.RESTAURANT.getFld());
	}
}
