package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.Idu;
import irille.pub.idu.IduIns;
import irille.wx.wx.WxAccountDAO;

public class WptWxTipsDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("该微信号已在群发预览用户组中")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WptWxTipsDAO.class);

	public static class Ins extends IduIns<Ins, WptWxTips> {

		@Override
		public void before() {
			getB().stAccount(WxAccountDAO.getByUser(Idu.getUser()));
		}
		
		@Override
		public void run() {
			if(WptWxTips.chk(WptWxTips.class, getB().getPkey()) != null)
				throw LOG.err(Msgs.uniqueErr);
			super.run();
		}
	}

	
}
