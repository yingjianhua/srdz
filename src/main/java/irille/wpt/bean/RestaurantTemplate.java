package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wpt_restaurant_template database table.
 * 
 */
@Entity
@Table(name="wpt_restaurant_template")
@NamedQuery(name="RestaurantTemplate.findAll", query="SELECT r FROM RestaurantTemplate r")
public class RestaurantTemplate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@Column(name="img_url")
	private String imgUrl;

	private String name;

	private String path;

	@Column(name="row_version")
	private Short rowVersion = 1;

	public RestaurantTemplate() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

}