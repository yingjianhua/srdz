package irille.wpt.extjs;

import java.util.ArrayList;
import java.util.List;

public class ComboTrigger {

	private List<ComboTriggerItem> items;
	
	public ComboTrigger() {
		super();
		items = new ArrayList<ComboTriggerItem>();
	}

	public void add(ComboTriggerItem item) {
		this.items.add(item);
	}
	
	public List<ComboTriggerItem> getItems() {
		return items;
	}

	public void setItems(List<ComboTriggerItem> items) {
		this.items = items;
	}
	
}
