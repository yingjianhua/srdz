package irille.wpt.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 订单支付流水
 * @author Yingjianhua
 *
 */
/**
 * The persistent class for the wpt_order database table.
 * 
 */
@Entity
@Table(name="wpt_order_pay_journal")
@NamedQuery(name="OrderPayJournal.findAll", query="SELECT w FROM OrderPayJournal w")
public class OrderPayJournal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer pkey;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order")
	private Order order;
	
	public OrderPayJournal() {
	}

	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
}
