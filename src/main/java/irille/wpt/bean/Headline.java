package irille.wpt.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;


/**
 * The persistent class for the wpt_top database table.
 * 
 */
@Entity
@Table(name="wpt_top")
@NamedQuery(name="Headline.findAll", query="SELECT h FROM Headline h")
public class Headline implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	private Integer banquet;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="city")
	private City city;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cityline")
	private CityLine cityline;

	@Lob
	private String content;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="img_url")
	private String imgUrl;

	@Column(name="row_version")
	private Short rowVersion;

	private Integer sort;

	private String title;

	private Byte top;

	private String url;

	public Headline() {
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

	public Integer getBanquet() {
		return this.banquet;
	}

	public void setBanquet(Integer banquet) {
		this.banquet = banquet;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public CityLine getCityline() {
		return this.cityline;
	}

	public void setCityline(CityLine cityline) {
		this.cityline = cityline;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@JSON(format="yyyy-MM-dd")
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public Byte getTop() {
		return this.top;
	}

	public void setTop(Byte top) {
		this.top = top;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}