package irille.wpt.dao.impl;

import java.math.BigDecimal;
import java.util.Date;

import irille.wx.wpt.WptCommissionJournal;

public class CommissionJournalDao {
	
	public WptCommissionJournal ins(String orderid, BigDecimal price, BigDecimal commission, Integer fans, String imageUrl, String nickname, Integer wxuser, Integer account) {
		WptCommissionJournal journal = new WptCommissionJournal();
		journal.setOrderid(orderid);
		journal.setPrice(price);
		journal.setCommission(commission);
		journal.setFans(fans);
		journal.setImageUrl(imageUrl);
		journal.setNickname(nickname);
		journal.setWxuser(wxuser);
		journal.setCreateTime(new Date());
		journal.setAccount(account);
		journal.setRowVersion((short)1);
		journal.ins();
		return journal;
	}
}
