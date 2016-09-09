package irille.wxpub.util.mch;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

import irille.pub.Log;
import irille.wx.pay.IOrder;
import irille.wx.wx.WxAccount;
import irille.wxpub.util.MessageUtil;
import irille.wxpub.util.WeixinUtil;

public class UnifiedOrder extends MchUtil {
	private static final Log LOG = new Log(UnifiedOrder.class);
	/**
	 * 统一下单接口链接
	 */
	protected static final String PAY_UNIFIEDORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	@Sendable
	protected String sign, nonce_str;
	@Sendable
	protected String appid,mch_id,device_info,body,detail,attach,out_trade_no,fee_type,spbill_create_ip,time_start,time_expire,goods_tag,notify_url,trade_type,product_id,limit_pay,openid;
	@Sendable
	protected int total_fee;
	
	private UnifiedOrder(WxAccount account, String device_info, String body, String detail, String attach, String out_trade_no, String fee_type, int total_fee, String spbill_create_ip, String time_start, String time_expire, String goods_tag, String notify_url, String trade_type, String product_id, String limit_pay, String openid) throws NoSuchAlgorithmException {
		this.appid = account.getAccountAppid();
		this.mch_id = account.getMchId();
		this.device_info = device_info;
		this.body = body;
		this.detail = detail;
		this.attach = attach;
		this.out_trade_no = out_trade_no;
		this.fee_type = fee_type;
		this.total_fee = total_fee;
		this.spbill_create_ip = spbill_create_ip;
		this.time_start = time_start;
		this.time_expire = time_expire;
		this.goods_tag = goods_tag;
		this.notify_url = notify_url;
		this.trade_type = trade_type;
		this.product_id = product_id;
		this.limit_pay = limit_pay;
		this.openid = openid;
		this.nonce_str = createNonceStr(32);
		this.sign = createSign(account.getMchKey());
	}
	
	/**
	 * 
	 * @param account 微信公众帐号
	 * @param order 实现了IOrder接口， 拥有getBody(),getDetail(),getOutTradeNo()三个方法
	 * @param device_info 设备号 可以为空 <strong>如：</strong>"013467007045764 " <strong>注：</strong>终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	 * @param attach 附加数据 可以为空 <strong>如：</strong>"深圳分店 " <strong>注：</strong>附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	 * @param fee_type 货币类型 可以为空 <strong>如：</strong>"CNY " <strong>注：</strong>符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * @param spbill_create_ip 终端IP 不可为空 <strong>如：</strong>"123.12.12.123 " <strong>注：</strong>APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	 * @param time_start 交易起始时间 可以为空 <strong>如：</strong>"20091225091010 " <strong>注：</strong>订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * @param time_expire 交易结束时间 可以为空 <strong>如：</strong>"20091227091010 " <strong>注：</strong>订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则 注意：最短失效时间间隔必须大于5分钟
	 * @param goods_tag 商品标记 可以为空 <strong>如：</strong>"WXG " <strong>注：</strong>商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
	 * @param notify_url 通知地址 不可为空 <strong>如：</strong>"http://www.weixin.qq.com/wxpay/pay.php " <strong>注：</strong>接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	 * @param trade_type 交易类型 不可为空 <strong>如：</strong>"JSAPI " <strong>注：</strong>详细说明见参数规定
	 * @param product_id 商品ID 可以为空 <strong>如：</strong>"12235413214070356458058 " <strong>注：</strong>trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	 * @param limit_pay 指定支付方式 可以为空 <strong>如：</strong>"no_credit " <strong>注：</strong>no_credit--指定不能使用信用卡支付
	 * @param openid 用户标识 可以为空 <strong>如：</strong>"oUpF8uMuAJO_M2pxb1Q9zNjWeS6o " <strong>注：</strong>trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
	 * @return 
	 * appid 公众帐号ID 如：wx8888888888888888 	调用接口提交的公众账号ID<br>
	 * mch_id 商户号 如：1900000109 	调用接口提交的商户号<br>
	 * device_info 设备号 如：013467007045764 	调用接口提交的终端设备号，<br>
	 * nonce_str 随机字符串 如：5K8264ILTKCH16CQ2502SI8ZNMTM67VS 	微信返回的随机字符串<br>
	 * sign 签名 如：C380BEC2BFD727A4B6845133519F3AD6 	微信返回的签名，详见签名算法<br>
	 * trade_type 交易类型 如：JSAPI 	调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，详细说明见参数规定<br>
	 * prepay_id 预支付交易会话标识 如：wx201410272009395522657a690389285100 	微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时<br>
	 * code_url 二维码链接 如：URl：weixin：//wxpay/s/An4baqw 	trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付<br>
	 * @throws Exception 
	 */
	public static Map<String, String> unifiedorder(WxAccount account, String body, String detail, String outTradeNo
			, String device_info, String attach, String fee_type, BigDecimal totalFee, String spbill_create_ip, String time_start
			, String time_expire, String goods_tag, String notify_url, String trade_type
			, String product_id, String limit_pay, String openid) throws Exception {
		UnifiedOrder uorder = new UnifiedOrder(account, device_info, body, detail, attach, outTradeNo, fee_type, totalFee.multiply(BigDecimal.valueOf(100)).intValue(), spbill_create_ip, time_start, time_expire, goods_tag, notify_url, trade_type, product_id, limit_pay, openid);
		String result = WeixinUtil.httpRequestStr(PAY_UNIFIEDORDER_URL, "POST", uorder.trans2XML());
		/**
			<xml>
			<return_code>SUCCESS</return_code>
			<return_msg>OK</return_msg>
			<appid>wx1210b69ac5df2080</appid>
			<mch_id>1259734201</mch_id>
			<device_info>WEB</device_info>
			<nonce_str>16pMQ5mFFANkC5Ds</nonce_str>
			<sign>A9DE0C941A1D2F81ABE92C4ABCDD2EBE</sign>
			<result_code>SUCCESS</result_code>
			<prepay_id>wx201602261152528696c40e060564370216</prepay_id>
			<trade_type>JSAPI</trade_type>
			</xml>
		 */
		Map<String, String> map_result = MessageUtil.parseXml(result);
		map_result = new TreeMap<String, String>(map_result);
		
		if(!map_result.containsKey("return_code") || map_result.get("return_code").equals("FAIL")) throw LOG.err("err", map_result.get("return_msg"));
		if(!map_result.containsKey("result_code") || map_result.get("result_code").equals("FAIL")) throw LOG.err(map_result.get("err_code"), map_result.get("err_code_des"));
		
		return map_result;
	}
	public static Map<String, String> unifiedorder(WxAccount account, String body, String outTradeNo, BigDecimal totalFee, String spbill_create_ip, String notify_url, String openid) throws Exception {
		return unifiedorder(account, body, null, outTradeNo, "WEB", null, null, totalFee, spbill_create_ip, null, null, null, notify_url, "JSAPI", null, null, openid);
	}
	
	/**
	 * 
	 * @param account 微信公众帐号
	 * @param order 实现了IOrder接口， 拥有getBody(),getDetail(),getOutTradeNo()三个方法
	 * @param device_info 设备号 可以为空 <strong>如：</strong>"013467007045764 " <strong>注：</strong>终端设备号(门店号或收银设备ID)，注意：PC网页或公众号内支付请传"WEB"
	 * @param attach 附加数据 可以为空 <strong>如：</strong>"深圳分店 " <strong>注：</strong>附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
	 * @param fee_type 货币类型 可以为空 <strong>如：</strong>"CNY " <strong>注：</strong>符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
	 * @param spbill_create_ip 终端IP 不可为空 <strong>如：</strong>"123.12.12.123 " <strong>注：</strong>APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
	 * @param time_start 交易起始时间 可以为空 <strong>如：</strong>"20091225091010 " <strong>注：</strong>订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * @param time_expire 交易结束时间 可以为空 <strong>如：</strong>"20091227091010 " <strong>注：</strong>订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。其他详见时间规则 注意：最短失效时间间隔必须大于5分钟
	 * @param goods_tag 商品标记 可以为空 <strong>如：</strong>"WXG " <strong>注：</strong>商品标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
	 * @param notify_url 通知地址 不可为空 <strong>如：</strong>"http://www.weixin.qq.com/wxpay/pay.php " <strong>注：</strong>接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
	 * @param trade_type 交易类型 不可为空 <strong>如：</strong>"JSAPI " <strong>注：</strong>详细说明见参数规定
	 * @param product_id 商品ID 可以为空 <strong>如：</strong>"12235413214070356458058 " <strong>注：</strong>trade_type=NATIVE，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
	 * @param limit_pay 指定支付方式 可以为空 <strong>如：</strong>"no_credit " <strong>注：</strong>no_credit--指定不能使用信用卡支付
	 * @param openid 用户标识 可以为空 <strong>如：</strong>"oUpF8uMuAJO_M2pxb1Q9zNjWeS6o " <strong>注：</strong>trade_type=JSAPI，此参数必传，用户在商户appid下的唯一标识。openid如何获取，可参考【获取openid】。企业号请使用【企业号OAuth2.0接口】获取企业号内成员userid，再调用【企业号userid转openid接口】进行转换
	 * @return 
	 * appid 公众帐号ID 如：wx8888888888888888 	调用接口提交的公众账号ID<br>
	 * mch_id 商户号 如：1900000109 	调用接口提交的商户号<br>
	 * device_info 设备号 如：013467007045764 	调用接口提交的终端设备号，<br>
	 * nonce_str 随机字符串 如：5K8264ILTKCH16CQ2502SI8ZNMTM67VS 	微信返回的随机字符串<br>
	 * sign 签名 如：C380BEC2BFD727A4B6845133519F3AD6 	微信返回的签名，详见签名算法<br>
	 * trade_type 交易类型 如：JSAPI 	调用接口提交的交易类型，取值如下：JSAPI，NATIVE，APP，详细说明见参数规定<br>
	 * prepay_id 预支付交易会话标识 如：wx201410272009395522657a690389285100 	微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时<br>
	 * code_url 二维码链接 如：URl：weixin：//wxpay/s/An4baqw 	trade_type为NATIVE是有返回，可将该参数值生成二维码展示出来进行扫码支付<br>
	 * @throws Exception 
	 */
	public static Map<String, String> unifiedorder(WxAccount account, IOrder order, String device_info, String attach, String fee_type, String spbill_create_ip, String time_start, String time_expire, String goods_tag, String notify_url, String trade_type, String product_id, String limit_pay, String openid) throws Exception {
		UnifiedOrder uorder = new UnifiedOrder(account, device_info, order.getBody(), order.getDetail(), attach, order.getOutTradeNo(), fee_type, order.getTotalFee(), spbill_create_ip, time_start, time_expire, goods_tag, notify_url, trade_type, product_id, limit_pay, openid);
		String result = WeixinUtil.httpRequestStr(PAY_UNIFIEDORDER_URL, "POST", uorder.trans2XML());
		/**
			<xml>
			<return_code>SUCCESS</return_code>
			<return_msg>OK</return_msg>
			<appid>wx1210b69ac5df2080</appid>
			<mch_id>1259734201</mch_id>
			<device_info>WEB</device_info>
			<nonce_str>16pMQ5mFFANkC5Ds</nonce_str>
			<sign>A9DE0C941A1D2F81ABE92C4ABCDD2EBE</sign>
			<result_code>SUCCESS</result_code>
			<prepay_id>wx201602261152528696c40e060564370216</prepay_id>
			<trade_type>JSAPI</trade_type>
			</xml>
		 */
		Map<String, String> map_result = MessageUtil.parseXml(result);
		map_result = new TreeMap<String, String>(map_result);
		
		if(!map_result.containsKey("return_code") || map_result.get("return_code").equals("FAIL")) throw LOG.err("err", map_result.get("return_msg"));
		if(!map_result.containsKey("result_code") || map_result.get("result_code").equals("FAIL")) throw LOG.err(map_result.get("err_code"), map_result.get("err_code_des"));
		
		return map_result;
	}
}
