package irille.action.wpt;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import irille.action.ActionWx;
import irille.pub.idu.Idu;
import irille.wx.wpt.WptServiceCen;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxAccountDAO;

public class WptServiceCenAction extends ActionWx<WptServiceCen, WptServiceCenAction> {
	private WptServiceCen _serviceCen;

	public WptServiceCen getServiceCen() {
		return _serviceCen;
	}

	public void setServiceCen(WptServiceCen serviceCen) {
		_serviceCen = serviceCen;
	}

	public WptServiceCen getBean() {
		return _bean;
	}

	public void setBean(WptServiceCen bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptServiceCen.class;
	}

	/**
	 * 客服中心设置
	 */
	public void insOrUpd() throws IOException, JSONException {
		if (getBean().getPkey() == null) {
			WxAccount account = WxAccountDAO.getByUser(Idu.getUser());
			WptServiceCen serviceCen = new WptServiceCen();
			serviceCen.setAccount(account.getPkey());
			serviceCen.setMobile(getBean().getMobile());
			serviceCen.setPkey(account.getPkey());
			serviceCen.setQrcode(getBean().getQrcode());
			serviceCen.setSmsTips(getBean().getSmsTips());
			serviceCen.ins();
		} else {
			WptServiceCen serviceCen = WptServiceCen.load(WptServiceCen.class, getBean().getPkey());
			serviceCen.setMobile(getBean().getMobile());
			serviceCen.setQrcode(getBean().getQrcode());
			serviceCen.setSmsTips(getBean().getSmsTips());
			serviceCen.upd();
		}
		ServletActionContext.getResponse().getWriter().println(new JSONObject().put(SUCCESS, true));
	}
}
