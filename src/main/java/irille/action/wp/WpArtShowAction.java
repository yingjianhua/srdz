package irille.action.wp;

import java.util.List;

import irille.action.ActionWx;
import irille.pub.idu.Idu;
import irille.wx.wp.WpArt;
import irille.wx.wp.WpArtBanner;
import irille.wx.wp.WpArtShow;
import irille.wx.wp.WpArtShowDAO;
import irille.wx.wp.WpArtTheme;
import irille.wx.wp.WpBsn;

public class WpArtShowAction extends ActionWx<WpArtShow,WpArtShowAction> {
	private List<WpArtTheme> _themeList;
	private List<WpArtBanner> _bannerList;
	private List<WpBsn> _bsnList;
	
	public List<WpBsn> getBsnList() {
		return _bsnList;
	}
	public void setBsnList(List<WpBsn> bsnList) {
		_bsnList = bsnList;
	}
	public List<WpArtBanner> getBannerList() {
		return _bannerList;
	}
	public void setBannerList(List<WpArtBanner> bannerList) {
		_bannerList = bannerList;
	}
	public WpArtShow getBean() {
		return _bean;
	}
	public void setBean(WpArtShow bean) {
		this._bean = bean;
	}
	
	public List<WpArtTheme> getThemeList() {
		return _themeList;
	}
	public void setThemeList(List<WpArtTheme> themeList) {
		_themeList = themeList;
	}
	@Override
	public Class beanClazz() {
		return WpArtShow.class;
	}
	
	@Override
	public WpArtShow insRun() throws Exception {
		insBefore();
		WpArtShowDAO.Ins ins = new WpArtShowDAO.Ins();
		ins.setB(_bean);
		ins.setUrl(setUrl(true, "show"));
		ins.commit();
		insAfter();
		return ins.getB();
	}
	
	/**
	 * 分享
	 * @throws Exception
	 */
	public void share() throws Exception {
		WpArtShow model = WpArtShow.load(WpArtShow.class, getPkey());
		writeSuccess(model.gtJsMenushare());
	} 
	
	@Override
	public void showRun() {
		super.showRun();
		setBean(WpArtShow.chk(WpArtShow.class, getPkey()));
		setAccount(getBean().gtAccount());
		setSarg1(getAccount().getAccountName());
		String themeSql = "{0}=? ORDER BY {1}";
		setThemeList(WpArtTheme.list(WpArtTheme.class, Idu.sqlString(themeSql, WpArtTheme.T.ART_SHOW, WpArtTheme.T.SORT), false, getBean().getPkey()));
		setBsnList(WpBsn.list(WpBsn.class, "1=1", false));
		String bannerSql = Idu.sqlString("{0} IN (SELECT {1} FROM {2} WHERE {3}=?) ORDER BY {4}", WpArtBanner.T.ART, WpArt.T.PKEY, WpArt.TB.getCodeSqlTb(), WpArt.T.ART_SHOW, WpArtBanner.T.SORT);
		setBannerList(WpArtBanner.list(WpArtBanner.class, bannerSql, false, getBean().getPkey()));
		String whereSql = Idu.sqlString("{0}=? ORDER BY {1}", WpArtTheme.T.ART_SHOW, WpArtTheme.T.SORT);
		setThemeList(WpArtTheme.list(WpArtTheme.class, whereSql, false, getBean().getPkey()));
		crtJsMenuShare(getBean().gtJsMenushare());	//设置分享Js
		setJsCode(crtJs()); //创建JsSDK代码
		setResult("wp/fif/theme.jsp");
		setRtnStr(TRENDS);
	}
}