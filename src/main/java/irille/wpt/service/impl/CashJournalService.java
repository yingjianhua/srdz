package irille.wpt.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import irille.wpt.bean.CashJournal;
import irille.wpt.service.AbstractService;

@Service
public class CashJournalService extends AbstractService<CashJournal> {

	public List<CashJournal> listByMember(Integer memberId) {
		return cashJournalDao.listByMember(memberId);
	}
}
