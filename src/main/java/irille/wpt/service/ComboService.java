package irille.wpt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import irille.wpt.bean.Combo;
import irille.wpt.dao.impl.ComboDao;

@Service
public class ComboService {

	@Resource
	private ComboDao comboDao;
	
	@Transactional
	public List<Combo> findAll() {
		return comboDao.findByCondition();
	}
	@Transactional
	public Combo get(Integer id) {
		return comboDao.get(id);
	}
}
