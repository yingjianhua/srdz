package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.core.sys.Sys.OYn;
import irille.wpt.bean.Special;
import irille.wpt.dao.AbstractDao;

@Repository
public class SpecialDao extends AbstractDao<Special, Integer>{

	public List<Special> listByCity(Integer cityId) {
		return list("select * from wpt_special where city=? or ignore_city=? order by sort", cityId, OYn.YES.getLine().getKey());
	}
}
