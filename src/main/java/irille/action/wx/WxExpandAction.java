package irille.action.wx;

import irille.action.ActionBase;
import irille.pub.idu.Idu;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxExpand;

public class WxExpandAction extends ActionBase<WxExpand> {
  public WxExpand getBean() {
    return _bean;
  }

  public void setBean(WxExpand bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return WxExpand.class;
  }
  @Override
  public String crtAll() {
    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
    if(account==null){
      return "1=2";
    }else{
      return WxExpand.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
    }
  }

}
