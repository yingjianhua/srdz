package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.WxTips;
import irille.wpt.dao.AbstractDao;

@Repository
public class WxTipsDao extends AbstractDao<WxTips, Integer>{

	public List<WxTips> list(Integer accountId) {
		return list("select * from wpt_wx_tips where account=?", accountId);
	}

}
