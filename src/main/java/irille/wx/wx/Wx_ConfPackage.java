//Created on 2005-10-24
package irille.wx.wx;

import irille.core.sys.ConfPackage;
import irille.wx.js.Js;
import irille.wx.wa.Wa;
import irille.wx.wax.Wax;
import irille.wx.wm.Wm;
import irille.wx.wp.Wp;
import irille.wx.wpt.Wpt;

public class Wx_ConfPackage extends ConfPackage {
	public static final Wx_ConfPackage INST = new Wx_ConfPackage();

	public void installWx() {
		add(Wx.class, 8200);
		add(Wm.class, 8300);
		add(Wa.class, 8400);
		add(Js.class, 8600);
		add(Wp.class, 8500);
		add(Wpt.class, 8700);
		add(Wax.class, 8800);
	}
	
	@Override
	public void initPacks() {
		_packsFlag = true;
		installSys();
		installWx();
	}
}
