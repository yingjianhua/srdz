package irille.wpt.service;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.wpt.tools.SmsTool;
import irille.wpt.tools.TradeNoFactory;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptCityLine;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptComboLine;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.WptOrderDAO;
import irille.wx.wpt.WptOrderLine;
import irille.wx.wpt.WptOrderService;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptService;
import irille.wx.wpt.WptServiceCen;
import irille.wx.wpt.WptWxTips;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxMessageDAO;
import irille.wx.wx.WxUser;
import irille.wxpub.util.mch.MchUtil;

public class OrderService {
	private static final Log LOG = new Log(OrderService.class);
	private SmsTool smsTool;
	private DistributionRuleService distributionRuleService; 
	private QrcodeRuleService qrcodeRuleService;
	
	private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm");
	/**
	 * 根据参数生成一个订单对象
	 * @param contactMan 联系人姓名
	 * @param contactSex 联系人性别
	 * @param date 用餐时间 yyyy/MM/dd HH:mm
	 * @param contactWay 联系号码
	 * @param contactType 联系方式
	 * @param rem 备注
	 * @param comboId 套餐id
	 * @param banquetId 宴会类型id
	 * @param pnum 就餐人数
	 * @param perCapitaBudget 人均消费
	 * @param areaId 区域
	 * @param services 定制服务 id1,id2,id3,id4
	 * @param user 微信用户id
	 * @param account 微信公众号id
	 * @return 生成的订单
	 */
	public WptOrder createOrder(String contactMan, String contactSex, String date, String contactWay, String contactType, String rem,
			Integer comboId, Integer banquetId, Double pnum, Double perCapitaBudget, Integer areaId, String services, Integer user, Integer account) {
		WptOrder order = new WptOrder();
		try {
			order.setTime(INPUT_DATE_FORMAT.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		order.setContactMan(contactMan);
		order.setContactSex(Byte.valueOf(contactSex));
		order.setContactType(Byte.valueOf(contactType));
		order.setContactWay(contactWay);
		order.setRem(rem);
		order.setBanquet(banquetId);
		order.setWxuser(user);
		if(comboId != null) {
			WptCombo combo = WptCombo.load(WptCombo.class, comboId);
			WptRestaurant restaurant = combo.gtRestaurant();
			order.setComboName(combo.getName());
			order.setRestaurant(combo.getRestaurant());
			order.setPrice(combo.getPrice());
			order.setCity(restaurant.getCity());
		} else {
			order.setCity(WptCityLine.get(WptCityLine.class, areaId).getCity());
		}
		if(perCapitaBudget != null) {
			order.setConsumption(BigDecimal.valueOf(perCapitaBudget));
		}
		if(pnum != null) {
			order.setNumber(pnum.intValue());
		}
		
		order.setCreateTime(new Date());
		order.setAccount(account);
		order.setRowVersion((short)1);
		if((services != null && !services.equals("")) || comboId == null) {
			//是私人定制类型的订单
			order.stIsPt(true);
			order.stStatus(OStatus.NOTACCEPTED);
		} else {
			//不是私人定制类型的订单
			order.stIsPt(false);
			order.stStatus(OStatus.UNPAYMENT);
		}
		order.setOrderid(TradeNoFactory.createOrderidUnique());
		order.setDepPayId(order.getOrderid()+"d");
		order.ins();
		//添加订单的菜品
		if(comboId != null) {
			List<WptComboLine> comboLines = BeanBase.list(WptComboLine.class, WptComboLine.T.COMBO+"=?", false, comboId);
			for(WptComboLine line:comboLines) {
				WptOrderLine orderLine = new WptOrderLine();
				orderLine.setAccount(account);
				orderLine.setName(line.gtMenu().getName());
				orderLine.setPrice(line.getPrice());
				orderLine.setNumber(1);
				orderLine.stWptorder(order);
				orderLine.ins();
			}
		}
		//添加订单的服务
		if(services != null && !services.equals("")) {
			for(WptService line:BeanBase.list(WptService.class, WptService.T.PKEY+" in ("+services+")", false)) {
				WptOrderService orderService = new WptOrderService();
				orderService.setName(line.getName());
				orderService.setPrice(line.getPrice());
				orderService.setWptorder(order.getPkey());
				orderService.setAccount(order.getAccount());
				orderService.ins();
			}
		}
		//假如是私人订制的订单,或者有备注的订单发送短信和微信提醒 
		//if(order.gtIsPt() == true || (order.getRem()!= null && !order.getRem().trim().equals("")))
			doSent(order);
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
	public JSONObject createPreparePay(String orderId, Integer comboNumber, WxUser user, WxAccount account, HttpServletRequest request) {
		WptOrder order = WptOrder.loadUniqueOrderid(false, orderId);
		if(order.gtStatus() != OStatus.UNPAYMENT && order.gtStatus() != OStatus.DEPOSIT && order.gtStatus() != OStatus.ACCEPTED) {
			throw LOG.err("numberErr", "套餐数量异常");
		}
		if(!order.getWxuser().equals(user.getPkey())) {return null;}
		if(comboNumber != null) {
			if(comboNumber < 1) {
				throw LOG.err("statusErr", "状态异常");
			} else if(comboNumber > 1) {
				List<WptOrderLine> list = BeanBase.list(WptOrderLine.class, WptOrderLine.T.WPTORDER+"=?", false, order.getPkey());
				for(WptOrderLine line:list) {
					line.setNumber(comboNumber*line.getNumber());
					line.upd();
				}
				order.setPrice(order.getPrice().multiply(BigDecimal.valueOf(comboNumber)));
				order.upd();
			}
		}
		
		Map<String, String> payParams;
		try {
			payParams = WptOrderDAO.prepareParams(request, order, user, account);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return new JSONObject(payParams);
	}
	public List<WptOrder> list(Integer user) {
		String orderBy = " ORDER BY " + WptOrder.T.CREATE_TIME + " DESC ";
		List<WptOrder> orders = BeanBase.list(WptOrder.class, WptOrder.T.WXUSER+"=?" + orderBy, false, user);
		return orders;
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
	
	public SmsTool getSmsTool() {
		return smsTool;
	}
	public void setSmsTool(SmsTool smsTool) {
		this.smsTool = smsTool;
	}
	public DistributionRuleService getDistributionRuleService() {
		return distributionRuleService;
	}
	public void setDistributionRuleService(DistributionRuleService distributionRuleService) {
		this.distributionRuleService = distributionRuleService;
	}
	public QrcodeRuleService getQrcodeRuleService() {
		return qrcodeRuleService;
	}
	public void setQrcodeRuleService(QrcodeRuleService qrcodeRuleService) {
		this.qrcodeRuleService = qrcodeRuleService;
	}
}
