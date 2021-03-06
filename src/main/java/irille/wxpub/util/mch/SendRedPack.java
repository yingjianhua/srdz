package irille.wxpub.util.mch;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import irille.pub.Log;
import irille.wpt.tools.TradeNoFactory;
import irille.wx.wx.WxAccount;
import irille.wxpub.util.MessageUtil;
import irille.wxpub.util.WeixinUtil;

public class SendRedPack extends MchUtil {
	private static final Log LOG = new Log(SendRedPack.class);
	private static final String SEND_RED_PACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	@Sendable
	protected String sign,nonce_str;
	@Sendable
	protected String mch_billno,mch_id,wxappid,send_name,re_openid,wishing,client_ip,act_name,remark;
	@Sendable
	protected int total_amount, total_num;

	private SendRedPack(WxAccount account, String send_name, String re_openid, int total_amount, 
			String wishing, String client_ip, String act_name, String remark)
					throws NoSuchAlgorithmException {
		this.mch_billno = TradeNoFactory.createMchBillNo(account.getMchId());
		this.mch_id = account.getMchId();
		this.wxappid = account.getAccountAppid();
		this.send_name = send_name;
		this.re_openid = re_openid;
		this.total_amount = total_amount;
		this.total_num = 1;
		this.wishing = wishing;
		this.client_ip = client_ip;
		this.act_name = act_name;
		this.remark = remark;
		this.nonce_str = createNonceStr(32);
		this.sign = createSign(account.getMchKey());
	}

	public static Map<String, String> sendRedPack(WxAccount account, String openId, String send_name, int total_amount,
			String wishing, String client_ip, String act_name, String remark) {
		Map<String, String> map_result = null;
		try {
			SendRedPack uorder = new SendRedPack(account, send_name, openId, total_amount, wishing, client_ip, act_name, remark);
			String result = WeixinUtil.httpPost(SEND_RED_PACK_URL, uorder.trans2XML(), createSSLFactory(account));
			map_result = MessageUtil.parseXml(result);
		} catch (Exception e) {
			e.printStackTrace();
			throw LOG.err("err", "红包发送出错，错误未知");
		}
		map_result = new TreeMap<String, String>(map_result);

		if(!map_result.containsKey("return_code") || map_result.get("return_code").equals("FAIL")) throw LOG.err("err", map_result.get("return_msg"));
		if(!map_result.containsKey("result_code") || map_result.get("result_code").equals("FAIL")) throw LOG.err(map_result.get("err_code"), map_result.get("err_code_des"));
		
		return map_result;
	}
}
