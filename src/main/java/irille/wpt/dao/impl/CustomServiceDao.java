package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.CustomService;
import irille.wpt.dao.AbstractDao;

@Repository
public class CustomServiceDao extends AbstractDao<CustomService, Integer>{

	public List<CustomService> listByIds(String ids) {
		return list("select * from wpt_service where pkey in ("+ids+")");
	}

	public List<CustomService> listByAccount(Integer account) {
		return list("select * from wpt_service where account=?", account);
	}
}
