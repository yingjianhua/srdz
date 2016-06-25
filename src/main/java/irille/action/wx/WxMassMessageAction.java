package irille.action.wx;

import irille.action.ActionBase;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.pub.inf.IExtName;
import irille.pub.svr.DbPool;
import irille.wx.wm.WmNews;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxAuto;
import irille.wx.wx.WxMassMessage;
import irille.wx.wx.WxMassMessageDAO;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

public class WxMassMessageAction extends ActionBase<WxMassMessage> {
	private String _toUser;
	private int _tempPkey;
	private String _tempClass;
	private boolean _isToAll;
	private Integer _wxGroupPkey;
	
	
	public String getToUser() {
		return _toUser;
	}

	public void setToUser(String toUser) {
		this._toUser = toUser;
	}
	
	public int getTempPkey() {
		return _tempPkey;
	}

	public void setTempPkey(int tempPkey) {
		this._tempPkey = tempPkey;
	}

	public String getTempClass() {
		return _tempClass;
	}

	public void setTempClass(String tempClass) {
		this._tempClass = tempClass;
	}
	
	public boolean isIsToAll() {
		return _isToAll;
	}

	public void setIsToAll(boolean isToAll) {
		this._isToAll = isToAll;
	}

	public Integer getWxGroupPkey() {
		return _wxGroupPkey;
	}

	public void setWxGroupPkey(Integer wxGroupPkey) {
		this._wxGroupPkey = wxGroupPkey;
	}

	public WxMassMessage getBean() {
		return _bean;
	}

	public void setBean(WxMassMessage bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WxMassMessage.class;
	}
  
	/**
	 * 群发预览功能
	 * @throws ClassNotFoundException 
	 * @throws JSONException 
	 * @throws IOException 
	 * 
	 * @throws Exception
	 */
	public void preview() throws IOException, JSONException {
		WxMassMessageDAO.preview(_tempClass, _tempPkey, _toUser);
		ServletActionContext.getResponse().getWriter().print(new JSONObject().put("success", true).toString());
	}
	
	/**
	 * 群发功能 
	 * @throws IOException
	 * @throws JSONException
	 */
	public void mass() throws IOException, JSONException {
		WxMassMessageDAO.mass(_tempClass, _tempPkey, _isToAll, _wxGroupPkey);
		ServletActionContext.getResponse().getWriter().print(new JSONObject().put("success", true).toString());
	}
	
  public static void main(String[] args) throws JSONException {
  	DbPool.getInstance();
  	WmNews template = WmNews.load(WmNews.class, 6);
  	StringBuffer buf = new StringBuffer();
  	buf.append("\"content\":\""+template.getContent().replaceAll("\"", "\\\\\"")+"\",");
  	JSONObject json = new JSONObject();
  	json.put("content", template.getContent().replaceAll("\"", "\"")+"");
  	System.out.println(json.toString());
  }
  @Override
  public JSONObject crtJsonExt(JSONObject json, Bean bean, String pref) throws Exception {
    Bean obj = ((WxMassMessage) bean).gtTemplate();
    String showname = obj.getPkey().toString();
    if (IExtName.class.isAssignableFrom(obj.clazz()))
      showname = ((IExtName) obj).getExtName();
    json.put(pref + WxAuto.T.TEMPLATE.getFld().getCode(), obj.getPkey() + BEAN_SPLIT + showname);
    return json;
  }
  @Override
  public String crtAll() {
    WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
    if(account==null){
      return "1=2";
    }else{
      return WxAuto.T.ACCOUNT.getFld().getCodeSqlField() + " = " + account.getPkey();
    }
  }
}
