package irille.action.wm;

import irille.action.ActionWx;
import irille.wx.wm.WmVideo;
import irille.wx.wm.WmVideoDAO;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

public class WmVideoAction extends ActionWx<WmVideo,WmVideoAction> {
	public WmVideo getBean() {
		return _bean;
	}

	public void setBean(WmVideo bean) {
		this._bean = bean;
	}
	

	@Override
	public Class beanClazz() {
		return WmVideo.class;
	}
	
	public void sync() throws Exception {
		WmVideo bean = WmVideoDAO.sync(getPkey());
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(bean, "bean.");
		json.put(SUCCESS, true);
		response.getWriter().print(json.toString());
	}
	public void unsync() throws Exception {
		WmVideo bean = WmVideoDAO.unsync(getPkey());
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(bean, "bean.");
		json.put(SUCCESS, true);
		response.getWriter().print(json.toString());
	}
}
