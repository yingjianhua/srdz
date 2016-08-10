package irille.wpt.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * The persistent class for the wpt_restaurant database table.
 * 
 */
@Entity
@Table(name="wpt_restaurant")
@NamedQuery(name="Restaurant.findAll", query="SELECT w FROM Restaurant w")
public class Restaurant implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int pkey;

	private int account;

	private String addr;

	private int city;

	private int cityline;

	private String cityname;

	private BigDecimal consumption;

	private String coordinate;

	private String des;

	private byte display;

	private byte enabled;

	private String imgUrl;

	private String longitude;

	private String manager;

	private String mobile;

	private String name;

	private String rem;

	private short rowVersion;

	private String startdate;

	private String stopdate;

	private int template;

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

	public int getCity() {
		return this.city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public int getCityline() {
		return this.cityline;
	}

	public void setCityline(int cityline) {
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

	public String getCoordinate() {
		return this.coordinate;
	}

	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
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

	public int getTemplate() {
		return this.template;
	}

	public void setTemplate(int template) {
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
	public String toString() {
		return "Restaurant [pkey=" + pkey + ", account=" + account + ", addr=" + addr + ", city=" + city + ", cityline="
				+ cityline + ", cityname=" + cityname + ", consumption=" + consumption + ", coordinate=" + coordinate
				+ ", des=" + des + ", display=" + display + ", enabled=" + enabled + ", imgUrl=" + imgUrl
				+ ", longitude=" + longitude + ", manager=" + manager + ", mobile=" + mobile + ", name=" + name
				+ ", rem=" + rem + ", rowVersion=" + rowVersion + ", startdate=" + startdate + ", stopdate=" + stopdate
				+ ", template=" + template + ", wifiaccount=" + wifiaccount + ", wifipassword=" + wifipassword + "]";
	}
}