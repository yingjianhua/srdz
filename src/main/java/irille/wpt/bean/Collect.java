package irille.wpt.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the wpt_collect database table.
 * 
 */
@Entity
@Table(name="wpt_collect")
@NamedQuery(name="Collect.findAll", query="SELECT c FROM Collect c")
public class Collect implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer pkey;

	private Integer account;

	@Column(name="row_version")
	private Short rowVersion = 1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="top")
	private Headline headline;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="wxuser")
	private Member member;

	public Collect() {
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

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Headline getHeadline() {
		return this.headline;
	}

	public void setHeadline(Headline headline) {
		this.headline = headline;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}