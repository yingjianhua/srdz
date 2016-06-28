package irille.wpt.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import org.json.JSONObject;

import irille.wx.wx.WxUser;

public class UserAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3883419323449309464L;

	public void cashDetail() {
		WxUser user = chkWxUser();
		BigDecimal commission = user.getCashableCommission();
		boolean isMember = user.gtIsMember();
		JSONObject json = new JSONObject();
		json.put("commission", commission);
		json.put("isMember", isMember);
		PrintWriter writer;
		try {
			writer = getResponse().getWriter();
			writer.print(json.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 佣金提现
	 */
	public void cash() {
		//TODO
	}
}
