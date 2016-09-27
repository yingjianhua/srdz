package irille.wpt.actions.resource.impl;

import java.io.IOException;

import javax.annotation.security.PermitAll;

import org.json.JSONException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Feedback;
@Controller
@Scope("prototype")
public class FeedbackAction extends AbstractCRUDAction<Feedback> {
	private static final long serialVersionUID = 1L;
	
	private String content;
	private String contactWay;
	private Byte contactType;
	
	/**
	 * 把反馈插入到数据库
	 */
	@PermitAll
	public String suggest(){
		feedbackService.suggest(content, contactType, contactWay, getAccount().getPkey());
		return BEAN;
	}
	
	/**
	 * 反馈处理
	 */
	public String toDo() throws IOException, JSONException{
		bean = feedbackService.toDo(bean);
		return BEAN;
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
