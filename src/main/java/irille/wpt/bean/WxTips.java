package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the wpt_wx_tips database table.
 * 
 */
@Entity
@Table(name="wpt_wx_tips")
@NamedQuery(name="WxTip.findAll", query="SELECT w FROM WxTip w")
public class WxTips implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne
	@JoinColumn(name="pkey")
	private Member member;

	private Integer account;

	@Column(name="row_version")
	private Short rowVersion = 1;

	public WxTips() {
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Short getRowVersion() {
		return rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

}