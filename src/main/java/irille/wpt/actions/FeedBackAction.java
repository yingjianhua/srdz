package irille.wpt.actions;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;

import irille.wpt.service.FeedBackService;
@Controller
public class FeedBackAction extends AbstractWptAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -404067309809885214L;
	private String content;
	private String contactWay;
	private Byte contactType;
	@Resource
	private FeedBackService feedBackService;
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
		feedBackService.suggest(content, contactType, contactWay, getAccount().getPkey());
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
}
