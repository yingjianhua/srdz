package irille.wpt.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import irille.wx.wpt.WptOrder;
import irille.wxpub.util.mch.MchUtil;

/**
 * 订单号产生器，用于根据时间产生不重复的订单号
 * @author YJH
 *
 */
public class TradeNoFactory {
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	/**
	 * 生成一个订单号，如："201506015842" 前8位是当前日期，后4位是随机4位数字
	 * @return
	 */
	public static String createOrderidUnique() {
		int num = 100;
		String orderid = "";
		while(num-->0) {
			orderid = format.format(new Date())+MchUtil.createRandomNum(4);
			if(WptOrder.chkUniqueOrderid(false, orderid) == null) {
				break;
			}
		}
		return orderid;
	}
	public static String createOutRefundNoUnique() {
		int num = 100;
		String orderid = "";
		while(num-->0) {
			orderid = format.format(new Date())+MchUtil.createRandomNum(4);
			if(WptOrder.chkUniqueOutRefundNo(false, orderid) == null) {
				break;
			}
		}
		return orderid;
	}
	/**
	 * 生成商户订单号（每个订单号必须唯一） 
	 * 组成：mch_id+yyyymmdd+10位一天内不能重复的数字
	 * @return
	 */
	public synchronized static String createMchBillNo(String mch_id) {
		if(a >= 20000000000L) {
			a = 10000000000L;
		}
		StringBuilder str = new StringBuilder(mch_id);
		str.append(format.format(new Date()));
		str.append(((a++)+"").substring(1));
		return str.toString();
	}
	private static long a = 10000000000L;
}
