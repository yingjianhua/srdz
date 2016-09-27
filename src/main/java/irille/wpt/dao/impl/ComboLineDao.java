package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.ComboLine;
import irille.wpt.dao.AbstractDao;

@Repository
public class ComboLineDao extends AbstractDao<ComboLine, Integer>{
	
	public List<ComboLine> listByCombo(Integer comboId) {
		return list("select * from wpt_combo_line where combo=?", comboId);
	}
}
