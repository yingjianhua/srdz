package irille.action.wx;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import irille.action.ActionUpload;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WxAccountAction extends ActionUpload<WxAccount> {
  public WxAccount getBean() {
    return _bean;
  }

  public void setBean(WxAccount bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return WxAccount.class;
  }
  
  /**
   * 上传商户支付证书
 * @throws JSONException 
 * @throws IOException 
   */
  public void uploadCert() throws IOException, JSONException {
	  WxAccountDAO.uploadCert(getBean().getPkey(), getBean().getMchPayCert());
	  ServletActionContext.getResponse().getWriter().print(new JSONObject().put(SUCCESS, true));
  }
}
