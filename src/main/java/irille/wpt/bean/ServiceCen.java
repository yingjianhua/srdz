package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wpt_service_cen database table.
 * 
 */
@Entity
@Table(name="wpt_service_cen")
@NamedQuery(name="ServiceCen.findAll", query="SELECT s FROM ServiceCen s")
public class ServiceCen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	private String mobile;

	private String qrcode;

	@Column(name="row_version")
	private Short rowVersion = 1;

	@Column(name="sms_tips")
	private String smsTips;

	public ServiceCen() {
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

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getQrcode() {
		return this.qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public String getSmsTips() {
		return this.smsTips;
	}

	public void setSmsTips(String smsTips) {
		this.smsTips = smsTips;
	}

}