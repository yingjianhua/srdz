package irille.wpt.actions.resource.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Special;
import irille.wpt.bean.SpecialLine;
import irille.wpt.service.impl.SpecialService;
@Controller
@Scope("prototype")
public class SpecialAction extends AbstractCRUDAction<Special> {
	private static final long serialVersionUID = 1L;
	
	private List<SpecialLine> listLine;
	
	@Resource
	private SpecialService specialService;

	@Override
	public String ins() {
		System.out.println(bean);
		System.out.println(listLine.size());
		specialService.save(bean, listLine, account.getPkey());
		return BEAN;
	}
	
	@Override
	public String upd() {
		System.out.println(bean);
		System.out.println(listLine.size());
		specialService.update(bean, listLine, account.getPkey());
		return BEAN;
	}
	
	@Override
	public String del() {
		specialService.delete(bean);
		return BEAN;
	}

	public List<SpecialLine> getListLine() {
		return listLine;
	}

	public void setListLine(List<SpecialLine> listLine) {
		this.listLine = listLine;
	}
	
}
