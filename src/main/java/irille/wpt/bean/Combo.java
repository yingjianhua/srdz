package irille.wpt.bean;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


/**
 * The persistent class for the wpt_combo database table.
 * 
 */
@Entity
@Table(name="wpt_combo")
@PrimaryKeyJoinColumn(name="comboId")
@NamedQuery(name="Combo.findAll", query="SELECT c FROM Combo c")
public class Combo extends Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="number_max")
	private Integer numberMax;

	@Column(name="number_min")
	private Integer numberMin;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;

	@Column(name="service_date")
	private String serviceDate;

	@Column(name="service_time")
	private String serviceTime;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="combo")
	private Set<ComboLine> comboLines;
	
    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="combo")
    @OrderBy("sort")
	private Set<ComboBanner> banners;
    
	private Integer sort;

	public Combo() {
	}

	public Integer getNumberMax() {
		return this.numberMax;
	}

	public void setNumberMax(Integer numberMax) {
		this.numberMax = numberMax;
	}

	public Integer getNumberMin() {
		return this.numberMin;
	}

	public void setNumberMin(Integer numberMin) {
		this.numberMin = numberMin;
	}

	public Restaurant getRestaurant() {
		return this.restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
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

	public Set<ComboLine> getComboLines() {
		return comboLines;
	}

	public void setComboLines(Set<ComboLine> comboLines) {
		this.comboLines = comboLines;
	}

	public Set<ComboBanner> getBanners() {
		return banners;
	}

	public void setBanners(Set<ComboBanner> banners) {
		this.banners = banners;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Combo [numberMax=" + numberMax + ", numberMin=" + numberMin + ", restaurant=" + restaurant
				+ ", serviceDate=" + serviceDate + ", serviceTime=" + serviceTime + ", sort=" + sort + "]";
	}
}