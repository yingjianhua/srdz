package irille.wpt.service.impl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.Log;
import irille.wpt.bean.City;
import irille.wpt.bean.CityLine;
import irille.wpt.dao.impl.CityDao;
import irille.wpt.dao.impl.CityLineDao;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptPetitionCity;
@Service
public class CityService {
	public static final Log LOG = new Log(CityService.class);
	
	@Resource
	private CityDao cityDao;
	@Resource
	private CityLineDao cityLineDao;
	
	public void save(City city, List<CityLine> listLine, Integer accountId) {
		city.setAccount(accountId);
		cityDao.save(city);
		for(CityLine cityLine:listLine) {
			cityLine.setAccount(accountId);
			cityLine.setCity(city);
			cityLineDao.save(cityLine);
		}
	}
	/**
	 * 添加请愿城市
	 */
	public void insOrUpd(String cityName, int account) {
		if(cityName == null) {
			return ;
		}
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
	public List<City> search(Integer accountId) {
		return cityDao.search(accountId);
	}
	
	public City findByName(String name, Integer accountId) {
		return cityDao.findByName(name, accountId);
	}
}
