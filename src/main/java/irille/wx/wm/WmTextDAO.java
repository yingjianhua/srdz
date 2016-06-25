package irille.wx.wm;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxAuto;
import irille.wx.wx.WxMenu;
import irille.wx.wx.WxSubscribe;
import irille.wx.wx.WxUser;
import irille.wx.wx.WxUserGroup;
import irille.wx.wx.Wx.OMassMsgType;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class WmTextDAO implements MessageMassable {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		noAccountErr("该用户没有配置公众号!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
  public static final Log LOG = new Log(WmTextDAO.class);

  public static class Ins extends IduIns<Ins, WmText> {
    @Override
    public void before() {
      super.before();
      WxAccount account = WxAccountDAO.getByUser(getUser());
      if(account==null)
        throw LOG.err(Msgs.noAccountErr);
      getB().setAccount(account.getPkey());
      getB().setCreatedTime(new Date());
      getB().setUpdatedTime(new Date());
    }  
  }
  public static class Upd extends IduUpd<Upd, WmText> {
    @Override
    public void before() {
      super.before();
      WmText dbBean = load(getB().getPkey());
      PropertyUtils.copyPropertiesWithout(dbBean, getB(),WmText.T.ACCOUNT,WmText.T.CREATED_TIME,WmText.T.UPDATED_TIME);
      dbBean.setUpdatedTime(new Date());
      setB(dbBean);
    }
    
  }
  public static class Del extends IduDel<Del, WmText> {
    @Override
    public void valid() {
      super.valid();
      haveBeUsed(WxSubscribe.class, WxSubscribe.T.TEMPLATE, b.gtLongPkey());
      haveBeUsed(WxAuto.class, WxAuto.T.TEMPLATE, b.gtLongPkey());
      haveBeUsed(WxMenu.class, WxMenu.T.TEMPLATE, b.gtLongPkey());
    }
  }
  public static String transform2XML(Integer pkey,String accountId, String openId, String createTime) {
  	WmText bean = WmText.load(WmText.class, pkey);
  	return transform2XML(bean,accountId, openId,createTime);
  }
  public static String transform2XML(WmText bean,String accountId, String openId, String createTime) {
	  return transform2XML(bean.getContent(), accountId, openId, createTime);
  }
  public static String transform2XML(String content, String accountId, String openId, String createTime) {
	  StringBuffer result = new StringBuffer();
	  	result.append("<xml>");
	  	result.append("<ToUserName><![CDATA["+openId+"]]></ToUserName>");
	  	result.append("<FromUserName><![CDATA["+accountId+"]]></FromUserName>");
	  	result.append("<CreateTime>"+createTime+"</CreateTime>");
	  	result.append("<MsgType><![CDATA[text]]></MsgType>");
	  	result.append("<Content><![CDATA["+content+"]]></Content>");
	  	result.append("</xml>");
	    //gtLongTbObj
	    return result.toString();
  }
  
	@Override
	public OMassMsgType getMessageType() {
		return OMassMsgType.TEXT;
	}
	@Override
	public String transform4Preview(Integer wxUserPkey, Integer tempPkey) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("touser", Bean.load(WxUser.class, wxUserPkey).getOpenId());
		json.put("text", new JSONObject().put("content", Bean.load(WmText.class, tempPkey).getContent()));
		json.put("msgtype", "text");
		return json.toString();
	}

	@Override
	public String transform4Mass(boolean isToAll, Integer wxGroupPkey, Integer tempPkey) throws JSONException {
		JSONObject json = new JSONObject();
		JSONObject filter = new JSONObject();
		filter.put("is_to_all", isToAll);
		if(!isToAll) {
			filter.put("group_id", Bean.load(WxUserGroup.class, wxGroupPkey).getWxid());
		}
		json.put("filter", filter);
		json.put("text", new JSONObject().put("content", Bean.load(WmText.class, tempPkey).getContent()));
		json.put("msgtype", "text");
		return json.toString();
	}
}
