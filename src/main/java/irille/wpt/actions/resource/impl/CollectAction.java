package irille.wpt.actions.resource.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Collect;
import irille.wpt.service.impl.CollectService;
import irille.wx.wx.WxUser;
@Controller
@Scope("prototype")
public class CollectAction extends AbstractCRUDAction<Collect> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@Resource
	private CollectService collectService;
	
	/**
	 * 收藏或取消收藏
	 */
	public void collect() {
		WxUser user = chkWxUser();
		collectService.collectOrCancel(id, user);
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
