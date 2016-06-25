package irille.action.wax;

import java.io.IOException;

import irille.action.ActionWx;
import irille.pub.Exp;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.wx.wax.WaxBnft;
import irille.wx.wax.WaxBnftEntry;
import irille.wx.wp.WpBsn;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;
import irille.wxpub.js.JMCloseWindow;
import irille.wxpub.js.JMOpenLocation;
import irille.wxpub.js.JQFunDefine;
import irille.wxpub.js.JsExp;

public class WaxBnftEntryAction extends ActionWx<WaxBnftEntry,WaxBnftEntryAction> {
	public static final Log LOG = new Log(WaxBnftEntryAction.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		unsubErr("请先关注公众号！"),
		entrySucc("恭喜，报名成功！"),
		qrcodeErr("qrerr")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} 
	private WaxBnft _bnft;
	private WaxBnftEntry _bnftEntry;
	private WpBsn _bsn;


	public WpBsn getBsn() {
		return _bsn;
	}

	public void setBsn(WpBsn bsn) {
		_bsn = bsn;
	}

	public WaxBnftEntry getBnftEntry() {
		return _bnftEntry;
	}

	public void setBnftEntry(WaxBnftEntry bnftEntry) {
		_bnftEntry = bnftEntry;
	}
	public WaxBnftEntry getBean() {
		return _bean;
	}

	public void setBean(WaxBnftEntry bean) {
		this._bean = bean;
	}
	public WaxBnft getBnft() {
		return _bnft;
	}

	public void setBnft(WaxBnft bnft) {
		_bnft = bnft;
	}

	@Override
	public Class beanClazz() {
		return WaxBnftEntry.class;
	}
	
	@Override
	public void showBefore() {
		super.showBefore();
		setResult("/wa/bnft/bnftEntry.jsp");
		setBnft(WaxBnft.chk(WaxBnft.class, getBnft().getPkey()));
		setAccount(getBnft().gtAccount());
		setAccount(WxAccount.chk(WxAccount.class, getAccount().getPkey()));
		//进行网页授权
		if(doAuthorize(getAccount())) 
			return;
		try {
			chkSubscrib();
		} catch (Exp e) {
			JQFunDefine fun = new JQFunDefine(".ok","click");//创建JQuery方法
			JMCloseWindow cw = new JMCloseWindow();//创建关闭当前页面接口
			add(fun.add(cw));
			setJsCode(crtJs());
			setErrMsg(e.getLastMessage());
			setRtnStr(TRENDS);
		}
	}
	@Override
	public void showRun() {
		super.showRun();
		WaxBnftEntry bnftEntry = WaxBnftEntry.chkUniqueWxUserBnft(false, getWxUser().getPkey(), getBnft().getPkey());
		if(bnftEntry != null){//已报名,打印报名者的信息
			setBnftEntry(bnftEntry);
			setBsn(bnftEntry.gtBnft().gtBsn());
		}
		JQFunDefine fd = new JQFunDefine("#add","click");//创建JQuery方法
		JMOpenLocation ol = new JMOpenLocation();//创建微信打开地理位置对象
		ol.setLatitude("parseFloat($(this).children().attr('latitude'))");
		ol.setLongitude("parseFloat($(this).children().attr('longitude'))");
		ol.setName(new JsExp("$(this).children().attr('name')"));
		ol.setAddress(new JsExp("$(this).children().attr('address')"));
		ol.setScale(14);
		add(fd.add(ol));
		crtJsImage(1);
		setJsCode(crtJs());
		setRtnStr(TRENDS);
	}
	
	@Override
	public void xcuteBefore() {
		super.xcuteBefore();
		setBnft(WaxBnft.chk(WaxBnft.class, getBnft().getPkey()));
		setAccount(getBnft().gtAccount());
		setAccount(WxAccount.chk(WxAccount.class, getAccount().getPkey()));
	}

	@Override
	public void xcuteRun() {
		super.xcuteRun();
		String[] urls = null;
		try {
			urls = getImageUrls();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//第一次报名
		WxUser user = chkWxUser();
		WaxBnftEntry waBnftEntry = new WaxBnftEntry();
		waBnftEntry.setBnft(getBnft().getPkey());
		waBnftEntry.setName(getBean().getName());
		waBnftEntry.setMobile(getBean().getMobile());
		waBnftEntry.setWxUser(user.getPkey());
		waBnftEntry.setAccount(user.getAccount());
		waBnftEntry.setImgUrl(urls[0]);
		waBnftEntry.ins();
		setSuccMsg(Msgs.entrySucc.getMsg());
		crtJsImage(1);
		JQFunDefine fun = new JQFunDefine(".ok","click");//创建JQuery方法
		JMCloseWindow cw = new JMCloseWindow();//创建关闭当前页面接口
		add(fun.add(cw));
		setJsCode(crtJs());
		setResult("/wax/bnft/bnftEntry.jsp");
		setRtnStr(TRENDS);
	}
}
