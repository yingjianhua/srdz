package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.OrderPayJournal;
import irille.wpt.dao.AbstractDao;

@Repository
public class OrderPayJournalDao extends AbstractDao<OrderPayJournal, Integer>{
	
	public OrderPayJournal findByOutTradeNo(String outTradeNo) {
		return findUnique("select * from wpt_order_pay_journal where outTradeNo=?", outTradeNo);
	}

	public List<OrderPayJournal> listByOrder(Integer orderId) {
		return list("select * from wpt_order_pay_journal where order_id=?", orderId);
	}
}
