package irille.action.wx;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import irille.action.ActionBase;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.wx.wa.WaQRCodeDAO;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAutoDAO;
import irille.wx.wx.WxMassMessageDAO;
import irille.wx.wx.WxMenuDAO;
import irille.wx.wx.WxMessageDAO;
import irille.wx.wx.WxSubscribeDAO;
import irille.wx.wx.WxUser;
import irille.wx.wx.WxUserDAO;
import irille.wxpub.bean.TextMessageResp;
import irille.wxpub.util.Crypto;
import irille.wxpub.util.MessageUtil;
import irille.wxpub.util.SignUtil;

public class WechatAction extends ActionBase {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		actionErr("action中beanClazz未重写!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WechatAction.class);
	private String _appid;
	private String _secret;
	private String _code;
	private JSONObject _param;
	private String _pageParam;
	private JSONObject _uparams;

	public String getAppid() {
		return _appid;
	}

	public void setAppid(String appid) {
		_appid = appid;
	}

	public String getSecret() {
		return _secret;
	}

	public void setSecret(String secret) {
		_secret = secret;
	}

	public String getCode() {
		return _code;
	}

	public void setCode(String code) {
		_code = code;
	}

	public JSONObject getParam() {
		return _param;
	}

	public void setParam(String param) throws JSONException {
		_param = new JSONObject(Crypto.decrypt(param));
	}

	public String getPageParam() {
		return _pageParam;
	}

	public void setPageParam(String pageParam) {
		_pageParam = pageParam;
	}

	public JSONObject getUparams() {
		return _uparams;
	}

	public void setUparams(String uparams) throws UnsupportedEncodingException,
			JSONException {
		_uparams = new JSONObject(URLDecoder.decode(uparams, "utf-8"));
	}

	public Class beanClazz() {
		throw LOG.err(Msgs.actionErr);
	}

	// 微信的入口
	public String execute() throws Exception {
		String method = ServletActionContext.getRequest().getMethod();
		if (method.equals("POST"))
			wechatPost();
		else
			wechatGet();
		return NONE;
	}

	// 微信签名认证
	public void wechatGet() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out
				.println("--------------=-=-=-=-=-=-=-=-=-=-=-=-WeChat getRequest(GET)-=-=-=-=-=-=-=-=-=-=-=-=--------------");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		List<WxAccount> accounts = WxAccount.list(WxAccount.class, "", false);
		for (WxAccount account : accounts) {
			if (SignUtil.checkSignature(account.getAccountToken(), signature,
					timestamp, nonce)) {
				try {
					response.getWriter().print(echostr);
					break;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 微信消息处理
	public void wechatPost() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		System.out
				.println("--------------=-=-=-=-=-=-=-=-=-=-=-=-WeChat getRequest(POST)-=-=-=-=-=-=-=-=-=-=-=-=--------------");
		String respMessage = coreService(request);
		PrintWriter out = response.getWriter();
		System.out
				.println("----------------------===============WeChat Write================--------------------------");
		System.out.println(respMessage);
		out.print(respMessage);
		out.close();
	}

	public String coreService(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			//String respContent = "您的信息已收到，请等待客服回复。";
			String respContent = "";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息创建时间
			String createTime = requestMap.get("CreateTime");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			// 消息id
			String msgId = requestMap.get("MsgId");

			int account = WxAccount.loadUniqueAccountId(false, toUserName).getPkey();
			
			// 【微信触发类型】文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				// 记录用户发送的消息
				WxMessageDAO.autoAdd(content, toUserName, fromUserName,
						createTime, Long.parseLong(msgId));
				// 根据关键字自动回复
				respMessage = WxAutoDAO.autoReceive(content, toUserName,
						fromUserName, createTime);
			}
			// 【微信触发类型】图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "您发送的是图片消息！";
			}
			// 【微信触发类型】地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 【微信触发类型】链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 【微信触发类型】音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				// 判断是否包含语音识别结果列，包含则开启了语音识别功能。将进行业务处理
				if (requestMap.containsKey("Recognition")) {
					// content = requestMap.get("Recognition");
				} else {
					respContent = "您发送的是音频消息！";
				}
			}
			// 【微信触发类型】事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				System.out
						.println("------------微信客户端发送请求------------------【微信触发类型】事件推送---");
				// 事件类型
				String eventType = requestMap.get("Event");
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					if (requestMap.containsKey("EventKey")
							&& !requestMap.get("EventKey").equals("")) {
						//通过扫描二维码关注
						String eventKey = requestMap.get("EventKey");
						//未关注的扫描二维码，场景值前面会有前缀 qrscene_ ，需要先去掉
						if(eventKey.startsWith("qrscene_")) eventKey = eventKey.substring(8);
						String ticket = requestMap.get("Ticket");
						System.out.println("-----------------扫描二维码关注-------------------");
						System.out.println("FromUserName:"+fromUserName);
						System.out.println("eventKey:"+eventKey);
						WxUserDAO.subscribe(toUserName, fromUserName, createTime, eventKey);// 将关注用户添加到数据库中
						respMessage = WxSubscribeDAO.getMessage(account, toUserName, fromUserName);//回复关注欢迎语
					} else {
						// 通过普通方式关注
						System.out.println("-----------------扫描二维码关注-------------------");
						System.out.println("FromUserName:"+fromUserName);
						WxUserDAO.subscribe(toUserName, fromUserName, createTime, null);// 将关注用户添加到数据库中
						respMessage = WxSubscribeDAO.getMessage(account, toUserName, fromUserName);// 回复关注欢迎语
					}
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
					// 扫描二维码
					String eventKey = requestMap.get("EventKey");
					String ticket = requestMap.get("Ticket");
					System.out.println("-----------------扫描二维码-------------------");
					System.out.println("FromUserName:"+fromUserName);
					System.out.println("eventKey:"+eventKey);
					WaQRCodeDAO qrDao = new WaQRCodeDAO();
					qrDao.scan(account, toUserName, fromUserName, createTime, eventKey);
					respMessage = WxSubscribeDAO.getMessage(account, toUserName, fromUserName);//回复关注欢迎语
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消关注
					System.out.println("-----------------取消关注-------------------");
					System.out.println("FromUserName:"+fromUserName);
					WxUser user = WxUserDAO.unsubscribe(toUserName, fromUserName, createTime);// 将数据库中关注用户的关注状态设置为取消关注
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {
					// 上报地理位置

					// 维度
					String latitude = requestMap.get("Latitude");
					// 经度
					String longitude = requestMap.get("Longitude");
					// 精度
					String precision = requestMap.get("Precision");
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					String eventKey = requestMap.get("EventKey");
					respMessage = WxMenuDAO.doMenuClick(toUserName,fromUserName, createTime, eventKey);
					// 自定义菜单点击事件(自定义菜单未事件类型)
				} else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {
					String eventKey = requestMap.get("EventKey");
					// 自定义菜单点击事件(自定义菜单未链接类型)
				} else if (eventType
						.equals(MessageUtil.EVENT_TYPE_MASSSENDJOBFINISH)) {
					// 群发结果事件
					long msgID = Long.parseLong(requestMap.get("MsgID"));
					String status = requestMap.get("Status");
					WxMassMessageDAO.jobFinish(msgID, status);
				}
			}
//			try {
//				String LISTEN = ServletActionContext.getServletContext()
//						.getInitParameter("listenurl");
//				if (LISTEN != null && !LISTEN.equals("")) {
//					JSONObject jsonobj = new JSONObject(requestMap);
//					JSONObject myres = WeixinUtil.httpRequest(LISTEN, "POST",
//							jsonobj.toString());
//					respMessage = myres.getString("xml");
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			if(respMessage == null) {
				respMessage = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}

