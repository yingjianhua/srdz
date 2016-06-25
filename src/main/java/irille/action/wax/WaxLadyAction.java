package irille.action.wax;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.Idu;
import irille.wx.wax.WaxLady;
import irille.wx.wax.WaxLadyDAO;
import irille.wx.wax.WaxLadyPic;
import irille.wx.wx.Wx;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;
import irille.wxpub.js.JMCloseWindow;
import irille.wxpub.js.JQFunDefine;

public class WaxLadyAction extends ActionWx<WaxLady,WaxLadyAction> {
	public static final Log LOG = new Log(WaxLadyAction.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		unsubErr("请先关注公众号！"),
		entrySucc("恭喜，报名成功！"),
		unsbscribErr("unsbscribErr"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	
	private List<WaxLadyPic> _listLine;

	public WaxLady getBean() {
		return _bean;
	}

	public void setBean(WaxLady bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WaxLady.class;
	}

	public List<WaxLadyPic> getListLine() {
		return _listLine;
	}

	public void setListLine(List<WaxLadyPic> listLine) {
		_listLine = listLine;
	}

	@Override
	public WaxLady insRun() throws Exception {
		insBefore();
		WaxLadyDAO.Ins ins = new WaxLadyDAO.Ins();
		ins.setB(_bean);
		ins.setLines(_listLine);
		ins.commit();
		insAfter();
		return ins.getB();
	}

	@Override
	public WaxLady updRun() throws Exception {
		updBefore();
		WaxLadyDAO.Upd upd = new WaxLadyDAO.Upd();
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
		setResult("/wax/lady/lady.jsp");
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
		WaxLady lady=WaxLady.chkUniqueWxUserAccount(false, user.getPkey(), getAccount().getPkey());
		if(lady!=null){
			lady.setName(getBean().getName());
			lady.setMobile(getBean().getMobile());
			lady.setWxUser(user.getPkey());
			lady.setAccount(user.getAccount());
			lady.upd();
			List<WaxLadyPic> listPic = new ArrayList<WaxLadyPic>();
			for (String url : urls) {
				WaxLadyPic pic = new WaxLadyPic().init();
				pic.setLady(lady.getPkey());
				pic.setAccount(getAccount().getPkey());
				pic.setImgUrl(url);
				listPic.add(pic);
			}
			Idu.updLine(lady, listPic, WaxLadyPic.T.LADY.getFld());
		}
		else{
			WaxLady waLady = new WaxLady();
			waLady.setName(getBean().getName());
			waLady.setMobile(getBean().getMobile());
			waLady.setWxUser(user.getPkey());
			waLady.setAccount(user.getAccount());
			waLady.ins();
			for(short i=0;i<urls.length;i++) {
				WaxLadyPic pic = new WaxLadyPic();
				pic.setLady(waLady.getPkey());
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
		setResult("/wax/lady/lady.jsp");
		setRtnStr(TRENDS);
	}
}
