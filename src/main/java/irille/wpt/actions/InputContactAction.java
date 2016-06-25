package irille.wpt.actions;

public class InputContactAction extends AbstractWptAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1636918385373685964L;
	private Integer comboId;
	private Integer banquetId;
	private Double pnum;
	private Double perCapitaBudget;
	private Integer areaId;
	private String services;
	@Override
	public String execute() throws Exception {
		System.out.println("------------InputContactAction-------------");
		System.out.println("comboId:"+comboId);
		System.out.println("services:"+services);
		setResult("pt/inputContact.jsp");
		System.out.println("------------InputContactAction-------------");
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
		return perCapitaBudget;
	}
	public void setPerCapitaBudget(Double perCapitaBudget) {
		this.perCapitaBudget = perCapitaBudget;
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
