package irille.wpt.bean;

import java.io.Serializable;
import javax.persistence.*;

import org.apache.struts2.json.annotations.JSON;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the wx_user database table.
 * 
 */
@Entity
@Table(name="wx_user")
@NamedQuery(name="Member.findAll", query="SELECT w FROM Member w")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer pkey;

	private Integer account;

	@Column(name="cashable_commission")
	private BigDecimal cashableCommission;

	private String city;

	private String country;

	@Column(name="history_commission")
	private BigDecimal historyCommission;

	@Column(name="image_url")
	private String imageUrl;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invited1")
	private Member invited1;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="invited2")
	private Member invited2;

	@Column(name="is_member")
	private Boolean isMember;

	private String nickname;

	@Column(name="open_id")
	private String openId;

	private String province;

	private String qrcode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="qrcode_expire_time")
	private Date qrcodeExpireTime;

	private String rem;

	@Column(name="row_version")
	private Short rowVersion = 1;

	private Byte sex;

	private Byte status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="subscribe_time")
	private Date subscribeTime;

	@Column(name="sync_status")
	private Byte syncStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="sync_time")
	private Date syncTime;

	@Column(name="union_id")
	private String unionId;

	@Column(name="user_group")
	private Integer userGroup;

	public Member() {
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

	public BigDecimal getCashableCommission() {
		return this.cashableCommission;
	}

	public void setCashableCommission(BigDecimal cashableCommission) {
		this.cashableCommission = cashableCommission;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public BigDecimal getHistoryCommission() {
		return this.historyCommission;
	}

	public void setHistoryCommission(BigDecimal historyCommission) {
		this.historyCommission = historyCommission;
	}

	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Member getInvited1() {
		return this.invited1;
	}

	public void setInvited1(Member invited1) {
		this.invited1 = invited1;
	}

	public Member getInvited2() {
		return this.invited2;
	}

	public void setInvited2(Member invited2) {
		this.invited2 = invited2;
	}

	public Boolean getIsMember() {
		return this.isMember;
	}

	public void setIsMember(Boolean isMember) {
		this.isMember = isMember;
	}

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpenId() {
		return this.openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getQrcode() {
		return this.qrcode;
	}

	public void setQrcode(String qrcode) {
		this.qrcode = qrcode;
	}

	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getQrcodeExpireTime() {
		return this.qrcodeExpireTime;
	}

	public void setQrcodeExpireTime(Date qrcodeExpireTime) {
		this.qrcodeExpireTime = qrcodeExpireTime;
	}

	public String getRem() {
		return this.rem;
	}

	public void setRem(String rem) {
		this.rem = rem;
	}

	public Short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(Short rowVersion) {
		this.rowVersion = rowVersion;
	}

	public Byte getSex() {
		return this.sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public Byte getStatus() {
		return this.status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}
	
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getSubscribeTime() {
		return this.subscribeTime;
	}

	public void setSubscribeTime(Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}

	public Byte getSyncStatus() {
		return this.syncStatus;
	}

	public void setSyncStatus(Byte syncStatus) {
		this.syncStatus = syncStatus;
	}

	public Date getSyncTime() {
		return this.syncTime;
	}

	public void setSyncTime(Date syncTime) {
		this.syncTime = syncTime;
	}

	public String getUnionId() {
		return this.unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public Integer getUserGroup() {
		return this.userGroup;
	}

	public void setUserGroup(Integer userGroup) {
		this.userGroup = userGroup;
	}

}