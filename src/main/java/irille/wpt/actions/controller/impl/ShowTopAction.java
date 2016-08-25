package irille.wpt.actions.controller.impl;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wx.wpt.WptCollect;
import irille.wx.wpt.WptTop;
@Controller
@Scope("prototype")
public class ShowTopAction extends AbstractControllAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5934588705020079409L;

	private Integer id;
	private WptTop top;
	private boolean isCollect;

	/**
	 * 头条详情
	 */
	@Override
	public String execute() throws Exception {
		top = WptTop.load(WptTop.class, id);
		isCollect = WptCollect.chkUniqueWxUserTop(false, chkWxUser().getPkey(), id) == null ? false : true;
		setResult("front/topDetail.jsp");
		return TRENDS;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public WptTop getTop() {
		return top;
	}
	public void setTop(WptTop top) {
		this.top = top;
	}
	public boolean isCollect() {
		return isCollect;
	}
	public void setCollect(boolean isCollect) {
		this.isCollect = isCollect;
	}
}
