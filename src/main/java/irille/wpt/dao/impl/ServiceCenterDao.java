package irille.wpt.dao.impl;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.ServiceCenter;
import irille.wpt.dao.AbstractDao;

@Repository
public class ServiceCenterDao extends AbstractDao<ServiceCenter, Integer>{

	
	public ServiceCenter find(Integer account) {
		return findUnique("select * from wpt_service_cen where account=?", account);
	}

}
