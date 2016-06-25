package irille.wx.wa;

import irille.pub.Cn;
import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUpdLines;
import irille.wx.js.Js;
import irille.wx.js.JsMenuShare;
import irille.wx.wa.Wa.OActVoteStatus;
import irille.wx.wx.Wx.OAccountType;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

import java.util.Date;

public class WaActVoteDAO {
	public static final Log LOG = new Log(WaActVoteDAO.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		oauthErr("请先为当前公众号配置网页授权代理号"),
		emptyErr("奖品配置不可为空!"),
		statusErr("非【{0}】状态，不能操作!"),
		closeErr("【{0}】状态，不能操作!"),
		dateErr("报名时间必须在活动时间范围之内!")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on

	public static class Ins extends IduInsLines<Ins, WaActVote, WaActVotePrize> {
		String url;
		JsMenuShare jsMenuShare;
		
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		
		@Override
		public void valid()  {
			WxAccount account = WxAccountDAO.getByUser(getUser());
			if(account.gtAccountType() == OAccountType.SUBSCRIPTION && account.getOpenPlat() == null) {
				throw LOG.err(Msgs.oauthErr);
			}
			validDate(getB());
			super.valid();
		}
		@Override
		public void before() {
			super.before();
			jsMenuShare=new JsMenuShare().init();
			jsMenuShare.stEnabled(false);
			jsMenuShare.stAccount(WxAccountDAO.getByUser(getUser()));
			jsMenuShare.setType(Js.OJsMenuType.LINK.getLine().getKey());
			jsMenuShare.ins();
			getB().setJsMenushare(jsMenuShare.getPkey());
			getB().stStatus(Wa.OActVoteStatus.INIT);
			getB().setAccount(jsMenuShare.getAccount());
		}

		@Override
		public void after() {
			super.after();
			if (getLines() != null) {
				for (WaActVotePrize line : getLines())
					line.setAccount(getB().getAccount());
				insLine(getB(), getLines(), WaActVotePrize.T.VOTE.getFld());
			}
			jsMenuShare.setLink(getUrl() + "?pkey=" + getB().getPkey());
			jsMenuShare.upd();
		}
	}

	public static class Upd extends IduUpdLines<Upd, WaActVote, WaActVotePrize> {
		
		@Override
		public void valid() {
			validDate(getB());
			super.valid();
		}

		@Override
		public void before() {
			super.before();
			WaActVote dbBean = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaActVote.T.ACCOUNT,WaActVote.T.STATUS,WaActVote.T.DES,WaActVote.T.JS_MENUSHARE);
			setB(dbBean);
		}

		@Override
		public void after() {
			super.after();
			if(getLines() != null) {
				for (WaActVotePrize line : getLines())
					line.setAccount(getB().getAccount());
				updLine(getB(), getLines(), WaActVotePrize.T.VOTE.getFld());
			}
		}
	}

	public static class Del extends IduDel<Del, WaActVote> {
		@Override
		public void before() {
			super.before();
			WaActVote dbBean = load(getB().getPkey());
			if(!dbBean.gtStatus().equals(Wa.OActVoteStatus.INIT)){
				throw LOG.err(Msgs.statusErr, Wa.OActVoteStatus.INIT.getLine().getName());
			}
			else{
				JsMenuShare jsMenuShare=dbBean.gtJsMenushare();
				jsMenuShare.del();
			}
		}
		@Override
		public void after() {
			super.after();
			delLine(getLines(WaActVotePrize.T.VOTE.getFld(), getB().getPkey()));
		}
	}

	public static class Publish extends IduOther<Publish, WaActVote> {
		public static Cn CN = new Cn("doOpen", "发布");

		@Override
		public void run() {
			super.run();
			WaActVote actVote = load(getB().getPkey());
			actVote.stStatus(OActVoteStatus.PUBLISH);
			actVote.upd();
			setB(actVote);
		}
	}

	public static class Close extends IduOther<Close, WaActVote> {
		public static Cn CN = new Cn("doClose", "关闭");

		@Override
		public void run() {
			super.run();
			WaActVote actVote = load(getB().getPkey());
			actVote.stStatus(OActVoteStatus.CLOSE);
			actVote.upd();
			setB(actVote);
		}
	}
	
	public static class Edit  extends IduOther<Edit, WaActVote> {
		public static Cn CN = new Cn("edit", "编辑");
		
		@Override
		public void run() {
			super.run();
			
			WaActVote model = load(getB().getPkey());
			if (model.getStatus().equals(Wa.OActVoteStatus.CLOSE.getLine().getKey())) {
				throw LOG.err(Msgs.closeErr, Wa.OActVoteStatus.CLOSE.getLine().getName());
			}
			model.setDes(getB().getDes());
			model.upd();
			setB(model);
		}
	}
	public static void validDate(WaActVote vote) {
		Date actStart = vote.getActStartTime();
		Date entryStart = vote.getEntryStartTime();
		Date entryEnd = vote.getEntryEndTime();
		Date actEnd = vote.getActEndTime();
		if(actStart.after(entryStart)||entryStart.after(entryEnd)||entryEnd.after(actEnd)) 
			throw LOG.err(Msgs.dateErr);
	}
}