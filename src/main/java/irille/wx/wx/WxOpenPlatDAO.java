package irille.wx.wx;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;

public class WxOpenPlatDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		Unique("注册邮箱为[{0}]的开放平台记录已经存在！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WxOpenPlatDAO.class);

	public static class Ins extends IduIns<Ins, WxOpenPlat> {
		@Override
		public void valid() {
			if(WxOpenPlat.chkUniqueEMail(false, getB().getEmail())!=null) {
				throw LOG.err(Msgs.Unique, getB().getEmail());
			}
			super.valid();
		}
	}
	public static class Del extends IduDel<Del, WxOpenPlat> {
		@Override
		public void valid() {
			//  已经被使用的是不能删除的
			haveBeUsed(WxAccount.class, WxAccount.T.OPEN_PLAT, getB().getPkey());
			super.valid();
		}
	}
}
 