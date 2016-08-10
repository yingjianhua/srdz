package irille.wpt.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptCommissionJournal;
@Repository
public class CommissionJournalDao {
	
	public WptCommissionJournal ins(String orderid, BigDecimal price, BigDecimal commission, Integer fans, String imageUrl, String nickname, Integer wxuser, Byte status, Integer account) {
		WptCommissionJournal journal = new WptCommissionJournal();
		journal.setOrderid(orderid);
		journal.setPrice(price);
		journal.setCommission(commission);
		journal.setFans(fans);
		journal.setImageUrl(imageUrl);
		journal.setNickname(nickname);
		journal.setWxuser(wxuser);
		journal.setStatus(status);
		journal.setCreateTime(new Date());
		journal.setAccount(account);
		journal.setRowVersion((short)1);
		journal.ins();
		return journal;
	}
	/**
	 * 将该订单的所对应的佣金流水的状态设置为已完成
	 * @param orderid
	 */
	public List<WptCommissionJournal> upd(String orderid) {
		String sql = Idu.sqlString("update {0} set {1}=? where {2}=?", WptCommissionJournal.class, WptCommissionJournal.T.STATUS, WptCommissionJournal.T.ORDERID);
		Bean.executeUpdate(sql, OStatus.FINISH.getLine().getKey(), orderid);
		String where = Idu.sqlString("{0}=?", WptCommissionJournal.T.ORDERID);
		return Bean.list(WptCommissionJournal.class, where, false, orderid);
	}
}
