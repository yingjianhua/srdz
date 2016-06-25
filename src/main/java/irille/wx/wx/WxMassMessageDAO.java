package irille.wx.wx;

import irille.core.sys.SysTable;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.wx.wm.MessageMassable;
import irille.wxpub.util.WeixinUtil;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class WxMassMessageDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		typeErr("暂时不支持该消息类型"),
		userErr("该关注用户不存在"),
		overLimitErr("openID超出范围"),
		secondMenuErr("一级菜单【{0}】下已有5个二级菜单"),
		thirdMenuErr("不能创建三级菜单"),
		building("功能正在开发中！")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	
	//根据分组进行群发
	public final static String message_mass_sendall_url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	//根据OpenID列表群发【订阅号不可用，服务号认证后可用】
	public final static String message_mass_send_url = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	//删除群发【订阅号与服务号认证后均可用】
	public final static String message_mass_delete_url = "https://api.weixin.qq.com/cgi-bin/message/mass/delete?access_token=ACCESS_TOKEN";
	//预览接口【订阅号与服务号认证后均可用】
	public final static String message_mass_preview_url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token=ACCESS_TOKEN";
	//查询群发消息发送状态【订阅号与服务号认证后均可用】
	public final static String message_mass_get_url = "https://api.weixin.qq.com/cgi-bin/message/mass/get?access_token=ACCESS_TOKEN";
	//上传图文消息内的图片获取URL【订阅号与服务号认证后均可用】 
	public final static String media_uploading_url = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	//上传图文消息素材【订阅号与服务号认证后均可用】 
	public final static String media_uploadnews_url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token=ACCESS_TOKEN";
	
  public static final Log LOG = new Log(WxMassMessageDAO.class);

  /**
   * 对微信用户发送群发预览消息
   * @param tempclass 消息素材的类名
   * @param tempPkey 消息素材的pkey
   * @param toUser 发送用户 例如：“1，5，9”
   * @since 2016/2/18
   * @author yingjianhua
   */
	public static void preview(String tempclass, int tempPkey, String toUser) {
		try {
			String accessToken = WxAccountDAO.getAccessToken(Idu.getUser());
			String requestUrl = message_mass_preview_url.replaceAll("ACCESS_TOKEN",accessToken);
			Object dao = Class.forName(tempclass+"DAO").newInstance();
			if(dao instanceof MessageMassable) {
				String [] toUsers = toUser.split(",");
				for(String line : toUsers) {
					String json = ((MessageMassable)dao).transform4Preview(Integer.parseInt(line), tempPkey);
					JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", json);
					if (!result.has("errcode") || result.getInt("errcode") != 0) {
						throw LOG.err("" + result.getInt("errcode"), "" + result.getString("errmsg"));
					}
				}
			} else {
				throw LOG.err(Msgs.building);
			}
		} catch (NumberFormatException | InstantiationException | IllegalAccessException | ClassNotFoundException | JSONException e) {
			throw LOG.err("unknownErr", "未知错误导致群发预览失败，请联系管理员");
		}
	}
	/**
	 * 对微信分组或所有用户进行群发
	 * @param tempclass 消息素材的类名
	 * @param tempPkey 消息素材的pkey
	 * @param isToAll 是否针对全部用户
	 * @param wxGroupPkey 群发分组
	 * @since 2016/2/18
	 * @author yingjianhua
	 */
	public static void mass(String tempclass, int tempPkey, boolean isToAll, Integer wxGroupPkey) {
		try {
			String accessToken = WxAccountDAO.getAccessToken(Idu.getUser());
			String requestUrl = message_mass_sendall_url.replaceAll("ACCESS_TOKEN",accessToken);
			Object dao = Class.forName(tempclass+"DAO").newInstance();
			if(dao instanceof MessageMassable) {
				MessageMassable mdao = (MessageMassable)dao;
				String json = mdao.transform4Mass(isToAll, wxGroupPkey, tempPkey);
				JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", json);
				if (!result.has("errcode") || result.getInt("errcode") != 0) {
					throw LOG.err("" + result.getInt("errcode"), "" + result.getString("errmsg"));
				}
				//新增群发纪录
				WxMassMessage bean = new WxMassMessage();
				bean.stMassmsgType(mdao.getMessageType());
				bean.setTemplate(Bean.gtLongPkey(tempPkey, SysTable.gtTable(Class.forName(tempclass)).getPkey()));
				bean.stIsToAll(isToAll);
				bean.setUserGroup(WxUserGroup.load(WxUserGroup.class, wxGroupPkey).getExtName());
				bean.setCreatedTime(new Date());
				bean.setMsgId(result.getLong("msg_id"));
				bean.setMsgDataId(result.has("msg_data_id")?result.getLong("msg_data_id"):null);
				bean.stAccount(WxAccountDAO.getByUser(Idu.getUser()));
				bean.setRowVersion(Short.valueOf((short)0));
				bean.ins();
			} else {
				throw LOG.err(Msgs.building);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | JSONException e) {
			throw LOG.err("unknownErr", "未知错误导致群发失败，请联系管理员");
		}
	}
  private static void delete() {
  	//删除群发
  }
  public static void jobFinish(long msgID, String status) {
  	String where = Idu.sqlString("{0}=?", WxMassMessage.T.MSG_ID);
		WxMassMessage bean = WxMassMessage.list(WxMassMessage.class, where, true, msgID).get(0);
		bean.setRem(status);
		bean.setCompleteTime(new Date());
		bean.upd();
  }
  //根据用户分组进行群发
  private static void send(WxUserGroup group, boolean is_to_all, Wx.OMassMsgType msg_type, String content) throws JSONException {
  	String accessToken = WxAccountDAO.getAccessToken(Idu.getUser());
  	String outputStr = getJson(group, is_to_all, msg_type, content);
  	String requestUrl = message_mass_sendall_url.replace("ACCESS_TOKEN", accessToken);
  	
  	JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", outputStr);
  	if(!result.get("errcode").equals(0)) {
  		throw LOG.err("errcode:"+result.get("errcode"), ""+result.get("errmsg"));
  	}
  }
  //获取根据用户分组群发的json
  private static String getJson(WxUserGroup group, boolean is_to_all, Wx.OMassMsgType msg_type, String content) {
  	StringBuffer buf = new StringBuffer();
  	buf.append("{");
  	buf.append("\"filter\":{\"is_to_all\":"+is_to_all+"\"group_id\":\""+group.getWxid()+"\"},");
  	buf.append(getMsgJson(msg_type, content));
  	buf.append("}");
  	return buf.toString();
  }
  
  //获取根据openID群发的json
  private static String getJson(String openID, Wx.OMassMsgType msg_type, String content) {
  	StringBuffer buf = new StringBuffer();
  	buf.append("{");
  	String [] list = openID.split(",");
  	if(list.length<2||list.length>10000) {
  		throw LOG.err(Msgs.overLimitErr);
  	}
  	buf.append("\"touser\":[");
  	for(String line:list) {
  		buf.append("\""+line+"\",");
  	}
  	buf.append("],");
  	buf.append(getMsgJson(msg_type, content));
  	buf.append("}");
  	return buf.toString();
  }
  //获取消息部分的json
  private static String getMsgJson(Wx.OMassMsgType msg_type, String content) {
  	StringBuffer buf = new StringBuffer();
  	if(msg_type == Wx.OMassMsgType.ARTICLE){//图文消息
  		buf.append("\"mpnews\":{\"media_id\":\""+content+"\"},\"msgtype\":\"mpnews\"");
  	}else	if(msg_type==Wx.OMassMsgType.TEXT) {//文本消息
  		buf.append("\"text\":{\"content\":\""+content+"\"},\"msgtype\":\"text\"");
  	} else if(msg_type == Wx.OMassMsgType.VOICE){//语音消息
  		buf.append("\"voice\":{\"media_id\":\""+content+"\"},\"msgtype\":\"voice\"");
  	} else if(msg_type == Wx.OMassMsgType.IMAGE){//图片消息
  		buf.append("\"image\":{\"media_id\":\""+content+"\"},\"msgtype\":\"image\"");
  	} else if(msg_type == Wx.OMassMsgType.VIDEO){//视频消息
  		buf.append("\"mpvideo\":{\"media_id\":\""+content+"\"},\"msgtype\":\"mpvideo\"");
  	} else if(msg_type == Wx.OMassMsgType.CARD){//卡券消息
  		buf.append("\"wxcard\":{\"card_id\":\""+content+"\"},\"msgtype\":\"wxcard\"");
  	}
  	return buf.toString();
  }
}
