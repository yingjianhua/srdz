package irille.wpt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import irille.wpt.bean.Banquet;
import irille.wpt.service.AbstractService;

@Service
public class BanquetService extends AbstractService<Banquet> {
	
	public List<Banquet> listByAccount(Integer account) {
		return banquetDao.listByAccount(account);
	}
}
