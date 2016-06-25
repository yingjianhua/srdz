package irille.wx.wm;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wm.WmVoiceDAO.Msgs;
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

public class WmImageDAO implements MessageMassable{
	public static final Log LOG = new Log(WmImage.class);

	public static class Ins extends IduIns<Ins, WmImage> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().stStatus(Wx.OSyncStatus.INIT);
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WmImage> {
		@Override
		public void before() {
			super.before();
			WmImage dbBean = load(getB().getPkey());
			// 假如imgUrl改变，把media_id清空，需要重新上传后才能使用
			if (!dbBean.getImgUrl().equals(getB().getImgUrl())) {
				dbBean.stStatus(Wx.OSyncStatus.INIT);
			}
			PropertyUtils.copyProperties(dbBean, getB(), WmImage.T.NAME,
					WmImage.T.IMG_URL);
			setB(dbBean);
		}
	}

	public static class Del extends IduDel<Del, WmImage> {
		@Override
		public void before() {
			// 删除前先判断是否已经被使用
			Long longpkey = getB().gtLongPkey();
			haveBeUsed(WxSubscribe.class, WxSubscribe.T.TEMPLATE, longpkey);
			haveBeUsed(WxAuto.class, WxAuto.T.TEMPLATE, longpkey);
			haveBeUsed(WxMenu.class, WxMenu.T.TEMPLATE, longpkey);
			super.before();
		}
	}

	/**
	 * 同步图片素材
	 * @param voice
	 * @since 2016/2/19
	 */
	public static WmImage sync(WmImage image) {
		try {
			JSONObject result = WxMaterialUtil.addMaterial(WxAccountDAO.getAccessToken(Idu.getUser()), image.getImgUrl(), WxMaterialUtil.MaterialType.IMAGE);
			image.stStatus(OSyncStatus.SYNC);
			image.setMediaId(result.getString("media_id"));
			image.upd();
			return image;
		} catch (JSONException e) {
			throw LOG.err(Msgs.syncErr);
		}
	}
	/**
	 * 同步图片素材
	 * @param voice
	 * @since 2016/2/19
	 */
	public static WmImage sync(Serializable image) {
		return sync(WmImage.load(WmImage.class, image));
	}
	/**
	 * 取消同步
	 * @param voice
	 * @return
	 * @since 2016/2/19
	 */
	public static WmImage unsync(WmImage image) {
		// 判断是否被使用，包括（自动回复，自定义菜单，关注欢迎语）
	    // 删除永久素材，删除media_id，并将状态修改为未同步
		if(image.gtStatus() != OSyncStatus.SYNC) return image;
		haveBeUsed(image);
		try {
			WxMaterialUtil.delMaterial(WxAccountDAO.getAccessToken(Idu.getUser()), image.getMediaId());
			image.setMediaId(null);
			image.stStatus(OSyncStatus.INIT);
			image.upd();
		} catch (JSONException e) {
			throw LOG.err(Msgs.unsyncErr);
		}
		
		return image;
	}
	/**
	 * 取消同步
	 * @param voice
	 * @return
	 * @since 2016/2/19
	 */
	public static WmImage unsync(Serializable image) {
		return unsync(WmImage.load(WmImage.class, image));
	}
	
	/**
	 * 检查是否已经被使用了（包括自动回复，自定义菜单，关注欢迎语）
	 * @author yingjianhua
	 * @since 2016/2/19
	 */
	private static void haveBeUsed(WmImage image) {
		Long longPkey = Bean.gtLongPkey(image.getPkey(), WmImage.TB.getID());
		Idu.haveBeUsed(WxAuto.class, WxAuto.T.TEMPLATE, longPkey);
		Idu.haveBeUsed(WxMenu.class, WxMenu.T.TEMPLATE, longPkey);
		Idu.haveBeUsed(WxSubscribe.class, WxSubscribe.T.TEMPLATE, longPkey);
	}

	public static String transform2XML(Integer pkey, String accountId,String openId, String createTime) {
		WmImage bean = WmImage.load(WmImage.class, pkey);
		return transform2XML(bean, accountId, openId, createTime);
	}

	public static String transform2XML(WmImage bean, String accountId,String openId, String createTime) {
		StringBuffer result = new StringBuffer();
		
		result.append("<xml>");
		result.append("<ToUserName><![CDATA[" + openId + "]]></ToUserName>");
		result.append("<FromUserName><![CDATA[" + accountId+ "]]></FromUserName>");
		result.append("<CreateTime>" + createTime + "</CreateTime>");
		result.append("<MsgType><![CDATA[image]]></MsgType>");
		result.append("<Image>");
		result.append("<MediaId><![CDATA[" + bean.getMediaId()+ "]]></MediaId>");
		result.append("</Image>");
		result.append("</xml>");

		return result.toString();
	}
	@Override
	public OMassMsgType getMessageType() {
		return OMassMsgType.IMAGE;
	}

	@Override
	public String transform4Preview(Integer wxUserPkey, Integer tempPkey) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("touser", Bean.load(WxUser.class, wxUserPkey).getOpenId());
		json.put("image", new JSONObject().put("media_id", Bean.load(WmImage.class, tempPkey).getMediaId()));
		json.put("msgtype", "image");
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
		json.put("image", new JSONObject().put("media_id", Bean.load(WmImage.class, tempPkey).getMediaId()));
		json.put("msgtype", "image");
		return json.toString();
	}
}
