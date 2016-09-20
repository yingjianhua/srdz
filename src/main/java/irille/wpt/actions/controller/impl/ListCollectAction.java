package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.Collect;
import irille.wpt.service.impl.CollectService;
import irille.wpt.service.impl.HeadlineService;
@Controller
@Scope("prototype")
public class ListCollectAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private HeadlineService headlineService;
	@Resource
	private CollectService collectService;
	
	private List<Collect> collects;

	/**
	 * 我的收藏
	 */
	@Override
	public String execute() throws Exception {
		collects = collectService.listByMember(chkMember().getPkey());
		setResult("me/listCollect.jsp");
		return TRENDS;
	}

	public List<Collect> getCollects() {
		return collects;
	}

	public void setCollects(List<Collect> collects) {
		this.collects = collects;
	}

}
