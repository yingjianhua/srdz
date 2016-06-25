package irille.action.wax;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;
import org.json.JSONObject;

import irille.action.ActionWx;
import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.wx.wax.WaxBnft;
import irille.wx.wax.WaxBnftDAO;
import irille.wx.wax.WaxLady;
import irille.wx.wx.Wx;
import irille.wx.wx.WxUser;
import irille.wxpub.util.WeixinUtil;

public class WaxBnftAction extends ActionWx<WaxBnft,WaxBnftAction> {
	public static final Log LOG = new Log(WaxBnftAction.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		unsubErr("请先关注公众号！")
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	} //@formatter:on
	private WaxBnft _bnft;
	public WaxBnft getBean() {
		return _bean;
	}

	public void setBean(WaxBnft bean) {
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
		return WaxBnft.class;
	}
	public void close() throws Exception {
		WaxBnftDAO.close bnft = new WaxBnftDAO.close();
		bnft.setBKey(getPkey());
		bnft.commit();
		writeSuccess(bnft.getB());
	}
//	@Override
//	public void showBefore() {
//		super.showBefore();
//		setBnft(WaBnft.chk(WaBnft.class, getPkey()));
//		if (!getSession().containsKey("openid")) {
//			JSONObject param = new JSONObject();
//			try {
//				param.put("url", "expand_wa_WaBnft_show?pkey=" + getPkey());
//				param.put("sync", true);
//				setResult(WeixinUtil.authorizeCode(getBnft().gtAccount(), param));
//			} catch (JSONException e) {
//				e.printStackTrace();
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//			setRtnStr(RTRENDS);
//		}
//	}
//
//	@Override
//	public void showRun() {
//		super.showRun();
//		WxUser user = (WxUser) getSession().get("wxUser");
//		if (user.gtStatus().equals(Wx.OStatus.NOFOLLOW))
//			throw LOG.err(Msgs.unsubErr);
//		setResult("/wa/bnft/bnftEntry.jsp");
//		setRtnStr(TRENDS);
//	}
}
