package irille.wpt.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Order;
import irille.wpt.dao.AbstractDao;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.Wpt.OStatus;

@Repository
public class OrderDao extends AbstractDao<Order, Integer>{

	
	public Order findByOrderid(String orderid) {
		return findUnique("select * from wpt_order where orderid=?", orderid);
	}
	public List<Order> listByMember(Integer memberId) {
		return list("select * from wpt_order where member=?", memberId);
	}
	public List<Order> listByRestaurant(Integer restaurantId, String orderid, Byte status) {
		if(orderid == null && status == null) {
			return list("select * from wpt_order where restaurant=?", restaurantId);
		} else if(orderid != null && status != null) {
			return list("select * from wpt_order where restaurant=? and orderid like ? and status=?", restaurantId, "%"+orderid+"%", status);
		} else if(orderid != null) {
			return list("select * from wpt_order where restaurant=? and orderid like ?", restaurantId, "%"+orderid+"%");
		} else {
			return list("select * from wpt_order where restaurant=? and status=?", restaurantId, status);
		}
	}
	public Long countPending(Integer memberId) {
		return count("select count(*) from wpt_order where member=? and status <> 5 and status <> 6", memberId);
	}

	//统计某一会员的订单累计消费
	public BigDecimal totalConsumptionByMember(Integer memberId) {
		return (BigDecimal)uniqueResult("select sum(price) from wpt_order where member=? and status=?", memberId, OStatus.FINISH.getLine().getKey());
	}
	//某一会员的订单单笔消费最大金额
	public BigDecimal MaxSingleConsumptionByMember(Integer memberId) {
		return (BigDecimal)uniqueResult("select max(price) from wpt_order where member=? and status=?", memberId, OStatus.FINISH.getLine().getKey());
	}

}
