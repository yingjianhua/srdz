package irille.wx.wx;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wm.WmImage;
import irille.wx.wm.WmImageDAO;
import irille.wx.wm.WmMusic;
import irille.wx.wm.WmMusicDAO;
import irille.wx.wm.WmNews;
import irille.wx.wm.WmNewsDAO;
import irille.wx.wm.WmText;
import irille.wx.wm.WmTextDAO;
import irille.wx.wm.WmVideo;
import irille.wx.wm.WmVideoDAO;
import irille.wx.wm.WmVoice;
import irille.wx.wm.WmVoiceDAO;
import irille.wxpub.util.WeixinUtil;

import java.util.Date;
import java.util.List;

public class WxSubscribeDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("一个公众号只能保留一条用户关注语!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
  public static final Log LOG = new Log(WxSubscribeDAO.class);

  public static class Ins extends IduIns<Ins, WxSubscribe> {
    @Override
    public void before() {
      super.before();
      WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
      String where = Idu.sqlString("{0}=?", WxSubscribe.T.ACCOUNT);
      List<WxSubscribe> list = Bean.list(WxSubscribe.class, where, false, account.getPkey());
      if(list.size()>0) {
      	throw LOG.err(Msgs.uniqueErr);
      }
   // 判断消息类型设置模板的longpkey
      Wx.OMsgType msg_type = getB().gtMsgType();
      int table_id = 0;
      switch (msg_type) {
      case TEXT: 
    	  table_id = WmText.TB.getID();
    	  break;
      case IMAGE: 
    	  table_id = WmImage.TB.getID();
    	  break;
      case MUSIC: 
    	  table_id = WmMusic.TB.getID();
    	  break;
      case VIDEO: 
    	  table_id = WmVideo.TB.getID();
    	  break;
      case VOICE: 
    	  table_id = WmVoice.TB.getID();
    	  break;
      case NEWS: 
    	  table_id = WmNews.TB.getID();
    	  break;
      }
      getB().setTemplate(Bean.gtLongPkey(getB().getTemplate(), table_id));
      getB().stAccount(WxAccountDAO.getByUser(getUser()));
      getB().setUpdatedTime(new Date());
    }
    
  }
  public static class Upd extends IduUpd<Upd, WxSubscribe> {
    @Override
    public void before() {
      super.before();
      WxSubscribe dbBean = load(getB().getPkey());
   // 判断消息类型设置模板的longpkey
      Wx.OMsgType msg_type = getB().gtMsgType();
      int table_id = 0;
      switch (msg_type) {
      case TEXT: 
    	  table_id = WmText.TB.getID();
    	  break;
      case IMAGE: 
    	  table_id = WmImage.TB.getID();
    	  break;
      case MUSIC: 
    	  table_id = WmMusic.TB.getID();
    	  break;
      case VIDEO: 
    	  table_id = WmVideo.TB.getID();
    	  break;
      case VOICE: 
    	  table_id = WmVoice.TB.getID();
    	  break;
      case NEWS: 
    	  table_id = WmNews.TB.getID();
    	  break;
      }
      getB().setTemplate(Bean.gtLongPkey(getB().getTemplate(), table_id));
      PropertyUtils.copyProperties(dbBean, getB(),WxSubscribe.T.MSG_TYPE,WxSubscribe.T.TEMPLATE);
      setB(dbBean);
    }
    
  }
  public static class Del extends IduDel<Del, WxAccount> {
    
  }
  public static String getMessage(Integer account, String accountId, String openId) {
  	String where = Idu.sqlString("{0}=?", WxSubscribe.T.ACCOUNT);
  	List<WxSubscribe> list = BeanBase.list(WxSubscribe.class, where, false, account);
  	WxSubscribe bean;
  	if(list.size()==0) return null; 
  	bean = list.get(0);
  	Wx.OMsgType msg_type = bean.gtMsgType();
    switch (msg_type) {
    case TEXT: 
    	return WmTextDAO.transform2XML(((WmText)bean.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + "");  
    case IMAGE: 
    	return WmImageDAO.transform2XML(((WmImage)bean.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + ""); 
    case MUSIC: 
    	return WmMusicDAO.transform2XML(((WmMusic)bean.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + "");
    case VIDEO: 
    	return WmVideoDAO.transform2XML(((WmVideo)bean.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + "");
    case VOICE: 
    	return WmVoiceDAO.transform2XML(((WmVoice)bean.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + "");
    case NEWS: 
    	return WmNewsDAO.transform2XML(((WmNews)bean.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + ""); 
    default :
    	return null;
    }
  }
 
}
