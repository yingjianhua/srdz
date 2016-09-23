package irille.wpt.actions.resource.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Hot;
import irille.wpt.service.impl.HotService;
@Controller
@Scope("prototype")
public class HotAction extends AbstractCRUDAction<Hot> {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private HotService hotService;

	@Override
	public String ins() {
		hotService.save(bean);
		return BEAN;
	}
	
	@Override
	public String upd() {
		hotService.update(bean);
		return BEAN;
	}
	
}
