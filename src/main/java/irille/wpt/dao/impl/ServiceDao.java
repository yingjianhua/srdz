package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Service;
import irille.wpt.dao.AbstractDao;

@Repository
public class ServiceDao extends AbstractDao<Service, Integer>{

	public List<Service> listByIds(String ids) {
		return list("select * from wpt_service where pkey in ("+ids+")");
	}
}
