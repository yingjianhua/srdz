package irille.wpt.bean;

import java.io.Serializable;
import java.math.BigDecimal;

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


/**
 * The persistent class for the product_combo_line database table.
 * 
 */
@Entity
@Table(name="product_combo_line")
@NamedQuery(name="ComboLine.findAll", query="SELECT c FROM ComboLine c")
public class ComboLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="combo")
	private Combo combo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="menu")
	private RestaurantMenu menu;

	private BigDecimal price;

	@Column(name="row_version")
	private Short rowVersion = 1;

	private Integer sort;

	public ComboLine() {
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

	public Combo getCombo() {
		return this.combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public RestaurantMenu getMenu() {
		return this.menu;
	}

	public void setMenu(RestaurantMenu menu) {
		this.menu = menu;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "ComboLine [pkey=" + pkey + ", account=" + account + ", combo=" + combo + ", menu=" + menu + ", price="
				+ price + ", rowVersion=" + rowVersion + ", sort=" + sort + "]";
	}

}