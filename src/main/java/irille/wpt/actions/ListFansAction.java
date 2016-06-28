package irille.wpt.actions;

import irille.wpt.service.UserService;

public class ListFansAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8752277328802692183L;
	private int fans1Num;
	private int fans2Num;
	private int fans3Num;
	private UserService service;
	@Override
	public String execute() throws Exception {
		Integer userid = chkWxUser().getPkey();
		fans1Num = service.getFans1Num(userid);
		fans2Num = service.getFans2Num(userid);
		fans3Num = service.getFans3Num(userid);
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
	public int getFans3Num() {
		return fans3Num;
	}
	public void setFans3Num(int fans3Num) {
		this.fans3Num = fans3Num;
	}
	public UserService getService() {
		return service;
	}
	public void setService(UserService service) {
		this.service = service;
	}
}
