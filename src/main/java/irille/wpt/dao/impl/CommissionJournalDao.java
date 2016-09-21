package irille.wpt.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.CommissionJournal;
import irille.wpt.dao.AbstractDao;
@Repository
public class CommissionJournalDao extends AbstractDao<CommissionJournal, Integer> {
	
	public BigDecimal countFans1Sale(Integer memberId) {
		return (BigDecimal)uniqueResult("select sum(price) from wpt_commission_journal where wxuser=?", memberId);
	}
	
	public CommissionJournal findByOrderId(String orderid) {
		return findUnique("select * from wpt_commission_journal where orderid=?", orderid);
	}
	
	public List<CommissionJournal> listByOrderIdOrFanId(Integer memberId, String orderIdOrFanId) {
		if(orderIdOrFanId != null && !orderIdOrFanId.equals("")) {
			return list("select * from wpt_commission_journal where wxuser=? and (orderid=? or fans=?)", memberId, orderIdOrFanId);
		} else {
			return list("select * from wpt_commission_journal where wxuser=?", memberId);
		}
	}
}
