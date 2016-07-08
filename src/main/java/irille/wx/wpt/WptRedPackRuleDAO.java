package irille.wx.wpt;

import java.math.BigDecimal;

import irille.wx.wx.WxAccount;

public class WptRedPackRuleDAO {

	public static void initRedPack(Integer accountPkey) {
		WxAccount account = WxAccount.get(WxAccount.class, accountPkey);
		WptRedPackRule b = new WptRedPackRule();
		b.setPkey(accountPkey);
		b.setActName("美食分享");
		b.setSendName(account.getAccountName());
		b.setWishing("感谢您对"+account.getAccountName()+"的支持");
		b.setRemark("感谢您对"+account.getAccountName()+"的支持");
		b.setLeastAmt(BigDecimal.ZERO);
		b.setRowVersion((short)1);
		b.ins();
	}
}
