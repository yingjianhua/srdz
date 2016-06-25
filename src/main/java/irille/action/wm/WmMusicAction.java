package irille.action.wm;

import irille.action.ActionWx;
import irille.wx.wm.WmMusic;
import irille.wx.wm.WmMusicDAO;
import irille.wx.wm.WmMusicDAO.Upload;

public class WmMusicAction extends ActionWx<WmMusic,WmMusicAction> {
	public WmMusic getBean() {
		return _bean;
	}

	public void setBean(WmMusic bean) {
		this._bean = bean;
	}
	

	@Override
	public Class beanClazz() {
		return WmMusic.class;
	}
	
	public void upload() throws Exception {
		WmMusicDAO.Upload upload = new Upload();
		upload.setBKey(getPkey());
		upload.commit();
		writeSuccess(upload.getB());
	}	
}
