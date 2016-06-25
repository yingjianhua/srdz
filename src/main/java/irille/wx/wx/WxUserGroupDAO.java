package irille.wx.wx;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.wxpub.util.WeixinUtil;

import java.util.List;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 默认组、星标组、黑名单组由微信同步时，自动添加；且不能作修改及删除操作
 * 
 * 新增：状态为'未同步'，微信ID为NULL
 * 修改：当状态为'已同步'并且名称有变更时，修改状态为'未同步'
 * 删除：未同步时直接删除档案；修改状态为'已删除'，并将所属关注用户中的用户组置空
 * 微信同步：
 * 0.发起获取所有用户组事件，如系统的用户分组ID在微信组中不存在时 -- 作类似用户组删除操作并且直接删除记录
 * 1.微信ID为NULL，并状态未同步 -- 发起创建用户组事件，并更新微信ID
 * 2.微信ID有，并状态未同步 -- 发起更新用户组事件
 * 3.状态已同步 -- 不处理
 * 4.状态已删除 -- 发起删除用户组事件，并删除此记录 (所属用户自动归到默认组中)
 * @author whx
 * @version 创建时间：2015年8月13日 上午8:54:53
 */
public class WxUserGroupDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		getGroupErr("获取所有用户分组失败!"),
		delGroupErr("删除用户分组[{0}]失败"),
		createGroupErr("新建用户分组[{0}]失败"),
		updateGroupErr("更新用户分组[{0}]失败"),
		sysGrpErr("系统分组[{0}]不可更改!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WxUserGroupDAO.class);
	private static String INS_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	private static String GET_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	private static String DEL_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
	private static String UPD_GROUP_URL = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";

	//微信-获取所有用户分组-现有的ID
	private static List<Integer> wxGetGroup(String accessToken) {
		String requestUrl = GET_GROUP_URL.replace("ACCESS_TOKEN", accessToken);
		List<Integer> ids = new Vector<Integer>();
		JSONObject result = WeixinUtil.httpRequest(requestUrl, "GET", null);//公众平台通用接口工具类WeixinUtil,返回json
		try {
			JSONArray ja = result.getJSONArray("groups");
			for (int i = 0; i < ja.length(); i++) {
				JSONObject json = ja.getJSONObject(i);
				ids.add(json.getInt("id"));
			}
		} catch (JSONException e) {
			throw LOG.err(Msgs.getGroupErr);
		}
		return ids;
	}

	//微信-删除用户分组
	private static void wxDelGroup(String accessToken, WxUserGroup gp) {
		String requestUrl = DEL_GROUP_URL.replace("ACCESS_TOKEN", accessToken);
		try {
			JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", "{\"group\":{\"id\":" + gp.getWxid() + "}}");
			if(result.has(WeixinUtil.ERR_CODE)&&result.getInt(WeixinUtil.ERR_CODE) != 0)
				throw LOG.err(Msgs.delGroupErr, gp.getName());
		} catch (JSONException e) {
			throw LOG.err(Msgs.delGroupErr, gp.getName());
		}
	}

	//微信-新建用户分组
	private static void wxInsGroup(String accessToken, WxUserGroup gp) {
		String requestUrl = INS_GROUP_URL.replace("ACCESS_TOKEN", accessToken);
		try {
			JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", "{\"group\":{\"name\":\"" + gp.getName() + "\"}}");
			if (result.has(WeixinUtil.ERR_CODE))
				throw LOG.err(Msgs.createGroupErr, gp.getName());
			gp.setWxid(result.getJSONObject("group").getInt("id"));
			gp.upd();
		} catch (JSONException e) {
			throw LOG.err(Msgs.createGroupErr, gp.getName());
		}
	}

	//微信-更改用户分组
	private static void wxUpdGroup(String accessToken, WxUserGroup gp) {
		String requestUrl = UPD_GROUP_URL.replace("ACCESS_TOKEN", accessToken);
		try {
			JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", "{\"group\":{\"id\":" + gp.getWxid()
			    + ",\"name\":\"" + gp.getName() + "\"}}");
			if (result.getInt(WeixinUtil.ERR_CODE) != 0)
				throw LOG.err(Msgs.updateGroupErr, gp.getName());
		} catch (JSONException e) {
			throw LOG.err(Msgs.updateGroupErr, gp.getName());
		}
	}

	/**
	 * 微信同步功能
	 */
	public static void sync() {
		WxUser.TB.getCode();
		WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
		String accessToken = WxAccountDAO.getAccessToken(Idu.getUser());
		WxUserGroup defaultGrp = WxUserGroup.loadUniqueWxidAccount(false, 0, account.getPkey());
		List<Integer> wxids = wxGetGroup(accessToken);
		List<WxUserGroup> list = BeanBase.list(WxUserGroup.class, "1=1", true);
		for (WxUserGroup line : list) {
			if (line.getWxid()!=null&&wxids.contains(line.getWxid()) == false) { //由微信删除
				line.del();
				Bean.executeUpdate(Idu.sqlString("update {0} set {1}=? where {1}=?", WxUser.class, WxUser.T.USER_GROUP),
				    defaultGrp.getPkey(), line.getPkey());
				continue;
			}
			if (line.gtSyncStatus() == Wx.OSyncStatus.SYNC) //已同步
				continue;
			if (line.gtSyncStatus() == Wx.OSyncStatus.DEL) { //已删除
				wxDelGroup(accessToken, line);
				line.del();
				continue;
			}
			if (line.gtSyncStatus() == Wx.OSyncStatus.INIT) {//未同步
				line.stSyncStatus(Wx.OSyncStatus.SYNC);
				line.upd();
				if (line.getWxid() == null)
					wxInsGroup(accessToken, line);
				else
					wxUpdGroup(accessToken, line);
			}
		}
	}

	/**
	 * 初始化默认的用户组：默认0、黑名单1、星标2
	 */
	public static void initGrp(Integer  account) {
		/*WxUserGroup g1 = WxUserGroup.chkUniqueWxidAccount(false, 0, account);
		if (g1 != null)
			return g1;*/
		WxUserGroup g1 = new WxUserGroup().init();
		g1 = new WxUserGroup().init();
		g1.setAccount(account);
		g1.setWxid(0);
		g1.setName("默认分组");
		g1.stSyncStatus(Wx.OSyncStatus.SYNC);
		g1.ins();
		WxUserGroup g2 = new WxUserGroup().init();
		g2.setAccount(account);
		g2.setWxid(1);
		g2.setName("黑名单");
		g2.stSyncStatus(Wx.OSyncStatus.SYNC);
		g2.ins();
		WxUserGroup g3 = new WxUserGroup().init();
		g3.setAccount(account);
		g3.setWxid(2);
		g3.setName("星标组");
		g3.stSyncStatus(Wx.OSyncStatus.SYNC);
		g3.ins();
		//return g1;
	}

	private static void checkSys(WxUserGroup grp) {
		if (grp.getWxid() != null && grp.getWxid() <= 2)
			throw LOG.err(Msgs.sysGrpErr, grp.getName());
	}

	public static class Ins extends IduIns<Ins, WxUserGroup> {
		@Override
		public void before() {
			super.before();
			getB().stSyncStatus(Wx.OSyncStatus.INIT);
			getB().stAccount(WxAccountDAO.getByUser(Idu.getUser()));
			getB().setUpdatedTime(Env.INST.getSystemTime());
		}

	}

	public static class Upd extends IduUpd<Upd, WxUserGroup> {
		@Override
		public void before() {
			super.before();
			WxUserGroup dbBean = load(getB().getPkey());
			checkSys(dbBean);
			if (dbBean.gtSyncStatus() == Wx.OSyncStatus.SYNC && dbBean.getName().equals(getB().getName()) == false)
				dbBean.stSyncStatus(Wx.OSyncStatus.INIT);
			dbBean.setName(getB().getName());
			dbBean.setUpdatedTime(Env.INST.getSystemTime());
			setB(dbBean);
		}

	}

	public static class Del extends IduDel<Del, WxUserGroup> {

		@Override
		public void run() {
			checkSys(getB());
			if (getB().getWxid() == null) {
				getB().del();
			} else {
				getB().stSyncStatus(Wx.OSyncStatus.DEL);
				getB().upd();
				Bean.executeUpdate(Idu.sqlString("update {0} set {1}=? where {1}=?", WxUser.class, WxUser.T.USER_GROUP),
						1,getB().getPkey());
			}
		}
	}

}
