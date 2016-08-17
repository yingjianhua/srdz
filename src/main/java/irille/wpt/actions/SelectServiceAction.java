package irille.wpt.actions;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.pub.bean.BeanBase;
import irille.wx.wpt.WptService;
@Controller
@Scope("prototype")
public class SelectServiceAction extends AbstractWptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3449736950840931142L;
	private List<WptService> services;
	private Integer comboId;
	private Integer banquetId;
	private String pnum;
	private String budget;
	private Integer areaId;
	
	@Override
	public String execute() throws Exception {
		services = BeanBase.list(WptService.class, WptService.T.ACCOUNT+"=?", false, getAccount().getPkey());
		setResult("pt/selectService.jsp");
		return TRENDS;
	}

	public List<WptService> getServices() {
		return services;
	}
	public void setServices(List<WptService> services) {
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
