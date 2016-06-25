package irille.wx.wx;

import irille.core.sys.SysUser;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;

public class WxSettingDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("一个用户账号同一时间只能配置一个公众号!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WxSettingDAO.class);

	public static WxAccount getAccountByUser(Integer user) {
		WxAccount wa = null;
		try {
			wa = WxSetting.chkUniqueUserSys(false, user).gtAccount();
		} catch (Exception e) {
			// throw LOG.err("noAccount", "该用户没有配置公众号");
			return null;
		}
		return wa;
	}

	public static WxAccount getAccountByUser(SysUser user) {
		return getAccountByUser(user.getPkey());
	}

	public static class Ins extends IduIns<Ins, WxSetting> {
		@Override
		public void valid() {
			// TODO Auto-generated method stub
			super.valid();
			validate(getB().getUserSys());
		}
		
	}

	public static class Upd extends IduUpd<Upd, WxSetting> {
	}
	
	private static void validate(Integer user) {
		WxSetting model = WxSetting.chkUniqueUserSys(false, user);
		if (model != null)
			throw LOG.err(Msgs.uniqueErr);
	}
}
