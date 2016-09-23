package irille.wpt.bean;

import java.io.Serializable;
import java.math.BigDecimal;
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

import irille.wpt.extjs.IComboTrigger;


/**
 * The persistent class for the restaurant database table.
 * 餐厅
 */
@Entity
@Table(name="wpt_restaurant")
@NamedQuery(name="Restaurant.findAll", query="SELECT r FROM Restaurant r")
public class Restaurant implements Serializable,IComboTrigger {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	private String addr;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="city")
	private City city;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cityline")
	private CityLine cityline;

	private String cityname;

	private BigDecimal consumption;

	private String des;

	private Byte display;

	private Byte enabled;

	@Column(name="img_url")
	private String imgUrl;
	
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant")
    @OrderBy("sort")
	private Set<RestaurantBanner> banners;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant")
	private Set<Combo> combos;
    
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="restaurant")
	private Set<RestaurantCase> cases;

	private String latitude;

	private String longitude;

	private String manager;

	private String mobile;

	private String name;

	private String rem;

	@Column(name="row_version")
	private short rowVersion = 1;

	private String startdate;

	private String stopdate;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="template")
	private RestaurantTemplate template;

	private String wifiaccount;

	private String wifipassword;

	public Restaurant() {
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

	public String getAddr() {
		return this.addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
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

	public String getCityname() {
		return this.cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}

	public BigDecimal getConsumption() {
		return this.consumption;
	}

	public void setConsumption(BigDecimal consumption) {
		this.consumption = consumption;
	}

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public Byte getDisplay() {
		return this.display;
	}

	public void setDisplay(Byte display) {
		this.display = display;
	}

	public Byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<RestaurantBanner> getBanners() {
		return banners;
	}

	public void setBanners(Set<RestaurantBanner> banners) {
		this.banners = banners;
	}

	public Set<Combo> getCombos() {
		return combos;
	}

	public void setCombos(Set<Combo> combos) {
		this.combos = combos;
	}

	public Set<RestaurantCase> getCases() {
		return cases;
	}

	public void setCases(Set<RestaurantCase> cases) {
		this.cases = cases;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getManager() {
		return this.manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRem() {
		return this.rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public String getStartdate() {
		return this.startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getStopdate() {
		return this.stopdate;
	}

	public void setStopdate(String stopdate) {
		this.stopdate = stopdate;
	}

	public RestaurantTemplate getTemplate() {
		return this.template;
	}

	public void setTemplate(RestaurantTemplate template) {
		this.template = template;
	}

	public String getWifiaccount() {
		return this.wifiaccount;
	}

	public void setWifiaccount(String wifiaccount) {
		this.wifiaccount = wifiaccount;
	}

	public String getWifipassword() {
		return this.wifipassword;
	}

	public void setWifipassword(String wifipassword) {
		this.wifipassword = wifipassword;
	}

	@Override
	public String getValue() {
		return getPkey().toString();
	}

	@Override
	public String getText() {
		return getName();
	}
	
}