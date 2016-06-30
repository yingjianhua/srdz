package irille.wx.wa;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.Idu;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.wx.wx.Wx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxUser;
import irille.wx.wx.WxUserDAO;
import irille.wx.wx.Wx.OQRCodeType;
import irille.wxpub.util.WeixinUtil;

public class WaQRCodeDAO {
	public static final Log LOG = new Log(WaQRCodeDAO.class);

	//创建二维码ticket
	public final static String qrcode_create_url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
	//通过ticket换取二维码
	public final static String showqrcode_url = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";

	private static final String ABSTRACT_PATH;
	private static final String RELATIVE_PATH;
	static {
		RELATIVE_PATH = ServletActionContext.getServletContext().getInitParameter("qrcodePath");
		String classPath = WaQRCodeDAO.class.getClassLoader().getResource("/").getPath();
		ABSTRACT_PATH = classPath.replace("/WEB-INF/classes", RELATIVE_PATH);
	}
	public static class Ins extends IduIns<Ins, WaQRCode> {

		@Override
		public void valid() {
			super.valid();
			WaQRCodeDAO.valid(getB());
		}

		@Override
		public void before() {
			super.before();
			WaQRCode bean = getB();
			WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
			bean.stAccount(account);
			bean.setCreatedTime(new Date());
		}

	}

	public static class Upd extends IduUpd<Upd, WaQRCode> {
		@Override
		public void valid() {
			super.valid();
			WaQRCodeDAO.valid(getB());
		}

		@Override
		public void before() {
			super.before();
			WaQRCode dbBean = WaQRCode.loadAndLock(WaQRCode.class, getB().getPkey());
			WaQRCode bean = getB();
			if (!dbBean.getSceneKey().equals(bean.getSceneKey())) {
				dbBean.setExpireTime(null);
				dbBean.setImgUrl(null);
				dbBean.setUrl(null);
			}
			PropertyUtils.copyProperties(dbBean, bean, WaQRCode.T.SCENE_KEY, WaQRCode.T.SUMMARY, WaQRCode.T.VALID_TERM,
			    WaQRCode.T.TYPE);
			dbBean.setCreatedTime(new Date());
			setB(dbBean);
		}

	}

	public static class Obtain extends IduOther<Obtain, WaQRCode> {

		public void run() {
			WaQRCode bean = WaQRCode.load(WaQRCode.class, getB().getPkey());
			Map<String, Object> result = obtain(bean, bean.gtAccount(), bean.getSceneKey()+".jpg");

			bean.setUrl((String)result.get("url"));
			bean.setImgUrl((String)result.get("imgUrl"));
			if(bean.gtType()==Wx.OQRCodeType.TEMPORARY) {
				Calendar time = Calendar.getInstance();
				time.add(Calendar.SECOND, (int)bean.getValidTerm().doubleValue()*24*60*60);
				bean.setExpireTime(time.getTime());
			}
			bean.upd();
			setB(bean);
		}
	}
	public static Map<String, Object> obtain(boolean temporary, String sceneKey, BigDecimal validTerm, WxAccount account, String filename) {
		JSONObject json = qrcode2json(temporary, sceneKey, validTerm);
		return obtain(json, account, filename);
	}
	public static Map<String, Object> obtain(WaQRCode qrcode, WxAccount account, String filename) {
		JSONObject json = qrcode2json(qrcode);
		return obtain(json, account, filename);
	}	
	public static Map<String, Object> obtain(JSONObject json, WxAccount account, String filename) {
		Map<String, Object> resultMap = null;
		try {
			String accessToken = WxAccountDAO.getAccessToken(account);
			String requestUrl = qrcode_create_url.replaceAll("TOKEN", accessToken);
			JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", json.toString());
			//判断是否执行成功
			if (!result.has("errcode")) {
				//取到ticket
				String ticket = result.getString("ticket");
				String url = result.getString("url");
				//7.通过ticket获取图片
				String qrcodeimgurl = showqrcode_url.replace("TICKET", ticket);
				//文件名
				//存储物理路径 upload/weixinqrcode/
				String targetPath = ABSTRACT_PATH + "/" + filename;
				File target = new File(targetPath);
				WeixinUtil.saveHttpImage(qrcodeimgurl, "GET", target);
				resultMap = new HashMap<String, Object>();
				resultMap.put("url", url);
				resultMap.put("imgUrl", RELATIVE_PATH+"/"+filename);
			} else {
				throw LOG.err(result.getInt("errcode") + "", "二维码生成失败！错误信息为：" + result.getString("errmsg"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	//根据场景值sceneKey，在指定地点path，生成一个临时二维码图片，图片名为sceneKey.jpg -15/09/15
	public static void temporary(long sceneKey, String path) throws JSONException {
		JSONObject json = new JSONObject();
		JSONObject scene = new JSONObject();
		scene.put("scene_id", sceneKey);
		json.put("expire_seconds", 604800);
		json.put("action_name", "QR_SCENE");
		json.put("action_info", new JSONObject().put("scene", scene));
		List<WxAccount> accounts = WxAccount.list(WxAccount.class, "1=1", false);
		if(accounts.size()==0) {
			throw LOG.err("noAccount","二维码生成失败！尚未配置公众号");
		}
		WxAccount account = accounts.get(0);
		String accessToken = WxAccountDAO.getAccessToken(account);
		String requestUrl = qrcode_create_url.replaceAll("TOKEN", accessToken);
		JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", json.toString());

		//判断是否执行成功
		if (!result.has("errcode")) {
			//取到ticket
			String ticket = result.getString("ticket");
			//二维码的解析地址
			String url = result.getString("url");
			//7.通过ticket获取图片
			String qrcodeimgurl = showqrcode_url.replace("TICKET", ticket);
			//文件名
			String filename = sceneKey + ".jpg";
			//存储物理路径 upload/weixinqrcode/
			String targetPath = path + "/" + filename;
			File target = new File(targetPath);
			WeixinUtil.saveHttpImage(qrcodeimgurl, "GET", target);
		} else {
			throw LOG.err(result.getInt("errcode") + "", "二维码生成失败！错误信息为：" + result.getString("errmsg"));
		}
	}
	
	private static JSONObject qrcode2json(WaQRCode bean) {
		return qrcode2json(bean.gtType()==OQRCodeType.TEMPORARY, bean.getSceneKey(), bean.getValidTerm());
	}
	/**
	 * 
	 * @param temporary 是否临时二维码
	 * @param sceneKey 场景值
	 * @param validTerm 有效天数
	 * @return
	 */
	private static JSONObject qrcode2json(boolean temporary, String sceneKey, BigDecimal validTerm) {
		JSONObject json = new JSONObject();
		JSONObject scene = new JSONObject();
		try {
			if (temporary) {
				scene.put("scene_id", sceneKey);
				json.put("expire_seconds", (int) validTerm.doubleValue() * 24 * 60 * 60);
				json.put("action_name", "QR_SCENE");
			} else {
				try {
					json.put("action_name", "QR_LIMIT_SCENE");
					Integer.parseInt(sceneKey);
					scene.put("scene_id", sceneKey);
				} catch (NumberFormatException e) {
					json.put("action_name", "QR_LIMIT_STR_SCENE");
					scene.put("scene_str", sceneKey);
				}
			}
			json.put("action_info", new JSONObject().put("scene", scene));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static void valid(WaQRCode bean) {
		BigDecimal validTerm = bean.getValidTerm();
		if (validTerm.compareTo(BigDecimal.valueOf(30)) > 0 || validTerm.compareTo(BigDecimal.ZERO) <= 0) {
			throw LOG.err("validerr", "有效天数必须在0~30天");
		}
		if (bean.gtType() == Wx.OQRCodeType.TEMPORARY) {
			//临时二维码的场景值 为32位非零整型
			try {
				long key = Long.parseLong(bean.getSceneKey());
				if (key < 0 || key > (long) 4294967295L) {
					throw LOG.err("validerr", "场景值超出范围（临时二维码的场景值为0~4294967295的数字类型）");
				}
			} catch (NumberFormatException e) {
				throw LOG.err("validerr", "场景值超出范围（临时二维码的场景值为0~4294967295的数字类型）");
			}
		} else {
			//永久二维码的场景值 为数字类型时 最大值为100000，字符串类型时 长度限制为1到64；
			try {
				int key = Integer.parseInt(bean.getSceneKey());
				if (key < 0 || key > 100000) {
					throw LOG.err("validerr", "场景值超出范围（永久二维码的数字型场景值在0~100000之间）");
				}
			} catch (NumberFormatException e) {
				if (bean.getSceneKey().length() > 64) {
					throw LOG.err("validerr", "场景值超出范围（永久二维码的字符串场景值 长度限制为1到64）");
				}
			}
		}
	}
	
	public void scan(int account, String accountId, String openId, String createTime, String SceneKey) throws UnsupportedEncodingException, JSONException {
		WxUserDAO.subscribe(accountId, openId, createTime, SceneKey);
	}

}
