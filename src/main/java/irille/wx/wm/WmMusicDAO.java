package irille.wx.wm;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.Idu;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.DepConstants;
import irille.wx.wx.Wx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wxpub.util.WxMaterialUtil;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class WmMusicDAO {
	public static final Log LOG = new Log(WmMusic.class);

	public static class Ins extends IduIns<Ins, WmMusic> {
		@Override
		public void before() {
			super.before();
			WmMusic bean = getB();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			bean.stStatus(Wx.OSyncStatus.INIT);
			bean.setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WmMusic> {
		@Override
		public void before() {
			super.before();
			WmMusic bean = getB();
			WmMusic dbBean = load(bean.getPkey());
			if (dbBean.getThumbUrl() == null || bean.getThumbUrl() == null || !bean.getThumbUrl().equals(dbBean.getThumbUrl())) {
				bean.stStatus(Wx.OSyncStatus.INIT);
			}  else {
				bean.stStatus(dbBean.gtStatus());
			}
			PropertyUtils.copyPropertiesWithout(dbBean, bean, WmMusic.T.ACCOUNT, WmMusic.T.THUMB_MEDIA_ID);
			setB(dbBean);
		}
	}

	public static class Upload extends IduOther<Upload, WmMusic> {
		@Override
		public void run() {
			try {
				String thumb_url = getB().getThumbUrl();
				if (thumb_url == null) {
					throw LOG.err("noThumb", "请先上传缩略图");
				}
				JSONObject result = WxMaterialUtil.addMaterial(
						WxAccountDAO.getAccessToken(Idu.getUser()), thumb_url,
						WxMaterialUtil.MaterialType.THUMB);
				String media_id = result.getString("media_id");
				getB().setThumbMediaId(media_id);
				getB().stStatus(Wx.OSyncStatus.SYNC);
				getB().upd();
			} catch (JSONException e) {
				e.printStackTrace();
				throw LOG.err("uploaderr", "上传到微信服务器失败，请稍后再试或联系管理员");
			}
			super.run();
		}
	}
	public static String transform2XML(Integer pkey, String accountId,String openId, String createTime) {
		WmMusic bean = WmMusic.load(WmMusic.class, pkey);
		return transform2XML(bean, accountId, openId, createTime);
	}

	public static String transform2XML(WmMusic bean, String accountId,String openId, String createTime) {
		StringBuffer result = new StringBuffer();
		String webpath = ServletActionContext.getServletContext().getInitParameter("webPath");
		
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + openId + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + accountId+ "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[music]]></MsgType>");
		result.append("<Music>");
		result.append("<Title><![CDATA["+ bean.getTitle() +"]]></Title>");
		result.append("<Description><![CDATA["+ bean.getDescription() +"]]></Description>");
		result.append("<MusicUrl><![CDATA["+ webpath + DepConstants.FILE_SEPA + bean.getMusicUrl() +"]]></MusicUrl>");
		result.append("<HQMusicUrl><![CDATA["+ webpath + DepConstants.FILE_SEPA + bean.getHqmusicUrl() +"]]></HQMusicUrl>");
		result.append("<ThumbMediaId><![CDATA["+ bean.getThumbMediaId() +"]]></ThumbMediaId>");
		result.append("</Music>");
		result.append("</xml>");

		return result.toString();
	}
}
