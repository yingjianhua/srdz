package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the wpt_red_pack_rule database table.
 * 
 */
@Entity
@Table(name="wpt_red_pack_rule")
@NamedQuery(name="RedPackRule.findAll", query="SELECT r FROM RedPackRule r")
public class RedPackRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	@Column(name="act_name")
	private String actName;

	@Column(name="least_amt")
	private BigDecimal leastAmt;

	private String remark;

	@Column(name="row_version")
	private Short rowVersion = 1;

	@Column(name="send_name")
	private String sendName;

	private String wishing;

	public RedPackRule() {
	}

	public Integer getPkey() {
		return this.pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public String getActName() {
		return this.actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public BigDecimal getLeastAmt() {
		return this.leastAmt;
	}

	public void setLeastAmt(BigDecimal leastAmt) {
		this.leastAmt = leastAmt;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public String getSendName() {
		return this.sendName;
	}

	public void setSendName(String sendName) {
		this.sendName = sendName;
	}

	public String getWishing() {
		return this.wishing;
	}

	public void setWishing(String wishing) {
		this.wishing = wishing;
	}

}