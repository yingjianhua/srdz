package irille.wpt.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import irille.wpt.bean.CashJournal;
import irille.wpt.dao.AbstractDao;
@Repository
public class CashJournalDao extends AbstractDao<CashJournal, Integer> {

	public List<CashJournal> listByMember(Integer memberId) {
		return list("select * from wpt_cash_journal where wxuser=?", memberId);
	}
}
