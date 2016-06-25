package irille.wpt.service;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.wx.wpt.WptCollect;
import irille.wx.wpt.WptTop;

public class TopService {

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
	public List<WptTop> search(Integer cityId, Integer areaId, Integer banquetId, Integer accountId) {
		StringBuilder where = new StringBuilder("1=1");
		if (areaId != null) {
			where.append(" AND " + WptTop.T.CITYLINE + " = " + areaId);
		}
		if (banquetId != null) {
			where.append(" AND " + WptTop.T.BANQUET + " = " + banquetId);
		}
		where.append(" AND " + WptTop.T.CITY + " = ?");
		where.append(" AND " + WptTop.T.ACCOUNT + " = ?");
		where.append(" ORDER BY " + WptTop.T.TOP + " DESC " + "," + WptTop.T.SORT);
		return WptTop.list(WptTop.class, where.toString(), false, cityId, accountId);
	}
	public JSONArray search4Json(Integer cityId, Integer areaId, Integer banquetId, Integer accountId) {
		List<WptTop> tops = search(cityId, areaId, banquetId, accountId);
		JSONArray result = new JSONArray();
		try {
			for(WptTop top:tops) {
				JSONObject o = new JSONObject();
				o.put("url", top.getUrl());
				o.put("pkey", top.getPkey());
				o.put("imgUrl", top.getImgUrl());
				o.put("title", top.getTitle());
				o.put("date", top.getDate());
				result.put(o);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}
}
