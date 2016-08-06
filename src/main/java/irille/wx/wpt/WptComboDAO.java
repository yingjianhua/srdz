package irille.wx.wpt;

import java.io.Serializable;
import java.util.List;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.Svr;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptComboDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		imgEmptyErr("菜品不可为空!"),
		uniqueErr("记录【{0}】已存在，不可操作！"),
		difference("菜品不属于此餐厅"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WptComboDAO.class);

	public static class Ins extends IduInsLines<Ins, WptCombo, WptComboLine> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			if(WptCombo.chkUniqueRestaurantName(false, getB().getRestaurant(), getB().getName())!= null)
				throw LOG.err(Msgs.uniqueErr, getB().getName());
		}

		@Override
		public void after() {
			super.after();
			if (getLines()==null||getLines().size() ==0)
				throw LOG.err(Msgs.imgEmptyErr);
			for (WptComboLine line : getLines()){
				line.setAccount(account.getPkey());
				WptMenu menu = WptMenu.load(WptMenu.class, line.getMenu());
				if(menu.getRestaurant() != getB().getRestaurant())
					throw LOG.err(Msgs.difference);
			}
			insLine(getB(), getLines(), WptComboLine.T.COMBO.getFld());
			if(getB().getImgUrl() != null){//把餐厅图片添加到餐厅顶图中
				WptComboBanner banner = new WptComboBanner();
				banner.setAccount(account.getPkey());
				banner.setImgUrl(getB().getImgUrl());
				banner.setSort(0);
				banner.setCombo(getB().getPkey());
				banner.ins();
			}
		}
	}

	public static class Upd extends IduUpdLines<Upd, WptCombo, WptComboLine> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			WptCombo dbBean = WptCombo.chkUniqueRestaurantName(false, getB().getRestaurant(), getB().getName());
			WptCombo model = null;
			if (dbBean != null)
				if (dbBean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getName());
				else
					model = dbBean;
			else 
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptCombo.T.ACCOUNT);
			account = WxAccountDAO.getByUser(getUser());
			setB(model);
			if (getLines()==null||getLines().size() ==0)
				throw LOG.err(Msgs.imgEmptyErr);
			String sql = Idu.sqlString("delete from {0} where {1}=?", WptComboLine.class, WptComboLine.T.COMBO);
			Bean.executeUpdate(sql, getB().getPkey());
			for (WptComboLine line : getLines()){
				line.setPkey(null);
				line.setAccount(account.getPkey());
				line.setCombo(getB().getPkey());
				WptMenu menu = WptMenu.load(WptMenu.class, line.getMenu());
				if(menu.getRestaurant() != getB().getRestaurant())
					throw LOG.err(Msgs.difference);
				line.ins();
			}
		}
	}

	public static class Del extends IduDel<Del, WptCombo> {
		@Override
		public void before() {
			super.after();
			delLine(getLines(WptComboLine.T.COMBO.getFld(), getB().getPkey()));
		}
	}
	public WptCombo enableDisable(Serializable pkey) {
		WptCombo combo = WptCombo.load(WptCombo.class, pkey);
		if(combo.gtEnabled()) {
			combo.stEnabled(false);
		} else {
			combo.stEnabled(true);
		}
		combo.upd();
		return combo;
	}
	public static void main(String[] args) {
		List<WptCombo> combos = Bean.list(WptCombo.class, "1=1", false);
		for(WptCombo combo:combos) {
			WptComboBanner banner = new WptComboBanner();
			banner.setAccount(combo.getAccount());
			banner.setImgUrl(combo.getImgUrl());
			banner.setSort(0);
			banner.setCombo(combo.getPkey());
			banner.ins();
		}
		Svr.commit();
	}
}