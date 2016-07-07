package irille.wpt.service;

import java.math.BigDecimal;
import java.util.List;

import irille.wpt.dao.impl.CommissionJournalDao;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptCommissionJournal;
import irille.wx.wpt.WptOrder;
import irille.wx.wx.WxUser;

public class CommissionJournalService {
	private CommissionJournalDao commissionJournalDao;
	/**
	 * 用户订单付款后，新增邀请人的佣金流水
	 * @param order
	 * @param fan
	 * @param commission
	 * @param user
	 * @return
	 */
	public WptCommissionJournal add(WptOrder order, WxUser fan, BigDecimal commission, WxUser user) {
		return commissionJournalDao.ins(order.getOrderid(), order.getPrice(), commission, fan.getPkey(), fan.getImageUrl(), fan.getNickname(), user.getPkey(), OStatus.PAYMENT.getLine().getKey(), user.getAccount());
	}
	/**
	 * 用户订单完成后， 讲邀请人的对应的佣金流水的状态修改为已完成，并给邀请人增加佣金和历史佣金
	 * @param orderid
	 */
	public void upd(WptOrder order) {
		if(order.gtStatus() != OStatus.FINISH) {
			return;
		}
		List<WptCommissionJournal> journals = commissionJournalDao.upd(order.getOrderid());
		for(WptCommissionJournal journal:journals) {
			WxUser user = journal.gtWxuser();
			user.setHistoryCommission(user.getHistoryCommission().add(journal.getCommission()));
			user.setCashableCommission(user.getCashableCommission().add(journal.getCommission()));
			user.upd();
		}
	}
	public CommissionJournalDao getCommissionJournalDao() {
		return commissionJournalDao;
	}
	public void setCommissionJournalDao(CommissionJournalDao commissionJournalDao) {
		this.commissionJournalDao = commissionJournalDao;
	}
}
