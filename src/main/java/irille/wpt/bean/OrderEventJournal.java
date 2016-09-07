package irille.wpt.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 订单事件流水
 * @author Yingjianhua
 *
 */
/**
 * The persistent class for the wpt_order database table.
 * 
 */
@Entity
@Table(name="wpt_order_event_journal")
@NamedQuery(name="OrderEventJournal.findAll", query="SELECT w FROM OrderEventJournal w")
public class OrderEventJournal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer pkey;
	
	private String desc;
	
	private String tips;
	
	private Date createTime;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order")
	private Order order;

	public OrderEventJournal() {
	}

	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
