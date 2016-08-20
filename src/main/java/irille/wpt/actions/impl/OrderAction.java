package irille.wpt.actions.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.Exp;
import irille.pub.Log;
import irille.wpt.actions.AbstractCRUDAction;
import irille.wpt.interceptor.CityInterceptor;
import irille.wpt.service.OrderService;
import irille.wx.wpt.WptCity;
import irille.wx.wpt.WptOrder;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;
import irille.wxpub.util.MessageUtil;
import irille.wxpub.util.mch.MchUtil;
@Controller
@Scope("prototype")
public class OrderAction extends AbstractCRUDAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4912468017257111167L;
	private static final Log LOG = new Log(OrderAction.class);
	
	private String contactMan;
	private String contactSex;
	private String date;
	private String contactWay;
	private String contactType;
	private String rem;
	//若没有 : comboId == null
	private Integer comboId;
	private Integer banquetId;
	private String pnum;
	private String budget;
	private Integer areaId;
	private String services;

	private String orderId;
	private Integer comboNumber;

	private String checkCode;
	@Resource
	private OrderService orderService;

	public void confirmOrder() throws ParseException, JSONException, IOException {
		LOG.info("--------------confirmOrder():start--------------");
		WptOrder order = orderService.createOrder(contactMan, contactSex, date, contactWay, contactType, rem, 
				comboId, banquetId, pnum, budget, ((WptCity)getSession().get(CityInterceptor.CITY)).getPkey(), areaId, services, chkWxUser().getPkey(), getAccount().getPkey());
		JSONObject result = new JSONObject();
		if(order != null) {
			result.put(SUCCESS, true);
			result.put("orderId", order.getOrderid());
			ServletActionContext.getResponse().getWriter().print(result.toString());
		}
		LOG.info("--------------confirmOrder():end--------------");
	}
	/**
	 * 在快捷支付页面生成预支付参数,快捷支付页面可以调整订单中的套餐数量(只用于套餐订单)
	 * @throws Exception
	 */
	public void preparePay() throws Exception {
		LOG.info("--------------preparePay():start--------------");
		account = WxAccount.get(WxAccount.class, account.getPkey());
		JSONObject result = orderService.createPreparePay(orderId, comboNumber, chkWxUser(), account, getRequest());
		result.put(SUCCESS, true);
		ServletActionContext.getResponse().getWriter().print(result);
		LOG.info("--------------preparePay():end--------------");
	}
	/**
	 * 取消订单
	 * @throws JSONException
	 * @throws IOException
	 */
	public void cancel() {
		LOG.info("--------------cancel():start--------------");
		LOG.info("orderId:{0}", orderId);
		WptOrder order = WptOrder.loadUniqueOrderid(false, orderId);
		WxUser wxUser = chkWxUser();
		if(!order.getWxuser().equals(wxUser.getPkey())) { 
			return ;
		}
		try {
			orderService.cancelOrder(order);
		} catch (Exp e) {
			try {
				PrintWriter writer = ServletActionContext.getResponse().getWriter();
				writer.print(new JSONObject().put(SUCCESS, true).put("succMsg", e.getLastMessage()));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (JSONException e1) {
				e1.printStackTrace();
			}
		}
		LOG.info("--------------cancel():end--------------");
	}
	/**
	 * 校验核验码
	 */
	public void checkCode() throws IOException{
		try {
			orderService.complete(WptOrder.loadUniqueOrderid(false, orderId), checkCode);
			ServletActionContext.getResponse().getWriter().print("ok");
		} catch (Exp e) {
			ServletActionContext.getResponse().getWriter().print(e.getLastMessage());
		}
	}
	
	/**
	 * 接收微信支付异步通知回调
	 * <xml>
		  <appid><![CDATA[wx2421b1c4370ec43b]]></appid>
		  <attach><![CDATA[支付测试]]></attach>
		  <bank_type><![CDATA[CFT]]></bank_type>
		  <fee_type><![CDATA[CNY]]></fee_type>
		  <is_subscribe><![CDATA[Y]]></is_subscribe>
		  <mch_id><![CDATA[10000100]]></mch_id>
		  <nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>
		  <openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>
		  <out_trade_no><![CDATA[1409811653]]></out_trade_no>
		  <result_code><![CDATA[SUCCESS]]></result_code>
		  <return_code><![CDATA[SUCCESS]]></return_code>
		  <sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>
		  <sub_mch_id><![CDATA[10000100]]></sub_mch_id>
		  <time_end><![CDATA[20140903131540]]></time_end>
		  <total_fee>1</total_fee>
		  <trade_type><![CDATA[JSAPI]]></trade_type>
		  <transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>
		</xml>
	 * @return
	 * @throws Exception 
	 */
	public void notifyPay() throws Exception {
		LOG.info("--------------notifyPay():start--------------");
		PrintWriter writer = getResponse().getWriter();
		try {
			// xml请求解析
			Map<String, String> requestMap = new TreeMap<String, String>(MessageUtil.parseXml(getRequest()));
			if(!requestMap.containsKey("return_code") || !requestMap.get("return_code").equals("SUCCESS")) {
				writer.print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA["+requestMap.get("return_msg")+"]]></return_msg></xml>");
				return;
			}
			if(!requestMap.containsKey("result_code") || !requestMap.get("result_code").equals("SUCCESS")) {
				writer.print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA["+requestMap.get("err_code")+"]]></return_msg></xml>");
				return;
			}
			StringBuilder builder = new StringBuilder();
			for(String key:requestMap.keySet()) {
				if(key.equals("sign")) continue;
				builder.append(key+"="+requestMap.get(key)+"&");
			}
			builder.append("key="+WxAccount.loadUniqueAccountAppid(false, requestMap.get("appid")).getMchKey());
			LOG.info("wxSign:"+builder.toString());
			String sign = MchUtil.md5(builder.toString());
			LOG.info("sign:"+sign);
			if(sign.equals(requestMap.get("sign"))) {
				LOG.info("签名正确");
				orderService.paidCallback(requestMap.get("out_trade_no"), requestMap.get("total_fee"), requestMap.get("time_end")); 
				writer.print("<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
				return;
			} else {
				LOG.info("签名错误");
				writer.print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[签名错误]]></return_msg></xml>");
				return;
			}
		} catch (Exception e) {
			writer.print("<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[未知错误]]></return_msg></xml>");
		}
		LOG.info("--------------notifyPay():end--------------");
	}

	
	public String getContactMan() {
		return contactMan;
	}
	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}
	public String getContactSex() {
		return contactSex;
	}
	public void setContactSex(String contactSex) {
		this.contactSex = contactSex;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public String getContactType() {
		return contactType;
	}
	public void setContactType(String contactType) {
		this.contactType = contactType;
	}
	public String getRem() {
		return rem;
	}
	public void setRem(String rem) {
		this.rem = rem;
	}
	public Integer getComboId() {
		return comboId;
	}
	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}
	public Integer getBanquetId() {
		return banquetId;
	}
	public void setBanquetId(Integer banquetId) {
		this.banquetId = banquetId;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getComboNumber() {
		return comboNumber;
	}
	public void setComboNumber(Integer comboNumber) {
		this.comboNumber = comboNumber;
	}
	public String getCheckCode() {
		return checkCode;
	}
	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}
}
