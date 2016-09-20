package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wpt_restaurant_banner database table.
 * 
 */
@Entity
@Table(name="wpt_restaurant_banner")
@NamedQuery(name="RestaurantBanner.findAll", query="SELECT r FROM RestaurantBanner r")
public class RestaurantBanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@Column(name="img_url")
	private String imgUrl;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="restaurant")
	private Restaurant restaurant;

	@Column(name="row_version")
	private Short rowVersion = 1;

	private Integer sort;

	public RestaurantBanner() {
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

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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

}