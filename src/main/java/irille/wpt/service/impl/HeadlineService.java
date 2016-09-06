package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.Headline;
import irille.wpt.dao.impl.HeadlineDao;
@Service
public class HeadlineService {
	
	@Resource
	private HeadlineDao headlineDao;

	/**
	 * 根据搜索条件列出头条
	 * @param cityId 城市id 不可为空
	 * @param areaId 区域id 可以为空
	 * @param banquetId 宴会类型id 可以为空
	 * @param accountId 微信公众号id 不可为空
	 * @return
	 */
	public List<Headline> search(Integer cityId, Integer areaId, Integer banquetId, Integer accountId) {
		return headlineDao.search(cityId, areaId, banquetId, accountId);
	}
	
}
