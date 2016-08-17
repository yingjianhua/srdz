package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wpt_city_line database table.
 * 
 */
@Entity
@Table(name="wpt_city_line")
@NamedQuery(name="CityLine.findAll", query="SELECT c FROM CityLine c")
public class CityLine implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int pkey;

	private int account;

	private int city;

	private String name;

	@Column(name="row_version")
	private short rowVersion;

	public CityLine() {
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

	public int getCity() {
		return this.city;
	}

	public void setCity(int city) {
		this.city = city;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(short rowVersion) {
		this.rowVersion = rowVersion;
	}

}