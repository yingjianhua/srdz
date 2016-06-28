package irille.wpt.service;

import java.util.List;
import java.util.Map;

import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.wx.wx.WxUser;

public class UserService {

	public int getFansNum(Integer userid) {
		String sql = Idu.sqlString("select count(*) fansNum from {0} where {1}=? or {2}=? or {3}=?", WxUser.class, WxUser.T.INVITED1, WxUser.T.INVITED2, WxUser.T.INVITED3);
		Map<String, Object> resultMap = Bean.executeQueryMap(sql, userid, userid, userid)[0];
		long fansNum = (long)resultMap.get("fansNum");
		return (int)fansNum;
	}
	public List<WxUser> getFans(Integer userid) {
		List<WxUser> fanses = Bean.list(WxUser.class, Idu.sqlString("{0}=? or {1}=? or {2}=?", WxUser.T.INVITED1, WxUser.T.INVITED2, WxUser.T.INVITED3), false, userid, userid, userid);
		return fanses;
	}
}
