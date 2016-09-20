package irille.wpt.actions.controller.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.controller.AbstractControllAction;
import irille.wpt.bean.CustomService;
import irille.wpt.service.impl.CustomServiceService;
@Controller
@Scope("prototype")
public class SelectServiceAction extends AbstractControllAction {
	private static final long serialVersionUID = 1L;
	
	private List<CustomService> services;
	private Integer comboId;
	private Integer banquetId;
	private String pnum;
	private String budget;
	private Integer areaId;
	
	@Resource
	private CustomServiceService customServiceService;
	
	@Override
	public String execute() throws Exception {
		services = customServiceService.listByAccount(getAccount().getPkey());
		setResult("pt/selectService.jsp");
		return TRENDS;
	}

	public List<CustomService> getServices() {
		return services;
	}

	public void setServices(List<CustomService> services) {
		this.services = services;
	}

	public Integer getComboId() {
		return comboId;
	}
	public void setComboId(Integer comboId) {
		this.comboId = comboId;
	}
	public Integer getBanquetId() {
		return banquetId;
	}
	public void setBanquetId(Integer banquetId) {
		this.banquetId = banquetId;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
}
