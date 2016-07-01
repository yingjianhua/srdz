package irille.wx.wpt;

public class WptRedPackRuleDAO {

	public static void initRedPack(Integer accountPkey) {
		WptRedPackRule b = new WptRedPackRule();
		b.setPkey(accountPkey);
		b.setActName("");
		b.setSendName("");
		b.setWishing("");
		b.setRowVersion((short)1);
		b.ins();
	}
}
