package irille.action.wx;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import irille.action.ActionBase;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
import irille.wx.wx.Wx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxMenu;
import irille.wx.wx.WxMenuDAO;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class WxMenuAction extends ActionBase<WxMenu> {
  public WxMenu getBean() {
    return _bean;
  }

  public void setBean(WxMenu bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return WxMenu.class;
  }

  public void Sync() throws Exception {
    WxMenuDAO.Sync sync = new WxMenuDAO.Sync();
    sync.commit();
  }
  @Override
  public JSONObject crtJsonExt(JSONObject json, Bean bean, String pref) throws Exception {
    if(((WxMenu)bean).gtType() == Wx.OMotionType.MESSAGECLASS){
      Bean obj = ((WxMenu)bean).gtTemplate();
      String showname = obj.getPkey().toString();
      if (IExtName.class.isAssignableFrom(obj.clazz()))
        showname = ((IExtName)obj).getExtName();
      json.put(pref + WxMenu.T.TEMPLATE.getFld().getCode(), obj.getPkey()+BEAN_SPLIT+showname);      
    }
      return json;
  }
  @Override
  public String crtAll() {
    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
    if(account==null){
      return "1=2";
    }else{
      return WxMenu.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
    }
  }

  public void getWebPath() throws JSONException, IOException {
	  String webPath = ServletActionContext.getServletContext().getInitParameter("webPath");
	  HttpServletResponse response = ServletActionContext.getResponse();
	  JSONObject json = new JSONObject();
	  json.put("webpath", webPath);
	  response.getWriter().print(json.toString());
  }
}
