package irille.wpt.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.struts2.json.annotations.JSON;


/**
 * The persistent class for the wpt_order database table.
 * 
 */
@Entity
@Table(name="wpt_order")
@NamedQuery(name="Order.findAll", query="SELECT w FROM Order w")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer pkey;
	
	private String orderid;
	
	private Byte status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;
	
	@Column(name="combo_name")
	private String comboName;
	
	private Integer member;
	
	private String number;
	
	private BigDecimal price;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date time;

	private String checkcode;

	@Column(name="contact_man")
	private String contactMan;

	@Column(name="contact_sex")
	private Byte contactSex;

	@Column(name="contact_type")
	private Byte contactType;

	@Column(name="contact_way")
	private String contactWay;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="city")
	private City city;
	
	private String rem;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;
	
	private Integer account;
	
	@Column(name="row_version")
	private Short rowVersion;
	
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="order")
	private Set<OrderCoupon> coupons;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="order")
	private Set<OrderDetail> details;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="order")
	private Set<OrderEventJournal> eventJournals;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="order")
	private Set<OrderPayJournal> payJournals;

	public Order() {
	}

	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public String getComboName() {
		return comboName;
	}

	public void setComboName(String comboName) {
		this.comboName = comboName;
	}

	public Integer getMember() {
		return member;
	}

	public void setMember(Integer member) {
		this.member = member;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@JSON(format="yyyy-MM-dd HH:mm")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
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

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getRem() {
		return rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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

	public Set<OrderCoupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(Set<OrderCoupon> coupons) {
		this.coupons = coupons;
	}

	public Set<OrderDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<OrderDetail> details) {
		this.details = details;
	}

	public Set<OrderEventJournal> getEventJournals() {
		return eventJournals;
	}

	public void setEventJournals(Set<OrderEventJournal> eventJournals) {
		this.eventJournals = eventJournals;
	}

	public Set<OrderPayJournal> getPayJournals() {
		return payJournals;
	}

	public void setPayJournals(Set<OrderPayJournal> payJournals) {
		this.payJournals = payJournals;
	}
	
}