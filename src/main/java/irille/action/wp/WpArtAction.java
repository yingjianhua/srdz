package irille.action.wp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import irille.action.ActionWx;
import irille.pub.Str;
import irille.pub.idu.Idu;
import irille.wx.wp.WpArt;
import irille.wx.wp.WpArtShow;
import irille.wx.wp.WpArtTheme;
import irille.wx.wp.WpBsn;
import irille.wx.wp.WpBsnArea;
import irille.wx.wp.WpBsnCtg;
import irille.wxpub.js.JMOpenLocation;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;

public class WpArtAction extends ActionWx<WpArt,WpArtAction> {
	private WpArtShow _show;
	private WpArtTheme _theme;
	private WpBsnArea _area;
	private WpBsnCtg _ctg;
	private String _search;
	private List<WpArt> _list;
	private List<WpArtTheme> _themeList;
	private List<WpBsn> _bsnList;
	private List<WpBsnCtg> _ctgList;
	private List<WpBsnArea> _areaList;
	public WpArt getBean() {
		return _bean;
	}

	public void setBean(WpArt bean) {
		this._bean = bean;
	}

	public WpArtShow getShow() {
		return _show;
	}

	public void setShow(WpArtShow show) {
		_show = show;
	}

	public WpArtTheme getTheme() {
		return _theme;
	}

	public void setTheme(WpArtTheme theme) {
		_theme = theme;
	}

	public WpBsnArea getArea() {
		return _area;
	}

	public void setArea(WpBsnArea area) {
		_area = area;
	}

	public WpBsnCtg getCtg() {
		return _ctg;
	}

	public void setCtg(WpBsnCtg ctg) {
		_ctg = ctg;
	}
    
	public String getSearch() {
		return _search;
	}

	public void setSearch(String search) {
		this._search = search;
	}

	public List<WpArt> getList() {
		return _list;
	}

	public void setList(List<WpArt> list) {
		_list = list;
	}

	public List<WpArtTheme> getThemeList() {
		return _themeList;
	}

	public void setThemeList(List<WpArtTheme> themeList) {
		_themeList = themeList;
	}

	public List<WpBsn> getBsnList() {
		return _bsnList;
	}

	public void setBsnList(List<WpBsn> bsnList) {
		_bsnList = bsnList;
	}

	public List<WpBsnCtg> getCtgList() {
		return _ctgList;
	}

	public void setCtgList(List<WpBsnCtg> ctgList) {
		_ctgList = ctgList;
	}

	public List<WpBsnArea> getAreaList() {
		return _areaList;
	}

	public void setAreaList(List<WpBsnArea> areaList) {
		_areaList = areaList;
	}

	@Override
	public Class beanClazz() {
		return WpArt.class;
	}
	
	/**
	 * 展示到列表页面
	 * @return
	 * @throws Exception
	 */
	@Override
	public void showListRun() {
		super.showListRun();
		setShow(WpArtShow.chk(WpArtShow.class, getShow().getPkey()));
		setAccount(getShow().gtAccount());
		setSarg1(getAccount().getAccountName());
		setList(search());
		String whereSql = "{0}=? ORDER BY {1}";
		setThemeList(WpArtTheme.list(WpArtTheme.class, Idu.sqlString(whereSql, WpArtTheme.T.ART_SHOW, WpArtTheme.T.SORT), false, getShow().getPkey()));
		setBsnList(WpBsn.list(WpBsn.class, "1=1", false));
		String orderBy = "1=1 ORDER BY {0}";
		setAreaList(WpBsnArea.list(WpBsnArea.class, Idu.sqlString(orderBy,WpBsnArea.T.SORT), false));
		setCtgList(WpBsnCtg.list(WpBsnCtg.class, Idu.sqlString(orderBy, WpBsnCtg.T.SORT), false));
		JQFunDefine fun = new JQFunDefine(".ttb","click");//创建JQuery方法
		JMOpenLocation ol = new JMOpenLocation();//创建微信打开地理位置对象
		ol.setLatitude("parseFloat($(this).children().attr('latitude'))");
		ol.setLongitude("parseFloat($(this).children().attr('longitude'))");
		ol.setName(new JsExp("$(this).children().attr('name')"));
		ol.setAddress(new JsExp("$(this).children().attr('address')"));
		ol.setScale(14);
		add(fun.add(ol));
		crtJsMenuShare(getShow().gtJsMenushare());
		setJsCode(crtJs());
		setResult("wp/fif/art_list.jsp");
		setRtnStr(TRENDS);
	}
	
	@Override
	public void xcuteRun() {
		setList(search());
		String whereSql = "{0}=? ORDER BY {1}";
		setThemeList(WpArtTheme.list(WpArtTheme.class, Idu.sqlString(whereSql, WpArtTheme.T.ART_SHOW, WpArtTheme.T.SORT), false, getShow().getPkey()));
		setBsnList(WpBsn.list(WpBsn.class, "1=1", false));
		JSONArray list = new JSONArray();
		try {
			for(WpArt art : getList()) {
				JSONObject json = new JSONObject();
				for(WpArtTheme theme : getThemeList()) {
					if(theme.getPkey() == art.getTheme()) {
						json.put("theme", theme.getName());
					}
				}
				for(WpBsn bsn : getBsnList()) {
					if(bsn.getPkey() == art.getBsn()) {
						json.put("bsn", bsn.getName());
					}
				}
				json.put("title", art.getTitle());
				json.put("url", art.getUrl());
				json.put("description", art.getDescription());
				json.put("imgUrl", art.getImgUrl());
				list.put(json);
			}
			getJson().put("result", list);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		super.xcuteRun();
	}
	
	/**
	 * 搜索图文列表
	 * @return
	 */
	private List<WpArt> search() {
		int limit = 6;
		if (getLimit() != 0)
			limit = getLimit();
		String orderBy = " ORDER BY " + WpArt.T.DATE.getFld().getCodeSqlField() + " DESC";
		String search = Str.isEmpty(getSearch()) ? "%" : "%" + getSearch() + "%";
		String whereSql = "{0} IN (SELECT {1} FROM {2} WHERE {3} LIKE ? AND {4}=?)";
		String subWhere = " AND {0} IN (SELECT {1} FROM {2} WHERE {3}={4})";
		if (getTheme() != null && getTheme().getPkey() != null && getTheme().getPkey() != 0){//根据主题搜索
			setTheme(WpArtTheme.chk(WpArtTheme.class, getTheme().getPkey()));
			if (getTheme() != null)
				whereSql += " AND " + WpArt.T.THEME.getFld().getCodeSqlField() + "=" + getTheme().getPkey();
		}
		if (getArea() != null && getArea().getPkey() != null && getArea().getPkey() != 0) {
			setArea(WpBsnArea.chk(WpBsnArea.class, getArea().getPkey()));
			if (getArea() != null)//有选中区域 后半句判断后台中途删除区域
				whereSql += Idu.sqlString(subWhere, WpArt.T.BSN, WpBsn.T.PKEY, WpBsn.TB.getCodeSqlTb(), WpBsn.T.AREA, String.valueOf(getArea().getPkey()));
		}
		
		if (getCtg() != null && getCtg().getPkey() != null && getCtg().getPkey() != 0) {//有选中分类 后半句判断后台中途删除分类
			setCtg(WpBsnCtg.chk(WpBsnCtg.class, getCtg().getPkey()));
			if (getCtg() != null)//有选中分类 后半句判断后台中途删除分类
				whereSql += Idu.sqlString(subWhere, WpArt.T.BSN, WpBsn.T.PKEY, WpBsn.TB.getCodeSqlTb(), WpBsn.T.CTG, String.valueOf(getCtg().getPkey()));
		}
		whereSql = Idu.sqlString(whereSql, WpArt.T.BSN, WpBsn.T.PKEY, WpBsn.TB.getCodeSqlTb(), WpBsn.T.NAME, WpArt.T.ART_SHOW);
		return WpArt.list(WpArt.class, false, whereSql + orderBy, getStart(), limit, search, getShow().getPkey());
	}
	
	public String eat() throws Exception {
		setShow(WpArtShow.chk(WpArtShow.class, getShow().getPkey()));
		setAccount(getShow().gtAccount());
		String whereSql = Idu.sqlString("{0}=?", WpArt.T.ART_SHOW);
		List<WpArt> list = WpArt.list(WpArt.class, whereSql, false, getShow().getPkey());
		
		//随机取30条记录
		int limit = 30;
		if (list.size() < limit)
			limit = list.size();
		Random random = new Random();
		List<WpArt> listTmp = new ArrayList<WpArt>();
		for (int i = 0; i < limit; i++)
			listTmp.add(list.get(random.nextInt(list.size())));
		list = listTmp;
		
		List<JSONObject>jsonList=new ArrayList<JSONObject>();
		for (WpArt line : list) {
			JSONObject json;
			try {
				json = crtJsonByBean(line);
				jsonList.add(json);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		crtJsMenuShare(getShow().gtJsMenushare());
		setJsCode(crtJs());
		setSarg1(jsonList.toString());
		setResult("wp/fif/eat.jsp");
		return TRENDS;
	}
}
