package irille.action.wm;

import irille.action.ActionWx;
import irille.wx.wm.WmImage;
import irille.wx.wm.WmImageDAO;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

public class WmImageAction extends ActionWx<WmImage,WmImageAction> { 
	public WmImage getBean() {
		return _bean;
	}

	public void setBean(WmImage bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WmImage.class;
	}
	
	@Override
	public void insBefore() {
		super.insBefore();
		//将上传的图片文件保存到本地，并将保存路径设置到bean中
		//getBean().setImgUrl(saveMaterial(MaterialType.IMAGE));
	};
	public void sync() throws Exception {
		WmImage bean = WmImageDAO.sync(getPkey());
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(bean, "bean.");
		json.put(SUCCESS, true);
		response.getWriter().print(json.toString());
	}
	public void unsync() throws Exception {
		WmImage bean = WmImageDAO.unsync(getPkey());
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(bean, "bean.");
		json.put(SUCCESS, true);
		response.getWriter().print(json.toString());
	}
}
