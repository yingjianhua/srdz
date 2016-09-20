package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Banquet;
import irille.wpt.dao.AbstractDao;

@Repository
public class BanquetDao extends AbstractDao<Banquet, Integer>{

	public List<Banquet> listByAccount(Integer account) {
		return list("select * from wpt_banquet where account=?", account);
	}
}
