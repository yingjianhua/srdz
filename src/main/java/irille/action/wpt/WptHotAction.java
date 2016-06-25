package irille.action.wpt;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import irille.action.ActionWx;
import irille.wx.wpt.WptHot;
import irille.wx.wpt.WptRestaurant;

public class WptHotAction extends ActionWx<WptHot,WptHotAction> {
	public WptHot getBean() {
		return _bean;
	}

	public void setBean(WptHot bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptHot.class;
	}
	//
	public void addHot() throws JSONException, IOException{
		WptRestaurant restaurant = WptRestaurant.load(WptRestaurant.class, getPkeys());
		JSONObject result = new JSONObject();
		if(WptHot.chkUniqueRestaurantAccount(false, Integer.valueOf(getPkeys()), restaurant.getAccount()) != null){
			result.put(SUCCESS, false);
			result.put("msg", "已存在,请勿重复添加");
			ServletActionContext.getResponse().getWriter().print(result.toString());
		}else{
			WptHot hot = new WptHot();
			hot.setAccount(restaurant.getAccount());
			hot.setCity(restaurant.getCity());
			hot.setRestaurant(restaurant.getPkey());
			hot.setSort(0);
			hot.ins();
			result.put(SUCCESS, true);
			ServletActionContext.getResponse().getWriter().print(result.toString());
		}
	}
	public void delHot() throws JSONException, IOException{
		WptRestaurant restaurant = WptRestaurant.load(WptRestaurant.class, getPkeys());
		JSONObject result = new JSONObject();
		WptHot hot = WptHot.chkUniqueRestaurantAccount(false, Integer.valueOf(getPkeys()), restaurant.getAccount());
		if(hot != null){
			hot.del();
			result.put(SUCCESS, true);
			ServletActionContext.getResponse().getWriter().print(result.toString());
		}else{
			result.put(SUCCESS, false);
			result.put("msg", "热销中不存在");
			ServletActionContext.getResponse().getWriter().println(result.toString());
		}
	}
}
