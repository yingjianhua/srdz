package irille.action.wpt;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptMenu;
import irille.wx.wpt.WptRestaurant;
import irille.wx.wpt.WptRestaurantDAO;
import irille.wx.wpt.WptRestaurantLine;

public class WptRestaurantAction extends ActionWx<WptRestaurant, WptRestaurantAction> {
	public static final Log LOG = new Log(WptRestaurantAction.class);
	private List<WptRestaurantLine> _listLine;
	private List<WptMenu> _menuList;


	public WptRestaurant getBean() {
		return _bean;
	}
	public void setBean(WptRestaurant bean) {
		this._bean = bean;
	}
	public List<WptRestaurantLine> getListLine() {
		return _listLine;
	}
	public void setListLine(List<WptRestaurantLine> _listLine) {
		this._listLine = _listLine;
	}
	public List<WptMenu> getMenuList() {
		return _menuList;
	}
	public void setMenuList(List<WptMenu> menuList) {
		_menuList = menuList;
	}

	@Override
	public Class beanClazz() {
		return WptRestaurant.class;
	}

	@Override
	public WptRestaurant insRun() throws Exception {
		insBefore();
		WptRestaurantDAO.Ins ins = new WptRestaurantDAO.Ins();
		ins.setB(_bean);
		ins.setLines(_listLine);
		ins.commit();
		insAfter();
		return ins.getB();
	}

	@Override
	public WptRestaurant updRun() throws Exception {
		updBefore();
		WptRestaurantDAO.Upd upd = new WptRestaurantDAO.Upd();
		upd.setB(_bean);
		upd.setLines(_listLine);
		upd.commit();
		updAfter();
		return upd.getB();
	}

	public void insOrUpd() throws Exception {
		WptRestaurant model = WptRestaurant.load(WptRestaurant.class, getBean().getPkey());
		List<WptMenu> menuList = getMenuList();
		if(menuList ==null)
			menuList = new ArrayList();
		for (WptMenu line : menuList) {
			WptMenu tline = WptMenu.chkUniqueRestaurantName(false, model.getPkey(), line.getName());
			if(tline != null) {
				PropertyUtils.copyPropertiesWithout(line, tline, WptMenu.T.DES, WptMenu.T.PRICE);
			} else {
				line.setAccount(model.getAccount());
			}
		}
		Idu.updLine(model, menuList, WptMenu.T.RESTAURANT.getFld());
	}
	public void addSpec() throws IOException, JSONException {
		WptRestaurantDAO dao = new WptRestaurantDAO();
		String[] spkeys = getPkeys().split(",");
		int [] pkeys = new int[spkeys.length];
		for(int i=0;i<spkeys.length;i++) {
			pkeys[i] = Integer.parseInt(spkeys[i]);
		}
		dao.addSpec(pkeys);
		ServletActionContext.getResponse().getWriter().print(new JSONObject().put("success", true));
	}
	public void enableDisable() throws Exception {
		WptRestaurantDAO dao = new WptRestaurantDAO();
		JSONObject jbean = crtJsonByBean(dao.enableDisable(getPkey()), "bean.");
		ServletActionContext.getResponse().getWriter().print(jbean.put("success", true));
	}
}
