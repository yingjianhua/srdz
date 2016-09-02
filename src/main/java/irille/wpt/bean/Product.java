package irille.wpt.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="product")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private String des;

	private Byte enabled;

	@Column(name="img_url")
	private String imgUrl;

	private String name;

	@Column(name="orig_price")
	private BigDecimal origPrice;

	private BigDecimal price;

	private String rem;

	private Integer account;

	@Column(name="row_version")
	private Short rowVersion = 1;

	public Integer getPkey() {
		return pkey;
	}
	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Byte getEnabled() {
		return enabled;
	}
	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getOrigPrice() {
		return origPrice;
	}
	public void setOrigPrice(BigDecimal origPrice) {
		this.origPrice = origPrice;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getRem() {
		return rem;
	}
	public void setRem(String rem) {
		this.rem = rem;
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
	@Override
	public String toString() {
		return "Product [pkey=" + pkey + ", des=" + des + ", enabled=" + enabled + ", imgUrl=" + imgUrl + ", name="
				+ name + ", origPrice=" + origPrice + ", price=" + price + ", rem=" + rem + ", account=" + account
				+ ", rowVersion=" + rowVersion + "]";
	}
}
