package irille.wx.wpt;

import java.math.BigDecimal;

public class WptQrcodeRuleDAO {

	public static void initQrcodeRule(Integer accountPkey) {
		WptQrcodeRule b = new WptQrcodeRule();
		b.setPkey(accountPkey);
		b.setAmount(BigDecimal.valueOf(1000));
		b.setSingle(BigDecimal.valueOf(299));
		b.setValidityPeriod(30);
		b.setRowVersion((short)1);
		b.ins();
	}
}
