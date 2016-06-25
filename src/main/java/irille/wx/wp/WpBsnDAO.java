package irille.wx.wp;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;

public class WpBsnDAO {
	public static final Log LOG = new Log(WpBsn.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("记录【{0}】已存在，不可操作！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	
	public static class Ins extends IduIns<Ins, WpBsn> {
		@Override
		public void before() {
			super.before();
			getB().setOrg(getUser().getOrg());
			if (WpBsn.chkUniqueNameOrg(false, getB().getName(), getB().getOrg()) != null)
				throw LOG.err(Msgs.uniqueErr,getB().getName());
		}
	}

	public static class Upd extends IduUpd<Upd, WpBsn> {
		@Override
		public void before() {
			super.before();
			WpBsn bean = WpBsn.chkUniqueNameOrg(false,getB().getName(), getB().getOrg());
			WpBsn model = null;
			if (bean != null)
				if (bean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getName());
				else
					model = bean;
			else {
				model = load(getB().getPkey());
				model.setName(getB().getName());
			}
			PropertyUtils.copyPropertiesWithout(model, getB(), WpBsn.T.ORG);
			setB(model);
		}
	}
	public static class Del extends IduDel<Del, WpBsn> {
		@Override
		public void valid() {
			super.valid();
			haveBeUsed(WpArt.class, WpArt.T.BSN, getB().getPkey());
			
		}
	}
}
