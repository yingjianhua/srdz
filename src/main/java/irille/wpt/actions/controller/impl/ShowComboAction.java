package irille.wpt.actions.controller.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.actions.controller.IMenuShareAppMessage;
import irille.wpt.actions.controller.IMenuShareTimeline;
import irille.wpt.bean.Combo;
import irille.wpt.bean.ComboLine;
import irille.wpt.service.impl.ComboService;
@Controller
@Scope("prototype")
public class ShowComboAction extends AbstractControllAction implements IMenuShareAppMessage, IMenuShareTimeline{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Combo combo;
	private BigDecimal origPrice = BigDecimal.ZERO;
	
	@Resource
	private ComboService comboService;
	/**
	 * 显示套餐详情
	 */
	@Override
	public String execute() throws Exception {
		combo = comboService.get(id);
		if(getCombo().getOrigPrice().intValue() == 0) {
			for(ComboLine line:combo.getComboLines()) {
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
	
	public Combo getCombo() {
		if(combo == null)
			combo = comboService.get(id);
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public BigDecimal getOrigPrice() {
		return origPrice;
	}
	public void setOrigPrice(BigDecimal origPrice) {
		this.origPrice = origPrice;
	}
}
