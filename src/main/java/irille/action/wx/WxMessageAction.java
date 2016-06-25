package irille.action.wx;

import irille.action.ActionBase;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.svr.Env;
import irille.pub.tb.Fld;
import irille.pub.tb.Tb;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxMessage;
import irille.wx.wx.WxMessageDAO;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WxMessageAction extends ActionBase<WxMessage> {
  public WxMessage getBean() {
    return _bean;
  }

  public void setBean(WxMessage bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return WxMessage.class;
  }

  public void reply() throws Exception {
    WxMessageDAO.Reply reply = new WxMessageDAO.Reply();
    reply.setB(getBean());
    reply.commit();
    HttpServletResponse response = ServletActionContext.getResponse();
    JSONObject json = crtJsonByBean(reply.getB(), "bean.");
    json.put("success", true);
    response.getWriter().print(json.toString());
  }

  // 产生查询语句 AND关系
  public String crtFilter() throws JSONException {
    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
    String sql = " and wxmsg_dir <> 2 and "+ WxMessage.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
    if(getFilter()==null) {
      return crtFilterAll() + sql + orderBy();
    }
    JSONArray ja = new JSONArray(getFilter());
    Tb tb = tb();
    for (int i = 0; i < ja.length(); i++) {
      JSONObject json = ja.getJSONObject(i);
      String fldName = json.getString(QUERY_PROPERTY);
      String param = json.getString(QUERY_VALUE);
      if (Str.isEmpty(param))
        continue;
      if (!tb.chk(fldName))
        continue;
      Fld fld = tb.get(fldName);
      if (fld == null)
        continue;
      sql += " AND " + Env.INST.getDB().crtWhereSearch(fld, param);
    }
    return crtFilterAll() + sql + orderBy();
  }
  public void check() throws Exception {
    if (getPkey() == null)
      return;
    WxMessage message = BeanBase.load(WxMessage.class, getPkey());
    HttpServletResponse response = ServletActionContext.getResponse();
    JSONArray ja = new JSONArray();
    String sql = WxMessage.T.WX_USER.getFld().getCodeSqlField() + " =? ORDER BY " + WxMessage.T.PKEY.getFld().getCodeSqlField() + " DESC LIMIT 0,15";
    List<WxMessage> list = BeanBase.list(WxMessage.class,sql,false,message.getWxUser());
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    for (WxMessage wxMessage : list) {
      JSONObject json = new JSONObject();
      json.put("wxmsgDir", wxMessage.getWxmsgDir());
      json.put("account", wxMessage.gtAccount().getAccountName());
      json.put("wxUser", wxMessage.gtWxUser().getNickname());
      json.put("createdTime", format.format(wxMessage.getCreatedTime()));
      json.put("content", wxMessage.getContent());
      ja.put(json);
    }
    response.getWriter().print(ja.toString());
  }
}
