package irille.wpt.actions;

import java.io.IOException;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;

import irille.wpt.service.FeedBackService;

public class FeedBackAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -404067309809885214L;
	private String content;
	private String contactWay;
	private Byte contactType;
	
	private FeedBackService service;
	/**
	 * 意见反馈
	 */
	@Override
	public String execute() throws Exception {
		setResult("me/feedBack.jsp");
		return TRENDS;
	}
	/**
	 * 把反馈插入到数据库
	 */
	public void suggest(){
		service.suggest(content, contactType, contactWay, getAccount().getPkey());
		try {
			ServletActionContext.getResponse().getWriter().print(new JSONObject().put(SUCCESS, true));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContactWay() {
		return contactWay;
	}
	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}
	public Byte getContactType() {
		return contactType;
	}
	public void setContactType(Byte contactType) {
		this.contactType = contactType;
	}
	public FeedBackService getService() {
		return service;
	}
	public void setService(FeedBackService service) {
		this.service = service;
	}
}
