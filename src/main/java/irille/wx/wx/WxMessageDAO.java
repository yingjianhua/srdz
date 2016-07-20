package irille.wx.wx;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import irille.pub.Cn;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.Idu;
import irille.pub.idu.IduOther;
import irille.wx.wpt.WptCommissionJournal;
import irille.wx.wx.Wx.OWxMsgDir;
import irille.wx.wx.Wx.OWxMsgType;
import irille.wxpub.util.WeixinUtil;

public class WxMessageDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		messageErr("由于该用户48小时未与你互动，你不能再主动发消息给他。直到用户下次主动发消息给你才可以对其进行回复。"),
		replyErr("回复出错。代码如下\n{0}")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
  private static final Log LOG = new Log(WxMessageDAO.class);

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
    sendTextMessage(accessToken, openId, content);
  }
  public static void sendTextMessage(final String accessToken, final String openId, final String content) {
	  LOG.info("openId:{0}", openId);
	  LOG.info("content:{0}", content);
	  final String template = "{\"touser\":\"{OPENID}\",\"msgtype\":\"text\",\"text\":{\"content\":\"{CONTENT}\"}}";
	  String url = WeixinUtil.SEND_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
	  JSONObject result = WeixinUtil.httpRequest(url, "POST", template.replace("{OPENID}", openId).replace("{CONTENT}", content));
	  if(!result.has("errcode")) {
		  
	  } else {
		  try {
			int errcode = result.getInt("errcode");
			if(errcode != 0) {
				String errmsg = result.getString("errmsg");
				LOG.err(Msgs.replyErr, errmsg);
			}
		  } catch (JSONException e) {
			  e.printStackTrace();
		  }
	  }
  }
  /**
   * 提醒粉丝的邀请人粉丝关注的消息
   * @param user
   */
  public static void notifyInvited(String accessToken, WxUser user) {
	  WxUser invited3 = null;
	  WxUser invited2 = null;
	  WxUser invited1 = null;
	  if((invited3=user.gtInvited3()) != null) {
		  sendTextMessage(accessToken, invited3.getOpenId(), user.getNickname()+"通过您的邀请成为您的粉丝!");
		  if((invited2=user.gtInvited2()) != null) {
			  sendTextMessage(accessToken, invited2.getOpenId(), "您的朋友"+invited3.getNickname()+"新增粉丝"+user.getNickname());
			  if((invited1=user.gtInvited1()) != null) {
				  sendTextMessage(accessToken, invited1.getOpenId(), "您的朋友"+invited2.getNickname()+"新增粉丝"+user.getNickname());  
			  }
		  }
	  }
  }
  /**
   * 当用户获得一份佣金的时候，提醒用户
   */
  public static void notifyCommissionJournal(String accessToken, String openId, WptCommissionJournal journal){
	  StringBuilder content = new StringBuilder();
	  content.append("您的粉丝").append(journal.getNickname());
	  content.append("在").append(DateFormat.getDateTimeInstance().format(journal.getCreateTime())).append("购物\n");
	  content.append("订单号：").append(journal.getOrderid()).append("\n");
	  content.append("订单金额：").append(journal.getPrice()).append("元\n");
	  content.append("获得分享金额：").append(journal.getCommission()).append("元\n");
	  sendTextMessage(accessToken, openId, content.toString());
  }
  public static void notifyCommissionJournal(String accessToken, WptCommissionJournal journal3, String openId3, WptCommissionJournal journal2, String openId2, WptCommissionJournal journal1, String openId1){
	  LOG.info("--------------notifyCommissionJournal:start--------------");
	  if(journal3!=null) {
		  LOG.info("journal3:"+journal3);
		  notifyCommissionJournal(accessToken, openId3, journal3);
		  if(journal2!=null) {
			  LOG.info("journal2:"+journal2);
			  notifyCommissionJournal(accessToken, openId2, journal2);
			  if(journal1!=null) {
				  LOG.info("journal1:"+journal1);
				  notifyCommissionJournal(accessToken, openId1, journal1);
			  }
		  }
	  }
	  LOG.info("--------------notifyCommissionJournal:end--------------");
  }
}
