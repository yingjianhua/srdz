package irille.wx.wa;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.PubInfs.IMsg;
import irille.pub.Str;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduApprove;
import irille.pub.idu.IduInsLines;
import irille.pub.idu.IduOther;
import irille.pub.idu.IduUnapprove;
import irille.pub.idu.IduUpdLines;
import irille.pub.svr.Env;
import irille.pub.svr.Svr;
import irille.wx.wa.Wa.OActEntryStatus;
import irille.wx.wx.WxAccountDAO;
import irille.wxpub.util.WeixinUtil;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class WaActVoteEntryDAO {
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		statusErr("非【{0}】状态，不能操作!"),
		encodeErr("请勿在【{0}】和【{1}】中使用非法字符或表情！")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	public static final Log LOG = new Log(WaActVoteEntryDAO.class);
	
	public static class Ins extends IduInsLines<Ins, WaActVoteEntry, WaActVotePhoto> {
		private String name;
		private String des;
		@Override
		public void before() {
			super.before();
			name = getB().getNamePerson();
			des = getB().getDes();
			getB().stStatus(Wa.OActEntryStatus.INIT);
			getB().setEntryTime(Env.getSystemTime());
			getB().setNumber(getNumber(getB().getVote()));
			getB().stAccount(WxAccountDAO.getByUser(getUser()));
		}
		
		@Override
		public void after() {
			super.after();
			getB().setNamePerson(name);
			getB().setDes(des);
			if (getLines() != null) {
				for (WaActVotePhoto line : getLines())
					line.setAccount(getB().getAccount());
				insLine(getB(), getLines(), WaActVotePhoto.T.VOTE_ENTRY.getFld());
			}
		}
		
	}

	public static class Upd extends IduUpdLines<Upd, WaActVoteEntry, WaActVotePhoto> {
		private String name;
		private String des;
		@Override
		public void before() {
			super.before();
			name = getB().getNamePerson();
			des = getB().getDes();
			WaActVoteEntry dbBean = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(dbBean, getB(), WaActVoteEntry.T.ACCOUNT, WaActVoteEntry.T.STATUS, 
					WaActVoteEntry.T.ENTRY_TIME, WaActVoteEntry.T.NUMBER);
			setB(dbBean);
		}
		
		@Override
		public void after() {
			super.after();
			getB().setNamePerson(name);
			getB().setDes(des);
			if (getLines() != null) {
				for (WaActVotePhoto line : getLines())
					line.setAccount(getB().getAccount());
				updLine(getB(), getLines(), WaActVotePhoto.T.VOTE_ENTRY.getFld());
			}
		}

	}
	
	public static class DoAppr extends IduApprove<DoAppr, WaActVoteEntry> {
		@Override
		public void run() {
			super.run();
			WaActVoteEntry model = loadThisBeanAndLock();
			model.stStatus(Wa.OActEntryStatus.APPR);
			setB(model);
			model.upd();
		}
	}

	public static class UnAppr extends IduUnapprove<UnAppr, WaActVoteEntry> {
		@Override
		public void run() {
			super.run();
			WaActVoteEntry model = loadThisBeanAndLock();
			if (!model.gtStatus().equals(Wa.OActEntryStatus.APPR))
				throw LOG.err(Msgs.statusErr, Wa.OActEntryStatus.APPR.getLine().getName());
			model.stStatus(Wa.OActEntryStatus.INIT);
			setB(model);
			model.upd();
		}
	}
	
	public static class Block extends IduOther<Block, WaActVoteEntry> {
		@Override
		public void run() {
			super.run();
			WaActVoteEntry model = loadThisBeanAndLock();
			model.stStatus(Wa.OActEntryStatus.FAILED);
			setB(model);
			model.upd();
		}
	}
	/**
	 * 统计报名量
	 * @param vote
	 * @return
	 */
	  public static int count(WaActVote vote) {
		  String sql = Idu.sqlString("SELECT COUNT(*) FROM {0} WHERE {1}=? AND {2}=? ", WaActVoteEntry.class, WaActVoteEntry.T.VOTE, WaActVoteEntry.T.STATUS);
		  Object[] result = WaActVoteEntry.queryOneRow(sql, vote.getPkey(),OActEntryStatus.APPR.getLine().getKey());
		  if(result == null) {
			  return 0;
		  } else if(result[0] == null){
			  return 0;
		  }
		return ((Number) result[0]).intValue();
	  }
	  
	  /**
	   * 获取当前报名记录的排行
	   * @param bean
	   * @return
	   */
	  public static int getRank(WaActVoteEntry bean) {
		  String sql = Idu.sqlString("SELECT COUNT(*) FROM {0} WHERE {1}=? AND {2}=? AND {3}>? ", 
				  WaActVoteEntry.TB.getCodeSqlTb(), WaActVoteEntry.T.VOTE.getFld().getCodeSqlField(),
				  WaActVoteEntry.T.STATUS.getFld().getCodeSqlField(), WaActVoteEntry.T.VOTE_COUNT.getFld().getCodeSqlField());	
		return ((Number) WaActVoteEntry.queryOneRow(sql, bean.getVote(),OActEntryStatus.APPR.getLine().getKey(), bean.getVoteCount())[0]).intValue() + 1;
	  }
	  
	  /**
	   * 获取当前报名记录编号
	   * @param vote
	   * @return
	   */
	  public static int getNumber(WaActVote vote) {
		  return getNumber(vote.getPkey());
	  }
	  
	  /**
	   * 获取当前报名记录编号
	   * @param vote
	   * @return
	   */
	  public static int getNumber(Integer vote) {
		  String sql = Idu.sqlString("SELECT COUNT(*) FROM {0} WHERE {1}=?", WaActVoteEntry.TB.getCodeSqlTb(), WaActVoteEntry.T.VOTE.getFld().getCodeSqlField());		  
		return (((Number) WaActVoteEntry.queryOneRow(sql, vote)[0]).intValue()) + 1;
	  }
	  
	  public static void main(String[] args) {
		List<WaActVoteEntry> list = BeanBase.list(WaActVoteEntry.class, "1=1",false);
		for(WaActVoteEntry line: list) {
			line.setNamePerson(new String(WeixinUtil.decode(line.getNamePerson())));
			line.setDes(new String(WeixinUtil.decode(line.getDes())));
			line.upd();
		}
		Svr.commit();
	}
}
