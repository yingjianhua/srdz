package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the restaurant database table.
 * 
 */
@Entity
@Table(name="restaurant")
@NamedQuery(name="Restaurant.findAll", query="SELECT r FROM Restaurant r")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int pkey;

	private int account;

	private String addr;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="city")
	private City city;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cityline")
	private CityLine cityline;

	private String cityname;

	private BigDecimal consumption;

	private String des;

	private byte display;

	private byte enabled;

	@Column(name="img_url")
	private String imgUrl;

	private String latitude;

	private String longitude;

	private String manager;

	private String mobile;

	private String name;

	private String rem;

	@Column(name="row_version")
	private short rowVersion;

	private String startdate;

	private String stopdate;

	private Integer template;

	private String wifiaccount;

	private String wifipassword;

	public Restaurant() {
	}

	public int getPkey() {
		return this.pkey;
	}

	public void setPkey(int pkey) {
		this.pkey = pkey;
	}

	public int getAccount() {
		return this.account;
	}

	public void setAccount(int account) {
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

	public byte getDisplay() {
		return this.display;
	}

	public void setDisplay(byte display) {
		this.display = display;
	}

	public byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

	public Integer getTemplate() {
		return this.template;
	}

	public void setTemplate(Integer template) {
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

}