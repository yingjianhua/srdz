package irille.wpt.actions;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.service.UserService;
import irille.wx.wx.WxUser;
@Controller
@Scope("prototype")
public class ListFansSaleAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6909649638056942663L;
	private BigDecimal historyCommission;
	private BigDecimal saleAmount;
	@Resource
	private UserService userService;
	
	@Override
	public String execute() throws Exception {
		WxUser user = chkWxUser();
		historyCommission = user.getHistoryCommission();
		saleAmount = userService.getFansSaleAmount(user.getPkey());
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
}
