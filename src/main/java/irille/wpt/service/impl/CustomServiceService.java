package irille.wpt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import irille.wpt.bean.CustomService;
import irille.wpt.service.AbstractService;

@Service
public class CustomServiceService extends AbstractService<CustomService> {
	
	public void save(CustomService bean, Integer account) {
		bean.setAccount(account);
		customServiceDao.save(bean);
	}
	
	public void update(CustomService bean, Integer account) {
		bean.setAccount(account);
		customServiceDao.update(bean);
	}
	
	public List<CustomService> listByAccount(Integer account) {
		return customServiceDao.listByAccount(account);
	}
}
