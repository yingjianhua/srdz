package irille.wx.wpt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.bean.Bean;
import irille.pub.bean.BeanBuf;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduUpdLines;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptCityDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		imgEmptyErr("区不可为空!"),
		uniqueErr("记录【{0}】已存在，不可操作！"),
		repeat("区域不可重复"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WptCityDAO.class);
	
	/**
	 * 每个wxAccount的pkey对应一组wptCity
	 */
	private static final Hashtable<Integer, Map<String, WptCity>> cityCache = new Hashtable<Integer, Map<String, WptCity>>();
	/**
	 * 获取该微信公众号下的所有WptCity
	 */
	public static List<WptCity> getCitys(Integer pkey) {
		Map<String, WptCity> cityMap = cityCache.get(pkey);
		if(cityMap == null || cityMap.size() == 0 ) {
			if(cityMap == null) cityMap = new HashMap<String, WptCity>();
			for(WptCity city : Bean.list(WptCity.class, WptCity.T.ACCOUNT + "=?", false, pkey)) {
				cityMap.put(city.getName(), city);
			}
			cityCache.put(pkey, cityMap);
		}
		Collection<WptCity> citys = (Collection<WptCity>)cityMap.values();
		return new ArrayList<WptCity>(citys);
	}
	public static void clearCache(Integer pkey) {
		synchronized(cityCache) {
			cityCache.remove(pkey);
		}
	}
	public static WptCity findByName(Integer pkey, String name) {
		Map<String, WptCity> cityMap = cityCache.get(pkey);
		if(cityMap == null) {
			cityMap = refreshCity(pkey);
		}
		if(!cityMap.containsKey(name)) return null;
		return  cityMap.get(name);
	}
	private static Map<String, WptCity> refreshCity(Integer pkey) {
		synchronized (cityCache) {
			Map<String, WptCity> cityMap = null;
			if(cityCache.get(pkey) == null) {
				cityMap = new HashMap<String, WptCity>();
				for(WptCity city : Bean.list(WptCity.class, "1=1", false)) {
					cityMap.put(city.getName(), city);
				}
				cityCache.put(pkey, cityMap);
			}
			return cityMap;
		}
	}
	
	public static class Ins extends IduInsLines<Ins, WptCity, WptCityLine> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
			if(WptCity.chkUniqueNameAccount(false, getB().getName(), getB().getAccount()) != null)
				throw LOG.err(Msgs.uniqueErr, getB().getName());
		}

		@Override
		public void after() {
			super.after();
			if (getLines()==null||getLines().size() ==0)
				throw LOG.err(Msgs.imgEmptyErr);
			for(int i=0;i<getLines().size();i++){
				String area1 = getLines().get(i).getName();
				for(int j=0;j<getLines().size();j++){
					String area2 = getLines().get(j).getName();
					if(i != j && area1.equals(area2))
						throw LOG.err(Msgs.repeat);
				}
				getLines().get(i).setAccount(account.getPkey());
			}
			insLine(getB(), getLines(), WptCityLine.T.CITY.getFld());
			clearCache(account.getPkey());
		}
	}

	public static class Upd extends IduUpdLines<Upd, WptCity, WptCityLine> {
		WxAccount account;

		@Override
		public void before() {
			super.before();
			WptCity dbBean = WptCity.chkUniqueNameAccount(false, getB().getName(), getB().getAccount());
			WptCity model = null;
			if (dbBean != null)
				if (dbBean.getPkey() != getB().getPkey())
					throw LOG.err(Msgs.uniqueErr, getB().getName());
				else
					model = dbBean;
			else 
				model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WptCity.T.ACCOUNT);
			account = WxAccountDAO.getByUser(getUser());
			setB(model);
			if(getLines() == null || getLines().size() == 0)
				throw LOG.err(Msgs.imgEmptyErr);
			for (WptCityLine line : getLines()){
				WptCityLine tline = WptCityLine.chkUniqueCityName(false, getB().getPkey(), line.getName());
				if(tline != null)
					PropertyUtils.copyPropertiesWithout(line, tline, WptCityLine.T.ACCOUNT);
				line.setAccount(account.getPkey());
			}
			updLine(getB(), getLines(), WptCityLine.T.CITY.getFld());
		}
		@Override
		public void after() {
			BeanBuf.clear(WptCity.class, getB().getPkey());
			clearCache(account.getPkey());
			super.after();
		}
	}

	public static class Del extends IduDel<Del, WptCity> {
		@Override
		public void valid() {
			super.valid();
			haveBeUsed(WptRestaurant.class, WptRestaurant.T.CITY, getB().getPkey());
		}
		@Override
		public void before() {
			super.after();
			delLine(getLines(WptCityLine.T.CITY.getFld(), getB().getPkey()));
		}
		@Override
		public void after() {
			BeanBuf.clear(WptCity.class, getB().getPkey());
			clearCache(getB().getAccount());
			super.after();
		}
	}
}