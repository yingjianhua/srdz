package irille.wpt.actions;

import java.math.BigDecimal;
import java.util.List;

import irille.pub.idu.Idu;
import irille.wx.wpt.WptCombo;
import irille.wx.wpt.WptComboBanner;
import irille.wx.wpt.WptComboLine;

public class ShowComboAction extends AbstractWptAction {
	
	
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
		combo = WptCombo.load(WptCombo.class, id);
		String where = Idu.sqlString("{0}=? order by {1}", WptComboLine.T.COMBO, WptComboLine.T.SORT);
		comboLines = WptComboLine.list(WptComboLine.class, where, false, combo.getPkey());
		where = Idu.sqlString("{0}=? order by {1}", WptComboBanner.T.COMBO, WptComboBanner.T.SORT);
		banners = WptComboBanner.list(WptComboBanner.class, where, false, id);
		if(combo.getOrigPrice().intValue() == 0) {
			for(WptComboLine line:comboLines) {
				origPrice = origPrice.add(line.getPrice());
			}
		} else {
			origPrice = combo.getOrigPrice();
		}
		setResult("pt/comboDetail.jsp");
		return TRENDS;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public WptCombo getCombo() {
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
