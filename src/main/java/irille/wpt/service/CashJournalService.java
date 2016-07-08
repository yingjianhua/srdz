package irille.wpt.service;

import java.math.BigDecimal;

import irille.pub.Log;
import irille.wpt.dao.impl.CashJournalDao;
import irille.wx.wpt.WptCashJournal;
import irille.wx.wx.WxUser;

public class CashJournalService {
	private static final Log LOG = new Log(CashJournalService.class);
	private CashJournalDao cashJournalDao;
	public WptCashJournal add(WxUser user, BigDecimal amt) {
		user.setCashableCommission(user.getCashableCommission().subtract(amt));
		if(user.getCashableCommission().compareTo(BigDecimal.ZERO) < 0) {
			throw LOG.err("NOTENOUGH", "可提现金额不足");
		}
		user.upd();
		return cashJournalDao.ins(user.getPkey(), user.getAccount(), amt);
	}
	public CashJournalDao getCashJournalDao() {
		return cashJournalDao;
	}
	public void setCashJournalDao(CashJournalDao cashJournalDao) {
		this.cashJournalDao = cashJournalDao;
	}
}
