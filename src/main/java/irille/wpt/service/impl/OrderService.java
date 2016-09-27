package irille.wpt.service.impl;

import java.math.BigDecimal;
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
import irille.wpt.bean.City;
import irille.wpt.bean.Combo;
import irille.wpt.bean.ComboLine;
import irille.wpt.bean.CommissionJournal;
import irille.wpt.bean.CustomService;
import irille.wpt.bean.DistributionRule;
import irille.wpt.bean.Member;
import irille.wpt.bean.Order;
import irille.wpt.bean.OrderCustomService;
import irille.wpt.bean.OrderDetail;
import irille.wpt.bean.OrderPayJournal;
import irille.wpt.bean.Restaurant;
import irille.wpt.bean.ServiceCenter;
import irille.wpt.bean.WxTips;
import irille.wpt.exception.ExtjsException;
import irille.wpt.pay.WXPay;
import irille.wpt.service.AbstractService;
import irille.wpt.tools.Constant;
import irille.wpt.tools.SmsTool;
import irille.wx.wpt.Wpt.OContactStatus;
import irille.wx.wpt.Wpt.OPayChannel;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxMessageDAO;
import irille.wxpub.util.mch.MchUtil;
import irille.wxpub.util.mch.UnifiedOrder;
@Service
public class OrderService extends AbstractService<Order> {
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
	private CommissionJournalService commissionJournalService;
	@Resource
	private MemberService memberService;
	
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
			List<ComboLine> comboLines = comboLineDao.listByCombo(combo.getPkey());
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
				List<CustomService> services = customServiceDao.listByIds(serviceIds);
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
		payJournal.setIncome(true);
		payJournal.setPayChannel(OPayChannel.WX.getLine().getKey());
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

	/**
	 * 用户申请取消订单
	 */
	public void cancelOrder(String orderid, Member member) {
		Order order = orderDao.findByOrderid(orderid);
		if(!order.getMember().getPkey().equals(member.getPkey())) { 
			return ;
		}
		switch (order.getStatus()) {
		case 0: {//未处理订单，直接关闭
			order.setStatus(OStatus.CLOSE.getLine().getKey());
			break;
		}
		case 1: {//已受理订单，直接关闭
			order.setStatus(OStatus.CLOSE.getLine().getKey());
			break;
		}
		case 2: {//未付款订单，直接关闭
			order.setStatus(OStatus.CLOSE.getLine().getKey());
			break;
		}
		case 3: {//已付定金订单，直接关闭
			order.setStatus(OStatus.CLOSE.getLine().getKey());
			break;
		}
		case 4: {//已付款订单，设置为申请退款状态
			order.setStatus(OStatus.REFUND.getLine().getKey());
			break;
		}
		case 5: {//已完成订单，不做处理
			break;
		}
		case 6: {//已关闭订单，不做处理
			order.setStatus(OStatus.CLOSE.getLine().getKey());
			break;
		}
		case 7: {//正在申请关闭的订单，不做处理
			order.setStatus(OStatus.CLOSE.getLine().getKey());
			break;
		}
		case 8: {//申请退款订单，不做处理
			order.setStatus(OStatus.CLOSE.getLine().getKey());
			break;
		}
		}
		orderDao.update(order);
		return;
	}

	/**
	 * 订单完成
	 * @param order
	 * @param checkCode
	 * @return
	 */
	public Order completeOrder(String orderid, String checkCode) {
		Order order = orderDao.findByOrderid(orderid);
		if(!order.getStatus().equals(OStatus.PAYMENT.getLine().getKey())){
			throw LOG.err("statusErr", "订单未付款");
		} else if(!order.getCheckcode().equals(checkCode)) {
			throw LOG.err("validCheckCode", "核验码不正确");
		} 
		order.setStatus(OStatus.FINISH.getLine().getKey());
		orderDao.update(order);
		
		//判断用户是否符合成为会员条件，若符合，则成为会员并产生推广二维码
		memberService.becomeMember(order.getMember(), false);
		
		//更新佣金流水的状态，并给邀请人增加佣金以及历史佣金
		CommissionJournal journal = commissionJournalDao.findByOrderId(order.getOrderid());
		journal.setStatus(OStatus.FINISH.getLine().getKey());
		commissionJournalDao.update(journal);
		Member invited2 = journal.getMember();
		invited2.setHistoryCommission(invited2.getHistoryCommission().add(journal.getCommission()));
		invited2.setCashableCommission(invited2.getCashableCommission().add(journal.getCommission()));
		memberDao.update(invited2);
		
		return order;
	}

	/**
	 * 生成订单发送消息给管理人员
	 */
	public void doSent(Order order){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		final ServiceCenter serviceCen = serviceCenterDao.find(order.getAccount());
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
	 * 支付完成后，微信回调通知，其中参数不做校验
	 * @param outTradeNo 商户订单号
	 * @param totalFee 支付金额，单位为分
	 * @param time_end 支付完成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 * @return
	 */
	public boolean paidCallback(String outTradeNo, String totalFee, String time_end) {
		OrderPayJournal payJournal = orderPayJournalDao.findByOutTradeNo(outTradeNo);
		payJournal.setPayTime(new Date());
		orderPayJournalDao.update(payJournal);
		
		Order order = payJournal.getOrder();
		order.setPayment(order.getPayment().add(payJournal.getPrice()));
		if(order.getPayment().compareTo(order.getPayment()) >= 0) {//说明订单所有金额已支付完成,订单进入已付款状态,生成核验码,并进行分销处理
			order.setCheckcode(MchUtil.createRandomNum(6));
			order.setStatus(OStatus.PAYMENT.getLine().getKey());
			distribution(order.getMember(), order);
		} else if(order.getPayment().compareTo(order.getDeposit()) >= 0) {//支付定金成功，订单进入已付定金状态
			order.setStatus(OStatus.DEPOSIT.getLine().getKey());
		}
		orderDao.update(order);
		return true;
	}
	
	/**
	 * 用户订单进入已付款状态，根据分销规则，新增或更新佣金流水以及微信通知邀请人
	 */
	private void distribution(Member member, Order order){
		if(!order.getStatus().equals(OStatus.PAYMENT.getLine().getKey())) {
			LOG.err("statusErr", ""+order.getStatus());
			return;
		}
		DistributionRule distributionRule = distributionRuleDao.get(member.getAccount());
		CommissionJournal journal2 = null;
		Member invited2 = null;
		
		if((invited2 = member.getInvited2()) != null) {
			LOG.info("invited2:"+invited2);
			BigDecimal commission2 = order.getPrice().multiply(BigDecimal.valueOf(distributionRule.getBonus2())).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_FLOOR);
			//添加或者更新佣金流水
			commissionJournalService.saveOrUpdate(order, member, commission2, invited2);
		}
		
		try {
			//邀请人获得佣金，提醒用户
			String accessToken = WxAccountDAO.getAccessToken(Bean.get(WxAccount.class, member.getAccount()));
			WxMessageDAO.notifyCommissionJournal(accessToken, invited2.getOpenId(), journal2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("--------------orderBeenPaid():end--------------");
	}
	
	public List<Order> listByMember(Integer memberId) {
		return orderDao.listByMember(memberId);
	}
	public List<Order> listByRestaurant(Integer restaurantId) {
		return orderDao.listByRestaurant(restaurantId, null, OStatus.PAYMENT.getLine().getKey());
	}
	public List<Order> listByRestaurant(Integer restaurantId, String orderid, OStatus status) {
		return orderDao.listByRestaurant(restaurantId, orderid, status.getLine().getKey());
	}
	public Order findByOrderid(String orderid) {
		return orderDao.findByOrderid(orderid);
	}
	public Long countPending(Integer memberId) {
		return orderDao.countPending(memberId);
	}
}
