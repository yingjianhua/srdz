package irille.wx.wx;

import irille.core.sys.SysMenuDAO;
import irille.pub.bean.InstallBase;

public class Wx_Install extends InstallBase {
	public static final Wx_Install INST = new Wx_Install();

	@Override
	public void initMenu(SysMenuDAO m) {
		m.proc(WxOpenPlat.TB, 10, null);
		m.proc(WxAccount.TB, 20, null);
		m.proc(WxSubscribe.TB, 30, null);
		m.proc(WxAuto.TB, 40, null);
		m.proc(WxMenu.TB, 50, null);
		m.proc(WxSetting.TB, 60, null);
		
		m.proc(WxUser.TB, 10, null, "wxex");
		m.proc(WxUserGroup.TB, 20, null, "wxex");
		m.proc(WxMessage.TB, 30, null, "wxex");
		m.proc(WxMassMessage.TB, 40, null, "wxex");
		m.proc(WxExpand.TB, 50, null, "wxex");
		m.proc(WxActionRecord.TB, 60, null, "wxex");
	}
}
