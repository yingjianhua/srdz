package irille.action.wx;

import irille.action.ActionBase;
import irille.pub.idu.Idu;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxUserGroup;
import irille.wx.wx.WxUserGroupDAO;

import java.io.IOException;

import org.json.JSONException;

public class WxUserGroupAction extends ActionBase<WxUserGroup> {
	public WxUserGroup getBean() {
		return _bean;
	}

	public void setBean(WxUserGroup bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WxUserGroup.class;
	}

	public void sync() throws IOException, JSONException {
		WxUserGroupDAO.sync();
		writeSuccess();
	}
	@Override
	public String crtAll() {
	  WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
	  if(account==null){
	    return "1=2";
	  }else{
	    return WxUserGroup.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
	  }
	}
	
}
