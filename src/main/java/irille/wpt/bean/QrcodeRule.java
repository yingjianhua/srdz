package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the wpt_qrcode_rule database table.
 * 
 */
@Entity
@Table(name="wpt_qrcode_rule")
@NamedQuery(name="QrcodeRule.findAll", query="SELECT q FROM QrcodeRule q")
public class QrcodeRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	@Column(name="ahead_update")
	private Integer aheadUpdate;

	private BigDecimal amount;

	@Column(name="row_version")
	private Short rowVersion = 1;

	private BigDecimal single;

	@Column(name="validity_period")
	private Integer validityPeriod;

	public QrcodeRule() {
	}

	public Integer getPkey() {
		return this.pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public Integer getAheadUpdate() {
		return this.aheadUpdate;
	}

	public void setAheadUpdate(Integer aheadUpdate) {
		this.aheadUpdate = aheadUpdate;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public BigDecimal getSingle() {
		return this.single;
	}

	public void setSingle(BigDecimal single) {
		this.single = single;
	}

	public Integer getValidityPeriod() {
		return this.validityPeriod;
	}

	public void setValidityPeriod(Integer validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

}