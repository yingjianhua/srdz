package irille.wx.wp;

import irille.core.sys.SysMenu;
import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Wp_Install extends InstallBase {
	public static final Wp_Install INST = new Wp_Install();

	@Override
	public void initMenu(SysMenuDAO m) {
		SysMenu upXw = m.procParent("图文秀", "wp", 10, null);
		m.proc(WpArtShow.TB, 11, upXw);
		m.proc(WpArt.TB, 12, upXw);
		m.proc(WpArtTheme.TB, 13, upXw);
		m.proc(WpArtBanner.TB, 14, upXw);
		
		SysMenu upBsn = m.procParent("商家管理", "wp", 20, null);
		
		m.proc(WpBsn.TB, 21, upBsn);
		m.proc(WpBsnCtg.TB, 22, upBsn);
		m.proc(WpBsnArea.TB, 23, upBsn);
	}
}
