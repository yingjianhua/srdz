package irille.action.wx;

import irille.action.ActionBase;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxSubscribe;

import org.json.JSONObject;

public class WxSubscribeAction extends ActionBase<WxSubscribe> {
  public WxSubscribe getBean() {
    return _bean;
  }

  public void setBean(WxSubscribe bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return WxSubscribe.class;
  }

  @Override
  public JSONObject crtJsonExt(JSONObject json, Bean bean, String pref) throws Exception {
    Bean obj = ((WxSubscribe) bean).gtTemplate();
    String showname = obj.getPkey().toString();
    if (IExtName.class.isAssignableFrom(obj.clazz()))
      showname = ((IExtName) obj).getExtName();
    json.put(pref + WxSubscribe.T.TEMPLATE.getFld().getCode(), obj.getPkey() + BEAN_SPLIT + showname);
    return json;
  }

  @Override
  public String crtAll() {
    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
    if(account==null){
      return "1=2";
    }else{      
      return WxSubscribe.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
    }
  }
}
