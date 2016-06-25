package irille.action.wm;

import irille.action.ActionBase;
import irille.pub.idu.Idu;
import irille.wx.wm.WmText;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WmTextAction extends ActionBase<WmText> {
	public WmText getBean() {
		return _bean;
	}

	public void setBean(WmText bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WmText.class;
	}
	@Override
	public String crtAll() {
	  WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
	  if(account==null){
	    return "1=2";
	  }else{
	    return WmText.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
	  }
	}
	
}
