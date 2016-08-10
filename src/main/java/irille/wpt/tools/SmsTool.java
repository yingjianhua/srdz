package irille.wpt.tools;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.wx.wx.WxMessageDAO;
import irille.wx.wx.WxUser;
import irille.wxpub.util.HttpRequestUtil;
import irille.wxpub.util.ReadXmlUtil;

@Component
public class SmsTool {
	public static final Log LOG = new Log(SmsTool.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		faildErr("发送失败,【{0}】,余额为【{1}】"),
		xmlErr("{0}，请联系管理员！"),
		;
		private String _msg;

		private Msgs(String msg) {
			_msg = msg;
		}

		public String getMsg() {
			return _msg;
		}
	} // @formatter:on
	public void doSent(String mobile, String content){
		try {
			/***********短信机的实现***********************/
			LOG.info("mobile:{0}", mobile);
			LOG.info("content:{0}", content);
			content = URLEncoder.encode(content, "utf-8");
			//短信机请求，获取返回的xml文件格式的字符串
			String xmlStr = HttpRequestUtil.httpRequestPost(
					"http://218.244.141.161:8888/sms.aspx?action=send&userid=339&account=bl1174&password=xiangshiguang1030&mobile="+mobile+"&content="
							+ content + "&sendTime=&extno=");
			
			//获取节点系列mobile对应的值
			List<String> status = ReadXmlUtil.getValues("returnstatus", xmlStr);
			List<String> messages = ReadXmlUtil.getValues("message", xmlStr);
			List<String> remainpoint = ReadXmlUtil.getValues("remainpoint", xmlStr);
			if (status.get(0).equals("Faild"))
				throw LOG.err(Msgs.faildErr,messages.get(0),remainpoint.get(0));
			/**************************************/
		} catch (UnsupportedEncodingException e) {
			throw LOG.err(Msgs.xmlErr,"编码异常");
		} catch (ParserConfigurationException e) {
			throw LOG.err(Msgs.xmlErr,"XML异常");
		} catch (SAXException e) {
			throw LOG.err(Msgs.xmlErr,"SAX解析异常");
		} catch (IOException e) {
			throw LOG.err(Msgs.xmlErr,"IO异常");
		}
	}
	public void doSend(String accessToken, List<WxUser> users, String content) {
		for(WxUser user:users) {
			WxMessageDAO.sendTextMessage(accessToken, user.getOpenId(), content);
		}
	}
	public void doSend(String accessToken, WxUser user, String content) {
		WxMessageDAO.sendTextMessage(accessToken, user.getOpenId(), content);
	}
	public static void main(String[] args) {
		new SmsTool().doSent("15825662366", "【享食光】私人订制有新订单生成,内容如下:订单号:201604226972 餐厅 :重庆火锅 用餐时间:2016-04-22 20:30 联系人:小凡 联系方式:手机18702765875 服务 :[摄像 拍照 名卡 海报 头条 制服 ] 套餐:重庆火锅最便宜的套餐 备注:备注");
	}
}
