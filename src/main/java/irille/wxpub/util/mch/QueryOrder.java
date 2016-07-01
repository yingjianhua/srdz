package irille.wxpub.util.mch;

import irille.pub.Log;

public class QueryOrder extends MchUtil {
	private static final Log LOG = new Log(QueryOrder.class);
	/**
	 * 查询订单接口链接
	 */
	protected static final String PAY_ORDERQUERY_URL = "https://api.mch.weixin.qq.com/pay/orderquery";
}
