package irille.wpt.actions.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.idu.Idu;
import irille.wpt.actions.AbstractControllAction;
import irille.wpt.actions.IMenuShareAppMessage;
import irille.wpt.actions.IMenuShareTimeline;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptComboBanner;
import irille.wx.wpt.WptComboLine;
@Controller
@Scope("prototype")
public class ShowComboAction extends AbstractControllAction implements IMenuShareAppMessage, IMenuShareTimeline{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -513008521098240625L;

	private Integer id;
	private WptCombo combo;
	private List<WptComboLine> comboLines;
	private List<WptComboBanner> banners;
	private BigDecimal origPrice = BigDecimal.ZERO;
	/**
	 * 显示套餐详情
	 */
	@Override
	public String execute() throws Exception {
		String where = Idu.sqlString("{0}=? order by {1}", WptComboLine.T.COMBO, WptComboLine.T.SORT);
		comboLines = WptComboLine.list(WptComboLine.class, where, false, getCombo().getPkey());
		where = Idu.sqlString("{0}=? order by {1}", WptComboBanner.T.COMBO, WptComboBanner.T.SORT);
		banners = WptComboBanner.list(WptComboBanner.class, where, false, id);
		if(getCombo().getOrigPrice().intValue() == 0) {
			for(WptComboLine line:comboLines) {
				origPrice = origPrice.add(line.getPrice());
			}
		} else {
			origPrice = getCombo().getOrigPrice();
		}
		setResult("pt/comboDetail.jsp");
		return TRENDS;
	}
	
	@Override
	public String getShareTimelineTitle() {
		return getCombo().getName();
	}
	@Override
	public String getShareTimelineLink() {
		return getRequestUrl();
	}
	@Override
	public String getShareTimelineImgUrl() {
		return getDomain()+"/"+getCombo().getImgUrl();
	}
	@Override
	public String getShareAppMessageTitle() {
		return getCombo().getName();
	}
	@Override
	public String getShareAppMessageDesc() {
		return getCombo().getDes();
	}
	@Override
	public String getShareAppMessageLink() {
		return getRequestUrl();
	}
	@Override
	public String getShareAppMessageImgUrl() {
		return getDomain()+"/"+getCombo().getImgUrl();
	}
	@Override
	public String getShareAppMessageType() {
		return "link";
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public WptCombo getCombo() {
		if(combo == null)
			combo = WptCombo.load(WptCombo.class, id);
		return combo;
	}
	public void setCombo(WptCombo combo) {
		this.combo = combo;
	}
	public List<WptComboLine> getComboLines() {
		return comboLines;
	}
	public void setComboLines(List<WptComboLine> comboLines) {
		this.comboLines = comboLines;
	}
	public List<WptComboBanner> getBanners() {
		return banners;
	}
	public void setBanners(List<WptComboBanner> banners) {
		this.banners = banners;
	}
	public BigDecimal getOrigPrice() {
		return origPrice;
	}
	public void setOrigPrice(BigDecimal origPrice) {
		this.origPrice = origPrice;
	}
}
