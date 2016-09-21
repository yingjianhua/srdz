package irille.wpt.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.CommissionJournal;
import irille.wpt.bean.Member;
import irille.wpt.bean.Order;
import irille.wpt.dao.impl.CommissionJournalDao;
import irille.wx.wpt.Wpt.OStatus;
@Service
public class CommissionJournalService {
	@Resource
	private CommissionJournalDao commissionJournalDao;
	
	/**
	 * 用户订单付款后，根据订单信息产生或更新，邀请人的佣金流水
	 * @param order
	 * @param fan
	 * @param commission
	 * @param user
	 * @return
	 */
	public CommissionJournal saveOrUpdate(Order order, Member fan, BigDecimal commission, Member member) {
		CommissionJournal journal = commissionJournalDao.findByOrderId(order.getOrderid());
		if(journal != null) {
			journal.setPrice(order.getPrice());
			journal.setCommission(commission);
			commissionJournalDao.update(journal);
		} else {
			journal = new CommissionJournal();
			journal.setOrderid(order.getOrderid());
			journal.setPrice(order.getPrice());
			journal.setCommission(commission);
			journal.setFan(fan);
			journal.setImageUrl(fan.getImageUrl());
			journal.setNickname(fan.getNickname());
			journal.setMember(member);
			journal.setStatus(OStatus.PAYMENT.getLine().getKey());
			journal.setCreateTime(new Date());
			journal.setAccount(order.getAccount());
			commissionJournalDao.save(journal);
		}
		return journal;
	}
	public BigDecimal countFans1Sale(Integer memberId) {
		return commissionJournalDao.countFans1Sale(memberId);
	}
	/**
	 * 根据订单ID或者粉丝ID 搜索佣金流水
	 * @param orderIdOrFanId
	 * @return
	 */
	public List<CommissionJournal> list(Integer memberId, String orderIdOrFanId) {
		return commissionJournalDao.listByOrderIdOrFanId(memberId, orderIdOrFanId);
	}
}
