package irille.wx.wx;

import irille.core.sys.SysUser;
import irille.pub.Cn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBase;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduOther;
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
import irille.wx.wx.Wx.OMotionType;
import irille.wxpub.util.WeixinUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.json.JSONArray;
import org.json.JSONObject;

public class WxMenuDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		emptyUrl("消息链接类型，链接地址不能为空！"),
		linkUrlErr("链接URL开头需要带上\"http或者https\""),
		menuErr("菜单标识已存在"),
		firstMenuErr("一级菜单数量最多3个"),
		secondMenuErr("一级菜单【{0}】下已有5个二级菜单"),
		thirdMenuErr("不能创建三级菜单")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
  public static final Log LOG = new Log(WxMenuDAO.class);

  public static class Ins extends IduIns<Ins, WxMenu> {
    @Override
    public void before() {
      super.before();
      WxAccount account = WxAccountDAO.getByUser(getUser());
      if (getB().gtType() == Wx.OMotionType.MESSAGECLASS) {
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
      } else if(getB().gtType() == Wx.OMotionType.WEBLINKCLASS) {
    	  if (getB().getUrl() == null) {
    		  throw LOG.err(Msgs.emptyUrl);
    	  } else if (!getB().getUrl().startsWith("http") && !getB().getUrl().startsWith("https")) {
    		  throw LOG.err(Msgs.linkUrlErr);
    	  }
      }
      WxMenu menu = WxMenu.chkUniqueAccountKey(false, account.getPkey(), getB().getMenuKey());
      if (menu != null)
        throw LOG.err(Msgs.menuErr);
      getB().setAccount(account.getPkey());
    }

    @Override
    public void after() {
      super.after();
      checkMenu(getUser());
    }
  }

  public static class Upd extends IduUpd<Upd, WxMenu> {
    @Override
    public void before() {
      super.before();
      WxMenu dbBean = load(getB().getPkey());
      if (getB().gtType() == Wx.OMotionType.MESSAGECLASS) {
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
      } else if (getB().gtType() == Wx.OMotionType.WEBLINKCLASS){
    	  if(getB().getUrl() == null) {
    		  throw LOG.err(Msgs.emptyUrl);
    	  } else if (!getB().getUrl().startsWith("http") && !getB().getUrl().startsWith("https")) {
    		  throw LOG.err(Msgs.linkUrlErr);
    	  }
      }
        
      if (!dbBean.getMenuKey().equals(getB().getMenuKey())) {
        WxMenu menu = WxMenu.chkUniqueAccountKey(false, dbBean.gtAccount().getPkey(), getB().getMenuKey());
        if (menu != null) {
          throw LOG.err(Msgs.menuErr);
        }
      }
      PropertyUtils.copyPropertiesWithout(dbBean, getB(), WxMenu.T.ACCOUNT);
      setB(dbBean);
    }

    @Override
    public void after() {
      super.after();
      checkMenu(getUser());
    }
  }

  public static class Del extends IduDel<Del, WxMenu> {
    @Override
    public void valid() {
      super.valid();
      haveBeUsed(WxMenu.class, WxMenu.T.MENU_UP, b.getPkey());
    }
  }

  /**
   * @author whx
   * 同步微信操作
   */
  public static class Sync extends IduOther<Sync, WxMenu> {
    public static Cn CN = new Cn("sync", "同步到微信");
    private JSONArray jsonFirstMenu;
    private JSONArray jsonSecondMenu;
    private JSONObject jsonMenu;
    private JSONObject jsonFirst;

    @Override
    public void run() {
      super.run();
      WxMenu.TB.getCode();
      jsonFirstMenu = new JSONArray();
      jsonMenu = new JSONObject();
      WxAccount account = WxAccountDAO.getByUser(getUser());
      // 根据上级菜单为null的条件查出一级菜单
      String sql = WxMenu.T.ACCOUNT.getFld().getCodeSqlField() + " =? and " + WxMenu.T.MENU_UP.getFld().getCodeSqlField() + " is null ORDER BY sort ASC";
      List<WxMenu> first = BeanBase.list(WxMenu.class, sql, false, account.getPkey());
      try {
        for (WxMenu firstMenu : first) {
          jsonSecondMenu = new JSONArray();
          // 根据上级菜单查出二级菜单
          String sql1 = WxMenu.T.ACCOUNT.getFld().getCodeSqlField() + " =? and " + WxMenu.T.MENU_UP.getFld().getCodeSqlField() + " =? ORDER BY sort ASC";
          List<WxMenu> second = BeanBase.list(WxMenu.class, sql1, false, account.getPkey(), firstMenu.getPkey());
          // 判断是否只有二级菜单
          jsonFirst = new JSONObject();
          if (firstMenu.gtType() == OMotionType.WEBLINKCLASS) {
            jsonFirst.put("type", "view");
            jsonFirst.put("name", firstMenu.getName());
            jsonFirst.put("url", firstMenu.getUrl());
            if (second.size() != 0) {
              jsonFirst.remove("type");
              jsonFirst.put("sub_button", setJsonSecond(jsonFirst, jsonSecondMenu, second));
            }
            jsonFirstMenu.put(jsonFirst);
          } else if (firstMenu.gtType() == OMotionType.MESSAGECLASS) {
            jsonFirst.put("type", "click");
            jsonFirst.put("name", firstMenu.getName());
            jsonFirst.put("key", firstMenu.getMenuKey());
            if (second.size() != 0) {
              jsonFirst.remove("type");
              jsonFirst.put("sub_button", setJsonSecond(jsonFirst, jsonSecondMenu, second));
            }
            jsonFirstMenu.put(jsonFirst);
          }
          jsonMenu.put("button", jsonFirstMenu);
        }
        String accessToken = account.getAccessToken();
        String url = WeixinUtil.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
        JSONObject jsonObject = new JSONObject();
        jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu.toString());
        if (jsonObject != null) {
          if (0 == jsonObject.getInt("errcode")) {
            ServletActionContext.getResponse().getWriter().write("同步菜单信息数据成功！");
          } else {
            accessToken = WxAccountDAO.getAccessToken(getUser());
            url = WeixinUtil.MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
            jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu.toString());
            if (0 == jsonObject.getInt("errcode")) {
              ServletActionContext.getResponse().getWriter().write("同步菜单信息数据成功！");
            } else {
              ServletActionContext.getResponse().getWriter().write("error");
            }
          }
        } else {
          ServletActionContext.getResponse().getWriter().write("error");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * @param jsonFirst
   * @param jsonSecondMenu
   * @param second
   * @return 获取二级菜单并删除一级菜单中的属性
   * @throws Exception
   */
  public static JSONArray setJsonSecond(JSONObject jsonFirst, JSONArray jsonSecondMenu, List<WxMenu> second)
      throws Exception {
    for (WxMenu secondMenu : second) {
      JSONObject jsonSecond = new JSONObject();
      if (secondMenu.gtType() == OMotionType.WEBLINKCLASS) {
        jsonFirst.remove("url");
        jsonSecond.put("type", "view");
        jsonSecond.put("name", secondMenu.getName());
        jsonSecond.put("url", secondMenu.getUrl());
        jsonSecondMenu.put(jsonSecond);
      } else if (secondMenu.gtType() == OMotionType.MESSAGECLASS) {
        jsonFirst.remove("key");
        jsonSecond.put("type", "click");
        jsonSecond.put("name", secondMenu.getName());
        jsonSecond.put("key", secondMenu.getMenuKey());
        jsonSecondMenu.put(jsonSecond);
      }
    }
    return jsonSecondMenu;
  }

  /**
   * @param wxMenu
   * @return 
   * 检测自定义菜单是否菜单个数超出
   */
  public static void checkMenu(SysUser user) {
    WxAccount account = WxAccountDAO.getByUser(user);
    String sql = WxMenu.T.ACCOUNT.getFld().getCodeSqlField() + " =? ";
    List<WxMenu> list = BeanBase.list(WxMenu.class, sql, false, account.getPkey());
    List<WxMenu> first = new ArrayList<WxMenu>();
    HashMap<Integer, List<WxMenu>> repetition = new HashMap<Integer, List<WxMenu>>();
    for (WxMenu wxMenu : list) {
      if (wxMenu.getMenuUp() == null) {
        first.add(wxMenu);
        if (first.size() > 3)
          throw LOG.err(Msgs.firstMenuErr);
      } else {
        if (wxMenu.gtMenuUp().getMenuUp() != null) {
          throw LOG.err(Msgs.thirdMenuErr);
        }
        if (repetition.containsKey(wxMenu.getMenuUp()) == false) {
          List<WxMenu> mList = new ArrayList<WxMenu>();
          mList.add(wxMenu);
          repetition.put(wxMenu.getMenuUp(), mList);
        } else {
          repetition.get(wxMenu.getMenuUp()).add(wxMenu);
          if (repetition.get(wxMenu.getMenuUp()).size() > 5) {
            throw LOG.err(Msgs.secondMenuErr, wxMenu.gtMenuUp().getName());
          }
        }
      }
    }
  }

  public static String doMenuClick(String accountId, String openId, String createTime, String eventKey) {
    WxAccount account = WxAccount.loadUniqueAccountId(false, accountId);
    WxMenu menu = WxMenu.loadUniqueAccountKey(false, account.getPkey(), eventKey);
    Wx.OMsgType msg_type = menu.gtMsgType();
    switch (msg_type) {
    
    case TEXT: 
    	return WmTextDAO.transform2XML(((WmText)menu.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + "");  
    case IMAGE: 
    	return WmImageDAO.transform2XML(((WmImage)menu.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + ""); 
    case MUSIC: 
    	return WmMusicDAO.transform2XML(((WmMusic)menu.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + "");
    case VIDEO: 
    	return WmVideoDAO.transform2XML(((WmVideo)menu.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + "");
    case VOICE: 
    	return WmVoiceDAO.transform2XML(((WmVoice)menu.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + "");
    case NEWS: 
    	return WmNewsDAO.transform2XML(((WmNews)menu.gtTemplate()), accountId, openId, WeixinUtil.tranDate(new Date()) + ""); 
    default :
    	return null;
    }
  }
}
