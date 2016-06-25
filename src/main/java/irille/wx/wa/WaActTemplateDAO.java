package irille.wx.wa;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduUpdLines;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaActTemplateDAO {
	public static final Log LOG = new Log(WaActTemplateDAO.class);

	public static class Ins extends IduInsLines<Ins, WaActTemplate, WaActTemplateLine> {
		WxAccount account; 
		@Override
		public void before() {
			super.before();
			account = WxAccountDAO.getByUser(getUser());
		    getB().setAccount(account.getPkey());
		}

		@Override
		public void after() {
			super.after();
			for (WaActTemplateLine line : getLines())
				line.setAccount(account.getPkey());
			insLine(getB(), getLines(), WaActTemplateLine.T.MAIN.getFld());
		}
	}
	public static class Upd extends IduUpdLines<Upd, WaActTemplate, WaActTemplateLine> {
		WxAccount account; 

		@Override
		public void before() {
			super.before();
			WaActTemplate dbBean = load(getB().getPkey());	
		     PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaActTemplate.T.ACCOUNT);
		 	account = WxAccountDAO.getByUser(getUser());
	
		    getB().setAccount(account.getPkey());
			setB(dbBean);
			for (WaActTemplateLine line : getLines())
				line.setAccount(account.getPkey());
			updLine(getB(), getLines(), WaActTemplateLine.T.MAIN.getFld());
		}
		  @Override
		    public void after() {
		      super.after();   
		  	for (WaActTemplateLine line : getLines())
				line.setAccount(account.getPkey());
		  	updLine(getB(), getLines(), WaActTemplateLine.T.MAIN.getFld());
		    }
	}

	public static class Del extends IduDel<Del, WaActTemplate> {
		@Override
		 public void valid() {
		      super.valid();
			delLine(getLines(WaActTemplateLine.T.MAIN, getB().getPkey()));
		}
	
	}

}
