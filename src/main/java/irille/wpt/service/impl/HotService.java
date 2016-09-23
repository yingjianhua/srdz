package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.idu.Idu;
import irille.wpt.bean.Hot;
import irille.wpt.dao.impl.HotDao;
import irille.wx.wx.WxAccountDAO;

@Service
public class HotService {
	
	@Resource
	private HotDao hotDao;
	
	public void save(Hot bean) {
		bean.setAccount(WxAccountDAO.getByUser(Idu.getUser()).getPkey());
		hotDao.save(bean);
	}
	
	public void update(Hot bean) {
		bean.setAccount(WxAccountDAO.getByUser(Idu.getUser()).getPkey());
		hotDao.update(bean);
	}
	
	public List<Hot> listByCity(Integer cityId) {
		return hotDao.listByCity(cityId);
	}
}
