package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.ComboBanner;
import irille.wpt.dao.AbstractDao;

@Repository
public class ComboBannerDao extends AbstractDao<ComboBanner, Integer>{

	public List<ComboBanner> listByCombo(Integer comboId) {
		return list("select * from wpt_combo_banner where combo=?", comboId);
	}

}
