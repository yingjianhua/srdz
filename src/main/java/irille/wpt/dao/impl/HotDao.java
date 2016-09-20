package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Hot;
import irille.wpt.dao.AbstractDao;

@Repository
public class HotDao extends AbstractDao<Hot, Integer>{
	
	public List<Hot> listByCity(Integer cityId) {
		return list("select * from wpt_hot where city=? order by sort", cityId);
	}
}
