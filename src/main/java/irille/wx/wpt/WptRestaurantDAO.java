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
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptRestaurantDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		imgEmptyErr("宴会类型不可为空!"),
		uniqueErr("记录【{0}】已存在，不可操作！"),
		repeat("宴会类型不可重复"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WptRestaurantDAO.class);

	public static class Ins extends IduInsLines<Ins, WptRestaurant, WptRestaurantLine> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			if(WptRestaurant.chkUniqueName(false, getB().getName()) != null)
				throw LOG.err(Msgs.uniqueErr, getB().getName());
			account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			getB().setCityname(getB().gtCity().getName());
		}

		@Override
		public void after() {
			super.after();
			if (getLines()==null||getLines().size() ==0)
				throw LOG.err(Msgs.imgEmptyErr);
			for(int i=0;i<getLines().size();i++){
				int res1 = getLines().get(i).getBanquet();
				for(int j=0;j<getLines().size();j++){
					int res2 = getLines().get(j).getBanquet();
					if(i != j && res1 == res2)
						throw LOG.err(Msgs.repeat);
				}
				getLines().get(i).setAccount(account.getPkey());
			}
			insLine(getB(), getLines(), WptRestaurantLine.T.RESTAURANT.getFld());
			if(getB().getImgUrl() != null){//把餐厅图片添加到餐厅顶图中
				WptRestaurantBanner banner = new WptRestaurantBanner();
				banner.setAccount(account.getPkey());
				banner.setImgUrl(getB().getImgUrl());
				banner.setSort(0);
				banner.setRestaurant(getB().getPkey());
				banner.ins();
			}
		}
	}

	public static class Upd extends IduUpdLines<Upd, WptRestaurant, WptRestaurantLine> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			WptRestaurant dbBean = WptRestaurant.chkUniqueName(false, getB().getName());
			System.out.println(getB().getLongitude());
			System.out.println(getB().getLongitude() == null);
			WptRestaurant model = null;
			if (dbBean != null)
				if (dbBean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getName());
				else
					model = dbBean;
			else 
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptRestaurant.T.ACCOUNT);
			account = WxAccountDAO.getByUser(getUser());
			model.setCityname(getB().gtCity().getName());
			setB(model);
			for (WptRestaurantLine line : getLines())
				line.setAccount(account.getPkey());
			updLine(getB(), getLines(), WptRestaurantLine.T.RESTAURANT.getFld());
		}
	}

	public static class Del extends IduDel<Del, WptRestaurant> {
		@Override
		public void valid() {
			super.valid();
			haveBeUsed(WptCombo.class, WptCombo.T.RESTAURANT, getB().getPkey());
			haveBeUsed(WptSpecialLine.class, WptSpecialLine.T.RESTAURANT, getB().getPkey());
			haveBeUsed(WptHot.class, WptHot.T.RESTAURANT, getB().getPkey());
		}
		@Override
		public void after() {
			super.after();
			delLine(getLines(WptRestaurantLine.T.RESTAURANT.getFld(), getB().getPkey()));
		}
	}
	public void addSpec(int[] pkeys) {
		String where = "{0} in (select s.{0} from {1} b right join {2} s on b.{3}=s.{4} where b.{5} in (select {6} from {7} where {8} = ?))"; 
		where = Idu.sqlString(where, WptSpecial.T.PKEY, WptBanquet.class, WptSpecial.class, WptBanquet.T.NAME, WptSpecial.T.TITLE, WptBanquet.T.PKEY, WptRestaurantLine.T.BANQUET, WptRestaurantLine.class, WptRestaurantLine.T.RESTAURANT);
		for(int pkey:pkeys) {
			List<WptSpecial> sps = WptSpecial.list(WptSpecial.class, where, false, pkey);
			for(WptSpecial special:sps) {
				WptSpecialLine line = WptSpecialLine.chkUniqueSpecialRestaurant(false, special.getPkey(), pkey);
				if(line == null) {
					line = new WptSpecialLine();
					line.setAccount(special.getAccount());
					line.setRestaurant(pkey);
					line.setSort(1);
					line.setSpecial(special.getPkey());
					line.ins();
				}
			}
		}
	}
	public WptRestaurant enableDisable(Serializable pkey) {
		WptRestaurant restaurant = WptRestaurant.load(WptRestaurant.class, pkey);
		if(restaurant.gtEnabled()) {
			restaurant.stEnabled(false);
			Bean.executeUpdate(Idu.sqlString("delete from {0} where {1}=?", WptHot.class, WptHot.T.RESTAURANT), pkey);
		} else {
			restaurant.stEnabled(true);
		}
		restaurant.upd();
		return restaurant;
	}
	/*public static class MenuSet  extends IduOther<MenuSet, WptRestaurant> {
		public static Cn CN = new Cn("menuSet", "菜品设置");
		
		@Override
		public void run() {
			super.run();
			WptRestaurant model = load(getB().getPkey());
			setB(model);
		}
	}*/
}