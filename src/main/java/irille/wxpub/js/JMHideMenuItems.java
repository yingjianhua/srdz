package irille.wxpub.js;

import irille.pub.Log;

public class JMHideMenuItems extends JMBase<JMHideMenuItems> {
	public static final Log LOG = new Log(JMHideMenuItems.class);
	protected JsFldStr[] _menuList;
	public JsFldStr[] getMenuList() {
		return _menuList;
	}
	public void setMenuList(String...menuList) {
		JsFldStr[] arr = new JsFldStr[menuList.length];
		int i = 0;
		for (Object obj : menuList)
			arr[i++] = new JsFldStr(obj);
		this._menuList = arr;
	}
	
}