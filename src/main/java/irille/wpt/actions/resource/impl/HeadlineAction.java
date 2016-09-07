package irille.wpt.actions.resource.impl;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;

import org.apache.struts2.json.annotations.IncludeProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.bean.Headline;
import irille.wpt.service.impl.HeadlineService;
@Controller
@Scope("prototype")
public class HeadlineAction extends AbstractCRUDAction<Headline> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3731521594935712289L;
	
	private Integer id;
	private Integer cityId;
	private Integer areaId;
	private Integer banquetId;
	private Integer accountId;
	@Resource
	private HeadlineService headlineService;

	/**
	 * 筛选
	 */
	@IncludeProperties({
		"\\[\\d+\\]\\.pkey",
		"\\[\\d+\\]\\.url",
		"\\[\\d+\\]\\.imgUrl",
		"\\[\\d+\\]\\.title",
		"\\[\\d+\\]\\.date"
	})
	@PermitAll
	public String search() {
		beans = headlineService.search(cityId, areaId, banquetId, accountId);
		return BEANS;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Integer getBanquetId() {
		return banquetId;
	}
	public void setBanquetId(Integer banquetId) {
		this.banquetId = banquetId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}
