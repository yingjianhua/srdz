package irille.wx.wx;

import irille.core.sys.Sys;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
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

public class WxAutoDAO {
  public static final Log LOG = new Log(WxAutoDAO.class);

  public static class Ins extends IduIns<Ins, WxAuto> {
    @Override
    public void before() {
      super.before();
      getB().stEnabled(true);
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

  public static class Upd extends IduUpd<Upd, WxAuto> {
    @Override
    public void before() {
      super.before();
      WxAuto dbBean = load(getB().getPkey());
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
      
      PropertyUtils.copyProperties(dbBean, getB(),WxAuto.T.KEYWORD, WxAuto.T.MSG_TYPE, WxAuto.T.TEMPLATE);
      setB(dbBean);
    }

  }

  public static class Del extends IduDel<Del, WxAccount> {

  }

  /**
   * @param content
   * @param toUser
   * @param fromUser
   * @param createTime
   * @return 关键字自动回复
   */
  public static String autoReceive(String content, String accountId, String openId, String createTime) {
    String sql = WxAuto.T.KEYWORD + " LIKE '%"+content+"%' AND " + WxAuto.T.ENABLED + "<>" +Sys.OEnabled.FALSE.getLine().getKey()+ " AND " + WxAuto.T.ACCOUNT + "=? ORDER BY " + WxAuto.T.KEYWORD;
    List<WxAuto> list = BeanBase.list(WxAuto.class, sql, false, WxAccount.chkUniqueAccountId(false, accountId).getPkey());
    if(list.size()==0) return null;
    WxAuto bean = list.get(0);
    
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
