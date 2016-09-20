package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wpt_special_line database table.
 * 
 */
@Entity
@Table(name="wpt_special_line")
@NamedQuery(name="SpecialLine.findAll", query="SELECT s FROM SpecialLine s")
public class SpecialLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;

	@Column(name="row_version")
	private Short rowVersion = 1;

	private Integer sort;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="special")
	private Special special;

	public SpecialLine() {
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

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

	public Special getSpecial() {
		return this.special;
	}

	public void setSpecial(Special special) {
		this.special = special;
	}

}