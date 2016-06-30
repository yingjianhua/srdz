package irille.wpt.service;

import java.math.BigDecimal;

import irille.pub.bean.Bean;
import irille.wpt.dao.impl.CommissionJournalDao;
import irille.wx.wpt.WptCommissionJournal;
import irille.wx.wpt.WptDistributionRule;
import irille.wx.wpt.WptOrder;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxMessageDAO;
import irille.wx.wx.WxUser;

public class DistributionRuleService {
	private CommissionJournalDao commissionJournalDao;
	/**
	 * 用户订单完成，根据分销规则，进行佣金分配
	 */
	public void distribution(WxUser user, WptOrder order){
		WptDistributionRule rule = Bean.get(WptDistributionRule.class, user.getAccount());
		WptCommissionJournal commissionJournal1 = null;
		WptCommissionJournal commissionJournal2 = null;
		WptCommissionJournal commissionJournal3 = null;
		WxUser invited1 = null;
		WxUser invited2 = null;
		WxUser invited3 = null;
		
		if(user.getInvited3() != null) {
			invited3 = Bean.loadAndLock(WxUser.class, user.getInvited3());
			BigDecimal commission3 = order.getPrice().multiply(BigDecimal.valueOf(rule.getBonus3()));
			invited3.setHistoryCommission(invited3.getHistoryCommission().add(commission3));
			invited3.setCashableCommission(invited3.getCashableCommission().add(commission3));
			invited3.upd();
			commissionJournal3 = commissionJournalDao.ins(order.getOrderid(), order.getPrice(), commission3, user.getPkey(), user.getImageUrl(), user.getNickname(), invited3.getPkey(), user.getAccount());
			if(user.getInvited2() != null) {
				invited2 = Bean.loadAndLock(WxUser.class, user.getInvited2());
				BigDecimal commission2 = order.getPrice().multiply(BigDecimal.valueOf(rule.getBonus2()));
				invited2.setHistoryCommission(invited2.getHistoryCommission().add(commission2));
				invited2.setCashableCommission(invited2.getCashableCommission().add(commission2));
				invited2.upd();
				commissionJournal2 = commissionJournalDao.ins(order.getOrderid(), order.getPrice(), commission2, user.getPkey(), user.getImageUrl(), user.getNickname(), invited2.getPkey(), user.getAccount());
				if(user.getInvited1() != null) {
					invited1 = Bean.loadAndLock(WxUser.class, user.getInvited1());
					BigDecimal commission1 = order.getPrice().multiply(BigDecimal.valueOf(rule.getBonus1()));
					invited1.setHistoryCommission(invited1.getHistoryCommission().add(commission1));
					invited1.setCashableCommission(invited1.getCashableCommission().add(commission1));
					invited1.upd();	
					commissionJournal1 = commissionJournalDao.ins(order.getOrderid(), order.getPrice(), commission1, user.getPkey(), user.getImageUrl(), user.getNickname(), invited1.getPkey(), user.getAccount());
				}
			}
		}
		//邀请人获得佣金，提醒用户
		WxMessageDAO.notifyCommissionJournal(WxAccountDAO.getAccessToken(user.gtAccount()), commissionJournal3, invited3.getOpenId(), commissionJournal2, invited2.getOpenId(), commissionJournal1, invited1.getOpenId());
	}
	public CommissionJournalDao getCommissionJournalDao() {
		return commissionJournalDao;
	}
	public void setCommissionJournalDao(CommissionJournalDao commissionJournalDao) {
		this.commissionJournalDao = commissionJournalDao;
	}
}
