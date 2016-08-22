package irille.wx.wpt;

public class WptDistributionRuleDAO {

	public static void initDistributionRule(Integer accountPkey) {
		WptDistributionRule b = new WptDistributionRule();
		b.setPkey(accountPkey);
		b.setBonus1(10);
		b.setBonus2(20);
		b.setRowVersion((short)1);
		b.ins();
	}
}
