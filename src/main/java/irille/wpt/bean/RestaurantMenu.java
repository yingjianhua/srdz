package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the restaurant_menu database table.
 * 
 */
@Entity
@Table(name="restaurant_menu")
@NamedQuery(name="RestaurantMenu.findAll", query="SELECT r FROM RestaurantMenu r")
public class RestaurantMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int pkey;

	private int account;

	private String des;

	private String name;

	private BigDecimal price;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;

	@Column(name="row_version")
	private short rowVersion;

	public RestaurantMenu() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Restaurant getRestaurant() {
		System.out.println("restaurantMenu.getRestaurant");
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

}