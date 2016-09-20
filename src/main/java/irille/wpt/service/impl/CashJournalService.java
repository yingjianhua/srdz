package irille.wpt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.wpt.bean.CashJournal;
import irille.wpt.dao.impl.CashJournalDao;

@Service
public class CashJournalService {
	
	@Resource
	private CashJournalDao cashJournalDao;
	
	public List<CashJournal> listByMember(Integer memberId) {
		return cashJournalDao.listByMember(memberId);
	}
}
