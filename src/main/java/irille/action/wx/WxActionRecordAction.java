package irille.action.wx;

import irille.action.ActionBase;
import irille.wx.wx.WxActionRecord;

public class WxActionRecordAction extends ActionBase<WxActionRecord> {
  public WxActionRecord getBean() {
    return _bean;
  }

  public void setBean(WxActionRecord bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return WxActionRecord.class;
  }

}
