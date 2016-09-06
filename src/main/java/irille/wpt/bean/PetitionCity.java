package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wpt_petition_city database table.
 * 
 */
@Entity
@Table(name="wpt_petition_city")
@NamedQuery(name="PetitionCity.findAll", query="SELECT p FROM PetitionCity p")
public class PetitionCity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer pkey;

	private Integer account;

	private Integer count;

	private Byte enabled;

	private String name;

	@Column(name="row_version")
	private Short rowVersion = 1;

	public PetitionCity() {
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

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Byte getEnabled() {
		return this.enabled;
	}

	public void setEnabled(Byte enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

}