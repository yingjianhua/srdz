package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.SpecialLine;
import irille.wpt.dao.AbstractDao;

@Repository
public class SpecialLineDao extends AbstractDao<SpecialLine, Integer>{

	public List<SpecialLine> listBySpecial(Integer specialId) {
		return list("select * from wpt_special_line where special=?", specialId);
	}
}
