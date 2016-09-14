package irille.wpt.service.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import irille.pub.Log;
import irille.pub.Str;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.wpt.bean.City;
import irille.wpt.bean.Combo;
import irille.wpt.bean.ComboLine;
import irille.wpt.bean.CustomService;
import irille.wpt.bean.Member;
import irille.wpt.bean.Order;
import irille.wpt.bean.OrderCustomService;
import irille.wpt.bean.OrderDetail;
import irille.wpt.bean.OrderPayJournal;
import irille.wpt.bean.Restaurant;
import irille.wpt.bean.ServiceCen;
import irille.wpt.bean.WxTips;
import irille.wpt.dao.impl.ComboDao;
import irille.wpt.dao.impl.ComboLineDao;
import irille.wpt.dao.impl.CustomServiceDao;
import irille.wpt.dao.impl.OrderDao;
import irille.wpt.dao.impl.OrderDetailDao;
import irille.wpt.dao.impl.OrderPayJournalDao;
import irille.wpt.dao.impl.OrderServiceDao;
import irille.wpt.dao.impl.ServiceCenDao;
import irille.wpt.dao.impl.WxTipsDao;
import irille.wpt.exception.ExtjsException;
import irille.wpt.pay.WXPay;
import irille.wpt.tools.Constant;
import irille.wpt.tools.SmsTool;
import irille.wx.wpt.Wpt.OContactStatus;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.WptOrderService;
import irille.wx.wpt.WptServiceCen;
import irille.wx.wpt.WptWxTips;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxUser;
import irille.wxpub.util.mch.MchUtil;
import irille.wxpub.util.mch.UnifiedOrder;
@Service
public class OrderService {
	private static final Log LOG = new Log(OrderService.class);
	
	@Resource
	private Constant constant;
	@Resource
	private SmsTool smsTool;
	@Resource
	private DistributionRuleService distributionRuleService;
	@Resource
	private QrcodeRuleService qrcodeRuleService;
	
	@Resource
	private ComboDao comboDao;
	@Resource
	private ComboLineDao comboLineDao;
	@Resource
	private OrderDao orderDao;
	@Resource
	private OrderDetailDao orderDetailDao;
	@Resource
	private OrderServiceDao orderServiceDao;
	@Resource
	private OrderPayJournalDao orderPayJournalDao;
	@Resource
	private CustomServiceDao serviceDao;
	@Resource
	private ServiceCenDao serviceCenDao;
	@Resource
	private WxTipsDao wxTipsDao;
	
	private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private static final SimpleDateFormat ORDERID_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	/**
	 * 用户在微信端提交订单信息，产生的订单记录，
	 * @param comboId 套餐Id
	 * @param number 套餐数量
	 * @param time 用餐时间
	 * @param member 下单的微信用户
	 * @param rem 备注
	 * @param serviceIds 额外增加的服务列表 id1,id2,id3,id4
	 * @param contactMan 联系人
	 * @param contactSex 联系人性别
	 * @param contactType 联系方式
	 * @param contactWay 联系号码
	 * @return 创建成功的订单，若出错返回null
	 * @throws ParseException
	 */
	public Order createOrder(Integer comboId, Integer number, String time, Member member, String rem, String serviceIds, City city,
			String contactMan, String contactSex, String contactType, String contactWay) {
		Order order;
		try {
			order = new Order();
			order.setTime(INPUT_DATE_FORMAT.parse(time));
			order.setContactMan(contactMan);
			order.setContactSex(Byte.valueOf(contactSex));
			order.setContactType(Byte.valueOf(contactType));
			order.setContactWay(contactWay);
			order.setRem(rem);
			order.setMember(member);
			Combo combo = comboDao.load(comboId);
			Restaurant restaurant = combo.getRestaurant();
			order.setCombo(combo);
			order.setComboName(combo.getName());
			order.setRestaurant(restaurant);
			order.setRestaurantName(restaurant.getName());
			order.setPrice(combo.getPrice());
			order.setCity(restaurant.getCity());
			order.setNumber(number);
			order.setCreateTime(new Date());
			order.setAccount(member.getAccount());
			order.setOrderid(ORDERID_FORMAT.format(new Date())+MchUtil.createRandomNum(4));
			if(!Str.isEmpty(serviceIds)) {
				order.setStatus(OStatus.NOTACCEPTED.getLine().getKey());
			} else {
				order.setStatus(OStatus.UNPAYMENT.getLine().getKey());
			}
			orderDao.save(order);
			Set<ComboLine> comboLines = combo.getComboLines();
			Set<OrderDetail> details = new HashSet<OrderDetail>();
			for(ComboLine comboLine:comboLines) {
				OrderDetail detail = new OrderDetail();
				detail.setName(comboLine.getMenu().getName());
				detail.setNumber(1);
				detail.setPrice(comboLine.getPrice());
				detail.setOrder(order);
				orderDetailDao.save(detail);
				details.add(detail);
			}
			order.setDetails(details);
			if(!Str.isEmpty(serviceIds)) {
				List<CustomService> services = serviceDao.listByIds(serviceIds);
				Set<OrderCustomService> orderServices = new HashSet<OrderCustomService>();
				for (irille.wpt.bean.CustomService service : services) {
					OrderCustomService orderService = new OrderCustomService();
					orderService.setName(service.getName());
					orderService.setPrice(service.getPrice());
					orderService.setOrder(order);
					orderServiceDao.save(orderService);
					orderServices.add(orderService);
				}
				order.setServices(orderServices);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExtjsException("订单生成异常");
		}
		return order;
	}
	
	/**
	 * 生成支付时需要的准备参数
	 * @param orderId 订单号
	 * @param comboNumber 套餐数量
	 * @param user 微信用户
	 * @param account 微信公众号
	 * @param request 
	 * @return 
	 */
	public WXPay createPreparePay(String orderid, Integer comboNumber, Member member, WxAccount account, HttpServletRequest request) {
		Order order = orderDao.findByOrderid(orderid);
		if(!order.getStatus().equals(OStatus.UNPAYMENT.getLine().getKey())) {
			throw new ExtjsException("订单状态出错");
		}
		//假如套餐数量有增加，则修改套餐数量字段，并增加订单额外的套餐费用
		if(comboNumber != null && comboNumber > 1) {
			Integer extNumber = comboNumber - order.getNumber();
			if(extNumber > 0) {
				BigDecimal extPrice = order.getCombo().getPrice().multiply(BigDecimal.valueOf(extNumber));
				order.setNumber(comboNumber);
				order.setPrice(order.getPrice().add(extPrice));
				orderDao.update(order);
			}
		}
		BigDecimal needPay = null;
		if(order.getPayment().compareTo(order.getDeposit()) < 0) {//已支付金额未超过定金的，需支付剩余的定金
			needPay = order.getDeposit().subtract(order.getPayment());
		} else if(order.getPayment().compareTo(order.getPrice()) < 0){//已支付金额未超过总金额的，需支付剩余金额
			needPay = order.getPrice().subtract(order.getPayment());
		} else {//否则出错
			throw new ExtjsException("支付金额出错");
		}
		OrderPayJournal payJournal = new OrderPayJournal();
		payJournal.setOrder(order);
		payJournal.setCreateTime(new Date());
		payJournal.setPrice(needPay);
		payJournal.setOutTradeNo(order.getPkey()+ORDERID_FORMAT.format(new Date()));
		orderPayJournalDao.save(payJournal);
		
		WXPay wxpay;
		try {
			wxpay = prepareParams(payJournal, member, account, request);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExtjsException("参数产生出错");
		}
		return wxpay;
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
	private WXPay prepareParams(OrderPayJournal payJournal, Member member, WxAccount account, HttpServletRequest request) throws Exception {
		Map<String, String> payParams = new TreeMap<String, String>();
		String spbill_create_ip = request.getRemoteAddr();
		Order order = payJournal.getOrder();
		String body = order.getRestaurantName()+"|"+order.getComboName();
		String notify_url = constant.getWebPath()+"/wpt/resource/order_notifyPay";
		Map<String, String> result = UnifiedOrder.unifiedorder(account, body, payJournal.getOutTradeNo(), payJournal.getPrice(), spbill_create_ip, notify_url, member.getOpenId());
	
		WXPay wxpay = new WXPay();
		wxpay.setPayAppId(account.getAccountAppid());
		wxpay.setPayTimestamp(new Date().getTime()/1000+"");
		wxpay.setPayNonceStr(MchUtil.createRandom(32));
		wxpay.setPayPackage("prepay_id="+result.get("prepay_id"));
		wxpay.setPaySignType("MD5");
		StringBuilder buffer = new StringBuilder();
		for(String key:payParams.keySet()) {
			buffer.append(key+"="+payParams.get(key)+"&");
		}
		buffer.append("key=").append(account.getMchKey());
		System.out.println(buffer.toString());
		wxpay.setPayPaySign(MchUtil.md5(buffer.toString()));
		return wxpay;
	}
	
	public List<Order> list(Integer memberId) {
		return orderDao.listByMember(memberId);
	}
	
	/**
	 * 用户申请取消订单
	 * 私人订单：	
	 * 	未受理-->关闭
	 * 套餐订单：
	 * 	未付款-->关闭
	 */
	public void cancelOrder(WptOrder order) {
		String msg = "订单已取消";
		if(order.gtIsPt() == true) {
			if(order.gtStatus() == OStatus.NOTACCEPTED || order.gtStatus() == OStatus.ACCEPTED || order.gtStatus() == OStatus.DEPOSIT) {
				order.stStatus(OStatus.CLOSE); msg = "订单已取消";
			} else if(order.gtStatus() == OStatus.PAYMENT) {
				order.stStatus(OStatus.REFUND); msg = "已申请退款";
			} else {
				msg = "订单状态已过期";
			}
		} else {
			if(order.gtStatus() == OStatus.UNPAYMENT) {
				order.stStatus(OStatus.CLOSE); msg = "订单已取消";
			} else if(order.gtStatus() == OStatus.PAYMENT) {
				order.stStatus(OStatus.REFUND); msg = "已申请退款";
			}
		}
		order.upd();
		throw LOG.err("showMsg", msg);
	}

	public WptOrder complete(WptOrder order, String checkCode) {
		if(order.gtStatus() != OStatus.PAYMENT){
			throw LOG.err("statusErr", "订单未付款");
		} else if(!order.getCheckcode().equals(checkCode)) {
			throw LOG.err("validCheckCode", "核验码不正确");
		} 
		order.stStatus(OStatus.FINISH);
		order.upd();
		//假如订单完成
		if(order.gtStatus() == OStatus.FINISH) {
			qrcodeRuleService.create(order.gtWxuser(), false);
			distributionRuleService.orderBeenComplete(order);
		}
		return order;
	}
	/**
	 * 生成订单发送消息给管理人员
	 */
	public void doSent(Order order){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		final ServiceCen serviceCen = serviceCenDao.find(order.getAccount());
		StringBuilder c = new StringBuilder("【享食光】私人订制 单生成,内容如下:\n");
		c.append("订单号：").append(order.getOrderid()).append("\n");
		c.append("餐厅：").append(order.getRestaurantName()).append("\n");
		c.append("套餐：").append(order.getComboName()).append("\n");
		c.append("用餐时间：").append(format.format(order.getTime())).append("\n");
		c.append("服务：[");
		for(OrderCustomService line:order.getServices()){
			c.append(line.getName()).append(" ");
		}
		c.append("]").append("\n");
		if(order.getRem() != null) {
			c.append("备注:").append(order.getRem()).append("\n");
		}
		c.append("联系人：").append(order.getContactMan()).append("\n");
		c.append("联系方式：");
		for(OContactStatus line:OContactStatus.values()) {
			if(order.getContactType().equals(line.getLine().getKey())) {
				c.append(line.getLine().getName()).append(":");
				break;
			}
		}
		c.append(order.getContactWay()).append("\n");
		//发送到微信用户
		String accessToken = WxAccountDAO.getAccessToken(Bean.get(WxAccount.class, order.getAccount()));
		for(WxTips line:wxTipsDao.list(order.getAccount())) {
			smsTool.doSend(accessToken, line.getMember(), c.toString());
		}
		boolean flag = true;
		if(flag) {
			return ;
		}
		//发送到手机短信
		for(String line : serviceCen.getSmsTips().split(",")){
			smsTool.doSent(line, c.toString());
		}
	}

	/**
	 * 生成订单发送消息给管理人员
	 */
	@SuppressWarnings("unchecked")
	public void doSent(WptOrder order){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		final WptServiceCen serviceCen = WptServiceCen.load(WptServiceCen.class, order.getAccount());
		StringBuilder c = new StringBuilder("【享食光】私人订制 单生成,内容如下:\n");
		c.append("订单号:").append(order.getOrderid()).append("\n");
		c.append(" 餐厅 :").append(order.getRestaurant()!=null?order.gtRestaurant().getName():"无").append("\n");
		c.append(" 用餐时间:").append(format.format(order.getTime())).append("\n");
		c.append(" 联系人:").append(order.getContactMan()).append("\n");
		c.append(" ").append(order.gtContactType().getLine().getName()).append(":").append(order.getContactWay()).append("\n");
		c.append(" 宴会类型:").append(order.gtBanquet() != null?order.gtBanquet().getExtName():"无").append("\n");
		c.append("人均预算:").append(order.getConsumption() != null?order.getConsumption():"无").append("\n");
		c.append("人数:").append(order.getNumber() != null?order.getNumber():"无").append("\n");
		c.append(" 服务 :[");
		for( WptOrderService service : (List<WptOrderService>)Idu.getLines(WptOrderService.T.WPTORDER, order.getPkey())) {
			c.append(service.getName()).append(" ");
		}
		c.append("]").append("\n");
		if(order.getComboName() != null) {
			c.append(" 套餐:").append(order.getComboName()).append("\n");
		}
		if(order.getRem() != null) {
			c.append(" 备注:").append(order.getRem()).append("\n");
		}
		for(String line : serviceCen.getSmsTips().split(",")){
			smsTool.doSent(line, c.toString());
		}
		
		String sql = Idu.sqlString("select r.* from {0} r right join {1} s on (r.{2}=s.{3}) where s.{4}=?", WxUser.class, WptWxTips.class, WxUser.T.PKEY, WptWxTips.T.PKEY, WptWxTips.T.ACCOUNT);
		List<WxUser> users = BeanBase.list(sql, new BeanBase.ResultSetBean<WxUser>() {
			@Override
			public WxUser tran(ResultSet set) {
				WxUser bean = Bean.newInstance(WxUser.class);
				bean.fromResultSet(set);
				return bean;
			}
		}, order.getAccount());
		String accessToken = WxAccountDAO.getAccessToken(order.gtAccount());
		smsTool.doSend(accessToken, users, c.toString());
	}
	
	/**
	 * 支付完成后，微信回调通知，其中参数不做校验
	 * @param outTradeNo 商户订单号
	 * @param totalFee 支付金额，单位为分
	 * @param time_end 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * @return
	 */
	public boolean paidCallback(String outTradeNo, String totalFee, String time_end) {
		LOG.info("--------------paidCallback():start--------------");
		WptOrder order = WptOrder.chkUniqueDepPayId(false, outTradeNo);
		if(order == null) {
			//假如不是定金的支付回调,说明是普通订单的支付或者是私人订制订单的余款支付，总之订单会进入已付款状态
			order = WptOrder.chkUniqueOrderid(false, outTradeNo);
			order.setCheckcode(MchUtil.createRandomNum(6));
			order.stStatus(OStatus.PAYMENT);
			LOG.info("orderId:"+order.getOrderid());
			LOG.info("支付完成");
			//根据分销规则进行处理
			distributionRuleService.orderBeenPaid(order.gtWxuser(), order);
			//订单完成
			if(order.gtIsPt()) {
				//若是私人订制的订单，需要设置余款的支付方式为微信支付
				order.stResidueIsWxpay(true);
			}
		} else {
			LOG.info("orderId:"+order.getOrderid());
			LOG.info("支付定金");
			//是支付定金的回调
			order.stDepositIsWxpay(true);
			order.stStatus(OStatus.DEPOSIT);
		}
		order.upd();
		LOG.info("--------------paidCallback():end--------------");
		return true;
	}
	
	public Order findByOrderid(String orderid) {
		return orderDao.findByOrderid(orderid);
	}
	public Long countPending(Integer memberId) {
		return orderDao.countPending(memberId);
	}
}
