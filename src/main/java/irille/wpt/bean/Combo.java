package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the wpt_combo database table.
 * 
 */
@Entity
@Table(name="wpt_combo")
@NamedQuery(name="Combo.findAll", query="SELECT c FROM Combo c")
public class Combo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int pkey;
	private int account;
	private String des;
	private byte enabled;
	private String imgUrl;
	private String name;
	private int numberMax;
	private int numberMin;
	private BigDecimal origPrice;
	private BigDecimal price;
	private String rem;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private Restaurant restaurant;
	private short rowVersion;
	private String serviceDate;
	private String serviceTime;
	private int sort;

	public Combo() {
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

	public String getDes() {
		return this.des;
	}

	public void setDes(String des) {
		this.des = des;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumberMax() {
		return this.numberMax;
	}

	public void setNumberMax(int numberMax) {
		this.numberMax = numberMax;
	}

	public int getNumberMin() {
		return this.numberMin;
	}

	public void setNumberMin(int numberMin) {
		this.numberMin = numberMin;
	}

	public BigDecimal getOrigPrice() {
		return this.origPrice;
	}

	public void setOrigPrice(BigDecimal origPrice) {
		this.origPrice = origPrice;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRem() {
		return this.rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public String getServiceDate() {
		return this.serviceDate;
	}

	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getServiceTime() {
		return this.serviceTime;
	}

	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Combo [pkey=" + pkey + ", account=" + account + ", des=" + des + ", enabled=" + enabled + ", imgUrl="
				+ imgUrl + ", name=" + name + ", numberMax=" + numberMax + ", numberMin=" + numberMin + ", origPrice="
				+ origPrice + ", price=" + price + ", rem=" + rem + ", restaurant=" + restaurant + ", rowVersion="
				+ rowVersion + ", serviceDate=" + serviceDate + ", serviceTime=" + serviceTime + ", sort=" + sort + "]";
	}
}