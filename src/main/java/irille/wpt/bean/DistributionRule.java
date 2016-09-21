package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wpt_distribution_rule database table.
 * 
 */
@Entity
@Table(name="wpt_distribution_rule")
@NamedQuery(name="DistributionRule.findAll", query="SELECT d FROM DistributionRule d")
public class DistributionRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer bonus1;

	private Integer bonus2;

	@Column(name="row_version")
	private Short rowVersion = 1;

	public DistributionRule() {
	}

	public Integer getPkey() {
		return this.pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public Integer getBonus1() {
		return this.bonus1;
	}

	public void setBonus1(Integer bonus1) {
		this.bonus1 = bonus1;
	}

	public Integer getBonus2() {
		return this.bonus2;
	}

	public void setBonus2(Integer bonus2) {
		this.bonus2 = bonus2;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

}