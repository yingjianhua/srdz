package irille.wpt.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Repository;

import irille.wx.wpt.WptCashJournal;
@Repository
public class CashJournalDao{
	public WptCashJournal ins(Integer wxuser, Integer account, BigDecimal amt) {
		WptCashJournal journal = new WptCashJournal();
		journal.setWxuser(wxuser);
		journal.setPrice(amt);
		journal.setCreateTime(new Date());
		journal.setAccount(account);
		journal.setRowVersion((short)1);
		journal.ins();
		return journal;
	}
}
