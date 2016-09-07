package irille.wpt.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 订单明细
 * @author Yingjianhua
 *
 */
/**
 * The persistent class for the wpt_order database table.
 * 
 */
@Entity
@Table(name="wpt_order_detail")
@NamedQuery(name="OrderDetail.findAll", query="SELECT w FROM OrderDetail w")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer pkey;
	
	private String name;
	
	private String desc;
	
	private Integer number;
	
	private BigDecimal price;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order")
	private Order order;
	
	public OrderDetail() {
	}

	public Integer getPkey() {
		return pkey;
	}

	public void setPkey(Integer pkey) {
		this.pkey = pkey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}
