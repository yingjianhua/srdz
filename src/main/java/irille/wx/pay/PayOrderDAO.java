package irille.wx.pay;

import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;
import irille.wxpub.util.mch.MchUtil;
import irille.wxpub.util.mch.UnifiedOrder;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class PayOrderDAO {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * 统一下单接口链接
	 */
	private static final String pay_unifiedorder_url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/**
	 * 查询订单接口链接
	 */
	private static final String jj = "https://api.mch.weixin.qq.com/pay/orderquery";
	/**
	 * 生成订单
	 * @param amt
	 * @throws NoSuchAlgorithmException 
	 */
	public static PayOrder addOrder(BigDecimal amt) throws NoSuchAlgorithmException {
		PayOrder order = new PayOrder();
		order.setAmt(amt);
		order.setNumber(getNumber());
		order.setDescription("");
		order.ins();
		return order;
	}
	/**
	 * 生成订单号 根据时间拼接上随机字符串
	 * @return
	 */
	public static String getNumber() {
		return format.format(new Date())+MchUtil.createRandom(18);
	}
	/**
	 * 统一下单
	 * @param account 公众账号
	 * @param user 微信用户
	 * @param spbill_create_id 终端IP
	 * @param total_fee 总金额
	 * @throws Exception
	 */
	public static void unifiedOrder(WxAccount account, WxUser user, PayOrder order, HttpServletRequest request, String spbill_create_id) throws Exception {
		Map<String, String> result = UnifiedOrder.unifiedorder(account, order, "WEB", "支付测试", null, request.getRemoteAddr(), null, null, null, 
				"http://wxpay.weixin.qq.com/pub_v2/pay/notify.v2.php", "JSAPI", null, null, "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
	}
	public static void main(String[] args) {
		System.out.println(getNumber());
	}
}
