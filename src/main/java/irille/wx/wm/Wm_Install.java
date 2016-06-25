package irille.wx.wm;



import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Wm_Install extends InstallBase {
	public static final Wm_Install INST = new Wm_Install();

	@Override
	public void initMenu(SysMenuDAO m) {
		m.proc(WmText.TB, 10, null);
		m.proc(WmImage.TB, 20, null);
		m.proc(WmMusic.TB, 30, null);
		m.proc(WmVideo.TB, 40, null);
		m.proc(WmVoice.TB, 50, null);
		m.proc(WmNews.TB, 60, null);
		
	}
}
