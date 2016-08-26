package irille.wpt.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


/**
 * The persistent class for the wpt_combo database table.
 * 
 */
@Entity
@Table(name="product_combo")
@PrimaryKeyJoinColumn(name="comboId")
@NamedQuery(name="Combo.findAll", query="SELECT c FROM Combo c")
public class Combo extends Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="number_max")
	private int numberMax;

	@Column(name="number_min")
	private int numberMin;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;

	@Column(name="service_date")
	private String serviceDate;

	@Column(name="service_time")
	private String serviceTime;

	private int sort;

	public Combo() {
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

	public Restaurant getRestaurant() {
		System.out.println("combo.getRestaurant");
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

	public int getSort() {
		return this.sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Combo [numberMax=" + numberMax + ", numberMin=" + numberMin + ", restaurant=" + restaurant
				+ ", serviceDate=" + serviceDate + ", serviceTime=" + serviceTime + ", sort=" + sort + "]";
	}
}