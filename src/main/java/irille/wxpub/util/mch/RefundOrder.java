package irille.wxpub.util.mch;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import irille.pub.Log;
import irille.wx.pay.IOrder;
import irille.wx.wx.WxAccount;
import irille.wxpub.util.MessageUtil;
import irille.wxpub.util.WeixinUtil;

public class RefundOrder extends MchUtil {
	private static final Log LOG = new Log(RefundOrder.class);
	/**
	 * 申请退款接口链接
	 */
	protected static final String PAY_REFUNDORDER_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	@Sendable
	protected String sign, nonce_str;
	@Sendable
	protected String appid, mch_id, device_info, out_trade_no, out_refund_no, refund_fee_type, op_user_id;
	@Sendable
	protected int total_fee, refund_fee;
	
	private RefundOrder(WxAccount account, String device_info, String out_trade_no, String out_refund_no, String refund_fee_type, int total_fee, int refund_fee) throws NoSuchAlgorithmException {
		this.appid = account.getAccountAppid();
		this.mch_id = account.getMchId();
		this.device_info = device_info;
		this.out_trade_no = out_trade_no;
		this.out_refund_no = out_refund_no;
		this.refund_fee_type = refund_fee_type;
		this.op_user_id = account.getMchId();
		this.total_fee = total_fee;
		this.refund_fee = refund_fee;
		this.nonce_str = createNonceStr(32);
		this.sign = createSign(account.getMchKey());
	}
	
	public static Map<String, String> refundorder(WxAccount account, IOrder order, String device_info, String refund_fee_type) throws Exception {
		RefundOrder uorder = new RefundOrder(account, device_info, order.getOutTradeNo(), order.getOutRefundNo(), refund_fee_type, order.getTotalFee(), order.getTotalFee());
		String result = WeixinUtil.httpPost(PAY_REFUNDORDER_URL, uorder.trans2XML(), MchUtil.createSSLFactory(account));
		Map<String, String> map_result = MessageUtil.parseXml(result);
		map_result = new TreeMap<String, String>(map_result);

		if(!map_result.containsKey("return_code") || map_result.get("return_code").equals("FAIL")) throw LOG.err("err", map_result.get("return_msg"));
		if(!map_result.containsKey("result_code") || map_result.get("result_code").equals("FAIL")) throw LOG.err(map_result.get("err_code"), map_result.get("err_code_des"));
		
		return map_result;
	}
}
