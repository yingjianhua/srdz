package irille.wpt.service;

import java.math.BigDecimal;

import irille.wpt.dao.impl.CashJournalDao;
import irille.wx.wpt.WptCashJournal;
import irille.wx.wx.WxUser;

public class CashJournalService {
	private CashJournalDao cashJournalDao;
	public WptCashJournal add(WxUser user, BigDecimal amt) {
		return cashJournalDao.ins(user.getPkey(), user.getAccount(), amt);
	}
	public CashJournalDao getCashJournalDao() {
		return cashJournalDao;
	}
	public void setCashJournalDao(CashJournalDao cashJournalDao) {
		this.cashJournalDao = cashJournalDao;
	}
}
