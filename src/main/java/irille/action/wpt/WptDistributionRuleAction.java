package irille.action.wpt;

import irille.action.ActionBase;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptDistributionRule;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptDistributionRuleAction extends ActionBase<WptDistributionRule> {
	public WptDistributionRule getBean() {
		return _bean;
	}

	public void setBean(WptDistributionRule bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptDistributionRule.class;
	}
	public String crtAll() {
	    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
	    if(account==null){
	      return "1=2";
	    }else{
	      return WptDistributionRule.T.PKEY + "=" + account.getPkey();
	    }
	}
}
