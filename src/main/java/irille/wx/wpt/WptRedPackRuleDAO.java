package irille.wx.wpt;

import java.math.BigDecimal;

public class WptRedPackRuleDAO {

	public static void initRedPack(Integer accountPkey) {
		WptRedPackRule b = new WptRedPackRule();
		b.setPkey(accountPkey);
		b.setActName("");
		b.setSendName("");
		b.setWishing("");
		b.setLeastAmt(BigDecimal.ZERO);
		b.setRowVersion((short)1);
		b.ins();
	}
}
