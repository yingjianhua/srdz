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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the wpt_custom_form database table.
 * 
 */
@Entity
@Table(name="wpt_custom_form")
@NamedQuery(name="CustomForm.findAll", query="SELECT c FROM CustomForm c")
public class CustomForm implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;
	
	private String formid;

	private String banquet;//宴会类型
	
	private String budget;//预算
	
	private String number;//人数

	@Temporal(TemporalType.TIMESTAMP)
	private Date time;//用餐时间
	
	@Column(name="custom_services")
	private String customServices;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="city")
	private City city;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cityline")
	private CityLine cityline;

	@Column(name="contact_man")
	private String contactMan;
	
	@Column(name="contact_sex")
	private Byte contactSex;
	
	@Column(name="contact_type")
	private Byte contactType;
	
	@Column(name="contact_way")
	private String contactWay;
	
	private String rem;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="member")
	private Member member;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	
	private Integer account;

	@Column(name="row_version")
	private Short rowVersion = 1;

	public CustomForm() {
	}

	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String formid) {
		this.formid = formid;
	}

	public String getBanquet() {
		return banquet;
	}

	public void setBanquet(String banquet) {
		this.banquet = banquet;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCustomServices() {
		return customServices;
	}

	public void setCustomServices(String customServices) {
		this.customServices = customServices;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public CityLine getCityline() {
		return cityline;
	}

	public void setCityline(CityLine cityline) {
		this.cityline = cityline;
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

	public Byte getContactSex() {
		return contactSex;
	}

	public void setContactSex(Byte contactSex) {
		this.contactSex = contactSex;
	}

	public Byte getContactType() {
		return contactType;
	}

	public void setContactType(Byte contactType) {
		this.contactType = contactType;
	}

	public String getContactWay() {
		return contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Short getRowVersion() {
		return rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

}