package irille.action.wa;

import irille.action.ActionBase;
import irille.pub.idu.Idu;
import irille.wx.wa.WaQRCode;
import irille.wx.wa.WaQRCodeDAO;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxUser;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONObject;

public class WaQRCodeAction extends ActionBase<WaQRCode> {
  public WaQRCode getBean() {
    return _bean;
  }

  public void setBean(WaQRCode bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return WaQRCode.class;
  }
  public void obtain() throws Exception {
  	WaQRCodeDAO.Obtain obtain = new WaQRCodeDAO.Obtain();
  	obtain.setClazz(WaQRCode.class);
  	obtain.setB(getBean());
  	obtain.commit();
  	HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(obtain.getB(), "bean.");
		json.put("success", true);
		response.getWriter().print(json.toString());
  }
  @Override
  public String crtAll() {
    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
    if(account==null){
      return "1=2";
    }else{
      return WxUser.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
    }
  }
  
}
