package irille.action.wx;

import irille.action.ActionBase;
import irille.wx.wx.WxOpenPlat;

public class WxOpenPlatAction extends ActionBase<WxOpenPlat> {
  public WxOpenPlat getBean() {
    return _bean;
  }

  public void setBean(WxOpenPlat bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return WxOpenPlat.class;
  }
}
