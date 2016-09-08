package irille.wpt.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.wpt.bean.Member;
import irille.wpt.bean.QrcodeRule;
import irille.wpt.dao.impl.MemberDao;
import irille.wpt.dao.impl.QrcodeRuleDao;
import irille.wx.wpt.Wpt.OStatus;
import irille.wx.wpt.WptOrder;
import irille.wx.wpt.WptQrcodeRule;

@Service
public class MemberService {

	@Resource
	private MemberDao memberDao;
	@Resource
	private QrcodeRuleDao qrcodeRuleDao;
	
	public Member findByOpenidInAccount(Integer accountId, String openid) {
		return memberDao.findByOpenidInAccount(accountId, openid);
	}
	
	public void becomeMember(Member member) {
		if(member.getIsMember() == true) {
			//已经是会员，不做处理
			return;
		}
		boolean flag = false;
		QrcodeRule rule = qrcodeRuleDao.get(member.getAccount());
		if(!force) {
			BigDecimal single = rule.getSingle();
			BigDecimal amount = rule.getAmount();
			String sql = Idu.sqlString("select max({0}) max, sum({0}) sum from {1} where {2}=? and {3}=?", WptOrder.T.PRICE, WptOrder.class, WptOrder.T.WXUSER, WptOrder.T.STATUS);
			Map<String, Object> resultMap = Bean.executeQueryMap(sql, user.getPkey(), OStatus.FINISH.getLine().getKey())[0];
			//判断是否满足条件
			if(resultMap.get("max") != null && resultMap.get("sum") != null) {
				if(single.compareTo((BigDecimal)resultMap.get("max")) <= 0 || amount.compareTo((BigDecimal)resultMap.get("sum")) <= 0) {
					flag = true;
				}
			}
		} else {
			flag = true;
		}
		if(flag) {
			user.stIsMember(true);
			createQrcode(user, rule);
		}
	}
}
