package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.Headline;
import irille.wpt.dao.impl.HeadlineDao;
import irille.wx.wpt.WptCollect;
import irille.wx.wpt.WptTop;
@Service
public class HeadlineService {
	
	@Resource
	private HeadlineDao headlineDao;

	//TODO
	/**
	 * 若未收藏，收藏 若已收藏，取消收藏
	 * 
	 * @param topId
	 * @param userId
	 */
	public void collectOrCancel(Integer topId, Integer userId) {
		WptTop top = WptTop.load(WptTop.class, topId);
		WptCollect collect = WptCollect.chkUniqueWxUserTop(false, userId, topId);
		if (collect == null) {
			collect = new WptCollect();
			collect.setAccount(top.getAccount());
			collect.setTop(top.getPkey());
			collect.setWxuser(userId);
			collect.ins();
		} else {
			collect.del();
		}
	}

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
