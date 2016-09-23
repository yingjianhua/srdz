package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.Banquet;
import irille.wpt.dao.impl.BanquetDao;

@Service
public class BanquetService {
	
	@Resource
	private BanquetDao banquetDao;
	
	public List<Banquet> listByAccount(Integer account) {
		return banquetDao.listByAccount(account);
	}
}
