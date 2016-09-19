package irille.wpt.actions.controller.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.CustomForm;
import irille.wpt.service.impl.CustomFormService;
@Controller
@Scope("prototype")
public class ShowCustomFormAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;

	private String formid;
	private CustomForm customForm;
	
	@Resource
	private CustomFormService customFormService;

	/**
	 * 私人订制表单列表
	 */
	@Override
	public String execute() throws Exception {
		customForm = customFormService.findByFormid(formid);
		setResult("me/customFormDetail.jsp");
		return TRENDS;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public CustomForm getCustomForm() {
		return customForm;
	}

	public void setCustomForm(CustomForm customForm) {
		this.customForm = customForm;
	}
	
}
