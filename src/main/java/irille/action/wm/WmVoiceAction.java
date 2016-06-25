package irille.action.wm;

import irille.action.ActionWx;
import irille.wx.wm.WmVoice;
import irille.wx.wm.WmVoiceDAO;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

public class WmVoiceAction extends ActionWx<WmVoice,WmVoiceAction> {
	public WmVoice getBean() {
		return _bean;
	}

	public void setBean(WmVoice bean) {
		this._bean = bean;
	}
	

	@Override
	public Class beanClazz() {
		return WmVoice.class;
	}
	
	public void sync() throws Exception {
		WmVoice bean = WmVoiceDAO.sync(getPkey());
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(bean, "bean.");
		json.put(SUCCESS, true);
		response.getWriter().print(json.toString());
	}
	public void unsync() throws Exception {
		WmVoice bean = WmVoiceDAO.unsync(getPkey());
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(bean, "bean.");
		json.put(SUCCESS, true);
		response.getWriter().print(json.toString());
	}
}
