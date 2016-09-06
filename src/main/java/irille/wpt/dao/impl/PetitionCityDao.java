package irille.wpt.dao.impl;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.PetitionCity;
import irille.wpt.dao.AbstractDao;

@Repository
public class PetitionCityDao extends AbstractDao<PetitionCity, Integer>{
	
	public PetitionCity findByName(String name, Integer accountId) {
		return findUnique("select * from wpt_petition_city where name=? and account=?", name, accountId);
	}
	public void countIncrement(PetitionCity petitionCity) {
		petitionCity.setCount(petitionCity.getCount()+1);
		update(petitionCity);
	}
}
