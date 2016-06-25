package irille.wx.wa;

import java.util.List;

import irille.pub.Log;
import irille.pub.PropertyUtils;
import irille.pub.idu.IduDel;
import irille.pub.idu.IduIns;
import irille.pub.idu.IduUpd;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WaActVoteConfigDAO {
	public static final Log LOG = new Log(WaActVoteConfig.class);

	public static class Ins extends IduIns<Ins, WaActVoteConfig> {
		@Override
		public void before() {
			super.before();
			WxAccount account = WxAccountDAO.getByUser(getUser());
			getB().setAccount(account.getPkey());
		}
	}

	public static class Upd extends IduUpd<Upd, WaActVoteConfig> {
		@Override
		public void before() {
			super.before();
			WaActVoteConfig model = load(getB().getPkey());
			PropertyUtils.copyPropertiesWithout(model, getB(), WaActVoteConfig.T.ACCOUNT);
			setB(model);
		}
	}

	public static class Del extends IduDel<Del, WaActVoteConfig> {
		@Override
		public void before() {
			super.before();
			haveBeUsed(WaActVote.class, WaActVote.T.VOTE_Config.getFld().getCodeSqlField(), getB().getPkey());
		}
	}
	/**
	 * 获取周期对应的天数（根据参数设置的天/周，将周期转换成天）
	 * 
	 * @param config
	 *            配置信息
	 * @return 配置的周期天数
	 */
	public static int getDays(WaActConfig config) {
		if (config.gtUnit() == Wa.OActUnit.WEEK) {// 如果单位是周
			return config.getCycle() * 7;
		}
		return config.getCycle();
	}
}
