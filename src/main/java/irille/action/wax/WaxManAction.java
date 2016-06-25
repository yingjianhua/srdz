package irille.action.wax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.Idu;
import irille.wx.wax.WaxMan;
import irille.wx.wax.WaxManDAO;
import irille.wx.wax.WaxManPic;
import irille.wx.wx.Wx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;
import irille.wxpub.js.JMCloseWindow;
import irille.wxpub.js.JQFunDefine;

public class WaxManAction extends ActionWx<WaxMan,WaxManAction> {
	public static final Log LOG = new Log(WaxManAction.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		unsubErr("请先关注公众号！"),
		entrySucc("恭喜，报名成功！"),
		unsbscribErr("unsbscribErr"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	
	private List<WaxManPic> _listLine;

	public WaxMan getBean() {
		return _bean;
	}

	public void setBean(WaxMan bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WaxMan.class;
	}

	public List<WaxManPic> getListLine() {
		return _listLine;
	}

	public void setListLine(List<WaxManPic> listLine) {
		_listLine = listLine;
	}

	@Override
	public WaxMan insRun() throws Exception {
		insBefore();
		WaxManDAO.Ins ins = new WaxManDAO.Ins();
		ins.setB(_bean);
		ins.setLines(_listLine);
		ins.commit();
		insAfter();
		return ins.getB();
	}

	@Override
	public WaxMan updRun() throws Exception {
		updBefore();
		WaxManDAO.Upd upd = new WaxManDAO.Upd();
		upd.setB(_bean);
		upd.setLines(_listLine);
		upd.commit();
		updAfter();
		return upd.getB();
	}
	@Override
	public void showBefore() {
		super.showBefore();
		setAccount(new WxAccount());
		getAccount().setPkey(2);
		WxAccount account = WxAccount.chk(WxAccount.class, getAccount().getPkey());
		setAccount(account);
		//进行网页授权
		if(doAuthorize(account)) 
			return;
		WxUser user = chkWxUser();
		if(user == null || user.gtStatus() == Wx.OStatus.NOFOLLOW)
			setSarg1("show");
		crtJsImage(5);
		JQFunDefine fun = new JQFunDefine(".ok","click");//创建JQuery方法
		JMCloseWindow cw = new JMCloseWindow();//创建关闭当前页面接口
		add(fun.add(cw));
		setJsCode(crtJs());
	}

	@Override
	public void showRun() {
		super.showRun();
		setResult("/wax/man/man.jsp");
		setRtnStr(TRENDS);
	}

	@Override
	public void xcuteBefore() {
		super.xcuteBefore();
		setAccount(new WxAccount());
		getAccount().setPkey(2);
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
		WxUser user = chkWxUser();
		WaxMan man=WaxMan.chkUniqueWxUserAccount(false, user.getPkey(), getAccount().getPkey());
		if(man!=null){
			man.setName(getBean().getName());
			man.setMobile(getBean().getMobile());
			man.setWxUser(user.getPkey());
			man.setAccount(user.getAccount());
			man.upd();
			List<WaxManPic> listPic = new ArrayList<WaxManPic>();
			for (String url : urls) {
				WaxManPic pic = new WaxManPic().init();
				pic.setMan(man.getPkey());
				pic.setAccount(getAccount().getPkey());
				pic.setImgUrl(url);
				listPic.add(pic);
			}
			Idu.updLine(man, listPic, WaxManPic.T.MAN.getFld());
		}
		else{
			WaxMan waMan= new WaxMan();
			waMan.setName(getBean().getName());
			waMan.setMobile(getBean().getMobile());
			waMan.setWxUser(user.getPkey());
			waMan.setAccount(user.getAccount());
			waMan.ins();
			for(short i=0;i<urls.length;i++) {
				WaxManPic pic = new WaxManPic();
				pic.setMan(waMan.getPkey());
				pic.setAccount(getAccount().getPkey());
				pic.setImgUrl(urls[i]);
				pic.ins();
			}
			
		}
		setSuccMsg(Msgs.entrySucc.getMsg());
		crtJsImage(5);
		JQFunDefine fun = new JQFunDefine(".ok","click");//创建JQuery方法
		JMCloseWindow ol = new JMCloseWindow();//创建关闭当前页面接口
		add(fun.add(ol));
		setJsCode(crtJs());
		setResult("/wax/man/man.jsp");
		setRtnStr(TRENDS);
	}
}
