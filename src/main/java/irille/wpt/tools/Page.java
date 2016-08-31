package irille.wpt.tools;

import java.util.List;

public class Page<T> {

	private Long total;
	private List<T> items;
	
	public Page(Long total, List<T> items) {
		super();
		this.total = total;
		this.items = items;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public List<T> getItems() {
		return items;
	}
	public void setItems(List<T> items) {
		this.items = items;
	}
}
