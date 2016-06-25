package irille.wx.pay;

import java.util.Date;

public interface IOrder {
	/**
	 * 商品描述  商品或支付单简要描述
	 * @return
	 */
	public String getBody();
	/**
	 * 商品详情  商品名称明细列表
	 * @return
	 */
	public String getDetail();
	/**
	 * 商户订单号 商户系统内部的订单号,32个字符内、可包含字母, 要求商户订单号保持唯一性（建议根据当前系统时间加随机序列来生成订单号）
	 * @return
	 */
	public String getOutTradeNo();
	/**
	 * 预支付交易会话标识 微信生成的预支付回话标识，用于后续接口调用中使用，该值有效期为2小时
	 * @return
	 */
	public String getPrepareId();
	/**
	 * 设置预支付交易会话标识
	 * @param prepareId 预支付交易会话标识
	 * @param prepareTime 预支付交易会话标识创建时间
	 */
	public void setPrepareId(String prepareId, Date prepareTime);
	/**
	 * 商户退款单号 商户系统内部的退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔 String(32)
	 * @return
	 */
	public String getOutRefundNo();
	/**
	 * 获取该订单当前状态下，需要支付的金额,转换为以分为单位
	 * @return
	 */
	public int getTotalFee();
	
}
