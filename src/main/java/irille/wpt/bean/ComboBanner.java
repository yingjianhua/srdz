package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wpt_combo_banner database table.
 * 
 */
@Entity
@Table(name="wpt_combo_banner")
@NamedQuery(name="ComboBanner.findAll", query="SELECT c FROM ComboBanner c")
public class ComboBanner implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="combo")
	private Combo combo;

	@Column(name="img_url")
	private String imgUrl;

	@Column(name="row_version")
	private Short rowVersion = 1;

	private Integer sort;

	public ComboBanner() {
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

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
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