package irille.wxpub.util;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.wx.pay.IOrder;
import irille.wx.wm.WmNewsDAO;
import irille.wx.wx.WxAccount;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.net.ssl.SSLContext;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 商户平台调用工具
 * @author yingjianhua
 *
 */
public class MchUtil {
	private static final Log LOG = new Log(MchUtil.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		CertErr("商户平台证书错误!"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final String ALLCHAR = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	public static final String ALLNUM = "0123456789";
	private static final Random random = new Random();
	/**
	 * 统一下单接口链接
	 */
	private static final String pay_unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 申请退款接口链接
	 */
	private static final String pay_refundorder_url = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	/**
	 * 查询订单接口链接
	 */
	private static final String pay_orderquery_url = "https://api.mch.weixin.qq.com/pay/orderquery";

	/**
	 * 生成长度为 length 的随机字符串，包含数字和大写字母
	 * @param length
	 * @return
	 */
	public static final String createRandom(int length) {
		StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
        }
        return sb.toString();
	}
	/**
	 * 创建SSL
	 * @param account
	 * @return
	 */
	public final static SSLConnectionSocketFactory createSSLFactory(WxAccount account) {
		try {
			String classPath = MchUtil.class.getClassLoader().getResource("").getPath();
			classPath = classPath.replace("/WEB-INF/classes", "");
			return createSSLFactory(classPath + account.getMchPayCert(), account.getMchId(), "mmpaycert");
		} catch (KeyManagementException | UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			e.printStackTrace();
			throw LOG.err(Msgs.CertErr);
		}
	}
	/**
	 * 创建SSL
	 * @param path
	 * @param password
	 * @param alias
	 * @return
	 * @throws KeyStoreException 
	 * @throws IOException 
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws UnrecoverableKeyException 
	 * @throws KeyManagementException 
	 */
	private final static SSLConnectionSocketFactory createSSLFactory(String path, String password, String alias) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, KeyManagementException, UnrecoverableKeyException {
		KeyStore ks = KeyStore.getInstance("PKCS12");
		FileInputStream input = new FileInputStream(path);
		char[] npassword = null;
		if(password != null && !password.trim().equals("")) {
			npassword = password.toCharArray();
		}
		ks.load(input, npassword);
		input.close();
		SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(ks, npassword).build();
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		return sslsf;
	}
	/**
	 * 生成长度为 length 的随机字符串，包含数字
	 * @param length
	 * @return
	 */
	public static final String createRandomNum(int length) {
		StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLNUM.charAt(random.nextInt(ALLNUM.length())));  
        }  
        return sb.toString();
	}
	/**
	 * 对字符串进行MD5运算
	 * @param str
	 * @return 
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(String str) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		try {
			digest.update(str.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		StringBuilder md5 = new StringBuilder();
		int i;
		for(byte line:digest.digest()) {
			i = line;
			if(i < 0) {
				i+=256;
			}
			if(i < 16) {
				md5.append("0");
			}
			md5.append(Integer.toHexString(i).toUpperCase());
		}
		System.out.println(md5.toString().toUpperCase()+"   length:"+md5.toString().length());
		return md5.toString().toUpperCase();
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
		String result = WeixinUtil.httpRequestStr(pay_unifiedorder_url, "POST", uorder.trans2XML());
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
	public static Map<String, String> refundorder(WxAccount account, IOrder order, String device_info, String refund_fee_type) throws Exception {
		RefundOrder uorder = new RefundOrder(account, device_info, order.getOutTradeNo(), order.getOutRefundNo(), refund_fee_type, order.getTotalFee(), order.getTotalFee());
		String result = WeixinUtil.httpPost(pay_refundorder_url, uorder.trans2XML(), MchUtil.createSSLFactory(account));
		Map<String, String> map_result = MessageUtil.parseXml(result);
		map_result = new TreeMap<String, String>(map_result);

		if(!map_result.containsKey("return_code") || map_result.get("return_code").equals("FAIL")) throw LOG.err("err", map_result.get("return_msg"));
		if(!map_result.containsKey("result_code") || map_result.get("result_code").equals("FAIL")) throw LOG.err(map_result.get("err_code"), map_result.get("err_code_des"));
		
		return map_result;
	}
	static abstract class AbstractOrder {
		/**
		 * 生成随机字符串
		 * @param length
		 * @return
		 */
		protected String createNonceStr(int length) {
			return createRandom(length);
		}
		protected String createSign(String mchKey) throws NoSuchAlgorithmException {
			Field[] fields = this.getClass().getDeclaredFields();
			StringBuffer buffer = new StringBuffer();
			Map<String, Object> map = new TreeMap<String, Object>();
			for(Field field:fields) {
				try {
					if(field.get(this)==null) continue;
					map.put(field.getName(), field.get(this));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			for(String key:map.keySet()) {
				buffer.append(key+"="+map.get(key)+"&");
				
			}
			String stringSignTemp = buffer+"key="+mchKey;
			System.out.println(stringSignTemp);
			return md5(stringSignTemp);
		}
		public String trans2XML() {
			Field[] fields = this.getClass().getDeclaredFields();
			StringBuffer buffer = new StringBuffer();
			buffer.append("<xml>\r\n");
			for(Field field:fields) {
				System.out.println(field.getName());
				try {
					if(field.get(this)==null) continue;
					buffer.append("<"+field.getName()+">");
					buffer.append(field.get(this));
					buffer.append("</"+field.getName()+">\r\n");
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			buffer.append("</xml>");
			return buffer.toString();
		}
	}
	static class RefundOrder extends AbstractOrder {
		protected String sign, nonce_str; 
		protected String appid, mch_id, device_info, out_trade_no, out_refund_no, refund_fee_type, op_user_id;
		protected int total_fee, refund_fee;
		public RefundOrder() {
		}
		public RefundOrder(WxAccount account, String device_info, String out_trade_no, String out_refund_no, String refund_fee_type, int total_fee, int refund_fee) throws NoSuchAlgorithmException {
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
	}
	static class UnifiedOrder extends AbstractOrder{
		protected String sign, nonce_str; 
		protected String appid,mch_id,device_info,body,detail,attach,out_trade_no,fee_type,spbill_create_ip,time_start,time_expire,goods_tag,notify_url,trade_type,product_id,limit_pay,openid;
		protected int total_fee;
		public UnifiedOrder(WxAccount account, String device_info, String body, String detail, String attach, String out_trade_no, String fee_type, int total_fee, String spbill_create_ip, String time_start, String time_expire, String goods_tag, String notify_url, String trade_type, String product_id, String limit_pay, String openid) throws NoSuchAlgorithmException {
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
	}
	public static void main(String[] args) {
		byte a = -1;
		for(int i=7;i>=0;i--) {
			System.out.print(a>>i&1);
		}
	}
}
