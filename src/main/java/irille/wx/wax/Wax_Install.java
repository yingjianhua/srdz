package irille.wx.wax;



import irille.core.sys.SysMenu;
import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;
import irille.wx.wax.WaxBnft;
import irille.wx.wax.WaxBnftEntry;
import irille.wx.wax.WaxBnftPrize;
import irille.wx.wax.WaxLady;
import irille.wx.wax.WaxLadyBsn;

public class Wax_Install extends InstallBase {
	public static final Wax_Install INST = new Wax_Install();

	@Override
	public void initMenu(SysMenuDAO m) {
		SysMenu upBnft = m.procParent("福利活动", "wa", 50, null);
		SysMenu upLady = m.procParent("享女郎活动", "wa", 60, null);
		SysMenu upMan = m.procParent("男人帮活动", "wa", 70, null);
		
		m.proc(WaxBnft.TB, 51, upBnft, "wa");
		m.proc(WaxBnft.TB, 51, upBnft, "wa");//福利活动
		m.proc(WaxBnftEntry.TB, 53, upBnft, "wa");//福利报名
		m.proc(WaxBnftPrize.TB, 55, upBnft, "wa");//福利获奖
		
		m.proc(WaxLady.TB, 61, upLady, "wa");//享女郎
		m.proc(WaxLadyBsn.TB, 63, upLady, "wa");//拍摄商家
		
		m.proc(WaxMan.TB, 71, upMan, "wa");
		m.proc(WaxManBsn.TB, 73, upMan, "wa");
	}
}
