package irille.wpt.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;

import irille.pub.Log;
import irille.pub.PubInfs.IMsg;
import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.actions.controller.IMenuShareAppMessage;
import irille.wpt.actions.controller.IMenuShareTimeline;
import irille.wpt.tools.WxJsCreater;
import irille.wx.wx.WxAccount;
import irille.wx.wx.WxUser;
import irille.wxpub.js.JMHideMenuItems;
import irille.wxpub.js.JMOnMenuShareAppMessage;
import irille.wxpub.js.JMOnMenuShareTimeline;

public class JsCodeInterceptor extends MyAbstractInterceptor{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1937669532396607004L;
	private static final Log LOG = new Log(JsCodeInterceptor.class);
	public enum Msgs implements IMsg {// 信息定义的类名必须为Msgs, 以便系统能检索 @formatter:off
		AccountNotExist("没有该公众号"),
		noAccountPkey("缺少订阅号参数"),
		;
		private String _msg;
		private Msgs(String msg) { _msg=msg; }
		public String getMsg() {return _msg; }
	}
	
	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		Object action = actionInvocation.getAction();
		if(action instanceof AbstractControllAction) {
			AbstractControllAction wptAction = ((AbstractControllAction)action);
			WxAccount account = null;
			HttpServletRequest request = null;
			ActionContext actionContext = actionInvocation.getInvocationContext();
			Map<String, Object> map = actionContext.getParameters();
			String[] accountPkeys = (String[])map.get("account.pkey");
			if(accountPkeys != null && accountPkeys.length > 0) {
				account = WxAccount.get(WxAccount.class, Integer.parseInt(accountPkeys[0]));
				if(account == null) {
					throw LOG.err(Msgs.AccountNotExist);
				}
			} else {
				throw LOG.err(Msgs.noAccountPkey);
			}
			request = (HttpServletRequest)actionContext.get(ServletActionContext.HTTP_REQUEST);
			wptAction.addExtraWxJsCode();
			WxJsCreater jsCreater = wptAction.getJsCreater();
			WxUser user = wptAction.chkWxUser();
			JMOnMenuShareAppMessage msam = getFactory().getBean(JMOnMenuShareAppMessage.class);
			if(action instanceof IMenuShareAppMessage) {
				IMenuShareAppMessage imsam = (IMenuShareAppMessage)action;
				msam.setTitle(imsam.getShareAppMessageTitle());
				msam.setDesc(imsam.getShareAppMessageDesc());
				msam.setImgUrl(imsam.getShareAppMessageImgUrl());
				msam.setLink(imsam.getShareAppMessageLink()+"&invitedId="+user.getPkey());
				msam.setType(imsam.getShareAppMessageType());
			} else {
				//分享给朋友 ，带上account.pkey和openid两个参数
				msam.setLink(msam.getLink().getV().toString()+"?account.pkey="+account.getPkey()+"&invitedId="+user.getPkey());
			}
			//分享到朋友圈，
			JMOnMenuShareTimeline mstl = getFactory().getBean(JMOnMenuShareTimeline.class);
			if(action instanceof IMenuShareTimeline) {
				IMenuShareTimeline imstl = (IMenuShareTimeline)action;
				mstl.setTitle(imstl.getShareTimelineTitle());
				mstl.setImgUrl(imstl.getShareTimelineImgUrl());
				mstl.setLink(imstl.getShareTimelineLink()+"&invitedId="+user.getPkey());
			} else {
				mstl.setLink(mstl.getLink().getV().toString()+"?account.pkey="+account.getPkey()+"&invitedId="+user.getPkey());
			}
			//隐藏菜单功能 
			JMHideMenuItems hmi = getFactory().getBean(JMHideMenuItems.class);
			
			jsCreater.add(msam);
			jsCreater.add(mstl);
			jsCreater.add(hmi);
			
			wptAction.setJsCode(jsCreater.crtJs(account, request));
		}
		String rtn = actionInvocation.invoke();
		return rtn;
	}
	
}