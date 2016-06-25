package irille.wxpub.js;

import irille.pub.Log;

public class JMShowMenuItems extends JMBase<JMShowMenuItems> {
	public static final Log LOG = new Log(JMShowMenuItems.class);
	protected JsFldStr[] _menuList;
	public JsFldStr[] getMenuList() {
		return _menuList;
	}
	public void setMenuList(JsFldStr[] menuList) {
		_menuList = menuList;
	}
	public void stMenuList(Object... menuList) {
		JsFldStr[] arr = new JsFldStr[menuList.length];
		int i = 0;
		for (Object obj : menuList)
			arr[i++] = new JsFldStr(obj);
		this._menuList = arr;
	}
	
}