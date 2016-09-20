package irille.wpt.actions.controller.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.actions.controller.IMenuShareAppMessage;
import irille.wpt.actions.controller.IMenuShareTimeline;
import irille.wpt.bean.Special;
import irille.wpt.service.impl.SpecialService;
@Controller
@Scope("prototype")
public class ShowSpecialAction extends AbstractControllAction implements IMenuShareAppMessage, IMenuShareTimeline {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Special special;
	private static final String TITLE_PRE = "【享食光】• ";
	
	@Resource
	private SpecialService specialService;
	
	/**
	 * 发现详情页
	 */
	@Override
	public String execute() throws Exception {
		special = specialService.get(id);
		setResult("find/specialDetail.jsp");
		return TRENDS;
	}
	
	@Override
	public String getShareTimelineTitle() {
		return TITLE_PRE + getSpecial().getTitle();
	}
	@Override
	public String getShareTimelineLink() {
		return getRequestUrl();
	}
	@Override
	public String getShareTimelineImgUrl() {
		return getDomain()+"/"+getSpecial().getBaseImgUrl();
	}
	@Override
	public String getShareAppMessageTitle() {
		return TITLE_PRE + getSpecial().getTitle();
	}
	@Override
	public String getShareAppMessageDesc() {
		return getSpecial().getIntro();
	}
	@Override
	public String getShareAppMessageLink() {
		return getRequestUrl();
	}
	@Override
	public String getShareAppMessageImgUrl() {
		return getDomain()+"/"+getSpecial().getBaseImgUrl();
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
	public Special getSpecial() {
		if(special == null)
			special = specialService.get(id);
		return special;
	}
	public void setSpecial(Special special) {
		this.special = special;
	}
}
