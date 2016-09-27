package irille.wpt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import irille.wpt.bean.ComboLine;
import irille.wpt.service.AbstractService;

@Service
public class ComboLineService extends AbstractService<ComboLine> {
	
	public List<ComboLine> listByCombo(Integer comboId) {
		return comboLineDao.listByCombo(comboId);
	}
}
