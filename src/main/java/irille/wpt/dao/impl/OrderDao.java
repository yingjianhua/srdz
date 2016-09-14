package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Order;
import irille.wpt.dao.AbstractDao;

@Repository
public class OrderDao extends AbstractDao<Order, Integer>{

	
	public Order findByOrderid(String orderid) {
		return findUnique("select * from wpt_order where orderid=?", orderid);
	}
	public List<Order> listByMember(Integer memberId) {
		return list("select * from wpt_order where member=?", memberId);
	}
	public Long countPending(Integer memberId) {
		return count("select count(*) from wpt_order where member=? and status <> 5 and status <> 6", memberId);
	}

}
