package irille.wpt.actions;

import org.springframework.stereotype.Controller;

import irille.pub.Log;
@Controller
public class InputContactAction extends AbstractWptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1636918385373685964L;
	private static final Log LOG = new Log(InputContactAction.class);
	private Integer comboId;
	private Integer banquetId;
	private Double pnum;
	private Double budget;
	private Integer areaId;
	private String services;
	@Override
	public String execute() throws Exception {
		LOG.info("--------------InputContactAction():start--------------");
		LOG.info("comboId:{0}", comboId);
		LOG.info("services:{0}", services);
		setResult("pt/inputContact.jsp");
		LOG.info("--------------InputContactAction():end--------------");
		return TRENDS;
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
	public Double getPnum() {
		return pnum;
	}
	public void setPnum(Double pnum) {
		this.pnum = pnum;
	}
	public Double getPerCapitaBudget() {
		return budget;
	}
	public void setPerCapitaBudget(Double budget) {
		this.budget = budget;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
}
