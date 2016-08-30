package irille.wpt.actions.resource.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.IncludeProperties;
import org.json.JSONArray;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import irille.wpt.actions.resource.AbstractCRUDAction;
import irille.wpt.service.TopService;
import irille.wx.wx.WxUser;
@Controller
@Scope("prototype")
public class TopAction extends AbstractCRUDAction {

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
	private TopService topService;
	/**
	 * 收藏或取消收藏
	 */
	public void collect() {
		WxUser user = chkWxUser();
		topService.collectOrCancel(id, user.getPkey());
	}
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
	public String list() {
		beans = topService.search(cityId, areaId, banquetId, accountId);
		return "json";
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
