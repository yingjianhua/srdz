package irille.wpt.actions.controller;

import javax.annotation.Resource;

import irille.wpt.actions.AbstractWptAction;
import irille.wpt.tools.WxJsCreater;
/**
 * 用于展现微信端的界面
 * @author Yingjianhua
 *
 */
public abstract class AbstractControllAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String jsCode;
	@Resource(name="wxJsCreater")
	private WxJsCreater jsCreater;
	
	public String getJsCode() {
		return jsCode;
	}
	public void setJsCode(String jsCode) {
		this.jsCode = jsCode;
	}
	public WxJsCreater getJsCreater() {
		return jsCreater;
	}
	public void setJsCreater(WxJsCreater jsCreater) {
		this.jsCreater = jsCreater;
	}
	public void addExtraWxJsCode()  {
	}
}
