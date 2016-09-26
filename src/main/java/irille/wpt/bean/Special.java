package irille.wpt.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;


/**
 * The persistent class for the wpt_special database table.
 * 
 */
@Entity
@Table(name="wpt_special")
@NamedQuery(name="Special.findAll", query="SELECT s FROM Special s")
public class Special implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@Column(name="base_img_url")
	private String baseImgUrl;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="city")
	private City city;

	@Column(name="ignore_city")
	private byte ignoreCity;

	private String intro;
	
	@Column(name="row_version")
	private Short rowVersion = 1;

	private Integer sort;

	private String title;

	@Column(name="top_img_url")
	private String topImgUrl;

	public Special() {
	}

	public Integer getPkey() {
		return this.pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public Integer getAccount() {
		return this.account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public String getBaseImgUrl() {
		return this.baseImgUrl;
	}

	public void setBaseImgUrl(String baseImgUrl) {
		this.baseImgUrl = baseImgUrl;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public byte getIgnoreCity() {
		return this.ignoreCity;
	}

	public void setIgnoreCity(byte ignoreCity) {
		this.ignoreCity = ignoreCity;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTopImgUrl() {
		return this.topImgUrl;
	}

	public void setTopImgUrl(String topImgUrl) {
		this.topImgUrl = topImgUrl;
	}

}