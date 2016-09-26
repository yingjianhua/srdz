package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.idu.Idu;
import irille.wpt.bean.Headline;
import irille.wpt.dao.impl.BanquetDao;
import irille.wpt.dao.impl.CityDao;
import irille.wpt.dao.impl.CityLineDao;
import irille.wpt.dao.impl.HeadlineDao;
import irille.wx.wx.WxAccountDAO;
@Service
public class HeadlineService {
	
	@Resource
	private HeadlineDao headlineDao;
	@Resource
	private CityDao cityDao;
	@Resource
	private CityLineDao cityLineDao;
	@Resource
	private BanquetDao banquetDao;
	
	public void save(Headline bean) {
		bean.setAccount(WxAccountDAO.getByUser(Idu.getUser()).getPkey());
		headlineDao.save(bean);
		bean.setCity(cityDao.get(bean.getCity().getPkey()));
		bean.setCityline(cityLineDao.get(bean.getCityline().getPkey()));
		bean.setBanquet(banquetDao.get(bean.getBanquet().getPkey()));
	}
	
	public void update(Headline bean) {
		bean.setAccount(WxAccountDAO.getByUser(Idu.getUser()).getPkey());
		headlineDao.update(bean);
		bean.setCity(cityDao.get(bean.getCity().getPkey()));
		bean.setCityline(cityLineDao.get(bean.getCityline().getPkey()));
		bean.setBanquet(banquetDao.get(bean.getBanquet().getPkey()));
	}
	
	public void edit(Headline bean) {
		String content = bean.getContent();
		bean = headlineDao.get(bean.getPkey());
		bean.setContent(content);
		bean.setCity(cityDao.get(bean.getCity().getPkey()));
		bean.setCityline(cityLineDao.get(bean.getCityline().getPkey()));
		bean.setBanquet(banquetDao.get(bean.getBanquet().getPkey()));
		headlineDao.update(bean);
	}

	/**
	 * 根据搜索条件列出头条
	 * @param cityId 城市id 不可为空
	 * @param areaId 区域id 可以为空
	 * @param banquetId 宴会类型id 可以为空
	 * @param accountId 微信公众号id 不可为空
	 * @return
	 */
	public List<Headline> search(Integer cityId, Integer areaId, Integer banquetId, Integer account) {
		return headlineDao.search(cityId, areaId, banquetId, account);
	}
	public Headline get(Integer id) {
		return headlineDao.get(id);
	}
}
