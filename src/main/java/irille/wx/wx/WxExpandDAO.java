package irille.wx.wx;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduUpd;
import irille.wxpub.util.IExpandAction;

public class WxExpandDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		classErr("{0}没有继承IExpandAction接口")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
  public static final Log LOG = new Log(WxExpandDAO.class);

  public static class Upd extends IduUpd<Upd, WxExpand> {
    @Override
    public void before() {
      super.before();
      WxExpand dbBean = load(getB().getPkey());
      //判断消息类型设置模板的longpkey
      PropertyUtils.copyPropertiesWithout(dbBean, getB(),WxExpand.T.ACCOUNT,WxExpand.T.UPDATED_TIME);
      setB(dbBean);
    }
  }
  
  public static String transform2XML(WxExpand main,String accountId, String openId, String createTime) {
    String result = "";
  	try {
	    Class aClass = Class.forName(main.getAction());
	    if(IExpandAction.class.isAssignableFrom(aClass) ){
	    	IExpandAction action = (IExpandAction)(aClass.newInstance());
	    	result = action.excute(main.getImageUrl(), accountId, openId, null);
	    } else {
	    	throw LOG.err(Msgs.classErr, main.getAction());
	    }
    } catch (ClassNotFoundException e) {
	    e.printStackTrace();
    } catch (InstantiationException e) {
	    e.printStackTrace();
    } catch (IllegalAccessException e) {
	    e.printStackTrace();
    }
  	return result;
  }
}
