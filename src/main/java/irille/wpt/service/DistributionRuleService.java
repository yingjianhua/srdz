package irille.wpt.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.Log;
import irille.pub.bean.Bean;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptCommissionJournal;
import irille.wx.wpt.WptDistributionRule;
import irille.wx.wpt.WptOrder;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxMessageDAO;
import irille.wx.wx.WxUser;
@Service
public class DistributionRuleService {
	private static final Log LOG = new Log(DistributionRuleService.class);
	@Resource
	private CommissionJournalService commissionJournalService;
	/**
	 * 用户订单进入已付款状态，根据分销规则，产生佣金流水以及微信通知邀请人
	 */
	public void orderBeenPaid(WxUser user, WptOrder order){
		LOG.info("--------------orderBeenPaid():start--------------");
		if(order.gtStatus() != OStatus.PAYMENT) {
			LOG.err("statusErr", ""+order.getStatus());
			return;
		}
		WptDistributionRule rule = Bean.get(WptDistributionRule.class, user.getAccount());
		WptCommissionJournal commissionJournal1 = null;
		WptCommissionJournal commissionJournal2 = null;
		WxUser invited1 = null;
		WxUser invited2 = null;
		
		if(user.getInvited2() != null) {
			LOG.info("invited2:"+user.getInvited2());
			invited2 = Bean.load(WxUser.class, user.getInvited2());
			BigDecimal commission2 = order.getPrice().multiply(BigDecimal.valueOf(rule.getBonus2())).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_FLOOR);
			commissionJournal2 = commissionJournalService.add(order, user, commission2, invited2);
			if(user.getInvited1() != null) {
				LOG.info("invited1:"+user.getInvited1());
				invited1 = Bean.load(WxUser.class, user.getInvited1());
				BigDecimal commission1 = order.getPrice().multiply(BigDecimal.valueOf(rule.getBonus1())).divide(BigDecimal.valueOf(100), 2, BigDecimal.ROUND_FLOOR);
				commissionJournal1 = commissionJournalService.add(order, user, commission1, invited1);
			}
		}
		//邀请人获得佣金，提醒用户
		String accessToken = WxAccountDAO.getAccessToken(user.gtAccount());
		
		try {
			WxMessageDAO.notifyCommissionJournal(accessToken, commissionJournal2, invited2==null?null:invited2.getOpenId(), commissionJournal1, invited1==null?null:invited1.getOpenId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.info("--------------orderBeenPaid():end--------------");
	}
	
	public void orderBeenComplete(WptOrder order) {
		commissionJournalService.upd(order);
	}
}
