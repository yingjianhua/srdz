package irille.wpt.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.ServiceCen;
import irille.wpt.dao.impl.ServiceCenDao;

@Service
public class ServiceCenService {

	@Resource
	private ServiceCenDao serviceCenDao;
	
	public ServiceCen find(Integer account) {
		return serviceCenDao.find(account);
	}
}
