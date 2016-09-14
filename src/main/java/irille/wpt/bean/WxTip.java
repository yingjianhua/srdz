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
public class WxTip implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int pkey;

	private int account;

	@Column(name="row_version")
	private short rowVersion;

	public WxTip() {
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

	public short getRowVersion() {
		return this.rowVersion;
	}

	public void setRowVersion(short rowVersion) {
		this.rowVersion = rowVersion;
	}

}