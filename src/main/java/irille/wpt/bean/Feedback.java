package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the wpt_feed_back database table.
 * 
 */
@Entity
@Table(name="wpt_feed_back")
@NamedQuery(name="Feedback.findAll", query="SELECT f FROM Feedback f")
public class Feedback implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer pkey;

	private Integer account;

	@Column(name="contact_type")
	private Byte contactType;

	@Column(name="contact_way")
	private String contactWay;

	private String content;

	@Column(name="handle_man")
	private Integer handleMan;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="handle_time")
	private Date handleTime;

	@Column(name="is_handle")
	private Boolean isHandle;

	@Column(name="row_version")
	private Short rowVersion = 1;

	public Feedback() {
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

	public Byte getContactType() {
		return this.contactType;
	}

	public void setContactType(Byte contactType) {
		this.contactType = contactType;
	}

	public String getContactWay() {
		return this.contactWay;
	}

	public void setContactWay(String contactWay) {
		this.contactWay = contactWay;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getHandleMan() {
		return this.handleMan;
	}

	public void setHandleMan(Integer handleMan) {
		this.handleMan = handleMan;
	}

	public Date getHandleTime() {
		return this.handleTime;
	}

	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	public Boolean getIsHandle() {
		return this.isHandle;
	}

	public void setIsHandle(Boolean isHandle) {
		this.isHandle = isHandle;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

}