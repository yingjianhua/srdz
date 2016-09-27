package irille.wpt.service.impl;

import org.springframework.stereotype.Service;

import irille.pub.Log;
import irille.wpt.bean.City;
import irille.wpt.bean.PetitionCity;
import irille.wpt.exception.ExtjsException;
import irille.wpt.service.AbstractService;

@Service
public class PetitionCityService extends AbstractService<PetitionCity> {
	public static final Log LOG = new Log(PetitionCityService.class);
	
	public void petition(String name, Integer accountId) {
		if(name == null) {
			return ;
		}
		City city = cityDao.findByName(name, accountId);
		if(city != null){
			throw new ExtjsException("{0} 已经存在", name);
		}
		PetitionCity petitionCity = petitionCityDao.findByName(name, accountId);
		if(petitionCity == null){
			petitionCity = new PetitionCity();
			petitionCity.setName(name);
			petitionCity.setAccount(accountId);
			petitionCity.setCount(1);
			petitionCity.setEnabled((byte)1);
			petitionCityDao.save(petitionCity);
		} else {
			petitionCityDao.countIncrement(petitionCity);
		}
	}
	
}
