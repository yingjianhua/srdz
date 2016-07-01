package irille.wxpub.util.mch;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import irille.pub.Log;
import irille.wx.wx.WxAccount;
import irille.wxpub.util.MessageUtil;
import irille.wxpub.util.WeixinUtil;

public class SendRedPack extends MchUtil {
	private static final Log LOG = new Log(RefundOrder.class);
	private static final String SEND_RED_PACK_URL = "https://api.mch.weixin.qq.com/mmpaymkttransfers/sendredpack";
	@Sendable
	private String mch_billno,mch_id,wxappid,send_name,re_openid,wishing,client_ip,act_name,remark,nonce_str,sign;
	@Sendable
	private int total_amount,total_num;

	private SendRedPack(WxAccount account, String openId, String mch_billno, String send_name, String re_openid, int total_amount,
			int total_num, String wishing, String client_ip, String act_name, String remark)
					throws NoSuchAlgorithmException {
		this.mch_billno = mch_billno;
		this.mch_id = account.getMchId();
		this.wxappid = account.getAccountAppid();
		this.send_name = send_name;
		this.re_openid = openId;
		this.total_amount = total_amount;
		this.total_num = total_num;
		this.wishing = wishing;
		this.client_ip = client_ip;
		this.act_name = act_name;
		this.remark = remark;
		this.nonce_str = createNonceStr(32);
		this.sign = createSign(account.getMchKey());
	}

	public static Map<String, String> sendRedPack(WxAccount account, String openId, String mch_billno, String send_name, String re_openid, int total_amount,
			int total_num, String wishing, String client_ip, String act_name, String remark) throws Exception {
		SendRedPack uorder = new SendRedPack(account, openId, mch_billno, send_name, re_openid, total_amount, total_num, wishing, client_ip, act_name, remark);
		String result = WeixinUtil.httpPost(SEND_RED_PACK_URL, uorder.trans2XML(), createSSLFactory(account));
		Map<String, String> map_result = MessageUtil.parseXml(result);
		map_result = new TreeMap<String, String>(map_result);

		if(!map_result.containsKey("return_code") || map_result.get("return_code").equals("FAIL")) throw LOG.err("err", map_result.get("return_msg"));
		if(!map_result.containsKey("result_code") || map_result.get("result_code").equals("FAIL")) throw LOG.err(map_result.get("err_code"), map_result.get("err_code_des"));
		
		return map_result;
	}
	public static void main(String[] args) throws Exception {
		WxAccount.TB.getCode();
		WxAccount account = WxAccount.get(WxAccount.class, 3);
		SendRedPack uorder = new SendRedPack(account, "openId", "1", "2", "3", 4, 5, "6", "7", "8", "9");
		System.out.println(uorder.trans2XML());
	}
}
