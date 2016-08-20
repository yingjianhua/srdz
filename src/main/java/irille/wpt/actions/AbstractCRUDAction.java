package irille.wpt.actions;

import javax.annotation.security.RolesAllowed;
/**
 * 用于对资源做增删改查
 * @author Yingjianhua
 *
 */
public abstract class AbstractCRUDAction extends AbstractWptAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RolesAllowed("admin")
	public void add() {
		
	}
}
