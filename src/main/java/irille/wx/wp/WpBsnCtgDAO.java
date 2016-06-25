package irille.wx.wp;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;

public class WpBsnCtgDAO {
	public static final Log LOG = new Log(WpBsnCtg.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		uniqueErr("记录【{0}】已存在，不可操作！"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on

	public static class Ins extends IduIns<Ins, WpBsnCtg> {
		@Override
		public void before() {
			super.before();
			if (WpBsnCtg.chkUniqueName(false, getB().getName()) != null)
				throw LOG.err(Msgs.uniqueErr, getB().getName());
			getB().setOrg(getUser().getOrg());
		}
	}

	public static class Upd extends IduUpd<Upd, WpBsnCtg> {
		@Override
		public void before() {
			super.before();
			WpBsnCtg bean = WpBsnCtg.chkUniqueName(false,getB().getName());
			WpBsnCtg model = null;
			if (bean != null)
				if (bean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getName());
				else
					model = bean;
			else {
				model = load(getB().getPkey());
				model.setName(getB().getName());
			}
			PropertyUtils.copyPropertiesWithout(model, getB(), WpBsnCtg.T.ORG);
			setB(model);
		}
	}
	public static class Del extends IduDel<Del, WpBsnCtg> {
		@Override
		public void valid() {
			super.valid();
			haveBeUsed(WpBsn.class, WpBsn.T.CTG, getB().getPkey());
			
		}
	}
}
