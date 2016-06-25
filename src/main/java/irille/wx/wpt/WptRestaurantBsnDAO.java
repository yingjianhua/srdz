package irille.wx.wpt;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.Env;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptRestaurantBsnDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("有相同的记录存在,请不要重复！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WptRestaurantBsn.class);
	public static class Ins extends IduIns<Ins, WptRestaurantBsn> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			if(WptRestaurantBsn.chkUniqueWxUserAccount(false, getB().getWxuser(), account.getPkey()) != null)
				throw LOG.err(Msgs.uniqueErr);
			getB().setAccount(account.getPkey());
			getB().setCreateTime(Env.getSystemTime());
			getB().setOpenid(getB().gtWxuser().getOpenId());
		}
	}
}
