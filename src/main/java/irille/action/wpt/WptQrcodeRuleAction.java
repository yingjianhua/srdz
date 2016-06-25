package irille.action.wpt;

import irille.action.ActionBase;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptQrcodeRule;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptQrcodeRuleAction extends ActionBase<WptQrcodeRule> {
	public WptQrcodeRule getBean() {
		return _bean;
	}

	public void setBean(WptQrcodeRule bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptQrcodeRule.class;
	}
	 public String crtAll() {
	    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
	    if(account==null){
	      return "1=2";
	    }else{
	      return WptQrcodeRule.T.PKEY + "=" + account.getPkey();
	    }
	  }
}
