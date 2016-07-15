package irille.action.wx;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.action.ActionBase;
import irille.pub.Str;
import irille.pub.idu.Idu;
import irille.pub.idu.IduPage;
import irille.wpt.service.QrcodeRuleService;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxUser;
import irille.wx.wx.WxUserDAO;

public class WxUserAction extends ActionBase<WxUser> {
  public WxUser getBean() {
    return _bean;
  }

  public void setBean(WxUser bean) {
    this._bean = bean;
  }

  @Override
  public Class beanClazz() {
    return WxUser.class;
  }

  public void sync() throws JSONException, IOException {
    WxUserDAO userDao = new WxUserDAO();
    userDao.sync();
    HttpServletResponse response = ServletActionContext.getResponse();
    JSONObject json = new JSONObject().put("success", true).put("msg", "同步成功");
    response.getWriter().print(json.toString());
  }

  public void refresh() throws JSONException, IOException {
	  WxUserDAO userDao = new WxUserDAO();
	  userDao.refresh(getPkeys());
	  HttpServletResponse response = ServletActionContext.getResponse();
	  JSONObject json = new JSONObject().put("success", true).put("msg", "用户基本信息已更新");
	  response.getWriter().print(json.toString());
  }

  public void rem() throws Exception {
    HttpServletResponse response = ServletActionContext.getResponse();
    JSONObject json = crtJsonByBean(WxUserDAO.rem(getBean()), "bean.");
    json.put("success", true).put("msg", "修改成功");
    response.getWriter().print(json.toString());
  }

  public void toBlack() throws Exception {
    HttpServletResponse response = ServletActionContext.getResponse();
    JSONObject json = crtJsonByBean(WxUserDAO.toBlack(getPkeys()), "bean.");
    json.put("success", true).put("msg", "操作完成");
    response.getWriter().print(json.toString());
  }

  public void move() throws Exception {
    HttpServletResponse response = ServletActionContext.getResponse();
    JSONObject json = crtJsonByBean(WxUserDAO.move(getPkeys(), getBean().gtUserGroup()), "bean.");
    json.put("success", true).put("msg", "操作完成");
    response.getWriter().print(json.toString());
  }
	/**
	 * 让一个用户成为会员
	 * @throws Exception 
	 */
	public void beenMember() throws Exception {
		QrcodeRuleService service = new QrcodeRuleService();
		WxUser user = WxUser.load(WxUser.class, getBean().getPkey());
		service.create(user, true);
		HttpServletResponse response = ServletActionContext.getResponse();
		JSONObject json = crtJsonByBean(user, "bean.");
		response.getWriter().print(json.put("success", true).toString());
	}
	public void createAllQrcode() {
		QrcodeRuleService service = new QrcodeRuleService();
		service.createAllQrcode();
	}
  
  @Override
  public void list() throws Exception {
    JSONObject json = new JSONObject();
    JSONArray ja = new JSONArray();
    // 目前过滤器的搜索，是肯定会带初始条件的
    String where = Str.isEmpty(getQuery()) ? crtFilter() : crtQuery();
    IduPage page = newPage();
    page.setStart(getStart());
    page.setLimit(getLimit());
    page.setWhere(where);
    page.commit();
    List<WxUser> list = page.getList();
    JSONObject lineJson = null;
    for (WxUser line : list) {     
      lineJson = crtJsonByBean(line);
      ja.put(lineJson);
    }
    json.put(STORE_ROOT, ja);
    json.put(STORE_TOTAL, page.getCount());
    writerOrExport(json);
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
