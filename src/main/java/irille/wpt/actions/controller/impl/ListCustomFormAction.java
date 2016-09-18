package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.CustomForm;
import irille.wpt.bean.Member;
import irille.wpt.service.impl.CustomFormService;
@Controller
@Scope("prototype")
public class ListCustomFormAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;

	private List<CustomForm> customForms;
	
	@Resource
	private CustomFormService customFormService;

	/**
	 * 私人订制表单列表
	 */
	@Override
	public String execute() throws Exception {
		Member member = chkMember();
		customForms = customFormService.list(member.getPkey());
		setResult("me/customFormList.jsp");
		return TRENDS;
	}

	public List<CustomForm> getCustomForms() {
		return customForms;
	}

	public void setCustomForms(List<CustomForm> customForms) {
		this.customForms = customForms;
	}

}
