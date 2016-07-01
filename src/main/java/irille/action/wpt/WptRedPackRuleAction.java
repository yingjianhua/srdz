package irille.action.wpt;

import irille.action.ActionBase;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptRedPackRule;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptRedPackRuleAction extends ActionBase<WptRedPackRule> {
	public WptRedPackRule getBean() {
		return _bean;
	}

	public void setBean(WptRedPackRule bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptRedPackRule.class;
	}
	 public String crtAll() {
	    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
	    if(account==null){
	      return "1=2";
	    }else{
	      return WptRedPackRule.T.PKEY + "=" + account.getPkey();
	    }
	  }
}
