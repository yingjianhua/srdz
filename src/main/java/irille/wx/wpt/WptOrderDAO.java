package irille.wx.wpt;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduUpdLines;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxUser;
import irille.wxpub.util.mch.MchUtil;
import irille.wxpub.util.mch.RefundOrder;
import irille.wxpub.util.mch.UnifiedOrder;

public class WptOrderDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		imgEmptyErr("菜品不可为空!"),
		uniqueErr("记录【{0}】已存在，不可操作！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WptOrderDAO.class);
	private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 生成一个订单号，如："201506015842" 前8位是当前日期，后4位是随机4位数字
	 * @return
	 */
	public static String createOrderid() {
		return format.format(new Date())+MchUtil.createRandomNum(4);
	}
	/**
	 * 生成一个订单号，如："201506015842" 前8位是当前日期，后4位是随机4位数字
	 * @return
	 */
	public static String createOrderidUnique() {
		int num = 100;
		String orderid = "";
		while(num-->0) {
			orderid = WptOrderDAO.createOrderid();
			if(WptOrder.chkUniqueOrderid(false, orderid) == null) {
				break;
			}
		}
		return orderid;
	}
	
	public static class Ins extends IduInsLines<Ins, WptOrder, WptOrderLine> {
		public void run() {};
	}

	public static class Upd extends IduUpdLines<Upd, WptOrder, WptOrderLine> {
		WptOrder.T T = WptOrder.T.PKEY;
		@Override
		public void valid() {
			WptOrder dbBean = WptOrder.load(WptOrder.class, getB().getPkey());
			if(dbBean.gtStatus() != OStatus.UNPAYMENT && dbBean.gtStatus() != OStatus.NOTACCEPTED) throw LOG.err("statusErr", "状态异常");
			if(dbBean.gtIsPt()) {
				getB().setPrice(getB().getDeposit().add(getB().getResidue()));
			}
			PropertyUtils.copyProperties(dbBean, getB(), T.RESTAURANT, T.BANQUET, T.TIME, T.NUMBER, T.CONSUMPTION, 
					T.CITY, T.DEPOSIT, T.RESIDUE, T.CONTACT_MAN, T.CONTACT_SEX, T.CONTACT_TYPE, T.CONTACT_WAY,
					T.REM, T.COMBO_NAME, T.PRICE);
			setB(dbBean);
			if(getLines() == null) 
				setLines(new ArrayList<WptOrderLine>());
			for (WptOrderLine line : getLines()) 
				line.stAccount(WxAccountDAO.getByUser(getUser()));
			updLine(getB(), getLines(), WptOrderLine.T.WPTORDER.getFld());
			super.valid();
		}
	}

	public static class Del extends IduDel<Del, WptOrder> {
		@Override
		public void valid() {
			WptOrder order = WptOrder.load(WptOrder.class, getB().getPkey());
			if(order.gtStatus() != OStatus.UNPAYMENT && order.gtStatus() != OStatus.NOTACCEPTED) throw LOG.err("statusErr", "状态异常");
			super.valid();
		}
		@Override
		public void after() {
			super.after();
			delLine(getLines(WptOrderLine.T.WPTORDER, getB().getPkey()));
			delLine(getLines(WptOrderService.T.WPTORDER, getB().getPkey()));
		}
	}
	private static final String notify_pay_url = ServletActionContext.getServletContext().getInitParameter("webPath")+"/wpt/resource/order_notifyPay";
	/**
	 * 统一下单
	 * @param account 公众账号
	 * @param user 微信用户
	 * @param spbill_create_id 终端IP
	 * @param total_fee 总金额
	 * @throws Exception
	 */
	private static Map<String, String> unifiedOrder(WxAccount account, WxUser wxUser, WptOrder order, HttpServletRequest request) throws Exception {
		String spbill_create_id = request.getRemoteAddr();
		Map<String, String> result = UnifiedOrder.unifiedorder(account, order, "WEB", order.getClass().getName(), null, spbill_create_id, null, null, null, 
				notify_pay_url, "JSAPI", null, null, wxUser.getOpenId());
		return result;
	}
	/**
	 * 申请退款
	 * @param account
	 * @param order
	 * @return
	 * @throws Exception 
	 */
	private static Map<String, String> refundOrder(WxAccount account, WptOrder order) throws Exception {
		Map<String, String> result = RefundOrder.refundorder(account, order, null, null);
		return result;
	}
	/**
	 * 生成统一下单所需的参数
	 * @param request
	 * @param order
	 * @param wxUser
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public static Map<String, String> prepareParams(HttpServletRequest request, WptOrder order, WxUser wxUser, WxAccount account) throws Exception {
		Map<String, String> payParams = new TreeMap<String, String>();
		payParams.put("appId", account.getAccountAppid());
		payParams.put("timeStamp", new Date().getTime()/1000+"");
		payParams.put("nonceStr", MchUtil.createRandom(32));
		if(order.getPrepareId() == null) {
			order.setPrepareId(WptOrderDAO.unifiedOrder(account, wxUser, order, request).get("prepay_id"), new Date());
			order.upd();
		}
		payParams.put("package", "prepay_id="+order.getPrepareId()); 
		payParams.put("signType", "MD5");
		StringBuilder buffer = new StringBuilder();
		for(String key:payParams.keySet()) {
			buffer.append(key+"="+payParams.get(key)+"&");
		}
		String StringTemp = buffer+"key="+account.getMchKey();
		System.out.println(StringTemp);
		payParams.put("paySign", MchUtil.md5(StringTemp));
		return payParams;
	}
	public static void accept(WptOrder order) {
		if(order.gtStatus() != OStatus.NOTACCEPTED) throw LOG.err("statusErr", "状态异常");
		order.stStatus(OStatus.ACCEPTED);
		order.upd();
	}
 	public static void residue(WptOrder order) {
		if(order.gtStatus() != OStatus.DEPOSIT) throw LOG.err("statusErr", "状态异常");
		order.stStatus(OStatus.PAYMENT);
		order.stResidueIsWxpay(false);
		order.stResidueMan(Idu.getUser());
		order.setCheckcode(MchUtil.createRandomNum(6));
		order.upd();
	}
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static void refundOrder(WptOrder order) throws Exception {
		if(order.gtStatus() != OStatus.REFUND) throw LOG.err("statusErr", "状态异常"); 
		if(!order.gtIsPt() || order.gtResidueIsWxpay()) {
			order.setOutRefundNo(createOrderidUnique());
			refundOrder(order.gtAccount(), order);
		}
		order.stStatus(OStatus.CLOSE);
		order.upd();
	}
}