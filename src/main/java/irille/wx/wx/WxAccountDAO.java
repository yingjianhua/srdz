package irille.wx.wx;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.BeanBase;
import irille.pub.bean.BeanBuf;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.pub.svr.Svr;
import irille.wx.wpt.WptDistributionRuleDAO;
import irille.wx.wpt.WptQrcodeRuleDAO;
import irille.wx.wpt.WptRedPackRuleDAO;
import irille.wx.wx.Wx.OAccountType;
import irille.wxpub.util.WeixinUtil;

public class WxAccountDAO {
	/**
	 * 长链接转短链接接口
	 */
	public static final String short_url = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		err("订阅号类型不可变更!"),
		noAccountErr("该用户没有配置公众号!"),
		getTokenErr("获取ACCESS_TOKEN失败!"),
		getTicketErr("获取ACCESS_TOKEN失败!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
  public static final Log LOG = new Log(WxAccountDAO.class);

  public static WxAccount getByUser(Integer user) {
	WxAccount wa = WxSettingDAO.getAccountByUser(user);
	if (wa == null)
		throw LOG.err(Msgs.noAccountErr);
    return wa;
  }

  public static WxAccount getByUser(SysUser user) {
    return getByUser(user.getPkey());
  }

  public static class Ins extends IduIns<Ins, WxAccount> {

    @Override
    public void after() {
      super.after();
      try {
        // 获取accessToken
        getAccessToken(getB());
        // 初始化用户分组
        WxUserGroupDAO.initGrp(getB().getPkey());
        // 初始化推广二维码配置
        WptQrcodeRuleDAO.initQrcodeRule(getB().getPkey());
        // 初始化分销规则配置
        WptDistributionRuleDAO.initDistributionRule(getB().getPkey());
        // 初始化红包规则配置
        WptRedPackRuleDAO.initRedPack(getB().getPkey());
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static class Upd extends IduUpd<Upd, WxAccount> {
    @Override
    public void before() {
      super.before();
      WxAccount dbBean = load(getB().getPkey());
      if(dbBean.gtAccountType() == OAccountType.SERVICE && getB().gtAccountType() == OAccountType.SUBSCRIPTION) {
    	  throw LOG.err(Msgs.err);
      }
      PropertyUtils.copyPropertiesWithout(dbBean, getB(), WxAccount.T.ACCESS_TOKEN, WxAccount.T.ACCESS_TIME, WxAccount.T.JSAPI_TICKET);
      setB(dbBean);
    }

    @Override
    public void after() {
      super.after();
      try {
        getAccessToken(getB());
      } catch (Exception e) {
        e.printStackTrace();
      }
      BeanBuf.clear(WxAccount.class);
    }
  }
  public static void uploadCert(int accountPkey, String certPath) {
	  WxAccount account = WxAccount.load(WxAccount.class, accountPkey);
	  account.setMchPayCert(certPath);
	  account.upd();
	  BeanBuf.clear(WxAccount.class);
  }

  /**
   * @author whx
   *         出现静态块异常
   */
  public static class Del extends IduDel<Del, WxAccount> {
    @Override
    public void valid() {
      super.valid();
      haveBeUsed(WxSubscribe.class, WxSubscribe.T.ACCOUNT, b.getPkey());
      haveBeUsed(WxAuto.class, WxAuto.T.ACCOUNT, b.getPkey());
      haveBeUsed(WxMenu.class, WxMenu.T.ACCOUNT, b.getPkey());
    }

    @Override
    public void before() {
      super.before();
      String where = Idu.sqlString("{0}=?", WxUserGroup.T.ACCOUNT);
      List<WxUserGroup> groups = BeanBase.list(WxUserGroup.class, where, true, getB().getPkey());
      for (WxUserGroup line : groups)
        line.del();
    }
    @Override
    public void after() {
    	BeanBuf.clear(WxAccount.class);
    	super.after();
    }
  }

  public static String getAccessToken(SysUser user) {
    WxAccount wa = getByUser(user);
      return getAccessToken(wa);
  }
  
  /**
   * @param account 公众账号对象
   * @return 得到当前公众号的APPID和APPSECRET并向微信服务器获取AccessToken
   * @throws Exception
   */
  public static String getAccessToken(WxAccount account) {
	  String token = account.getAccessToken();
	  if (token != null && !tokenTimeout(account))
		  return token; // 有效期内直接返回
	  String requestUrl = WeixinUtil.ACCESS_TOKEN_URL.replace("APPID", account.getAccountAppid()).replace("APPSECRET",account.getAccountAppsecret());
	  JSONObject json = WeixinUtil.httpRequest(requestUrl, "GET", null);
	  try {
		  if (json.has(WeixinUtil.ERR_CODE))
			  throw LOG.err(Msgs.getTokenErr);
//        ServletActionContext.getResponse().getWriter().write("获取ACCESS_TOKEN失败");
		  token = json.getString("access_token");
		  account.setAccessToken(token);
		  account.setAccessTime(Env.INST.getSystemTime());
		  setJsapiTicket(account);
		  account.upd();
		  Svr.getConn().commit();
		  BeanBuf.clear(WxAccount.class);
	  } catch (Exception e) {
		  e.printStackTrace();
	  }
    return token;
  }
  
  public static String getJsapiTicket(WxAccount account) {
	  if (account.getJsapiTicket() != null && !tokenTimeout(account))
		  return account.getJsapiTicket(); //有效期内直接返回
	  getAccessToken(account);
	  return account.getJsapiTicket();
  }
  
  private static void setJsapiTicket(WxAccount account) throws JSONException {
	  String requestUrl = WeixinUtil.JSAPI_TICKET_URL.replace("ACCESS_TOKEN", account.getAccessToken());
	  JSONObject json = WeixinUtil.httpRequest(requestUrl, "GET", null);
	  if (json.getInt(WeixinUtil.ERR_CODE) != 0 || !json.getString(WeixinUtil.ERR_MSG).equals("ok"))
		  throw LOG.err(Msgs.getTicketErr);
	  account.setJsapiTicket(json.getString("ticket"));
  }
  
  private static boolean tokenTimeout(WxAccount account) {
	  return (Env.INST.getSystemTime().getTime() - account.getAccessTime().getTime()) / 1000 / 3600 > 1.9;
  }
  public static void main(String[] args) {
	  String accessToken = "nW6U57LExG517rIdlm_ikxPds0yJCYDzECwAU_o28dB9Yw2pBS5QaKZ3oSNe7jb3Zx3NeZ04wlCyqveE2y1YGxNBb7ZSVrBwmYLvl1LdX3sSBZfADABKI";
	  String long_url = "http://mp.weixin.qq.com/s?__biz=MzIxNTA5NTQyNQ==&mid=402687669&idx=2&sn=1911bceb12cbf32dcbd547f358581b24&scene=18#wechat_redirect";
	  try {
		long2short(accessToken, long_url);
	} catch (JSONException e) {
		e.printStackTrace();
	}
  }
  
  private static String long2short(String accessToken, String long_url) throws JSONException {
		String requestUrl = short_url.replaceAll("ACCESS_TOKEN", accessToken);
		JSONObject json = new JSONObject();
		json.put("action", "long2short");
		json.put("long_url", long_url);
		JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", json.toString());
		if(result.has("errcode") && (Integer)result.get("errcode") != 0)
			throw LOG.err(""+result.get("errcode"), "" + result.getString("errmsg"));
		return result.getString("short_url");
  }
  
}
