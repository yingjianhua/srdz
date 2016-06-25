package irille.wx.wm;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.Wx;
import irille.wx.wx.Wx.OMassMsgType;
import irille.wx.wx.Wx.OSyncStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxAuto;
import irille.wx.wx.WxMenu;
import irille.wx.wx.WxSubscribe;
import irille.wx.wx.WxUser;
import irille.wx.wx.WxUserGroup;
import irille.wxpub.util.WxMaterialUtil;
import irille.wxpub.util.WxMaterialUtil.MaterialType;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class WmVideoDAO implements MessageMassable{
	public static final Log LOG = new Log(WmVideo.class);

	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		unsyncErr("取消同步异常，请联系管理员"),
		syncErr("同步异常，请联系管理员");
		private String _msg;

		private Msgs(String msg) {
			_msg = msg;
		}

		public String getMsg() {
			return _msg;
		}
	} // @formatter:on
	
	public static class Ins extends IduIns<Ins, WmVideo> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			getB().stStatus(Wx.OSyncStatus.INIT);

		}
	}

	public static class Upd extends IduUpd<Upd, WmVideo> {
		@Override
		public void before() {
			super.before();
			WmVideo dbBean = load(getB().getPkey());
			if(!dbBean.getVideoUrl().equals(getB().getVideoUrl())) {
				dbBean.stStatus(Wx.OSyncStatus.INIT);
			}
			PropertyUtils.copyProperties(dbBean, getB(), WmVideo.T.TITLE, WmVideo.T.DESCRIPTION, WmVideo.T.VIDEO_URL);
			setB(dbBean);
		}
	}
	
	/**
	 * 同步视频素材
	 * @param voice
	 * @since 2016/2/19
	 */
	public static WmVideo sync(WmVideo video) {
		try {
			JSONObject result = WxMaterialUtil.addVideo( WxAccountDAO.getAccessToken(Idu.getUser()), video.getVideoUrl(), video.getTitle(), video.getDescription());
			video.stStatus(OSyncStatus.SYNC);
			video.setMediaId(result.getString("media_id"));
			video.upd();
			return video;
		} catch (JSONException e) {
			throw LOG.err(Msgs.syncErr);
		}
	}
	/**
	 * 同步视频素材
	 * @param voice
	 * @since 2016/2/19
	 */
	public static WmVideo sync(Serializable video) {
		return sync(WmVideo.load(WmVideo.class, video));
	}
	/**
	 * 取消同步
	 * @param voice
	 * @return
	 * @since 2016/2/19
	 */
	public static WmVideo unsync(WmVideo video) {
		// 判断是否被使用，包括（自动回复，自定义菜单，关注欢迎语）
	    // 删除永久素材，删除media_id，并将状态修改为未同步
		if(video.gtStatus() != OSyncStatus.SYNC) return video;
		haveBeUsed(video);
		try {
			WxMaterialUtil.delMaterial(WxAccountDAO.getAccessToken(Idu.getUser()), video.getMediaId());
			video.setMediaId(null);
			video.stStatus(OSyncStatus.INIT);
			video.upd();
		} catch (JSONException e) {
			throw LOG.err(Msgs.unsyncErr);
		}
		
		return video;
	}
	/**
	 * 取消同步
	 * @param voice
	 * @return
	 * @since 2016/2/19
	 */
	public static WmVideo unsync(Serializable video) {
		return unsync(WmVideo.load(WmVideo.class, video));
	}
	
	/**
	 * 检查是否已经被使用了（包括自动回复，自定义菜单，关注欢迎语）
	 * @author yingjianhua
	 * @since 2016/2/19
	 */
	private static void haveBeUsed(WmVideo video) {
		Long longPkey = Bean.gtLongPkey(video.getPkey(), WmVideo.TB.getID());
		Idu.haveBeUsed(WxAuto.class, WxAuto.T.TEMPLATE, longPkey);
		Idu.haveBeUsed(WxMenu.class, WxMenu.T.TEMPLATE, longPkey);
		Idu.haveBeUsed(WxSubscribe.class, WxSubscribe.T.TEMPLATE, longPkey);
	}
	
	public static String transform2XML(Integer pkey, String accountId,String openId, String createTime) {
		WmVideo bean = WmVideo.load(WmVideo.class, pkey);
		return transform2XML(bean, accountId, openId, createTime);
	}

	public static String transform2XML(WmVideo bean, String accountId,String openId, String createTime) {
		StringBuffer result = new StringBuffer();
		
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + openId + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + accountId+ "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[video]]></MsgType>");
		result.append("<Video>");
		result.append("<MediaId><![CDATA["+ bean.getMediaId() +"]]></MediaId>");
		result.append("<Title><![CDATA["+ bean.getTitle() +"]]></Title>");
		result.append("<Description><![CDATA["+ bean.getDescription() +"]]></Description>");
		result.append("</Video>");
		result.append("</xml>");

		return result.toString();
	}
	
	@Override
	public OMassMsgType getMessageType() {
		return OMassMsgType.VIDEO;
	}

	@Override
	public String transform4Preview(Integer wxUserPkey, Integer tempPkey) throws JSONException {
		WmVideo video = Bean.load(WmVideo.class, tempPkey);
		JSONObject result = WxMaterialUtil.upload(WxAccountDAO.getAccessToken(Idu.getUser()), video.getVideoUrl(), MaterialType.VIDEO);
		result = WxMaterialUtil.uploadvideo(WxAccountDAO.getAccessToken(Idu.getUser()), result.getString("media_id"), video.getTitle(), video.getDescription());
		JSONObject json = new JSONObject();
		json.put("touser", Bean.load(WxUser.class, wxUserPkey).getOpenId());
		json.put("mpvideo", new JSONObject().put("media_id", result.getString("media_id")));
		json.put("msgtype", "mpvideo");
		return json.toString();
	}

	@Override
	public String transform4Mass(boolean isToAll, Integer wxGroupPkey, Integer tempPkey) throws JSONException {
		WmVideo video = Bean.load(WmVideo.class, tempPkey);
		JSONObject result = WxMaterialUtil.upload(WxAccountDAO.getAccessToken(Idu.getUser()), video.getVideoUrl(), MaterialType.VIDEO);
		result = WxMaterialUtil.uploadvideo(WxAccountDAO.getAccessToken(Idu.getUser()), result.getString("media_id"), video.getTitle(), video.getDescription());
		JSONObject json = new JSONObject();
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", isToAll);
		if(!isToAll) {
			filter.put("group_id", Bean.load(WxUserGroup.class, wxGroupPkey).getWxid());
		}
		json.put("filter", filter);
		json.put("mpvideo", new JSONObject().put("media_id", result.getString("media_id")));
		json.put("msgtype", "mpvideo");
		return json.toString();
	}
}
