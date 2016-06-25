package irille.wx.wm;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
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

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class WmVoiceDAO implements MessageMassable {
	public static final Log LOG = new Log(WmVoice.class);

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
	
	public static class Ins extends IduIns<Ins, WmVoice> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().stStatus(Wx.OSyncStatus.INIT);
			getB().setAccount(account.getPkey());

		}
	}

	public static class Upd extends IduUpd<Upd, WmVoice> {
		@Override
		public void before() {
			super.before();
			WmVoice dbBean = load(getB().getPkey());
			if(!dbBean.getVoiceUrl().equals(getB().getVoiceUrl())) {
				dbBean.stStatus(Wx.OSyncStatus.INIT);
			}
			PropertyUtils.copyProperties(dbBean, getB(), WmVoice.T.NAME, WmVoice.T.VOICE_URL);
			setB(dbBean);
		}
	}
	
	/**
	 * 同步语音素材
	 * @param voice
	 * @since 2016/2/19
	 */
	public static WmVoice sync(WmVoice voice) {
		try {
			JSONObject result = WxMaterialUtil.addMaterial(WxAccountDAO.getAccessToken(Idu.getUser()), voice.getVoiceUrl(), WxMaterialUtil.MaterialType.VOICE);
			voice.stStatus(OSyncStatus.SYNC);
			voice.setMediaId(result.getString("media_id"));
			voice.upd();
			return voice;
		} catch (JSONException e) {
			throw LOG.err(Msgs.syncErr);
		}
	}
	/**
	 * 同步语音素材
	 * @param voice
	 * @since 2016/2/19
	 */
	public static WmVoice sync(Serializable voice) {
		return sync(WmVoice.load(WmVoice.class, voice));
	}
	/**
	 * 取消同步
	 * @param voice
	 * @return
	 * @since 2016/2/19
	 */
	public static WmVoice unsync(WmVoice voice) {
		// 判断是否被使用，包括（自动回复，自定义菜单，关注欢迎语）
	    // 删除永久素材，删除media_id，并将状态修改为未同步
		if(voice.gtStatus() != OSyncStatus.SYNC) return voice;
		haveBeUsed(voice);
		try {
			WxMaterialUtil.delMaterial(WxAccountDAO.getAccessToken(Idu.getUser()), voice.getMediaId());
			voice.setMediaId(null);
			voice.stStatus(OSyncStatus.INIT);
			voice.upd();
		} catch (JSONException e) {
			throw LOG.err(Msgs.unsyncErr);
		}
		
		return voice;
	}
	/**
	 * 取消同步
	 * @param voice
	 * @return
	 * @since 2016/2/19
	 */
	public static WmVoice unsync(Serializable voice) {
		return unsync(WmVoice.load(WmVoice.class, voice));
	}
	
	/**
	 * 检查是否已经被使用了（包括自动回复，自定义菜单，关注欢迎语）
	 * @author yingjianhua
	 * @since 2016/2/18
	 */
	private static void haveBeUsed(WmVoice voice) {
		Long longPkey = Bean.gtLongPkey(voice.getPkey(), WmVoice.TB.getID());
		Idu.haveBeUsed(WxAuto.class, WxAuto.T.TEMPLATE, longPkey);
		Idu.haveBeUsed(WxMenu.class, WxMenu.T.TEMPLATE, longPkey);
		Idu.haveBeUsed(WxSubscribe.class, WxSubscribe.T.TEMPLATE, longPkey);
	}
	
	public static String transform2XML(Integer pkey, String accountId,String openId, String createTime) {
		WmVoice bean = WmVoice.load(WmVoice.class, pkey);
		return transform2XML(bean, accountId, openId, createTime);
	}

	public static String transform2XML(WmVoice bean, String accountId,String openId, String createTime) {
		StringBuffer result = new StringBuffer();
		
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + openId + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + accountId+ "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[voice]]></MsgType>");
		result.append("<Voice>");
		result.append("<MediaId><![CDATA["+ bean.getMediaId() +"]]></MediaId>");
		result.append("</Voice>");
		result.append("</xml>");

		return result.toString();
	}
	@Override
	public OMassMsgType getMessageType() {
		return OMassMsgType.VOICE;
	}

	@Override
	public String transform4Preview(Integer wxUserPkey, Integer tempPkey) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("touser", Bean.load(WxUser.class, wxUserPkey).getOpenId());
		json.put("voice", new JSONObject().put("media_id", Bean.load(WmVoice.class, tempPkey).getMediaId()));
		json.put("msgtype", "voice");
		return json.toString();
	}

	@Override
	public String transform4Mass(boolean isToAll, Integer wxGroupPkey, Integer tempPkey) throws JSONException {
		JSONObject json = new JSONObject();
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", isToAll);
		if(!isToAll) {
			filter.put("group_id", Bean.load(WxUserGroup.class, wxGroupPkey).getWxid());
		}
		json.put("filter", filter);
		json.put("voice", new JSONObject().put("media_id", Bean.load(WmVoice.class, tempPkey).getMediaId()));
		json.put("msgtype", "voice");
		return json.toString();
	}
}
