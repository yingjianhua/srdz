package irille.action.wpt;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import irille.action.ActionWx;
import irille.pub.idu.Idu;
import irille.pub.svr.Env;
import irille.wx.wpt.WptFeedBack;

public class WptFeedBackAction extends ActionWx<WptFeedBack,WptFeedBackAction> {
	private WptFeedBack _feedBack;

	public WptFeedBack getFeedBack() {
		return _feedBack;
	}

	public void setFeedBack(WptFeedBack feedBack) {
		_feedBack = feedBack;
	}

	public WptFeedBack getBean() {
		return _bean;
	}

	public void setBean(WptFeedBack bean) {
		this._bean = bean;
	}

	@Override
	public Class beanClazz() {
		return WptFeedBack.class;
	}

	/**
	 * 反馈处理
	 * @throws JSONException 
	 */
	public void toDo() throws IOException, JSONException{
		System.out.println("pkey=" + getPkeys());
		for(String line : getPkeys().split(",")) {
			WptFeedBack feedBack = WptFeedBack.load(WptFeedBack.class, line);
			if(!feedBack.gtIsHandle()) {
				feedBack.setHandleMan(Idu.getUser().getPkey());
				feedBack.setHandleTime(Env.getSystemTime());
				feedBack.setIsHandle((byte)1);
				feedBack.upd();
			}
		}
		ServletActionContext.getResponse().getWriter().println(new JSONObject().put(SUCCESS, true));
	}
}
