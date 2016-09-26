package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.CustomService;
import irille.wpt.dao.impl.CustomServiceDao;

@Service
public class CustomServiceService {
	
	@Resource
	private CustomServiceDao customSerivceDao;
	
	public void save(CustomService bean, Integer account) {
		bean.setAccount(account);
		customSerivceDao.save(bean);
	}
	
	public void update(CustomService bean, Integer account) {
		bean.setAccount(account);
		customSerivceDao.update(bean);
	}
	
	public List<CustomService> listByAccount(Integer account) {
		return customSerivceDao.listByAccount(account);
	}
}
