package irille.wpt.actions.resource.impl;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Collect;
import irille.wpt.service.impl.CollectService;
@Controller
@Scope("prototype")
public class CollectAction extends AbstractCRUDAction<Collect> {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@Resource
	private CollectService collectService;
	
	/**
	 * 收藏或取消收藏
	 */
	@PermitAll
	public void collect() {
		collectService.collectOrCancel(id, chkMember());
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
