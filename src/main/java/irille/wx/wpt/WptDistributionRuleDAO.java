package irille.wx.wpt;

public class WptDistributionRuleDAO {

	public static void initDistributionRule(Integer accountPkey) {
		WptDistributionRule b = new WptDistributionRule();
		b.setPkey(accountPkey);
		b.setBonus1(5);
		b.setBonus2(10);
		b.setBonus3(15);
		b.setRowVersion((short)1);
		b.ins();
	}
}
