package irille.wpt.dao.impl;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.ServiceCen;
import irille.wpt.dao.AbstractDao;

@Repository
public class ServiceCenDao extends AbstractDao<ServiceCen, Integer>{

	
	public ServiceCen find(Integer account) {
		return findUnique("select * from wpt_service_cen where account=?", account);
	}

}
