package irille.wx.wa;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.bean.BeanBase;
import irille.pub.idu.Idu;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.pub.svr.DepConstants;
import irille.pub.svr.Env;
import irille.wx.wa.Wa.OActVoteStatus;
import irille.wx.wm.WmNewsDAO;
import irille.wx.wm.WmTextDAO;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;
import irille.wx.wx.WxUser;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

public class WaActVoteRecordDAO {
	public static final Log LOG = new Log(WaActVoteRecordDAO.class);

	public static class Ins extends IduIns<Ins, WaActVoteRecord> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}

	}

	public static class Upd extends IduUpd<Upd, WaActVoteRecord> {
		@Override
		public void before() {
			super.before();
			WaActVoteRecord dbBean = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(dbBean, getB(),
					WaActVoteRecord.T.ACCOUNT);
			setB(dbBean);
		}

	}

	public static class Del extends IduDel<Del, WaAct> {
		@Override
		public void valid() {
			super.valid();
		}
	}

	/**
	 * 统计投票数
	 */
	public static int count(WaActVote vote) {
//		String sql = Idu.sqlString("SELECT COUNT(*) FROM {0} WHERE {1}=? ",
//				WaActVoteRecord.TB.getCodeSqlTb(), WaActVoteRecord.T.ACT
//						.getFld().getCodeSqlField());
//		return ((Number) WaActVoteRecord.queryOneRow(sql, vote.getPkey())[0])
//				.intValue();
		String sql = Idu.sqlString("SELECT SUM({0}) FROM {1} WHERE {2}=? AND {3}=?", WaActVoteEntry.T.VOTE_COUNT, WaActVoteEntry.class, WaActVoteEntry.T.VOTE, WaActVoteEntry.T.STATUS);
		Object[] result = WaActVoteEntry.queryOneRowIsNull(sql, vote.getPkey(), Wa.OActEntryStatus.APPR.getLine().getKey());
		 if(result == null) {
			  return 0;
		  } else if(result[0] == null){
			  return 0;
		  }
		return ((Number) result[0]).intValue();
	}

	/**
	 * 根据用户删除对应的投票记录，并且投票数减1
	 * 
	 * @param user
	 */
	public static void delByWxUser(WxUser user) {
		String whereSql = "{0}=? AND {1} IN (SELECT {2} FROM {3} WHERE {4}=? AND {5}<?)";
		whereSql = Idu.sqlString(whereSql, WaActVoteRecord.T.WX_USER, WaActVoteRecord.T.ACT, WaActVote.T.PKEY, WaActVote.TB.getCodeSqlTb(), WaActVote.T.ACCOUNT, WaActVote.T.STATUS);
		List<WaActVoteRecord> list = WaActVoteRecord.list(
				WaActVoteRecord.class, whereSql, false, user.getPkey(), user.getAccount(), Wa.OActVoteStatus.ACTFIN.getLine().getKey());
		for (WaActVoteRecord line : list) {
			WaActVoteEntry entry = line.gtEntryRecord();
			entry.setVoteCount(entry.getVoteCount() - 1);
			entry.upd();
			line.del();
		}
	}

	public static int countWhere(String whereSql, Serializable... paras) {
		if (whereSql == null || whereSql.trim().isEmpty())
			whereSql = "1=1";
		String sql = "SELECT COUNT(*) FROM "
				+ WaActVoteRecord.TB.getCodeSqlTb() + " WHERE " + whereSql;
		return ((Number) BeanBase.queryOneRow(sql, paras)[0]).intValue();
	}

	public static int countByWxUser(WaActVote vote, WxUser user, Date stTime,
			Date edTime) {
		String whereSql = Idu.sqlString(
				"{0}=? AND {1}=? AND {2} BETWEEN ? AND ?",
				WaActVoteRecord.T.ACT, WaActVoteRecord.T.WX_USER,
				WaActVoteRecord.T.VOTE_TIME);
		return countWhere(whereSql, vote.getPkey(), user.getPkey(), stTime,
				edTime);
	}

	public static int countByIp(WaActVote vote, String ip, Date stTime,
			Date edTime) {
		String whereSql = Idu.sqlString(
				"{0}=? AND {1}=? AND {2} BETWEEN ? AND ?",
				WaActVoteRecord.T.ACT, WaActVoteRecord.T.IP,
				WaActVoteRecord.T.VOTE_TIME);
		return countWhere(whereSql, vote.getPkey(), ip, stTime, edTime);
	}

	public static String vote(String openId, String accountId, int number, String createTime) {
		String err = null;
		WxAccount account = WxAccount.loadUniqueAccountId(false, accountId);
		List<WaActVote> votes = WaActVote.list(WaActVote.class, Idu.sqlString("{0}=? AND {1}=?", WaActVote.T.ACCOUNT, WaActVote.T.STATUS),false, account.getPkey(), OActVoteStatus.PUBLISH.getLine().getKey());
		if (votes.size() == 0) {
			return WmTextDAO.transform2XML("当前没有可以投票的活动！", accountId, openId, createTime);
		}
		WaActVote vote = votes.get(0);
		if (!chkTimeAndStatus(vote))
			return WmTextDAO.transform2XML("当前没有可以投票的活动！", accountId, openId, createTime);

		WxUser wxUser = WxUser.chkUniqueOpenIdAccount(false, openId,account.getPkey());
		if (wxUser == null) {
			return null;
		}
		System.out.println("vote.getpkey():"+vote.getPkey());
		System.out.println("wxUser.getPkey():"+wxUser.getPkey());
		List<WaActVoteEntry> entrys = WaActVoteEntry.list(WaActVoteEntry.class, Idu.sqlString("{0}=? AND {1}=?", WaActVoteEntry.T.VOTE, WaActVoteEntry.T.NUMBER), false, vote.getPkey(), number);
		if (entrys.size() == 0) {
			return WmTextDAO.transform2XML("没有编号为" + number + "的报名记录", accountId, openId, createTime);
		}
		WaActVoteEntry entry = entrys.get(0);
		
		err = chkActConf(vote, wxUser);
		if (err != null)
			return WmTextDAO.transform2XML(err, accountId, openId, createTime);

		// 投票量+1
		entry.setVoteCount(entry.getVoteCount() + 1);
		entry.upd();

		// 进行数据更改
		WaActVoteRecord record = new WaActVoteRecord();
		record.stAct(vote);
		record.stEntryRecord(entry);
		record.stWxUser(wxUser);
		record.setVoteTime(Env.getSystemTime());
		record.stAccount(account);
		record.ins();
		String webPath = ServletActionContext.getServletContext().getInitParameter("webPath");
		String title = "你已成功为“"+entry.getNamePerson()+"”，"+entry.getNumber()+"号选手投票，快转发帮他拉票吧！";
		List<WaActVotePhoto> listFoto = BeanBase.list(WaActVotePhoto.class, WaActVotePhoto.T.VOTE_ENTRY.getFld().getCodeSqlField() + "=? ORDER BY "+WaActVotePhoto.T.SORT.getFld().getCodeSqlField(), false, entry.getPkey());
		String picUrl =  webPath + DepConstants.FILE_SEPA +listFoto.get(0).getPhotoUrl();
		String url = webPath + DepConstants.FILE_SEPA + "expand_wa_WaActVoteEntry_showInfo?pkey="+entry.getPkey();
		WmNewsDAO newsDao = new WmNewsDAO();
		return newsDao.transform2XML(title, "", picUrl, url, accountId, openId, createTime);
	}

	/**
	 * 校验活动起止时间和报名起止时间
	 */
	public static boolean chkTimeAndStatus(WaActVote vote) {
		OActVoteStatus status = vote.gtStatus();
		Date now = Env.getSystemTime();
		if (status == Wa.OActVoteStatus.PUBLISH) {
			// 判断活动起止时间
			if (now.after(vote.getActEndTime())) {
				vote.stStatus(OActVoteStatus.ACTFIN);
				vote.upd();
				return false;
			}
			if (now.before(vote.getActStartTime()))
				return false;
			// 判断报名时间
			if (now.before(vote.getEntryStartTime()))
				return false;
			if (now.after(vote.getEntryEndTime())) {
				vote.stStatus(OActVoteStatus.ENTRYFIN);
				vote.upd();
				return false;
			}
		} else if (status == Wa.OActVoteStatus.ENTRYFIN) {
			if (now.after(vote.getActEndTime())) {
				vote.stStatus(OActVoteStatus.ACTFIN);
				vote.upd();
				return false;
			}
			return false;
		} else if (status == Wa.OActVoteStatus.ACTFIN) {
			return false;
		}
		return true;
	}

	/**
	 * 验证通用参数配置（周期参与数异常）
	 * 
	 * @param config
	 *            活动对应的通用参数设置
	 * @param wxUser
	 *            根据openID获取的WxUser的PKey值
	 */
	private static String chkActConf(WaActVote vote, WxUser wxUser) {
		WaActConfig config = vote.gtActConfig();
		// 活动周期天数
		int days = WaActVoteConfigDAO.getDays(config);
		// 当前时间
		Date now = Env.getSystemTime();
		// 开始时间
		Calendar calendar = Calendar.getInstance();
		calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH) - days + 1, 0, 0, 0);
		Date stTime = calendar.getTime();
		// 周期投票数异常
		if (WaActVoteRecordDAO.countByWxUser(vote, wxUser, stTime, now) >= config
				.getCycleLimit())
			return config.getCycleLimitWords();
		return null;
	}
}
