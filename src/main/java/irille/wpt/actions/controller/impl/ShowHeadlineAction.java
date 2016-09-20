package irille.wpt.actions.controller.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.Headline;
import irille.wpt.service.impl.CollectService;
import irille.wpt.service.impl.HeadlineService;
@Controller
@Scope("prototype")
public class ShowHeadlineAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private Headline headline;
	private boolean isCollect;
	
	@Resource
	private CollectService collectService;
	@Resource
	private HeadlineService headlineService;

	/**
	 * 头条详情
	 */
	@Override
	public String execute() throws Exception {
		headline = headlineService.get(id);
		isCollect = collectService.findByHeadlineMember(id, chkMember().getPkey()) == null ? false : true;
		setResult("headline/headlineDetail.jsp");
		return TRENDS;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Headline getHeadline() {
		return headline;
	}

	public void setHeadline(Headline headline) {
		this.headline = headline;
	}

	public boolean isCollect() {
		return isCollect;
	}
	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}
}
