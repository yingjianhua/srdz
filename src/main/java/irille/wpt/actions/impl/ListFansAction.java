package irille.wpt.actions.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.AbstractControllAction;
import irille.wpt.service.UserService;
@Controller
@Scope("prototype")
public class ListFansAction extends AbstractControllAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8752277328802692183L;
	private int fans1Num;
	private int fans2Num;
	@Resource
	private UserService userService;
	@Override
	public String execute() throws Exception {
		Integer userid = chkWxUser().getPkey();
		fans1Num = userService.getFans1Num(userid);
		fans2Num = userService.getFans2Num(userid);
		setResult("me/fansList.jsp");
		return TRENDS;
	}
	
	public int getFans1Num() {
		return fans1Num;
	}
	public void setFans1Num(int fans1Num) {
		this.fans1Num = fans1Num;
	}
	public int getFans2Num() {
		return fans2Num;
	}
	public void setFans2Num(int fans2Num) {
		this.fans2Num = fans2Num;
	}
}
