package irille.wx.wx;

import irille.pub.Cn;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.Idu;
import irille.pub.idu.IduOther;
import irille.wx.wx.Wx.OWxMsgDir;
import irille.wx.wx.Wx.OWxMsgType;
import irille.wxpub.util.WeixinUtil;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class WxMessageDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		messageErr("由于该用户48小时未与你互动，你不能再主动发消息给他。直到用户下次主动发消息给你才可以对其进行回复。"),
		replyErr("回复出错。代码如下\n{0}")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
  public static final Log LOG = new Log(WxMessageDAO.class);

  public static void autoAdd(String content, String accountId, String openId, String createTime, long msgId)
      throws Exception {
    WxMessage message = new WxMessage();
    WxAccount account = WxAccount.loadUniqueAccountId(false, accountId);
    WxUser user = WxUser.chkUniqueOpenIdAccount(false, openId, account.getPkey());
    Date date = WeixinUtil.tranDate(Long.parseLong(createTime));
    message.stWxmsgType(OWxMsgType.TEXT);
    message.stWxmsgDir(OWxMsgDir.RECEIVE);
    message.stIsReply(false);
    message.stIsCollect(false);
    message.setCreatedTime(date);
    message.setContent(content);
    message.stAccount(account);
    message.setWxUser(user.getPkey());
    message.setMsgId(msgId);
    message.ins();
  }

  public static class Reply extends IduOther<Reply, WxMessage> {
    public static Cn CN = new Cn("reply", "回复");

    @Override
    public void before() {
      super.before();
      WxAccount account = WxAccountDAO.getByUser(getUser());
      WxMessage dbBean = WxMessage.load(WxMessage.class, getB().getPkey());
      WxMessage message = new WxMessage();
      Date now = new Date();
      if ((now.getTime() - dbBean.getCreatedTime().getTime()) / 1000 / 3600 > 47)
        throw LOG.err(Msgs.messageErr);
      message.stWxmsgType(OWxMsgType.TEXT);
      message.stWxmsgDir(OWxMsgDir.REPLY);
      message.stIsReply(false);
      message.stIsCollect(false);
      message.setCreatedTime(new Date());
      message.setContent(getB().getContent());
      message.setAccount(account.getPkey());
      message.setWxUser(getB().getWxUser());
      file(message);
      message.ins();
      dbBean.stIsReply(true);
      setB(dbBean);
      getB().upd();

    }
  }

  public static void file(WxMessage message) {
    String accessToken = WxAccountDAO.getAccessToken(Idu.getUser());
    String openId = message.gtWxUser().getOpenId();
    String content = message.getContent();
    JSONObject jsonType = new JSONObject();
    JSONObject jsonMain = new JSONObject();
    JSONObject jsonObject = new JSONObject();
    try {
      jsonType.put("touser", openId);
      jsonType.put("msgtype", "text");
      jsonMain.put("content", content);
      jsonType.put("text", jsonMain);
      String url = WeixinUtil.SEND_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
      jsonObject = WeixinUtil.httpRequest(url, "POST", jsonType.toString());
      if (0 != jsonObject.getInt("errcode")) {
        throw LOG.err(Msgs.replyErr , jsonObject);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }

  }

}
