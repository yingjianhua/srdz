package irille.wx.wa;



import irille.core.sys.SysMenu;
import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Wa_Install extends InstallBase {
	public static final Wa_Install INST = new Wa_Install();

	@Override
	public void initMenu(SysMenuDAO m) {
		SysMenu upActVote = m.procParent("投票", "wa", 10, null);	
		SysMenu upAct = m.procParent("活动配置", "wa", 20, null);
		SysMenu upActTemp = m.procParent("模板配置", "wa", 30, null);
		
		m.proc(WaQRCode.TB, 40, null);//二维码
	}
}
