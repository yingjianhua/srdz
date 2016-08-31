package irille.wpt.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private int pkey;

	private int account;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="combo")
	private Combo combo;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="menu")
	private RestaurantMenu menu;

	private BigDecimal price;

	@Column(name="row_version")
	private short rowVersion;

	private int sort;

	public ComboLine() {
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

	public Combo getCombo() {
		System.out.println("comboLine.getCombo");
		return this.combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public RestaurantMenu getMenu() {
		System.out.println("comboLine.getMenu");
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

	public short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "ComboLine [pkey=" + pkey + ", account=" + account + ", combo=" + combo + ", menu=" + menu + ", price="
				+ price + ", rowVersion=" + rowVersion + ", sort=" + sort + "]";
	}

}