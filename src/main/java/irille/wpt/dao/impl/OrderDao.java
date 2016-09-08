package irille.wpt.dao.impl;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.Order;
import irille.wpt.dao.AbstractDao;

@Repository
public class OrderDao extends AbstractDao<Order, Integer>{

	public Order findByOrderid(String orderid) {
		return findUnique("select * from wpt_order where orderid=?", orderid);
	}
}
