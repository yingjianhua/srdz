package irille.wpt.service;


import irille.pub.Log;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptPetitionCity;

public class CityService {
	public static final Log LOG = new Log(CityService.class);
	/**
	 * 添加请愿城市
	 */
	public void insOrUpd(String cityName, int account) {
		WptCity city = WptCity.chkUniqueNameAccount(false, cityName, account);
		if(city != null){
			throw LOG.err("cityIsExists", "城市已经存在");
		}
		WptPetitionCity bean = WptPetitionCity.chkUniqueName(false, cityName);
		if(bean == null){
			bean = new WptPetitionCity();
			bean.setName(cityName);
			bean.setAccount(account);
			bean.setCount(1);
			bean.setEnabled((byte)1);
			bean.ins();
		}else{
			bean.setCount(bean.getCount() + 1);
			bean.upd();
		}
		
	}
}
