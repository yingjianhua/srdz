package irille.wx.wax;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import irille.action.ActionWx.Msgs;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.wx.wp.WpArtShow;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wxpub.util.HttpRequestUtil;
import irille.wxpub.util.ReadXmlUtil;

public class WaxBnftPrizeDAO {
	public static final Log LOG = new Log(WaxBnftPrizeDAO.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		faildErr("发送失败,【{0}】,余额为【{1}】"),
		xmlErr("{0}，请联系管理员！"),
		uniqueErr("记录【{0}】已存在，不可操作！"),
		;
		private String _msg;

		private Msgs(String msg) {
			_msg = msg;
		}

		public String getMsg() {
			return _msg;
		}
	} // @formatter:on

	public static class Ins extends IduIns<Ins, WaxBnftPrize> {
		@Override
		public void before() {
			super.before();
			if(WaxBnftPrize.chkUniqueBnftEntry(false, getB().getBnft(), getB().getEntry()) != null)
				throw LOG.err(Msgs.uniqueErr,getB().gtEntry().getName());
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}

	}

	public static class Upd extends IduUpd<Upd, WaxBnftPrize> {
		@Override
		public void before() {
			super.before();
			WaxBnftPrize bean = WaxBnftPrize.chkUniqueBnftEntry(false, getB().getBnft(), getB().getEntry());
			WaxBnftPrize model = null;
			if (bean != null)
				if (bean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().gtEntry().getName());
				else
					model = bean;
			else
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(bean, getB(), WaxBnftPrize.T.ACCOUNT);
			setB(bean);
		}
	}

	public static class Del extends IduDel<Del, WaxBnftPrize> {

	}
	public static class DoSend extends IduOther<DoSend, WaxBnftPrize> {
		private String pkeys;
		public String getPkeys() {
			return pkeys;
		}
		public void setPkeys(String pkeys) {
			this.pkeys = pkeys;
		}
		@Override
		public void run() {
			super.run();
			String whereSql = sqlString("{0} IN (SELECT {1} FROM {2} WHERE {3} IN (" + getPkeys() + "))", WaxBnftEntry.T.PKEY, WaxBnftPrize.T.ENTRY, WaxBnftPrize.TB.getCodeSqlTb(), WaxBnftPrize.T.PKEY);
			List<WaxBnftEntry> list = WaxBnftEntry.list(WaxBnftEntry.class, whereSql, false);
			String mobiles = "";
			WaxBnft bnft = list.get(0).gtBnft();
			try {
				for (WaxBnftEntry line : list)
					mobiles += "," + line.getMobile();
				mobiles=mobiles.substring(1);
				/***********短信机的实现***********************/
				String content = "您好，小享恭喜您获得了" + bnft.getName() + "提供的" + bnft.getGift()
						+ Env.LN + "领取时间：" + bnft.getGetDate()
						+ Env.LN + "【注意】" + bnft.getRem()
						+ Env.LN + "更多精彩活动请继续关注享食光微信公众号:lexiangwenzhou_APP";
				content = URLEncoder.encode("【享食光】验证码: 2878 (享食光手机注册验证，请完成验证)，如非本人操作，请忽略本条短息。", "utf-8");
				//短信机请求，获取返回的xml文件格式的字符串
				String xmlStr = HttpRequestUtil.httpRequestPost(
						"http://218.244.141.161:8888/sms.aspx?action=send&userid=339&account=bl1174&password=123456&mobile="+mobiles+"&content="
								+ content + "&sendTime=&extno=");
				//打印获取的xml格式的字符串
				System.out.println(xmlStr);
				
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
			List<WaxBnftPrize> prizeList = WaxBnftPrize.list(WaxBnftPrize.class, WaxBnftPrize.T.PKEY.getFld().getCodeSqlField() + " IN (" + getPkeys() + ")", false);
			for (WaxBnftPrize line : prizeList) {
				line.stSendSms(true);
				setB(line);
				line.upd();
			}
		}
	}
}
