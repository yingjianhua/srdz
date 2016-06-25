package irille.action.wx;

import irille.action.ActionBase;
import irille.wx.wx.WxSetting;

public class WxSettingAction extends ActionBase<WxSetting> {
	public WxSetting getBean() {
		return _bean;
	}

	public void setBean(WxSetting bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WxSetting.class;
	}
//
//	@Override
//	public String crtAll() {
//		return WxSetting.T.USER_SYS.getFld().getCodeSqlField() + " = "
//				+ Idu.getUser().getPkey();
//	}

}
