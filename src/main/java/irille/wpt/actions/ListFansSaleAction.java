package irille.wpt.actions;

import java.math.BigDecimal;

import irille.wpt.service.UserService;
import irille.wx.wx.WxUser;

public class ListFansSaleAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6909649638056942663L;
	private BigDecimal historyCommission;
	private BigDecimal saleAmount;
	private UserService service;
	
	@Override
	public String execute() throws Exception {
		WxUser user = chkWxUser();
		historyCommission = user.getHistoryCommission();
		saleAmount = service.getFansSaleAmount(user.getPkey());
		setResult("me/fansSaleList.jsp");
		return TRENDS;
	}
	
	public BigDecimal getHistoryCommission() {
		return historyCommission;
	}
	public void setHistoryCommission(BigDecimal historyCommission) {
		this.historyCommission = historyCommission;
	}
	public BigDecimal getSaleAmount() {
		return saleAmount;
	}
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
}
