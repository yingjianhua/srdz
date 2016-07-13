package irille.wx.wx;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.svr.Svr;
import irille.wxpub.util.WeixinUtil;

/**
 * 修改备注功能 -- 只能选择一个操作
 * 加入黑名单功能 -- 可以多选择操作
 * 组调整功能 -- 可以多选择操作
 * 微信同步功能 -- 定时同步、手动同步
 */
public class WxUserDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		infoErr("获取用户基本信息失败，错误原因[{0}]"),
		timeOutErr("同步超时"),
		synchronousErr("同步失败,错误原因[{0}]"),
		noGroup("用户分组不存在,请同步用户分组")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WxUserDAO.class);

	/** 拉取用户信息(需scope为 snsapi_userinfo) 网页授权**/
	public static final String USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	//获取用户基本信息接口
	public final static String user_info_url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	//获取用户列表信息
	public final static String user_List_url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
	//批量获取用户信息列表
	public final static String user_info_batchget_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	//设置备注名
	public final static String user_remark_url = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
	//移动用户分组
	public final static String user_group_update_url = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	
	public static WxUserGroup move(String pkeys, WxUserGroup group) throws JSONException {
		String[] sbeans = pkeys.split(",");
		for(String sbean:sbeans) {
			WxUser bean = WxUser.load(WxUser.class, Integer.parseInt(sbean));
			updateGroup(bean, group);
		}
		return group;
	}
	public static WxUserGroup toBlack(String pkeys) throws JSONException {
		WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
		WxUserGroup blacklist = WxUserGroup.loadUniqueWxidAccount(false, 1, account.getPkey());
		return move(pkeys,blacklist);
	}
	public static void updateGroup(WxUser bean, WxUserGroup group) throws JSONException {
		bean.stUserGroup(group);
		bean.upd();
		WxAccount account = bean.gtAccount();
		String accessToken = WxAccountDAO.getAccessToken(account);
		String requestUrl = user_group_update_url.replace("ACCESS_TOKEN", accessToken);
		String params = "{\"openid\":\""+bean.getOpenId()+"\",\"to_groupid\":"+group.getWxid()+"}";
		JSONObject jsonObj = WeixinUtil.httpRequest(requestUrl, "POST", params.toString());
		Integer errcode = jsonObj.getInt("errcode");
		if(errcode!=0) {
			throw LOG.err(jsonObj.getInt("errcode")+"", jsonObj.getString("errmsg"));
		}
	}
	public static WxUser rem(WxUser bean) throws JSONException {
		WxUser dbBean = WxUser.load(WxUser.class, bean.getPkey());
		dbBean.setRem(bean.getRem());
		dbBean.upd();
		
		WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
		String accessToken = WxAccountDAO.getAccessToken(account);
		String requestUrl = user_remark_url.replace("ACCESS_TOKEN", accessToken);
		
		String params = "{\"openid\":\""+dbBean.getOpenId()+"\",\"remark\":\""+dbBean.getRem()+"\"}";
		JSONObject jsonObj = WeixinUtil.httpRequest(requestUrl, "POST", params.toString());
		Integer errcode = jsonObj.getInt("errcode");
		if(errcode!=0) {
			throw LOG.err(jsonObj.getInt("errcode")+"", jsonObj.getString("errmsg"));
		}
		return dbBean;
	}
	
	/**
	 * 更新用户基本信息
	 * @param pkeys
	 * @throws JSONException
	 * @throws UnsupportedEncodingException
	 */
	public void refresh(String pkeys) throws JSONException, UnsupportedEncodingException {
		WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
		String accessToken = WxAccountDAO.getAccessToken(account);
		Date start = new Date();
		if(pkeys == null || pkeys.equals("")) {
			//没有选择指定的用户，就更新所有昵称为空的用户的基本信息
			String where = Idu.sqlString("{0}=? AND {1}=? AND {2} is null", WxUser.T.ACCOUNT,WxUser.T.STATUS, WxUser.T.NICKNAME);
			for(List<WxUser> local_users = WxUser.list(WxUser.class, true, where, 0, 100, account.getPkey(),Wx.OStatus.FOLLOW.getLine().getKey()); local_users.size()>0;System.gc()) {
				batchget(local_users, account.getPkey(), accessToken);
				Date step1 = new Date();
				Svr.commit();
				Date step2 = new Date();
				local_users.clear();
				local_users = WxUser.list(WxUser.class, true, where, 0, 100, account.getPkey(),Wx.OStatus.FOLLOW.getLine().getKey());
				Date step3 = new Date();
				useTime.add("数据库commit用时："+(step2.getTime()-step1.getTime())+"ms");
				useTime.add("数据库list用时："+(step3.getTime()-step2.getTime())+"ms");
			}
		} else {
			//选择了指定的用户，更新所选的用户的基本信息
			String where = Idu.sqlString("{0}=? and {1}=? and {2} in ("+pkeys+")", WxUser.T.ACCOUNT,WxUser.T.STATUS, WxUser.T.PKEY);
			int index = 0;
			for(List<WxUser> local_users = WxUser.list(WxUser.class, true, where, index, 100, account.getPkey(),Wx.OStatus.FOLLOW.getLine().getKey()); local_users.size()>0;System.gc()) {
				batchget(local_users, account.getPkey(), accessToken);
				Date step1 = new Date();
				Svr.commit();
				Date step2 = new Date();
				index += local_users.size();
				local_users.clear();
				local_users = WxUser.list(WxUser.class, true, where, index, 100, account.getPkey(),Wx.OStatus.FOLLOW.getLine().getKey());
				Date step3 = new Date();
				useTime.add("数据库commit用时："+(step2.getTime()-step1.getTime())+"ms");
				useTime.add("数据库list用时："+(step3.getTime()-step2.getTime())+"ms");
			}
		}
		Date end = new Date();
		useTime.add("同步完毕，总共用时："+(end.getTime()-start.getTime())+"ms");
		for(String str:useTime) {
			System.out.println(str);
		}
	}
	private static JSONObject getInfoJsonByAuthorize(String accessToken, String openid) {
		String requestUrl = USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replaceAll("OPENID", openid);
		return WeixinUtil.httpRequest(requestUrl, "GET", null);
	}
	private static JSONObject getInfoJsonByGeneral(String accessToken, String openid) {
		String requestUrl = user_info_url.replace("ACCESS_TOKEN", accessToken).replaceAll("OPENID", openid);
		return WeixinUtil.httpRequest(requestUrl, "GET", null);
	}
	
	/**
	 * 根据网页授权获取的用户信息，新增用户
	 * @param accessToken
	 * @param openid
	 * @return
	 */
	public static WxUser insByAuthorize(String accessToken, String openid, WxAccount account, Long invitedId) {
		return ins(getInfoJsonByAuthorize(accessToken, openid), account.getPkey(), invitedId);
	}
	public static WxUser insByGeneral(String accessToken, String openid, WxAccount account, Long invitedId) {
		return ins(getInfoJsonByGeneral(accessToken, openid), account.getPkey(), invitedId);
	}
	private static WxUser ins(JSONObject json, Integer accountPkey, Long invitedId) {
		WxUser user = buildWxUser(null, json, accountPkey);
		if(invitedId != null) { 
			WxUser invited3 = WxUser.load(WxUser.class, invitedId);
			Integer invited2 = invited3.getInvited3();
			Integer invited1 = invited3.getInvited2();
			user.stInvited3(invited3);
			user.setInvited2(invited2);
			user.setInvited1(invited1);
		}
		user.ins();
		return user;
	}
	public static WxUser updByGeneral(String accessToken, String openid, WxAccount account) {
		return upd(getInfoJsonByGeneral(accessToken, openid), account.getPkey());
	}
	private static WxUser upd(JSONObject json, Integer accountPkey) {
		WxUser user = buildWxUser(null, json, accountPkey);
		user.upd();
		return user;
	}
	/**
	 * 根据JSON中的内容新建一个wxUser对象或更新一个wxUser
	 * @param json
	 * @param account
	 * @return
	 */
	private static WxUser buildWxUser(WxUser user, JSONObject json, Integer accountPkey) {
		try {
			if(user == null) {
				user = new WxUser();
				user.stIsMember(false);
				user.setHistoryCommission(BigDecimal.ZERO);
				user.setCashableCommission(BigDecimal.ZERO);
				user.setAccount(accountPkey);
				user.stSyncStatus(true);
			}
			user.setOpenId(json.getString("openid"));
			user.setNickname(json.getString("nickname"));
			user.setSex((byte)json.getInt("sex"));
			user.setProvince(json.getString("province"));
			user.setCity(json.getString("city"));
			user.setCountry(json.getString("country"));
			user.setImageUrl(json.getString("headimgurl"));
			if(json.has("unionid")) {
				user.setUnionId(json.getString("unionid"));
			}
			if(json.has("groupid")) {
				user.stUserGroup(WxUserGroup.loadUniqueWxidAccount(false, json.getInt("groupid"), accountPkey));
			} else {
				user.stUserGroup(WxUserGroup.loadUniqueWxidAccount(false, 0, accountPkey));
			}
			if(json.has("subscribe")) {
				user.stStatus(json.getInt("subscribe")==0?Wx.OStatus.NOFOLLOW:Wx.OStatus.FOLLOW);
			} else {
				user.stStatus(Wx.OStatus.FOLLOW);
			}
			if(json.has("subscribe_time")) {
				user.setSubscribeTime(new Date(Long.parseLong(json.get("subscribe_time")+"000")));
			} else {
				user.setSubscribeTime(new Date());
			}
			if(json.has("remark")) {
				user.setRem(json.getString("remark"));
			}
		} catch (NumberFormatException | JSONException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	private void batchget(List<WxUser> list, Integer account, String accessToken) throws JSONException, UnsupportedEncodingException {
		//list的长度不能超过100
		Date step1 = new Date();
		String requestUrl = user_info_batchget_url.replace("ACCESS_TOKEN", accessToken);
		WxUserGroup group = null;
		StringBuilder params = new StringBuilder();
		params.append("{\"user_list\":[");
		for(WxUser line:list) {
			params.append("{\"openid\":\""+line.getOpenId()+"\"},");
		}
		params.append("]}");
		JSONObject result = WeixinUtil.httpRequest(requestUrl, "GET", params.toString());
		Date step2 = new Date();
		if(result.has("errcode") && result.getInt("errcode")!=0) {
			throw LOG.err(Msgs.infoErr, (result.has("errmsg")?result.getString("errmsg"):"未知错误"));
		}
		JSONArray jlist = result.getJSONArray("user_info_list");
		for(int i=0;i<jlist.length();i++) {
			JSONObject jline = jlist.getJSONObject(i);
			WxUser line = list.get(i);
			if(!jline.getString("openid").equals(line.getOpenId())) {
				throw LOG.err(Msgs.infoErr);
			}
			if(jline.getInt("subscribe")==0) {
				line.stStatus(Wx.OStatus.NOFOLLOW);
				line.upd();
				continue;
			}
			line.setNickname(jline.getString("nickname"));
			line.setSex((byte)jline.getInt("sex"));
			line.setCity(jline.getString("city"));
			line.setCountry(jline.getString("country"));
			line.setProvince(jline.getString("province"));
			line.setImageUrl(jline.getString("headimgurl"));
			line.setSubscribeTime(new Date(Long.parseLong(jline.get("subscribe_time")+"000")));
			line.setRem(jline.getString("remark"));
			if((group=WxUserGroup.chkUniqueWxidAccount(false, jline.getInt("groupid"), account))==null) {
				throw LOG.err(Msgs.noGroup);
			}
			line.stUserGroup(group);
			line.setUnionId(jline.has("unionid")?jline.getString("unionid"):null);
	      	line.upd();
		}
		Date step3 = new Date();
		useTime.add("http请求用时："+(step2.getTime()-step1.getTime())+"ms");
		useTime.add("数据处理用时："+(step3.getTime()-step2.getTime())+"ms");
	}
	/**
	 * 用于统计同步用户信息所用的时间
	 */
	private List<String> useTime = new ArrayList<String>();
	/**
	 * 同步微信用户列表
	 * @throws JSONException
	 */
	public void sync() throws JSONException {
		Date start = new Date();
		WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
		String accessToken = WxAccountDAO.getAccessToken(account);
		sync(account.getPkey(), accessToken);
		Date end = new Date();
		useTime.add("同步完毕，总共用时："+(end.getTime()-start.getTime())+"ms");
		for(String str:useTime) {
			System.out.println(str);
		}
	}
	/**
	 * 同步微信用户列表
	 * @param account
	 * 
	 * @param accessToken
	 * @throws JSONException
	 */
	private void sync(Integer account, String accessToken) throws JSONException {
		Integer defaultGroup = WxUserGroup.loadUniqueWxidAccount(false, 0, account).getPkey();
		byte follow = Wx.OStatus.FOLLOW.getLine().getKey();
		byte nofollow = Wx.OStatus.NOFOLLOW.getLine().getKey();
		String requestUrl = null;
		String next = null;
		int count = 0;
		Date start = null;
		Date end = null;
		//将该公众号下的所有微信用户的同步状态设置为未同步
		String sql = Idu.sqlString("update {0} set {1}=? where {2}=?", WxUser.class, WxUser.T.SYNC_STATUS, WxUser.T.ACCOUNT);
		WxUser.executeUpdate(sql, BeanBase.booleanToByte(false), account);
		do {
			start = new Date();
			requestUrl = user_List_url.replace("ACCESS_TOKEN", accessToken) + (Str.isEmpty(next)?"":("&next_openid="+next));
			JSONObject result = WeixinUtil.httpRequest(requestUrl, "GET", null);
			if(result.has("errcode") && result.getInt("errcode")!=0) {
				throw LOG.err(Msgs.synchronousErr, (result.has("errmsg")?result.getString("errmsg"):"未知错误"));
			}
			next = result.getString("next_openid");
			count = result.getInt("count");
			if(count > 0) {
				JSONArray list = result.getJSONObject("data").getJSONArray("openid");
				String openid = null;
				int length = list.length();
				for(int i = 0 ; i < length ; i++) {
					openid = list.getString(i);
					WxUser user = WxUser.chkUniqueOpenIdAccount(false, openid, account);
					if(user == null) {
						//本地没有的微信端关注用户 - 新增
						user = new WxUser();
						user.stIsMember(false);
						user.setAccount(account);
						user.setUserGroup(defaultGroup);
						user.setStatus(follow);
						user.stSyncStatus(true);
						user.setOpenId(openid);
						user.setHistoryCommission(BigDecimal.ZERO);
						user.setCashableCommission(BigDecimal.ZERO);
						user.ins();
					} else {
						//本地有的微信端关注用户，则更新为已关注
						user.setStatus(follow);
						user.stSyncStatus(true);
						user.upd();
					}
				}
				Svr.commit();
			}
			end = new Date();
			useTime.add("拉取"+count+"条用户列表，用时："+(end.getTime()-start.getTime())+"ms");
		} while(!Str.isEmpty(next));
		start = new Date();
		//将该公众号下的所有未同步的微信用户的状态设置为未关注
		sql = Idu.sqlString("update {0} set {1}=? where {2}=? AND {3}=?", WxUser.class, WxUser.T.STATUS, WxUser.T.ACCOUNT, WxUser.T.SYNC_STATUS);
		WxUser.executeUpdate(sql, nofollow, account, BeanBase.booleanToByte(false));
		end = new Date();
		useTime.add("将没有拉取到的用户设置为未关注，总共用时："+(end.getTime()-start.getTime())+"ms");
	}
	public static long useMemory() {
		return Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
	}
	
	public static WxUser subscribe(String accountId, String openId, String createTime, String SceneKey) throws UnsupportedEncodingException, JSONException {
		WxAccount account = WxAccount.loadUniqueAccountId(false, accountId);
		WxUser user = WxUser.chkUniqueOpenIdAccount(false, openId, account.getPkey());
		String accessToken = WxAccountDAO.getAccessToken(account);
		Long invitedId = null;
		try {
			invitedId = Long.parseLong(SceneKey);
		} catch (NumberFormatException e) {
		}
		if(user == null) {
			user = insByGeneral(accessToken, openId, account, invitedId);
			WxMessageDAO.notifyInvited(accessToken, user);
		} else {
			updByGeneral(accessToken, openId, account);
		}
		return user;
	}
	
	public static WxUser unsubscribe(String accountId, String openId, String createTime) {
		WxAccount account = WxAccount.loadUniqueAccountId(false, accountId);
		WxUser user = WxUser.chkUniqueOpenIdAccount(false, openId, account.getPkey());
	  if(user!=null) {
	  	user.stStatus(Wx.OStatus.NOFOLLOW);
	  	user.upd();
	  }
	  return user;
	}
}
